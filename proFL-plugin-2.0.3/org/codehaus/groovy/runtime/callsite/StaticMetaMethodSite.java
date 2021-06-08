// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.reflection.ParameterTypes;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;

public class StaticMetaMethodSite extends MetaMethodSite
{
    private final int version;
    
    public StaticMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
        super(site, metaClass, metaMethod, params);
        this.version = metaClass.getVersion();
    }
    
    public Object invoke(final Object receiver, final Object[] args) throws Throwable {
        MetaClassHelper.unwrap(args);
        try {
            return this.metaMethod.doMethodInvoke(receiver, args);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    protected final boolean checkCall(final Object receiver, final Object[] args) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, args);
    }
    
    protected final boolean checkCall(final Object receiver) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params);
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, arg1);
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, arg1, arg2);
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3);
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3, arg4);
    }
    
    @Override
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            try {
                return this.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    @Override
    public Object callStatic(final Class receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            return this.invoke(receiver, args);
        }
        return CallSiteArray.defaultCallStatic(this, receiver, args);
    }
    
    public static CallSite createStaticMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new StaticMetaMethodSite(site, metaClass, metaMethod, params);
        }
        if (AbstractCallSite.noCoerce(metaMethod, args)) {
            return new StaticMetaMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
        }
        if (metaMethod.getClass() == CachedMethod.class) {
            return ((CachedMethod)metaMethod).createStaticMetaMethodSite(site, metaClass, params);
        }
        return new StaticMetaMethodSiteNoUnwrapNoCoerce(site, metaClass, metaMethod, params);
    }
    
    public static class StaticMetaMethodSiteNoUnwrap extends StaticMetaMethodSite
    {
        public StaticMetaMethodSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
            try {
                return this.metaMethod.doMethodInvoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }
    
    public static class StaticMetaMethodSiteNoUnwrapNoCoerce extends StaticMetaMethodSite
    {
        public StaticMetaMethodSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
            try {
                return this.metaMethod.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }
}
