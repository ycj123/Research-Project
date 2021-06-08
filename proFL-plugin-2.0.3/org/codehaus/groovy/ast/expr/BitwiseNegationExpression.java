// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class BitwiseNegationExpression extends Expression
{
    private Expression expression;
    
    public BitwiseNegationExpression(final Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBitwiseNegationExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new BitwiseNegationExpression(transformer.transform(this.expression));
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
}
