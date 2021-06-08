// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.wrappers;

import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;

public abstract class Wrapper implements GroovyObject
{
    @Deprecated
    protected MetaClass delegatingMetaClass;
    protected final Class constrainedType;
    
    public Wrapper(final Class constrainedType) {
        this.constrainedType = constrainedType;
    }
    
    public MetaClass getMetaClass() {
        return this.getDelegatedMetaClass();
    }
    
    public Class getType() {
        return this.constrainedType;
    }
    
    public abstract Object unwrap();
    
    protected abstract Object getWrapped();
    
    protected abstract MetaClass getDelegatedMetaClass();
}
