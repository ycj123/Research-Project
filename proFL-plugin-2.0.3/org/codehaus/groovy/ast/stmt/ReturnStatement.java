// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public class ReturnStatement extends Statement
{
    public static final ReturnStatement RETURN_NULL_OR_VOID;
    private Expression expression;
    
    public ReturnStatement(final ExpressionStatement statement) {
        this(statement.getExpression());
        this.setStatementLabel(statement.getStatementLabel());
    }
    
    public ReturnStatement(final Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitReturnStatement(this);
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public String getText() {
        return "return " + this.expression.getText();
    }
    
    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
    
    public boolean isReturningNullOrVoid() {
        return this.expression instanceof ConstantExpression && ((ConstantExpression)this.expression).isNullExpression();
    }
    
    static {
        RETURN_NULL_OR_VOID = new ReturnStatement(ConstantExpression.NULL);
    }
}
