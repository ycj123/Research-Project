// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.expr.Expression;

public class Parameter extends AnnotatedNode implements Variable
{
    public static final Parameter[] EMPTY_ARRAY;
    private ClassNode type;
    private final String name;
    private boolean dynamicTyped;
    private Expression defaultValue;
    private boolean hasDefaultValue;
    private boolean inStaticContext;
    private boolean closureShare;
    
    public Parameter(final ClassNode type, final String name) {
        this.closureShare = false;
        this.name = name;
        this.setType(type);
        this.hasDefaultValue = false;
    }
    
    public Parameter(final ClassNode type, final String name, final Expression defaultValue) {
        this(type, name);
        this.defaultValue = defaultValue;
        this.hasDefaultValue = (defaultValue != null);
    }
    
    @Override
    public String toString() {
        return super.toString() + "[name:" + this.name + ((this.type == null) ? "" : (" type: " + this.type.getName())) + ", hasDefaultValue: " + this.hasInitialExpression() + "]";
    }
    
    public String getName() {
        return this.name;
    }
    
    public ClassNode getType() {
        return this.type;
    }
    
    public void setType(final ClassNode type) {
        this.type = type;
        this.dynamicTyped |= (type == ClassHelper.DYNAMIC_TYPE);
    }
    
    public boolean hasInitialExpression() {
        return this.hasDefaultValue;
    }
    
    public Expression getInitialExpression() {
        return this.defaultValue;
    }
    
    public void setInitialExpression(final Expression init) {
        this.defaultValue = init;
        this.hasDefaultValue = (this.defaultValue != null);
    }
    
    public boolean isInStaticContext() {
        return this.inStaticContext;
    }
    
    public void setInStaticContext(final boolean inStaticContext) {
        this.inStaticContext = inStaticContext;
    }
    
    public boolean isDynamicTyped() {
        return this.dynamicTyped;
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
    
    static {
        EMPTY_ARRAY = new Parameter[0];
    }
}
