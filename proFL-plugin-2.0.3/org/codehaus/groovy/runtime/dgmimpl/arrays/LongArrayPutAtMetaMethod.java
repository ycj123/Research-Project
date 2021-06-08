// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GString;
import org.codehaus.groovy.reflection.CachedClass;

public class LongArrayPutAtMetaMethod extends ArrayPutAtMetaMethod
{
    private static final CachedClass OBJECT_CLASS;
    private static final CachedClass ARR_CLASS;
    private static final CachedClass[] PARAM_CLASS_ARR;
    
    public LongArrayPutAtMetaMethod() {
        this.parameterTypes = LongArrayPutAtMetaMethod.PARAM_CLASS_ARR;
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return LongArrayPutAtMetaMethod.ARR_CLASS;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] args) {
        final long[] objects = (long[])object;
        final int index = ArrayMetaMethod.normaliseIndex((int)args[0], objects.length);
        final Object newValue = args[1];
        if (!(newValue instanceof Long)) {
            if (newValue instanceof Character || newValue instanceof String || newValue instanceof GString) {
                final Character ch = DefaultTypeTransformation.getCharFromSizeOneString(newValue);
                objects[index] = (long)DefaultTypeTransformation.castToType(ch, Long.class);
            }
            else {
                objects[index] = ((Number)newValue).longValue();
            }
        }
        else {
            objects[index] = (long)args[1];
        }
        return null;
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (!(args[0] instanceof Integer) || !(args[1] instanceof Long)) {
            return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
        }
        return new MyPojoMetaMethodSite(site, metaClass, metaMethod, params);
    }
    
    static {
        OBJECT_CLASS = ReflectionCache.OBJECT_CLASS;
        ARR_CLASS = ReflectionCache.getCachedClass(long[].class);
        PARAM_CLASS_ARR = new CachedClass[] { LongArrayPutAtMetaMethod.INTEGER_CLASS, LongArrayPutAtMetaMethod.OBJECT_CLASS };
    }
    
    private static class MyPojoMetaMethodSite extends PojoMetaMethodSite
    {
        public MyPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public Object call(final Object receiver, final Object[] args) throws Throwable {
            if (receiver instanceof long[] && args[0] instanceof Integer && args[1] instanceof Long && this.checkPojoMetaClass()) {
                final long[] objects = (long[])receiver;
                objects[ArrayMetaMethod.normaliseIndex((int)args[0], objects.length)] = (long)args[1];
                return null;
            }
            return super.call(receiver, args);
        }
        
        @Override
        public Object call(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
            if (this.checkPojoMetaClass()) {
                try {
                    final long[] objects = (long[])receiver;
                    objects[ArrayMetaMethod.normaliseIndex((int)arg1, objects.length)] = (long)arg2;
                    return null;
                }
                catch (ClassCastException e) {
                    if (receiver instanceof long[] && arg1 instanceof Integer) {
                        throw e;
                    }
                }
            }
            return super.call(receiver, arg1, arg2);
        }
    }
}
