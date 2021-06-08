// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;

public abstract class MetaMethodSite extends MetaClassSite
{
    final MetaMethod metaMethod;
    protected final Class[] params;
    
    public MetaMethodSite(final CallSite site, final MetaClass metaClass, final MetaMethod metaMethod, final Class[] params) {
        super(site, metaClass);
        this.metaMethod = metaMethod;
        this.params = params;
    }
}
