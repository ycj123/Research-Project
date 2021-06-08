// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.reflection.CachedField;
import java.lang.reflect.Field;
import groovy.lang.MetaClassImpl;

class GetEffectivePojoFieldSite extends AbstractCallSite
{
    private final MetaClassImpl metaClass;
    private final Field effective;
    private final int version;
    
    public GetEffectivePojoFieldSite(final CallSite site, final MetaClassImpl metaClass, final CachedField effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective.field;
        this.version = metaClass.getVersion();
    }
    
    @Override
    public final CallSite acceptGetProperty(final Object receiver) {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || receiver.getClass() != this.metaClass.getTheClass() || this.version != this.metaClass.getVersion()) {
            return this.createGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public final Object getProperty(final Object receiver) {
        try {
            return this.effective.get(receiver);
        }
        catch (IllegalAccessException e) {
            throw new GroovyRuntimeException("Cannot get the property '" + this.name + "'.", e);
        }
    }
}
