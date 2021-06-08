// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class UnaryMinusExpression extends Expression
{
    private final Expression expression;
    
    public UnaryMinusExpression(final Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitUnaryMinusExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new UnaryMinusExpression(transformer.transform(this.expression));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String getText() {
        return this.expression.getText();
    }
    
    @Override
    public ClassNode getType() {
        return this.expression.getType();
    }
    
    public boolean isDynamic() {
        return false;
    }
}
