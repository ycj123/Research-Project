// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class MetaClassConstructorSite extends MetaClassSite
{
    private final ClassInfo classInfo;
    private final int version;
    
    public MetaClassConstructorSite(final CallSite site, final MetaClass metaClass) {
        super(site, metaClass);
        this.classInfo = ClassInfo.getClassInfo(metaClass.getTheClass());
        this.version = this.classInfo.getVersion();
    }
    
    @Override
    public Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
        try {
            if (receiver == this.metaClass.getTheClass() && this.version == this.classInfo.getVersion()) {
                return this.metaClass.invokeConstructor(args);
            }
            return CallSiteArray.defaultCallConstructor(this, receiver, args);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
}
