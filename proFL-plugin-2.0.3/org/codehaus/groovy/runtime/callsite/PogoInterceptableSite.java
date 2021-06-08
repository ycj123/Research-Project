// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyObject;

public class PogoInterceptableSite extends AbstractCallSite
{
    public PogoInterceptableSite(final CallSite site) {
        super(site);
    }
    
    public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
        try {
            return ((GroovyObject)receiver).invokeMethod(this.name, InvokerHelper.asUnwrappedArray(args));
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    @Override
    public final Object call(final Object receiver, final Object[] args) throws Throwable {
        if (receiver instanceof GroovyObject) {
            try {
                return ((GroovyObject)receiver).invokeMethod(this.name, InvokerHelper.asUnwrappedArray(args));
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    @Override
    public Object callCurrent(final GroovyObject receiver, final Object[] args) throws Throwable {
        return this.call(receiver, args);
    }
}
