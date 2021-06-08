// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.classgen.Verifier;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.List;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.FieldNode;
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
public class DelegateASTTransformation implements ASTTransformation, Opcodes
{
    private static final ClassNode DEPRECATED_TYPE;
    private static final ClassNode GROOVYOBJECT_TYPE;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new GroovyBugError("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (parent instanceof FieldNode) {
            final FieldNode fieldNode = (FieldNode)parent;
            final ClassNode type = fieldNode.getType();
            final ClassNode owner = fieldNode.getOwner();
            if (type.equals(ClassHelper.OBJECT_TYPE) || type.equals(DelegateASTTransformation.GROOVYOBJECT_TYPE)) {
                this.addError("@Delegate field '" + fieldNode.getName() + "' has an inappropriate type: " + type.getName() + ". Please add an explicit type but not java.lang.Object or groovy.lang.GroovyObject.", parent, source);
                return;
            }
            if (type.equals(owner)) {
                this.addError("@Delegate field '" + fieldNode.getName() + "' has an inappropriate type: " + type.getName() + ". Delegation to own type not supported. Please use a different type.", parent, source);
                return;
            }
            final List<MethodNode> fieldMethods = this.getAllMethods(type);
            final Expression deprecatedElement = node.getMember("deprecated");
            final boolean deprecated = this.hasBooleanValue(deprecatedElement, true);
            for (final MethodNode mn : fieldMethods) {
                this.addDelegateMethod(fieldNode, owner, this.getAllMethods(owner), mn, deprecated);
            }
            for (final PropertyNode prop : type.getProperties()) {
                if (!prop.isStatic()) {
                    if (!prop.isPublic()) {
                        continue;
                    }
                    final String name = prop.getName();
                    this.addGetterIfNeeded(fieldNode, owner, prop, name);
                    this.addSetterIfNeeded(fieldNode, owner, prop, name);
                }
            }
            final Expression interfacesElement = node.getMember("interfaces");
            if (this.hasBooleanValue(interfacesElement, false)) {
                return;
            }
            final Set<ClassNode> allInterfaces = type.getAllInterfaces();
            final Set<ClassNode> ownerIfaces = owner.getAllInterfaces();
            for (final ClassNode iface : allInterfaces) {
                if (Modifier.isPublic(iface.getModifiers()) && !ownerIfaces.contains(iface)) {
                    final ClassNode[] ifaces = owner.getInterfaces();
                    final ClassNode[] newIfaces = new ClassNode[ifaces.length + 1];
                    System.arraycopy(ifaces, 0, newIfaces, 0, ifaces.length);
                    newIfaces[ifaces.length] = iface;
                    owner.setInterfaces(newIfaces);
                }
            }
        }
    }
    
    private List<MethodNode> getAllMethods(final ClassNode type) {
        ClassNode node = type;
        final List<MethodNode> result = new ArrayList<MethodNode>();
        while (node != null) {
            result.addAll(node.getMethods());
            node = node.getSuperClass();
        }
        return result;
    }
    
    private boolean hasBooleanValue(final Expression expression, final boolean bool) {
        return expression instanceof ConstantExpression && ((ConstantExpression)expression).getValue().equals(bool);
    }
    
    private void addSetterIfNeeded(final FieldNode fieldNode, final ClassNode owner, final PropertyNode prop, final String name) {
        final String setterName = "set" + Verifier.capitalize(name);
        if ((prop.getModifiers() & 0x10) == 0x0 && owner.getSetterMethod(setterName) == null) {
            owner.addMethod(setterName, 1, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(this.nonGeneric(prop.getType()), "value") }, null, new ExpressionStatement(new BinaryExpression(new PropertyExpression(new VariableExpression(fieldNode), name), Token.newSymbol(100, -1, -1), new VariableExpression("value"))));
        }
    }
    
    private void addGetterIfNeeded(final FieldNode fieldNode, final ClassNode owner, final PropertyNode prop, final String name) {
        final String getterName = "get" + Verifier.capitalize(name);
        if (owner.getGetterMethod(getterName) == null) {
            owner.addMethod(getterName, 1, this.nonGeneric(prop.getType()), Parameter.EMPTY_ARRAY, null, new ReturnStatement(new PropertyExpression(new VariableExpression(fieldNode), name)));
        }
    }
    
    private void addDelegateMethod(final FieldNode fieldNode, final ClassNode owner, final List<MethodNode> ownMethods, final MethodNode candidate, final boolean deprecated) {
        if (!candidate.isPublic() || candidate.isStatic() || 0x0 != (candidate.getModifiers() & 0x1000)) {
            return;
        }
        if (!candidate.getAnnotations(DelegateASTTransformation.DEPRECATED_TYPE).isEmpty() && !deprecated) {
            return;
        }
        for (final MethodNode mn : DelegateASTTransformation.GROOVYOBJECT_TYPE.getMethods()) {
            if (mn.getTypeDescriptor().equals(candidate.getTypeDescriptor())) {
                return;
            }
        }
        MethodNode existingNode = null;
        for (final MethodNode mn2 : ownMethods) {
            if (mn2.getTypeDescriptor().equals(candidate.getTypeDescriptor()) && !mn2.isAbstract() && !mn2.isStatic()) {
                existingNode = mn2;
                break;
            }
        }
        if (existingNode == null || existingNode.getCode() == null) {
            final ArgumentListExpression args = new ArgumentListExpression();
            final Parameter[] params = candidate.getParameters();
            final Parameter[] newParams = new Parameter[params.length];
            for (int i = 0; i < newParams.length; ++i) {
                final Parameter newParam = new Parameter(this.nonGeneric(params[i].getType()), params[i].getName());
                newParam.setInitialExpression(params[i].getInitialExpression());
                newParams[i] = newParam;
                args.addExpression(new VariableExpression(newParam));
            }
            final MethodNode newMethod = owner.addMethod(candidate.getName(), candidate.getModifiers() & 0xFFFFFBFF & 0xFFFFFEFF, this.nonGeneric(candidate.getReturnType()), newParams, candidate.getExceptions(), new ExpressionStatement(new MethodCallExpression(new VariableExpression(fieldNode), candidate.getName(), args)));
            newMethod.setGenericsTypes(candidate.getGenericsTypes());
        }
    }
    
    private ClassNode nonGeneric(final ClassNode type) {
        if (type.isUsingGenerics()) {
            final ClassNode nonGen = ClassHelper.makeWithoutCaching(type.getName());
            nonGen.setRedirect(type);
            nonGen.setGenericsTypes(null);
            nonGen.setUsingGenerics(false);
            return nonGen;
        }
        return type;
    }
    
    public void addError(final String msg, final ASTNode expr, final SourceUnit source) {
        final int line = expr.getLineNumber();
        final int col = expr.getColumnNumber();
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + '\n', line, col), source));
    }
    
    static {
        DEPRECATED_TYPE = new ClassNode(Deprecated.class);
        GROOVYOBJECT_TYPE = new ClassNode(GroovyObject.class);
    }
}
