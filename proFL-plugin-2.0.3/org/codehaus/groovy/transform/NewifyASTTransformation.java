// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.lang.Newify;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import java.util.Set;
import java.util.HashSet;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class NewifyASTTransformation extends ClassCodeExpressionTransformer implements ASTTransformation
{
    private static final ClassNode MY_TYPE;
    private static final String MY_NAME;
    private SourceUnit source;
    private ListExpression classesToNewify;
    private boolean auto;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        this.source = source;
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            this.internalError("Expecting [AnnotationNode, AnnotatedClass] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!NewifyASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            this.internalError("Transformation called from wrong annotation: " + node.getClassNode().getName());
        }
        final boolean autoFlag = this.determineAutoFlag(node.getMember("auto"));
        final ListExpression list = this.determineClassesToNewify(node.getMember("value"));
        if (parent instanceof ClassNode) {
            this.newifyClass(parent, autoFlag, list);
        }
        else if (parent instanceof MethodNode || parent instanceof FieldNode) {
            this.newifyMethodOrField(parent, autoFlag, list);
        }
    }
    
    private boolean determineAutoFlag(final Expression autoExpr) {
        return !(autoExpr instanceof ConstantExpression) || !((ConstantExpression)autoExpr).getValue().equals(false);
    }
    
    private ListExpression determineClassesToNewify(final Expression expr) {
        ListExpression list = new ListExpression();
        if (expr instanceof ClassExpression) {
            list.addExpression(expr);
        }
        else if (expr instanceof ListExpression) {
            list = (ListExpression)expr;
            final List<Expression> expressions = list.getExpressions();
            for (final Expression ex : expressions) {
                if (!(ex instanceof ClassExpression)) {
                    throw new RuntimeException("Error during @" + NewifyASTTransformation.MY_NAME + " processing. Annotation parameter must be a list of classes.");
                }
            }
            this.checkDuplicateNameClashes(list);
        }
        return list;
    }
    
    @Override
    public Expression transform(final Expression expr) {
        if (expr == null) {
            return null;
        }
        if (!(expr instanceof MethodCallExpression)) {
            return expr.transformExpression(this);
        }
        final MethodCallExpression mce = (MethodCallExpression)expr;
        final Expression args = this.transform(mce.getArguments());
        final Expression method = this.transform(mce.getMethod());
        final Expression object = this.transform(mce.getObjectExpression());
        if (this.isNewifyCandidate(mce)) {
            return this.transformMethodCall(mce, args);
        }
        return new MethodCallExpression(object, method, args);
    }
    
    private void newifyClass(final AnnotatedNode parent, final boolean autoFlag, final ListExpression list) {
        final ClassNode cNode = (ClassNode)parent;
        final String cName = cNode.getName();
        if (cNode.isInterface()) {
            throw new RuntimeException("Error processing interface '" + cName + "'. @" + NewifyASTTransformation.MY_NAME + " not allowed for interfaces.");
        }
        this.classesToNewify = list;
        this.auto = autoFlag;
        super.visitClass(cNode);
    }
    
    private void newifyMethodOrField(final AnnotatedNode parent, final boolean autoFlag, final ListExpression list) {
        final ListExpression oldClassesToNewify = this.classesToNewify;
        final boolean oldAuto = this.auto;
        this.checkClassLevelClashes(list);
        this.checkAutoClash(autoFlag);
        this.classesToNewify = list;
        this.auto = autoFlag;
        if (parent instanceof FieldNode) {
            super.visitField((FieldNode)parent);
        }
        else {
            super.visitMethod((MethodNode)parent);
        }
        this.classesToNewify = oldClassesToNewify;
        this.auto = oldAuto;
    }
    
    private void checkDuplicateNameClashes(final ListExpression list) {
        final Set<String> seen = new HashSet<String>();
        final List<ClassExpression> classes = (List<ClassExpression>)list.getExpressions();
        for (final ClassExpression ce : classes) {
            final String name = ce.getType().getNameWithoutPackage();
            if (seen.contains(name)) {
                throw new RuntimeException("Duplicate name '" + name + "' found during @" + NewifyASTTransformation.MY_NAME + " processing.");
            }
            seen.add(name);
        }
    }
    
    private void checkAutoClash(final boolean autoFlag) {
        if (this.auto && !autoFlag) {
            throw new RuntimeException("Error during @" + NewifyASTTransformation.MY_NAME + " processing. The 'auto' flag can't be false at method/constructor/field level if it is true at the class level.");
        }
    }
    
    private void checkClassLevelClashes(final ListExpression list) {
        final List<ClassExpression> classes = (List<ClassExpression>)list.getExpressions();
        for (final ClassExpression ce : classes) {
            final String name = ce.getType().getNameWithoutPackage();
            if (this.findClassWithMatchingBasename(name)) {
                throw new RuntimeException("Error during @" + NewifyASTTransformation.MY_NAME + " processing. Class '" + name + "' can't appear at method/constructor/field level if it already appears at the class level.");
            }
        }
    }
    
    private boolean findClassWithMatchingBasename(final String nameWithoutPackage) {
        if (this.classesToNewify == null) {
            return false;
        }
        final List<ClassExpression> classes = (List<ClassExpression>)this.classesToNewify.getExpressions();
        for (final ClassExpression ce : classes) {
            if (ce.getType().getNameWithoutPackage().equals(nameWithoutPackage)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isNewifyCandidate(final MethodCallExpression mce) {
        return mce.getObjectExpression() == VariableExpression.THIS_EXPRESSION || (this.auto && this.isNewMethodStyle(mce));
    }
    
    private boolean isNewMethodStyle(final MethodCallExpression mce) {
        final Expression obj = mce.getObjectExpression();
        final Expression meth = mce.getMethod();
        return obj instanceof ClassExpression && meth instanceof ConstantExpression && ((ConstantExpression)meth).getValue().equals("new");
    }
    
    private Expression transformMethodCall(final MethodCallExpression mce, final Expression args) {
        ClassNode classType;
        if (this.isNewMethodStyle(mce)) {
            classType = mce.getObjectExpression().getType();
        }
        else {
            classType = this.findMatchingCandidateClass(mce);
        }
        if (classType != null) {
            return new ConstructorCallExpression(classType, args);
        }
        mce.setArguments(args);
        return mce;
    }
    
    private ClassNode findMatchingCandidateClass(final MethodCallExpression mce) {
        if (this.classesToNewify == null) {
            return null;
        }
        final List<ClassExpression> classes = (List<ClassExpression>)this.classesToNewify.getExpressions();
        for (final ClassExpression ce : classes) {
            final ClassNode type = ce.getType();
            if (type.getNameWithoutPackage().equals(mce.getMethodAsString())) {
                return type;
            }
        }
        return null;
    }
    
    private void internalError(final String message) {
        throw new RuntimeException("Internal error: " + message);
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    static {
        MY_TYPE = new ClassNode(Newify.class);
        MY_NAME = NewifyASTTransformation.MY_TYPE.getNameWithoutPackage();
    }
}
