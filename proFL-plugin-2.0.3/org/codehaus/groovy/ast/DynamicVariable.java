// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.expr.Expression;

public class DynamicVariable implements Variable
{
    private String name;
    private boolean closureShare;
    private boolean staticContext;
    
    public DynamicVariable(final String name, final boolean context) {
        this.closureShare = false;
        this.staticContext = false;
        this.name = name;
        this.staticContext = context;
    }
    
    public ClassNode getType() {
        return ClassHelper.DYNAMIC_TYPE;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Expression getInitialExpression() {
        return null;
    }
    
    public boolean hasInitialExpression() {
        return false;
    }
    
    public boolean isInStaticContext() {
        return this.staticContext;
    }
    
    public boolean isDynamicTyped() {
        return true;
    }
    
    public boolean isClosureSharedVariable() {
        return this.closureShare;
    }
    
    public void setClosureSharedVariable(final boolean inClosure) {
        this.closureShare = inClosure;
    }
    
    public ClassNode getOriginType() {
        return this.getType();
    }
}
