// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.reflection.ClassInfo;

public class PerInstancePojoMetaClassSite extends AbstractCallSite
{
    private final ClassInfo info;
    
    public PerInstancePojoMetaClassSite(final CallSite site, final ClassInfo info) {
        super(site);
        this.info = info;
    }
    
    @Override
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.info.hasPerInstanceMetaClasses()) {
            try {
                return InvokerHelper.getMetaClass(receiver).invokeMethod(receiver, this.name, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
}
