// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.ArrayList;
import java.util.List;

public class MapExpression extends Expression
{
    private final List<MapEntryExpression> mapEntryExpressions;
    
    public MapExpression() {
        this(new ArrayList<MapEntryExpression>());
    }
    
    public MapExpression(final List<MapEntryExpression> mapEntryExpressions) {
        this.mapEntryExpressions = mapEntryExpressions;
        this.setType(ClassHelper.MAP_TYPE);
    }
    
    public void addMapEntryExpression(final MapEntryExpression expression) {
        this.mapEntryExpressions.add(expression);
    }
    
    public List<MapEntryExpression> getMapEntryExpressions() {
        return this.mapEntryExpressions;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitMapExpression(this);
    }
    
    public boolean isDynamic() {
        return false;
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new MapExpression(this.transformExpressions(this.getMapEntryExpressions(), transformer, MapEntryExpression.class));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String toString() {
        return super.toString() + this.mapEntryExpressions;
    }
    
    @Override
    public String getText() {
        final StringBuffer sb = new StringBuffer(32);
        sb.append("[");
        final int size = this.mapEntryExpressions.size();
        MapEntryExpression mapEntryExpression = null;
        if (size > 0) {
            mapEntryExpression = this.mapEntryExpressions.get(0);
            sb.append(mapEntryExpression.getKeyExpression().getText() + ":" + mapEntryExpression.getValueExpression().getText());
            for (int i = 1; i < size; ++i) {
                mapEntryExpression = this.mapEntryExpressions.get(i);
                sb.append(", " + mapEntryExpression.getKeyExpression().getText() + ":" + mapEntryExpression.getValueExpression().getText());
                if (sb.length() > 120 && i < size - 1) {
                    sb.append(", ... ");
                    break;
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void addMapEntryExpression(final Expression keyExpression, final Expression valueExpression) {
        this.addMapEntryExpression(new MapEntryExpression(keyExpression, valueExpression));
    }
}
