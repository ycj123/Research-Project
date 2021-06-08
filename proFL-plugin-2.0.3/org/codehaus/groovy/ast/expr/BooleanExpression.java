// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;

public class BooleanExpression extends Expression
{
    private final Expression expression;
    
    public BooleanExpression(final Expression expression) {
        this.expression = expression;
        this.setType(ClassHelper.boolean_TYPE);
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBooleanExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new BooleanExpression(transformer.transform(this.expression));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String getText() {
        return this.expression.getText();
    }
}
