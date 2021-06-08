// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import groovy.lang.Closure;

public class ClosureSourceBinding implements SourceBinding
{
    Closure closure;
    Object[] arguments;
    
    public ClosureSourceBinding(final Closure closure) {
        this(closure, new Object[0]);
    }
    
    public ClosureSourceBinding(final Closure closure, final Object[] arguments) {
        this.closure = closure;
        this.arguments = arguments;
    }
    
    public Closure getClosure() {
        return this.closure;
    }
    
    public void setClosure(final Closure closure) {
        this.closure = closure;
    }
    
    public Object getSourceValue() {
        return this.closure.call(this.arguments);
    }
    
    public void setClosureArguments(final Object[] arguments) {
        this.arguments = arguments;
    }
    
    public void setClosureArgument(final Object argument) {
        this.arguments = new Object[] { argument };
    }
}
