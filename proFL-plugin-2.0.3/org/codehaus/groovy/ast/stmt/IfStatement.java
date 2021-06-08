// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.BooleanExpression;

public class IfStatement extends Statement
{
    private BooleanExpression booleanExpression;
    private Statement ifBlock;
    private Statement elseBlock;
    
    public IfStatement(final BooleanExpression booleanExpression, final Statement ifBlock, final Statement elseBlock) {
        this.booleanExpression = booleanExpression;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitIfElse(this);
    }
    
    public BooleanExpression getBooleanExpression() {
        return this.booleanExpression;
    }
    
    public Statement getIfBlock() {
        return this.ifBlock;
    }
    
    public Statement getElseBlock() {
        return this.elseBlock;
    }
    
    public void setBooleanExpression(final BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
    }
    
    public void setIfBlock(final Statement statement) {
        this.ifBlock = statement;
    }
    
    public void setElseBlock(final Statement statement) {
        this.elseBlock = statement;
    }
}
