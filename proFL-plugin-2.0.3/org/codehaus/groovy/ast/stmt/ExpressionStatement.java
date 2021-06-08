// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public class ExpressionStatement extends Statement
{
    private Expression expression;
    
    public ExpressionStatement(final Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression cannot be null");
        }
        this.expression = expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitExpressionStatement(this);
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public String getText() {
        return this.toString();
    }
    
    @Override
    public String toString() {
        return super.toString() + "[expression:" + this.expression + "]";
    }
}
