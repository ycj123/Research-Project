// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.NullObject;

public final class NullCallSite extends AbstractCallSite
{
    public NullCallSite(final CallSite callSite) {
        super(callSite);
    }
    
    @Override
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        if (receiver == null) {
            try {
                return CallSiteArray.defaultCall(this, NullObject.getNullObject(), args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    @Override
    public Object getProperty(final Object receiver) throws Throwable {
        if (receiver == null) {
            try {
                return InvokerHelper.getProperty(NullObject.getNullObject(), this.name);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return this.acceptGetProperty(receiver).getProperty(receiver);
    }
}
