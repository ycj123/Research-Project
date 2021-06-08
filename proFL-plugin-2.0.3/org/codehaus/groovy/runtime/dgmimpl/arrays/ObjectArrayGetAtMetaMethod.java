// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.CachedClass;

public class ObjectArrayGetAtMetaMethod extends ArrayGetAtMetaMethod
{
    private static final CachedClass OBJECT_ARR_CLASS;
    
    @Override
    public Class getReturnType() {
        return Object.class;
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return ObjectArrayGetAtMetaMethod.OBJECT_ARR_CLASS;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        final Object[] objects = (Object[])object;
        return objects[ArrayMetaMethod.normaliseIndex((int)arguments[0], objects.length)];
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (!(args[0] instanceof Integer)) {
            return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
        }
        return new MyPojoMetaMethodSite(site, metaClass, metaMethod, params);
    }
    
    static {
        OBJECT_ARR_CLASS = ReflectionCache.OBJECT_ARRAY_CLASS;
    }
    
    private static class MyPojoMetaMethodSite extends PojoMetaMethodSite
    {
        public MyPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public Object call(final Object receiver, final Object arg) throws Throwable {
            if (this.checkPojoMetaClass()) {
                try {
                    final Object[] objects = (Object[])receiver;
                    return objects[ArrayMetaMethod.normaliseIndex((int)arg, objects.length)];
                }
                catch (ClassCastException e) {
                    if (receiver instanceof Object[] && arg instanceof Integer) {
                        throw e;
                    }
                }
            }
            return super.call(receiver, arg);
        }
    }
}
