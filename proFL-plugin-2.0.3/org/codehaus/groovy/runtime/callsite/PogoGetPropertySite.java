// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;

public class PogoGetPropertySite extends AbstractCallSite
{
    private final Class aClass;
    
    public PogoGetPropertySite(final CallSite parent, final Class aClass) {
        super(parent);
        this.aClass = aClass;
    }
    
    @Override
    public CallSite acceptGetProperty(final Object receiver) {
        if (receiver.getClass() != this.aClass) {
            return this.createGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public CallSite acceptGroovyObjectGetProperty(final Object receiver) {
        if (receiver.getClass() != this.aClass) {
            return this.createGroovyObjectGetPropertySite(receiver);
        }
        return this;
    }
    
    @Override
    public Object getProperty(final Object receiver) throws Throwable {
        try {
            return ((GroovyObject)receiver).getProperty(this.name);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
