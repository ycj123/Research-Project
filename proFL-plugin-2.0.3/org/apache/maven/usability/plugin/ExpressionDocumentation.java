// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.plugin;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

public class ExpressionDocumentation implements Serializable
{
    private List expressions;
    private Map expressionsBySyntax;
    private String modelEncoding;
    
    public ExpressionDocumentation() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addExpression(final Expression expression) {
        this.getExpressions().add(expression);
    }
    
    public List getExpressions() {
        if (this.expressions == null) {
            this.expressions = new ArrayList();
        }
        return this.expressions;
    }
    
    public void removeExpression(final Expression expression) {
        this.getExpressions().remove(expression);
    }
    
    public void setExpressions(final List expressions) {
        this.expressions = expressions;
    }
    
    public Map getExpressionsBySyntax() {
        if (this.expressionsBySyntax == null) {
            this.expressionsBySyntax = new HashMap();
            final List expressions = this.getExpressions();
            if (expressions != null && !expressions.isEmpty()) {
                for (final Expression expr : expressions) {
                    this.expressionsBySyntax.put(expr.getSyntax(), expr);
                }
            }
        }
        return this.expressionsBySyntax;
    }
    
    public void flushExpressionsBySyntax() {
        this.expressionsBySyntax = null;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
