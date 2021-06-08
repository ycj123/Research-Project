// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExecutionFailed;
import groovy.lang.MetaClass;

public class PogoMetaClassSite extends MetaClassSite
{
    public PogoMetaClassSite(final CallSite site, final MetaClass metaClass) {
        super(site, metaClass);
    }
    
    @Override
    public final Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver)) {
            try {
                try {
                    return this.metaClass.invokeMethod(receiver, this.name, args);
                }
                catch (MissingMethodException e) {
                    if (e instanceof MissingMethodExecutionFailed) {
                        throw (MissingMethodException)e.getCause();
                    }
                    if (receiver.getClass() == e.getType() && e.getMethod().equals(this.name)) {
                        return ((GroovyObject)receiver).invokeMethod(this.name, args);
                    }
                    throw e;
                }
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    protected final boolean checkCall(final Object receiver) {
        return receiver instanceof GroovyObject && ((GroovyObject)receiver).getMetaClass() == this.metaClass;
    }
    
    @Override
    public final Object callCurrent(final GroovyObject receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver)) {
            try {
                try {
                    return this.metaClass.invokeMethod(receiver, this.name, args);
                }
                catch (MissingMethodException e) {
                    if (e instanceof MissingMethodExecutionFailed) {
                        throw (MissingMethodException)e.getCause();
                    }
                    if (receiver.getClass() == e.getType() && e.getMethod().equals(this.name)) {
                        return receiver.invokeMethod(this.name, args);
                    }
                    throw e;
                }
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallCurrent(this, receiver, args);
    }
}
