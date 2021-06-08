// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import groovy.lang.Mixin;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import java.util.Arrays;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.ASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class MixinASTTransformation implements ASTTransformation
{
    private static final ClassNode MY_TYPE;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotationNode node = (AnnotationNode)nodes[0];
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        if (!MixinASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        final Expression expr = node.getMember("value");
        if (expr == null) {
            return;
        }
        Expression useClasses = null;
        if (expr instanceof ClassExpression) {
            useClasses = expr;
        }
        else if (expr instanceof ListExpression) {
            final ListExpression listExpression = (ListExpression)expr;
            for (final Expression ex : listExpression.getExpressions()) {
                if (!(ex instanceof ClassExpression)) {
                    return;
                }
            }
            useClasses = expr;
        }
        if (useClasses == null) {
            return;
        }
        if (parent instanceof ClassNode) {
            final ClassNode annotatedClass = (ClassNode)parent;
            final Parameter[] noparams = new Parameter[0];
            MethodNode clinit = annotatedClass.getDeclaredMethod("<clinit>", noparams);
            if (clinit == null) {
                clinit = annotatedClass.addMethod("<clinit>", 4105, ClassHelper.VOID_TYPE, noparams, null, new BlockStatement());
                clinit.setSynthetic(true);
            }
            final BlockStatement code = (BlockStatement)clinit.getCode();
            code.addStatement(new ExpressionStatement(new MethodCallExpression(new PropertyExpression(new ClassExpression(annotatedClass), "metaClass"), "mixin", useClasses)));
        }
    }
    
    static {
        MY_TYPE = new ClassNode(Mixin.class);
    }
}
