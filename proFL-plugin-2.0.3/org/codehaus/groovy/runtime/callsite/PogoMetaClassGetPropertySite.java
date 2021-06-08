// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;

public class PogoMetaClassGetPropertySite extends AbstractCallSite
{
    private final MetaClass metaClass;
    
    public PogoMetaClassGetPropertySite(final CallSite parent, final MetaClass metaClass) {
        super(parent);
        this.metaClass = metaClass;
    }
    
    @Override
    public final CallSite acceptGetProperty(final Object receiver) {
        if (!(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public final CallSite acceptGroovyObjectGetProperty(final Object receiver) {
        if (!(receiver instanceof GroovyObject) || ((GroovyObject)receiver).getMetaClass() != this.metaClass) {
            return this.createGroovyObjectGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public final Object getProperty(final Object receiver) throws Throwable {
        try {
            return this.metaClass.getProperty(receiver, this.name);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
