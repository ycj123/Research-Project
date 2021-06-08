// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class AttributeExpression extends PropertyExpression
{
    public AttributeExpression(final Expression objectExpression, final Expression property) {
        super(objectExpression, property, false);
    }
    
    public AttributeExpression(final Expression objectExpression, final Expression property, final boolean safe) {
        super(objectExpression, property, safe);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitAttributeExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final AttributeExpression ret = new AttributeExpression(transformer.transform(this.getObjectExpression()), transformer.transform(this.getProperty()), this.isSafe());
        ret.setSourcePosition(this);
        ret.setSpreadSafe(this.isSpreadSafe());
        return ret;
    }
}
