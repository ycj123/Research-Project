// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class RangeExpression extends Expression
{
    private Expression from;
    private Expression to;
    private boolean inclusive;
    
    public RangeExpression(final Expression from, final Expression to, final boolean inclusive) {
        this.from = from;
        this.to = to;
        this.inclusive = inclusive;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitRangeExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new RangeExpression(transformer.transform(this.from), transformer.transform(this.to), this.inclusive);
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getFrom() {
        return this.from;
    }
    
    public Expression getTo() {
        return this.to;
    }
    
    public boolean isInclusive() {
        return this.inclusive;
    }
    
    @Override
    public String getText() {
        return "(" + this.from.getText() + (this.isInclusive() ? ".." : "..<") + this.to.getText() + ")";
    }
}
