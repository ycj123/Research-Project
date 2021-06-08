// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.InvokerHelper;

public class PojoMetaClassGetPropertySite extends AbstractCallSite
{
    public PojoMetaClassGetPropertySite(final CallSite parent) {
        super(parent);
    }
    
    @Override
    public final CallSite acceptGetProperty(final Object receiver) {
        return this;
    }
    
    @Override
    public final Object getProperty(final Object receiver) throws Throwable {
        try {
            return InvokerHelper.getProperty(receiver, this.name);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    @Override
    public Object callGetProperty(final Object receiver) throws Throwable {
        try {
            return InvokerHelper.getProperty(receiver, this.name);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
