// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public class ThrowStatement extends Statement
{
    private Expression expression;
    
    public ThrowStatement(final Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitThrowStatement(this);
    }
    
    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
}
