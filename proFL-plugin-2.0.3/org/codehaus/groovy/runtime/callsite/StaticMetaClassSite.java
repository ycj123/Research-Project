// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;

public class StaticMetaClassSite extends MetaClassSite
{
    private final ClassInfo classInfo;
    private final int version;
    
    public StaticMetaClassSite(final CallSite site, final MetaClass metaClass) {
        super(site, metaClass);
        this.classInfo = ClassInfo.getClassInfo(metaClass.getTheClass());
        this.version = this.classInfo.getVersion();
    }
    
    private boolean checkCall(final Object receiver) {
        return receiver == this.metaClass.getTheClass() && this.version == this.classInfo.getVersion();
    }
    
    @Override
    public final Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver)) {
            try {
                return this.metaClass.invokeStaticMethod(receiver, this.name, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    @Override
    public final Object callStatic(final Class receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver)) {
            try {
                return this.metaClass.invokeStaticMethod(receiver, this.name, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallStatic(this, receiver, args);
    }
}
