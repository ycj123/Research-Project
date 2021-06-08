// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.transform.InheritConstructors;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import java.util.List;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.ArrayList;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.GroovyBugError;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class InheritConstructorsASTTransformation implements ASTTransformation, Opcodes
{
    private static final Class MY_CLASS;
    private static final ClassNode MY_TYPE;
    private static final String MY_TYPE_NAME;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new GroovyBugError("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!InheritConstructorsASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        if (parent instanceof ClassNode) {
            final ClassNode cNode = (ClassNode)parent;
            if (cNode.isInterface()) {
                this.addError("Error processing interface '" + cNode.getName() + "'. " + InheritConstructorsASTTransformation.MY_TYPE_NAME + " only allowed for classes.", cNode, source);
                return;
            }
            final ClassNode sNode = cNode.getSuperClass();
            for (final ConstructorNode cn : sNode.getDeclaredConstructors()) {
                final Parameter[] params = cn.getParameters();
                if (cn.isPrivate()) {
                    continue;
                }
                final Parameter[] pcopy = new Parameter[params.length];
                final List<Expression> args = new ArrayList<Expression>();
                for (int i = 0; i < params.length; ++i) {
                    final Parameter p = params[i];
                    pcopy[i] = (p.hasInitialExpression() ? new Parameter(p.getType(), p.getName(), p.getInitialExpression()) : new Parameter(p.getType(), p.getName()));
                    args.add(new VariableExpression(p.getName(), p.getType()));
                }
                if (this.isClashing(cNode, pcopy)) {
                    continue;
                }
                final BlockStatement body = new BlockStatement();
                body.addStatement(new ExpressionStatement(new ConstructorCallExpression(ClassNode.SUPER, new ArgumentListExpression(args))));
                cNode.addConstructor(cn.getModifiers(), pcopy, cn.getExceptions(), body);
            }
        }
    }
    
    private boolean isClashing(final ClassNode cNode, final Parameter[] pcopy) {
        for (final ConstructorNode cn : cNode.getDeclaredConstructors()) {
            if (this.conflictingTypes(pcopy, cn.getParameters())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean conflictingTypes(final Parameter[] pcopy, final Parameter[] parameters) {
        if (pcopy.length != parameters.length) {
            return false;
        }
        for (int i = 0; i < pcopy.length; ++i) {
            if (!pcopy[i].getType().equals(parameters[i].getType())) {
                return false;
            }
        }
        return true;
    }
    
    private void addError(final String msg, final ASTNode expr, final SourceUnit source) {
        final int line = expr.getLineNumber();
        final int col = expr.getColumnNumber();
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + '\n', line, col), source));
    }
    
    static {
        MY_CLASS = InheritConstructors.class;
        MY_TYPE = new ClassNode(InheritConstructorsASTTransformation.MY_CLASS);
        MY_TYPE_NAME = "@" + InheritConstructorsASTTransformation.MY_TYPE.getNameWithoutPackage();
    }
}
