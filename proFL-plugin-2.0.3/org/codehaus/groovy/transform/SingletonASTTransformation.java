// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class SingletonASTTransformation implements ASTTransformation, Opcodes
{
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException(String.format("Internal error: wrong types: %s / %s. Expected: AnnotationNode / AnnotatedNode", nodes[0].getClass(), nodes[1].getClass()));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (parent instanceof ClassNode) {
            final ClassNode classNode = (ClassNode)parent;
            final Expression member = node.getMember("lazy");
            if (member instanceof ConstantExpression && ((ConstantExpression)member).getValue().equals(true)) {
                this.createLazy(classNode);
            }
            else {
                this.createNonLazy(classNode);
            }
        }
    }
    
    private void createNonLazy(final ClassNode classNode) {
        final FieldNode fieldNode = classNode.addField("instance", 25, classNode, new ConstructorCallExpression(classNode, new ArgumentListExpression()));
        this.createConstructor(classNode, fieldNode);
        final BlockStatement body = new BlockStatement();
        body.addStatement(new ReturnStatement(new VariableExpression(fieldNode)));
        classNode.addMethod("getInstance", 9, classNode, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body);
    }
    
    private void createLazy(final ClassNode classNode) {
        final FieldNode fieldNode = classNode.addField("instance", 74, classNode, null);
        this.createConstructor(classNode, fieldNode);
        final BlockStatement body = new BlockStatement();
        final Expression instanceExpression = new VariableExpression(fieldNode);
        body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(instanceExpression, Token.newSymbol("!=", -1, -1), ConstantExpression.NULL)), new ReturnStatement(instanceExpression), new SynchronizedStatement(new ClassExpression(classNode), new IfStatement(new BooleanExpression(new BinaryExpression(instanceExpression, Token.newSymbol("!=", -1, -1), ConstantExpression.NULL)), new ReturnStatement(instanceExpression), new ReturnStatement(new BinaryExpression(instanceExpression, Token.newSymbol("=", -1, -1), new ConstructorCallExpression(classNode, new ArgumentListExpression())))))));
        classNode.addMethod("getInstance", 9, classNode, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body);
    }
    
    private void createConstructor(final ClassNode classNode, final FieldNode field) {
        final List list = classNode.getDeclaredConstructors();
        MethodNode found = null;
        for (final MethodNode mn : list) {
            final Parameter[] parameters = mn.getParameters();
            if (parameters == null || parameters.length == 0) {
                found = mn;
                break;
            }
        }
        if (found == null) {
            final BlockStatement body = new BlockStatement();
            body.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(new VariableExpression(field), Token.newSymbol("!=", -1, -1), ConstantExpression.NULL)), new ThrowStatement(new ConstructorCallExpression(ClassHelper.make(RuntimeException.class), new ArgumentListExpression(new ConstantExpression("Can't instantiate singleton " + classNode.getName() + ". Use " + classNode.getName() + ".instance")))), new EmptyStatement()));
            classNode.addConstructor(new ConstructorNode(2, body));
        }
    }
}
