// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;

public class ConstructorMetaMethodSite extends MetaMethodSite
{
    private final int version;
    
    public ConstructorMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod method, final Class[] params) {
        super(site, metaClass, method, params);
        this.version = metaClass.getVersion();
    }
    
    public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
        MetaClassHelper.unwrap(args);
        try {
            return this.metaMethod.doMethodInvoke(this.metaClass.getTheClass(), args);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    @Override
    public final Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
        if (receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, args)) {
            MetaClassHelper.unwrap(args);
            try {
                return this.metaMethod.doMethodInvoke(this.metaClass.getTheClass(), args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallConstructor(this, receiver, args);
    }
}
