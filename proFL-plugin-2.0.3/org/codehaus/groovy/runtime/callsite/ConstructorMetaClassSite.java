// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;

public class ConstructorMetaClassSite extends MetaClassSite
{
    public ConstructorMetaClassSite(final CallSite site, final MetaClass metaClass) {
        super(site, metaClass);
    }
    
    @Override
    public Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
        if (receiver == this.metaClass.getTheClass()) {
            try {
                return this.metaClass.invokeConstructor(args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallConstructor(this, receiver, args);
    }
}
