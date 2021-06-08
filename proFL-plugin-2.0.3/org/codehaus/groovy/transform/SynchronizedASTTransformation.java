// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.transform.Synchronized;
import java.util.List;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class SynchronizedASTTransformation implements ASTTransformation, Opcodes
{
    private static final Class MY_CLASS;
    private static final ClassNode MY_TYPE;
    private static final String MY_TYPE_NAME;
    private static final ClassNode OBJECT_TYPE;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!SynchronizedASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        final Expression valueExpr = node.getMember("value");
        String value = null;
        if (valueExpr instanceof ConstantExpression) {
            final ConstantExpression ce = (ConstantExpression)valueExpr;
            final Object valueObject = ce.getValue();
            if (valueObject != null) {
                value = valueObject.toString();
            }
        }
        if (parent instanceof MethodNode) {
            final MethodNode mNode = (MethodNode)parent;
            final ClassNode cNode = mNode.getDeclaringClass();
            final String lockExpr = this.determineLock(value, cNode, mNode.isStatic());
            final Statement origCode = mNode.getCode();
            final Statement newCode = new SynchronizedStatement(new VariableExpression(lockExpr), origCode);
            mNode.setCode(newCode);
        }
    }
    
    private String determineLock(final String value, final ClassNode cNode, final boolean isStatic) {
        if (value != null && value.length() > 0 && !value.equalsIgnoreCase("$lock")) {
            if (cNode.getDeclaredField(value) == null) {
                throw new RuntimeException("Error during " + SynchronizedASTTransformation.MY_TYPE_NAME + " processing: lock field with name '" + value + "' not found in class " + cNode.getName());
            }
            if (cNode.getDeclaredField(value).isStatic() != isStatic) {
                throw new RuntimeException("Error during " + SynchronizedASTTransformation.MY_TYPE_NAME + " processing: lock field with name '" + value + "' should " + (isStatic ? "" : "not ") + "be static");
            }
            return value;
        }
        else {
            if (isStatic) {
                final FieldNode field = cNode.getDeclaredField("$LOCK");
                if (field == null) {
                    final int visibility = 26;
                    cNode.addField("$LOCK", visibility, SynchronizedASTTransformation.OBJECT_TYPE, this.zeroLengthObjectArray());
                }
                else if (!field.isStatic()) {
                    throw new RuntimeException("Error during " + SynchronizedASTTransformation.MY_TYPE_NAME + " processing: $LOCK field must be static");
                }
                return "$LOCK";
            }
            final FieldNode field = cNode.getDeclaredField("$lock");
            if (field == null) {
                final int visibility = 18;
                cNode.addField("$lock", visibility, SynchronizedASTTransformation.OBJECT_TYPE, this.zeroLengthObjectArray());
            }
            else if (field.isStatic()) {
                throw new RuntimeException("Error during " + SynchronizedASTTransformation.MY_TYPE_NAME + " processing: $lock field must not be static");
            }
            return "$lock";
        }
    }
    
    private Expression zeroLengthObjectArray() {
        return new ArrayExpression(SynchronizedASTTransformation.OBJECT_TYPE, null, Arrays.asList(new ConstantExpression(0)));
    }
    
    static {
        MY_CLASS = Synchronized.class;
        MY_TYPE = new ClassNode(SynchronizedASTTransformation.MY_CLASS);
        MY_TYPE_NAME = "@" + SynchronizedASTTransformation.MY_TYPE.getNameWithoutPackage();
        OBJECT_TYPE = new ClassNode(Object.class);
    }
}
