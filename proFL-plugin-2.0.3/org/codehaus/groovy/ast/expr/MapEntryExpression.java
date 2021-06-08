// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class MapEntryExpression extends Expression
{
    private Expression keyExpression;
    private Expression valueExpression;
    
    public MapEntryExpression(final Expression keyExpression, final Expression valueExpression) {
        this.keyExpression = keyExpression;
        this.valueExpression = valueExpression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitMapEntryExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new MapEntryExpression(transformer.transform(this.keyExpression), transformer.transform(this.valueExpression));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String toString() {
        return super.toString() + "(key: " + this.keyExpression + ", value: " + this.valueExpression + ")";
    }
    
    public Expression getKeyExpression() {
        return this.keyExpression;
    }
    
    public Expression getValueExpression() {
        return this.valueExpression;
    }
    
    public void setKeyExpression(final Expression keyExpression) {
        this.keyExpression = keyExpression;
    }
    
    public void setValueExpression(final Expression valueExpression) {
        this.valueExpression = valueExpression;
    }
}
