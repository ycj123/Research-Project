// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import java.util.ListIterator;
import java.util.List;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public abstract class StatementReplacingVisitorSupport extends ClassCodeVisitorSupport
{
    private Statement replacement;
    
    public Statement replace(final Statement stat) {
        this.replacement = null;
        stat.visit(this);
        final Statement result = (this.replacement == null) ? stat : this.replacement;
        this.replacement = null;
        return result;
    }
    
    protected <T extends Statement> void replaceAll(final List<T> stats) {
        final ListIterator<T> iter = stats.listIterator();
        while (iter.hasNext()) {
            iter.set((T)this.replace(iter.next()));
        }
    }
    
    protected void replaceVisitedStatementWith(final Statement other) {
        this.replacement = other;
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement stat) {
        this.replaceAll(stat.getStatements());
    }
    
    @Override
    public void visitForLoop(final ForStatement stat) {
        stat.getCollectionExpression().visit(this);
        stat.setLoopBlock(this.replace(stat.getLoopBlock()));
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement stat) {
        stat.getBooleanExpression().visit(this);
        stat.setLoopBlock(this.replace(stat.getLoopBlock()));
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement stat) {
        stat.getBooleanExpression().visit(this);
        stat.setLoopBlock(this.replace(stat.getLoopBlock()));
    }
    
    @Override
    public void visitIfElse(final IfStatement stat) {
        stat.getBooleanExpression().visit(this);
        stat.setIfBlock(this.replace(stat.getIfBlock()));
        stat.setElseBlock(this.replace(stat.getElseBlock()));
    }
    
    @Override
    public void visitTryCatchFinally(final TryCatchStatement stat) {
        stat.setTryStatement(this.replace(stat.getTryStatement()));
        this.replaceAll(stat.getCatchStatements());
        stat.setFinallyStatement(this.replace(stat.getFinallyStatement()));
    }
    
    @Override
    public void visitSwitch(final SwitchStatement stat) {
        stat.getExpression().visit(this);
        this.replaceAll(stat.getCaseStatements());
        stat.setDefaultStatement(this.replace(stat.getDefaultStatement()));
    }
    
    @Override
    public void visitCaseStatement(final CaseStatement stat) {
        stat.getExpression().visit(this);
        stat.setCode(this.replace(stat.getCode()));
    }
    
    @Override
    public void visitSynchronizedStatement(final SynchronizedStatement stat) {
        stat.getExpression().visit(this);
        stat.setCode(this.replace(stat.getCode()));
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement stat) {
        stat.setCode(this.replace(stat.getCode()));
    }
}
