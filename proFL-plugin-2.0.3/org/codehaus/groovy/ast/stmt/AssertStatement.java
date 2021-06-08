// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.BooleanExpression;

public class AssertStatement extends Statement
{
    private BooleanExpression booleanExpression;
    private Expression messageExpression;
    
    public AssertStatement(final BooleanExpression booleanExpression) {
        this(booleanExpression, ConstantExpression.NULL);
    }
    
    public AssertStatement(final BooleanExpression booleanExpression, final Expression messageExpression) {
        this.booleanExpression = booleanExpression;
        this.messageExpression = messageExpression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitAssertStatement(this);
    }
    
    public Expression getMessageExpression() {
        return this.messageExpression;
    }
    
    public BooleanExpression getBooleanExpression() {
        return this.booleanExpression;
    }
    
    public void setBooleanExpression(final BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
    }
    
    public void setMessageExpression(final Expression messageExpression) {
        this.messageExpression = messageExpression;
    }
}
