// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import groovy.lang.MetaProperty;
import groovy.lang.MetaClass;

class GetEffectivePogoPropertySite extends AbstractCallSite
{
    private final MetaClass metaClass;
    private final MetaProperty effective;
    
    public GetEffectivePogoPropertySite(final CallSite site, final MetaClass metaClass, final MetaProperty effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective;
    }
    
    @Override
    public final Object callGetProperty(final Object receiver) throws Throwable {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGetPropertySite(receiver).getProperty(receiver);
        }
        try {
            return this.effective.getProperty(receiver);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
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
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGetPropertySite(receiver).getProperty(receiver);
        }
        try {
            return this.effective.getProperty(receiver);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    @Override
    public final CallSite acceptGroovyObjectGetProperty(final Object receiver) {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || !(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGroovyObjectGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public final Object getProperty(final Object receiver) throws Throwable {
        try {
            return this.effective.getProperty(receiver);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
