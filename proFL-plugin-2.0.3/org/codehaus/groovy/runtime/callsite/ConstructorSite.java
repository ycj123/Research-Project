// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import java.util.Map;
import org.codehaus.groovy.reflection.ParameterTypes;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.reflection.CachedConstructor;

public class ConstructorSite extends MetaClassSite
{
    final CachedConstructor constructor;
    final Class[] params;
    private final int version;
    
    public ConstructorSite(final CallSite site, final MetaClassImpl metaClass, final CachedConstructor constructor, final Class[] params) {
        super(site, metaClass);
        this.constructor = constructor;
        this.params = params;
        this.version = metaClass.getVersion();
    }
    
    @Override
    public Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            MetaClassHelper.unwrap(args);
            try {
                return this.constructor.doConstructorInvoke(args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallConstructor(this, receiver, args);
    }
    
    protected final boolean checkCall(final Object receiver, final Object[] args) {
        return receiver == this.metaClass.getTheClass() && ((MetaClassImpl)this.metaClass).getVersion() == this.version && MetaClassHelper.sameClasses(this.params, args);
    }
    
    public static ConstructorSite createConstructorSite(final CallSite site, final MetaClassImpl metaClass, final CachedConstructor constructor, final Class[] params, final Object[] args) {
        if (constructor.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new ConstructorSite(site, metaClass, constructor, params);
        }
        if (AbstractCallSite.noCoerce(constructor, args)) {
            return new ConstructorSiteNoUnwrap(site, metaClass, constructor, params);
        }
        return new ConstructorSiteNoUnwrapNoCoerce(site, metaClass, constructor, params);
    }
    
    public static class ConstructorSiteNoUnwrap extends ConstructorSite
    {
        public ConstructorSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final CachedConstructor constructor, final Class[] params) {
            super(site, metaClass, constructor, params);
        }
        
        @Override
        public final Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
            if (this.checkCall(receiver, args)) {
                try {
                    return this.constructor.doConstructorInvoke(args);
                }
                catch (GroovyRuntimeException gre) {
                    throw ScriptBytecodeAdapter.unwrap(gre);
                }
            }
            return CallSiteArray.defaultCallConstructor(this, receiver, args);
        }
    }
    
    public static class ConstructorSiteNoUnwrapNoCoerce extends ConstructorSite
    {
        public ConstructorSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final CachedConstructor constructor, final Class[] params) {
            super(site, metaClass, constructor, params);
        }
        
        @Override
        public Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
            if (this.checkCall(receiver, args)) {
                try {
                    return this.constructor.invoke(args);
                }
                catch (GroovyRuntimeException gre) {
                    throw ScriptBytecodeAdapter.unwrap(gre);
                }
            }
            return CallSiteArray.defaultCallConstructor(this, receiver, args);
        }
    }
    
    public static class NoParamSite extends ConstructorSiteNoUnwrapNoCoerce
    {
        private static final Object[] NO_ARGS;
        
        public NoParamSite(final CallSite site, final MetaClassImpl metaClass, final CachedConstructor constructor, final Class[] params) {
            super(site, metaClass, constructor, params);
        }
        
        @Override
        public final Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
            if (this.checkCall(receiver, args)) {
                final Object bean = this.constructor.invoke(NoParamSite.NO_ARGS);
                try {
                    ((MetaClassImpl)this.metaClass).setProperties(bean, (Map)args[0]);
                }
                catch (GroovyRuntimeException gre) {
                    throw ScriptBytecodeAdapter.unwrap(gre);
                }
                return bean;
            }
            return CallSiteArray.defaultCallConstructor(this, receiver, args);
        }
        
        static {
            NO_ARGS = new Object[0];
        }
    }
}
