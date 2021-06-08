// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GString;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.CachedClass;

public class ObjectArrayPutAtMetaMethod extends ArrayPutAtMetaMethod
{
    private static final CachedClass OBJECT_CLASS;
    private static final CachedClass OBJECT_ARR_CLASS;
    private static final CachedClass[] PARAM_CLASS_ARR;
    
    public ObjectArrayPutAtMetaMethod() {
        this.parameterTypes = ObjectArrayPutAtMetaMethod.PARAM_CLASS_ARR;
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return ObjectArrayPutAtMetaMethod.OBJECT_ARR_CLASS;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        final Object[] objects = (Object[])object;
        final int index = ArrayMetaMethod.normaliseIndex((int)arguments[0], objects.length);
        objects[index] = adjustNewValue(objects, arguments[1]);
        return null;
    }
    
    private static Object adjustNewValue(final Object[] objects, final Object newValue) {
        final Class arrayComponentClass = objects.getClass().getComponentType();
        Object adjustedNewVal = newValue;
        if (newValue instanceof Number) {
            if (!arrayComponentClass.equals(newValue.getClass())) {
                adjustedNewVal = DefaultTypeTransformation.castToType(newValue, arrayComponentClass);
            }
        }
        else if (Character.class.isAssignableFrom(arrayComponentClass)) {
            adjustedNewVal = DefaultTypeTransformation.getCharFromSizeOneString(newValue);
        }
        else if (Number.class.isAssignableFrom(arrayComponentClass) && (newValue instanceof Character || newValue instanceof String || newValue instanceof GString)) {
            final Character ch = DefaultTypeTransformation.getCharFromSizeOneString(newValue);
            adjustedNewVal = DefaultTypeTransformation.castToType(ch, arrayComponentClass);
        }
        return adjustedNewVal;
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (!(args[0] instanceof Integer)) {
            return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
        }
        return new MyPojoMetaMethodSite(site, metaClass, metaMethod, params);
    }
    
    static {
        OBJECT_CLASS = ReflectionCache.getCachedClass(Object.class);
        OBJECT_ARR_CLASS = ReflectionCache.OBJECT_ARRAY_CLASS;
        PARAM_CLASS_ARR = new CachedClass[] { ObjectArrayPutAtMetaMethod.INTEGER_CLASS, ObjectArrayPutAtMetaMethod.OBJECT_CLASS };
    }
    
    private static class MyPojoMetaMethodSite extends PojoMetaMethodSite
    {
        public MyPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public Object call(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
            if (this.checkPojoMetaClass()) {
                try {
                    final Object[] objects = (Object[])receiver;
                    objects[ArrayMetaMethod.normaliseIndex((int)arg1, objects.length)] = adjustNewValue(objects, arg2);
                    return null;
                }
                catch (ClassCastException e) {
                    if (receiver instanceof Object[] && arg1 instanceof Integer) {
                        throw e;
                    }
                }
            }
            return super.call(receiver, arg1, arg2);
        }
    }
}
