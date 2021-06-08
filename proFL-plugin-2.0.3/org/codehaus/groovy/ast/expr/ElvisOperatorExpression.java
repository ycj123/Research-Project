// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ASTNode;

public class ElvisOperatorExpression extends TernaryExpression
{
    public ElvisOperatorExpression(final Expression base, final Expression falseExpression) {
        super(getBool(base), base, falseExpression);
    }
    
    private static BooleanExpression getBool(final Expression base) {
        final BooleanExpression be = new BooleanExpression(base);
        be.setSourcePosition(base);
        return be;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitShortTernaryExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new ElvisOperatorExpression(transformer.transform(this.getTrueExpression()), transformer.transform(this.getFalseExpression()));
        ret.setSourcePosition(this);
        return ret;
    }
}
