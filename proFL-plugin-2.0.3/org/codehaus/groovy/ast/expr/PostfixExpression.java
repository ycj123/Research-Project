// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.syntax.Token;

public class PostfixExpression extends Expression
{
    private Token operation;
    private Expression expression;
    
    public PostfixExpression(final Expression expression, final Token operation) {
        this.operation = operation;
        this.expression = expression;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.expression + this.operation + "]";
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitPostfixExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new PostfixExpression(transformer.transform(this.expression), this.operation);
        ret.setSourcePosition(this);
        return ret;
    }
    
    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
    
    public Token getOperation() {
        return this.operation;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public String getText() {
        return "(" + this.expression.getText() + this.operation.getText() + ")";
    }
    
    @Override
    public ClassNode getType() {
        return this.expression.getType();
    }
}
