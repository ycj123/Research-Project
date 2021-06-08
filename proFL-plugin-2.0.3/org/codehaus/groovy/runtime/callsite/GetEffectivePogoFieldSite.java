// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.reflection.CachedField;
import java.lang.reflect.Field;
import groovy.lang.MetaClass;

public class GetEffectivePogoFieldSite extends AbstractCallSite
{
    private final MetaClass metaClass;
    private final Field effective;
    
    public GetEffectivePogoFieldSite(final CallSite site, final MetaClass metaClass, final CachedField effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective.field;
    }
    
    @Override
    public final Object callGetProperty(final Object receiver) throws Throwable {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGetPropertySite(receiver).getProperty(receiver);
        }
        return this.getProperty(receiver);
    }
    
    @Override
    public final CallSite acceptGetProperty(final Object receiver) {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public final Object callGroovyObjectGetProperty(final Object receiver) throws Throwable {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGroovyObjectGetPropertySite(receiver).getProperty(receiver);
        }
        return this.getProperty(receiver);
    }
    
    @Override
    public final CallSite acceptGroovyObjectGetProperty(final Object receiver) {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGroovyObjectGetPropertySite(receiver);
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
