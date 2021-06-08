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

public class CharacterArrayGetAtMetaMethod extends ArrayGetAtMetaMethod
{
    private static final CachedClass ARR_CLASS;
    
    @Override
    public Class getReturnType() {
        return Character.class;
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return CharacterArrayGetAtMetaMethod.ARR_CLASS;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] args) {
        final char[] objects = (char[])object;
        return objects[ArrayMetaMethod.normaliseIndex((int)args[0], objects.length)];
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (!(args[0] instanceof Integer)) {
            return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
        }
        return new MyPojoMetaMethodSite(site, metaClass, metaMethod, params);
    }
    
    static {
        ARR_CLASS = ReflectionCache.getCachedClass(char[].class);
    }
    
    private static class MyPojoMetaMethodSite extends PojoMetaMethodSite
    {
        public MyPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public Object call(final Object receiver, final Object arg) throws Throwable {
            if (receiver instanceof char[] && arg instanceof Integer && this.checkPojoMetaClass()) {
                final char[] objects = (char[])receiver;
                return objects[ArrayMetaMethod.normaliseIndex((int)arg, objects.length)];
            }
            return super.call(receiver, arg);
        }
    }
}
