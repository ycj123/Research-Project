// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.BooleanExpression;

public class WhileStatement extends Statement
{
    private BooleanExpression booleanExpression;
    private Statement loopBlock;
    
    public WhileStatement(final BooleanExpression booleanExpression, final Statement loopBlock) {
        this.booleanExpression = booleanExpression;
        this.loopBlock = loopBlock;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitWhileLoop(this);
    }
    
    public BooleanExpression getBooleanExpression() {
        return this.booleanExpression;
    }
    
    public Statement getLoopBlock() {
        return this.loopBlock;
    }
    
    public void setBooleanExpression(final BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
    }
    
    public void setLoopBlock(final Statement loopBlock) {
        this.loopBlock = loopBlock;
    }
}
