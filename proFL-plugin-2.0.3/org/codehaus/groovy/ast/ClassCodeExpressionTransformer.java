// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;

public abstract class ClassCodeExpressionTransformer extends ClassCodeVisitorSupport implements ExpressionTransformer
{
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        for (final Parameter p : node.getParameters()) {
            if (p.hasInitialExpression()) {
                final Expression init = p.getInitialExpression();
                p.setInitialExpression(this.transform(init));
            }
        }
        super.visitConstructorOrMethod(node, isConstructor);
    }
    
    @Override
    public void visitSwitch(final SwitchStatement statement) {
        final Expression exp = statement.getExpression();
        statement.setExpression(this.transform(exp));
        for (final CaseStatement caseStatement : statement.getCaseStatements()) {
            caseStatement.visit(this);
        }
        statement.getDefaultStatement().visit(this);
    }
    
    @Override
    public void visitField(final FieldNode node) {
        this.visitAnnotations(node);
        final Expression init = node.getInitialExpression();
        node.setInitialValueExpression(this.transform(init));
    }
    
    @Override
    public void visitProperty(final PropertyNode node) {
        this.visitAnnotations(node);
        Statement statement = node.getGetterBlock();
        this.visitClassCodeContainer(statement);
        statement = node.getSetterBlock();
        this.visitClassCodeContainer(statement);
    }
    
    @Override
    public void visitIfElse(final IfStatement ifElse) {
        ifElse.setBooleanExpression((BooleanExpression)this.transform(ifElse.getBooleanExpression()));
        ifElse.getIfBlock().visit(this);
        ifElse.getElseBlock().visit(this);
    }
    
    public Expression transform(final Expression exp) {
        if (exp == null) {
            return null;
        }
        return exp.transformExpression(this);
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        final List<AnnotationNode> annotations = node.getAnnotations();
        if (annotations.isEmpty()) {
            return;
        }
        for (final AnnotationNode an : annotations) {
            if (an.isBuiltIn()) {
                continue;
            }
            for (final Map.Entry<String, Expression> member : an.getMembers().entrySet()) {
                member.setValue(this.transform(member.getValue()));
            }
        }
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement statement) {
        statement.setExpression(this.transform(statement.getExpression()));
    }
    
    @Override
    public void visitAssertStatement(final AssertStatement as) {
        as.setBooleanExpression((BooleanExpression)this.transform(as.getBooleanExpression()));
        as.setMessageExpression(this.transform(as.getMessageExpression()));
    }
    
    @Override
    public void visitCaseStatement(final CaseStatement statement) {
        statement.setExpression(this.transform(statement.getExpression()));
        statement.getCode().visit(this);
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement loop) {
        loop.setBooleanExpression((BooleanExpression)this.transform(loop.getBooleanExpression()));
        super.visitDoWhileLoop(loop);
    }
    
    @Override
    public void visitForLoop(final ForStatement forLoop) {
        forLoop.setCollectionExpression(this.transform(forLoop.getCollectionExpression()));
        super.visitForLoop(forLoop);
    }
    
    @Override
    public void visitSynchronizedStatement(final SynchronizedStatement sync) {
        sync.setExpression(this.transform(sync.getExpression()));
        super.visitSynchronizedStatement(sync);
    }
    
    @Override
    public void visitThrowStatement(final ThrowStatement ts) {
        ts.setExpression(this.transform(ts.getExpression()));
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement loop) {
        loop.setBooleanExpression((BooleanExpression)this.transform(loop.getBooleanExpression()));
        super.visitWhileLoop(loop);
    }
    
    @Override
    public void visitExpressionStatement(final ExpressionStatement es) {
        es.setExpression(this.transform(es.getExpression()));
    }
}
