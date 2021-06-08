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

public class IntegerArrayPutAtMetaMethod extends ArrayPutAtMetaMethod
{
    private static final CachedClass OBJECT_CLASS;
    private static final CachedClass ARR_CLASS;
    private static final CachedClass[] PARAM_CLASS_ARR;
    
    public IntegerArrayPutAtMetaMethod() {
        this.parameterTypes = IntegerArrayPutAtMetaMethod.PARAM_CLASS_ARR;
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return IntegerArrayPutAtMetaMethod.ARR_CLASS;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] args) {
        final int[] objects = (int[])object;
        final int index = ArrayMetaMethod.normaliseIndex((int)args[0], objects.length);
        final Object newValue = args[1];
        if (!(newValue instanceof Integer)) {
            if (newValue instanceof Character || newValue instanceof String || newValue instanceof GString) {
                final Character ch = DefaultTypeTransformation.getCharFromSizeOneString(newValue);
                objects[index] = (int)DefaultTypeTransformation.castToType(ch, Integer.class);
            }
            else {
                objects[index] = ((Number)newValue).intValue();
            }
        }
        else {
            objects[index] = (int)args[1];
        }
        return null;
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (!(args[0] instanceof Integer) || !(args[1] instanceof Integer)) {
            return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
        }
        return new MyPojoMetaMethodSite(site, metaClass, metaMethod, params);
    }
    
    static {
        OBJECT_CLASS = ReflectionCache.OBJECT_CLASS;
        ARR_CLASS = ReflectionCache.getCachedClass(int[].class);
        PARAM_CLASS_ARR = new CachedClass[] { IntegerArrayPutAtMetaMethod.INTEGER_CLASS, IntegerArrayPutAtMetaMethod.OBJECT_CLASS };
    }
    
    private static class MyPojoMetaMethodSite extends PojoMetaMethodSite
    {
        public MyPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public Object call(final Object receiver, final Object[] args) throws Throwable {
            if (receiver instanceof int[] && args[0] instanceof Integer && args[1] instanceof Integer && this.checkPojoMetaClass()) {
                final int[] objects = (int[])receiver;
                objects[ArrayMetaMethod.normaliseIndex((int)args[0], objects.length)] = (int)args[1];
                return null;
            }
            return super.call(receiver, args);
        }
        
        @Override
        public Object call(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
            if (this.checkPojoMetaClass()) {
                try {
                    final int[] objects = (int[])receiver;
                    objects[ArrayMetaMethod.normaliseIndex((int)arg1, objects.length)] = (int)arg2;
                    return null;
                }
                catch (ClassCastException e) {
                    if (receiver instanceof int[] && arg1 instanceof Integer) {
                        throw e;
                    }
                }
            }
            return super.call(receiver, arg1, arg2);
        }
    }
}
