// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.lang.ref.SoftReference;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.FieldNode;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class LazyASTTransformation implements ASTTransformation, Opcodes
{
    private static final ClassNode SOFT_REF;
    private static final Expression NULL_EXPR;
    private static final ClassNode OBJECT_TYPE;
    private static final Token ASSIGN;
    private static final Token COMPARE_NOT_EQUAL;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (parent instanceof FieldNode) {
            final FieldNode fieldNode = (FieldNode)parent;
            final Expression member = node.getMember("soft");
            final Expression init = this.getInitExpr(fieldNode);
            fieldNode.rename("$" + fieldNode.getName());
            fieldNode.setModifiers(0x2 | (fieldNode.getModifiers() & 0xFFFFFFFA));
            if (member instanceof ConstantExpression && ((ConstantExpression)member).getValue().equals(true)) {
                this.createSoft(fieldNode, init);
            }
            else {
                this.create(fieldNode, init);
                if (ClassHelper.isPrimitiveType(fieldNode.getType())) {
                    fieldNode.setType(ClassHelper.getWrapper(fieldNode.getType()));
                }
            }
        }
    }
    
    private void create(final FieldNode fieldNode, final Expression initExpr) {
        final BlockStatement body = new BlockStatement();
        if (fieldNode.isStatic()) {
            this.addHolderClassIdiomBody(body, fieldNode, initExpr);
        }
        else if (this.isVolatile(fieldNode)) {
            this.addNonThreadSafeBody(body, fieldNode, initExpr);
        }
        else {
            this.addDoubleCheckedLockingBody(body, fieldNode, initExpr);
        }
        this.addMethod(fieldNode, body, fieldNode.getType());
    }
    
    private void addHolderClassIdiomBody(final BlockStatement body, final FieldNode fieldNode, final Expression initExpr) {
        final ClassNode declaringClass = fieldNode.getDeclaringClass();
        final ClassNode fieldType = fieldNode.getType();
        final int visibility = 10;
        final String fullName = declaringClass.getName() + "$" + fieldType.getNameWithoutPackage() + "Holder_" + fieldNode.getName().substring(1);
        final InnerClassNode holderClass = new InnerClassNode(declaringClass, fullName, 10, LazyASTTransformation.OBJECT_TYPE);
        final String innerFieldName = "INSTANCE";
        holderClass.addField("INSTANCE", 26, fieldType, initExpr);
        final Expression innerField = new PropertyExpression(new ClassExpression(holderClass), "INSTANCE");
        declaringClass.getModule().addClass(holderClass);
        body.addStatement(new ReturnStatement(innerField));
    }
    
    private void addDoubleCheckedLockingBody(final BlockStatement body, final FieldNode fieldNode, final Expression initExpr) {
        final Expression fieldExpr = new VariableExpression(fieldNode);
        final VariableExpression localVar = new VariableExpression(fieldNode.getName() + "_local", fieldNode.getType());
        body.addStatement(new ExpressionStatement(new DeclarationExpression(localVar, LazyASTTransformation.ASSIGN, fieldExpr)));
        body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(localVar, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ReturnStatement(localVar), new SynchronizedStatement(this.synchTarget(fieldNode), new IfStatement(new BooleanExpression(new BinaryExpression(fieldExpr, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ReturnStatement(fieldExpr), new ReturnStatement(new BinaryExpression(fieldExpr, LazyASTTransformation.ASSIGN, initExpr))))));
    }
    
    private void addNonThreadSafeBody(final BlockStatement body, final FieldNode fieldNode, final Expression initExpr) {
        final Expression fieldExpr = new VariableExpression(fieldNode);
        body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(fieldExpr, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ExpressionStatement(fieldExpr), new ExpressionStatement(new BinaryExpression(fieldExpr, LazyASTTransformation.ASSIGN, initExpr))));
    }
    
    private void addMethod(final FieldNode fieldNode, final BlockStatement body, final ClassNode type) {
        int visibility = 1;
        if (fieldNode.isStatic()) {
            visibility |= 0x8;
        }
        final String name = "get" + MetaClassHelper.capitalize(fieldNode.getName().substring(1));
        fieldNode.getDeclaringClass().addMethod(name, visibility, type, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body);
    }
    
    private void createSoft(final FieldNode fieldNode, final Expression initExpr) {
        final ClassNode type = fieldNode.getType();
        fieldNode.setType(LazyASTTransformation.SOFT_REF);
        this.createSoftGetter(fieldNode, initExpr, type);
        this.createSoftSetter(fieldNode, type);
    }
    
    private void createSoftGetter(final FieldNode fieldNode, final Expression initExpr, final ClassNode type) {
        final BlockStatement body = new BlockStatement();
        final Expression fieldExpr = new VariableExpression(fieldNode);
        final Expression resExpr = new VariableExpression("res", type);
        final MethodCallExpression callExpression = new MethodCallExpression(new VariableExpression(fieldNode), "get", new ArgumentListExpression());
        callExpression.setSafe(true);
        body.addStatement(new ExpressionStatement(new DeclarationExpression(resExpr, LazyASTTransformation.ASSIGN, callExpression)));
        final BlockStatement elseBlock = new BlockStatement();
        elseBlock.addStatement(new ExpressionStatement(new BinaryExpression(resExpr, LazyASTTransformation.ASSIGN, initExpr)));
        elseBlock.addStatement(new ExpressionStatement(new BinaryExpression(fieldExpr, LazyASTTransformation.ASSIGN, new ConstructorCallExpression(LazyASTTransformation.SOFT_REF, resExpr))));
        elseBlock.addStatement(new ExpressionStatement(resExpr));
        final Statement mainIf = new IfStatement(new BooleanExpression(new BinaryExpression(resExpr, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ExpressionStatement(resExpr), elseBlock);
        if (this.isVolatile(fieldNode)) {
            body.addStatement(mainIf);
        }
        else {
            body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(resExpr, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ExpressionStatement(resExpr), new SynchronizedStatement(this.synchTarget(fieldNode), mainIf)));
        }
        this.addMethod(fieldNode, body, type);
    }
    
    private void createSoftSetter(final FieldNode fieldNode, final ClassNode type) {
        final BlockStatement body = new BlockStatement();
        final Expression fieldExpr = new VariableExpression(fieldNode);
        final String name = "set" + MetaClassHelper.capitalize(fieldNode.getName().substring(1));
        final Parameter parameter = new Parameter(type, "value");
        final Expression paramExpr = new VariableExpression(parameter);
        body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(paramExpr, LazyASTTransformation.COMPARE_NOT_EQUAL, LazyASTTransformation.NULL_EXPR)), new ExpressionStatement(new BinaryExpression(fieldExpr, LazyASTTransformation.ASSIGN, new ConstructorCallExpression(LazyASTTransformation.SOFT_REF, paramExpr))), new ExpressionStatement(new BinaryExpression(fieldExpr, LazyASTTransformation.ASSIGN, LazyASTTransformation.NULL_EXPR))));
        int visibility = 1;
        if (fieldNode.isStatic()) {
            visibility |= 0x8;
        }
        fieldNode.getDeclaringClass().addMethod(name, visibility, ClassHelper.VOID_TYPE, new Parameter[] { parameter }, ClassNode.EMPTY_ARRAY, body);
    }
    
    private Expression synchTarget(final FieldNode fieldNode) {
        return fieldNode.isStatic() ? new ClassExpression(fieldNode.getDeclaringClass()) : VariableExpression.THIS_EXPRESSION;
    }
    
    private boolean isVolatile(final FieldNode fieldNode) {
        return (fieldNode.getModifiers() & 0x40) == 0x0;
    }
    
    private Expression getInitExpr(final FieldNode fieldNode) {
        Expression initExpr = fieldNode.getInitialValueExpression();
        fieldNode.setInitialValueExpression(null);
        if (initExpr == null) {
            initExpr = new ConstructorCallExpression(fieldNode.getType(), new ArgumentListExpression());
        }
        return initExpr;
    }
    
    static {
        SOFT_REF = ClassHelper.make(SoftReference.class);
        NULL_EXPR = ConstantExpression.NULL;
        OBJECT_TYPE = new ClassNode(Object.class);
        ASSIGN = Token.newSymbol("=", -1, -1);
        COMPARE_NOT_EQUAL = Token.newSymbol("!=", -1, -1);
    }
}
