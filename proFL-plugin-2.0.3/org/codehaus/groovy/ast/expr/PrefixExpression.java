// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.syntax.Token;

public class PrefixExpression extends Expression
{
    private Token operation;
    private Expression expression;
    
    public PrefixExpression(final Token operation, final Expression expression) {
        this.operation = operation;
        this.expression = expression;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.operation + this.expression + "]";
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitPrefixExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new PrefixExpression(this.operation, transformer.transform(this.expression));
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
        return "(" + this.operation.getText() + this.expression.getText() + ")";
    }
    
    @Override
    public ClassNode getType() {
        return this.expression.getType();
    }
}
