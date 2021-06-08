// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import java.util.List;

public class NamedArgumentListExpression extends MapExpression
{
    public NamedArgumentListExpression() {
    }
    
    public NamedArgumentListExpression(final List<MapEntryExpression> mapEntryExpressions) {
        super(mapEntryExpressions);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new NamedArgumentListExpression(this.transformExpressions(this.getMapEntryExpressions(), transformer, MapEntryExpression.class));
        ret.setSourcePosition(this);
        return ret;
    }
}
