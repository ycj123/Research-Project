// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.wrappers;

import groovy.lang.MetaClass;

public class PojoWrapper extends Wrapper
{
    protected MetaClass delegate;
    protected final Object wrapped;
    
    public PojoWrapper(final Object wrapped, final Class constrainedType) {
        super(constrainedType);
        this.wrapped = wrapped;
    }
    
    @Override
    public Object unwrap() {
        return this.wrapped;
    }
    
    public Object getProperty(final String property) {
        return this.delegate.getProperty(this.wrapped, property);
    }
    
    public Object invokeMethod(final String methodName, final Object arguments) {
        return this.delegate.invokeMethod(this.wrapped, methodName, arguments);
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.delegate = metaClass;
    }
    
    public void setProperty(final String property, final Object newValue) {
        this.delegate.setProperty(this.wrapped, property, newValue);
    }
    
    @Override
    protected Object getWrapped() {
        return this.wrapped;
    }
    
    @Override
    protected MetaClass getDelegatedMetaClass() {
        return this.delegate;
    }
}
