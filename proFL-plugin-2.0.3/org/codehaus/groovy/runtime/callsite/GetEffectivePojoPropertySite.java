// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import groovy.lang.MetaProperty;
import groovy.lang.MetaClassImpl;

public class GetEffectivePojoPropertySite extends AbstractCallSite
{
    private final MetaClassImpl metaClass;
    private final MetaProperty effective;
    private final int version;
    
    public GetEffectivePojoPropertySite(final CallSite site, final MetaClassImpl metaClass, final MetaProperty effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective;
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
    public final Object getProperty(final Object receiver) throws Throwable {
        try {
            return this.effective.getProperty(receiver);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
