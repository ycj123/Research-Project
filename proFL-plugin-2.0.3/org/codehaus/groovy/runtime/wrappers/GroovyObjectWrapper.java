// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.wrappers;

import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;

public class GroovyObjectWrapper extends Wrapper
{
    protected final GroovyObject wrapped;
    
    public GroovyObjectWrapper(final GroovyObject wrapped, final Class constrainedType) {
        super(constrainedType);
        this.wrapped = wrapped;
    }
    
    @Override
    public Object unwrap() {
        return this.wrapped;
    }
    
    public Object getProperty(final String property) {
        return this.wrapped.getProperty(property);
    }
    
    public Object invokeMethod(final String name, final Object args) {
        return this.wrapped.invokeMethod(name, args);
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.wrapped.setMetaClass(metaClass);
    }
    
    public void setProperty(final String property, final Object newValue) {
        this.wrapped.setProperty(property, newValue);
    }
    
    @Override
    protected Object getWrapped() {
        return this.wrapped;
    }
    
    @Override
    protected MetaClass getDelegatedMetaClass() {
        return this.wrapped.getMetaClass();
    }
}
