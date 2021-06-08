// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.ArrayList;
import java.util.List;

public class TryCatchStatement extends Statement
{
    private Statement tryStatement;
    private List<CatchStatement> catchStatements;
    private Statement finallyStatement;
    
    public TryCatchStatement(final Statement tryStatement, final Statement finallyStatement) {
        this.catchStatements = new ArrayList<CatchStatement>();
        this.tryStatement = tryStatement;
        this.finallyStatement = finallyStatement;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitTryCatchFinally(this);
    }
    
    public List<CatchStatement> getCatchStatements() {
        return this.catchStatements;
    }
    
    public Statement getFinallyStatement() {
        return this.finallyStatement;
    }
    
    public Statement getTryStatement() {
        return this.tryStatement;
    }
    
    public void addCatch(final CatchStatement catchStatement) {
        this.catchStatements.add(catchStatement);
    }
    
    public CatchStatement getCatchStatement(final int idx) {
        if (idx >= 0 && idx < this.catchStatements.size()) {
            return this.catchStatements.get(idx);
        }
        return null;
    }
    
    public void setTryStatement(final Statement tryStatement) {
        this.tryStatement = tryStatement;
    }
    
    public void setCatchStatement(final int idx, final CatchStatement catchStatement) {
        this.catchStatements.set(idx, catchStatement);
    }
    
    public void setFinallyStatement(final Statement finallyStatement) {
        this.finallyStatement = finallyStatement;
    }
}
