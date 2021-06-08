// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.MetaClass;

public abstract class MetaClassSite extends AbstractCallSite
{
    protected final MetaClass metaClass;
    
    public MetaClassSite(final CallSite site, final MetaClass metaClass) {
        super(site);
        this.metaClass = metaClass;
    }
}
