// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class NotExpression extends BooleanExpression
{
    public NotExpression(final Expression expression) {
        super(expression);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitNotExpression(this);
    }
    
    public boolean isDynamic() {
        return false;
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new NotExpression(transformer.transform(this.getExpression()));
        ret.setSourcePosition(this);
        return ret;
    }
}
