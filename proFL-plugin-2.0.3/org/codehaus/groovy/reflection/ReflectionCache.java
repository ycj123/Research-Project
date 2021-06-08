// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import java.util.HashMap;
import org.codehaus.groovy.util.TripleKeyHashMap;
import java.util.Map;

public class ReflectionCache
{
    private static Map primitiveTypesMap;
    static TripleKeyHashMap mopNames;
    static final CachedClass STRING_CLASS;
    public static final CachedClass OBJECT_CLASS;
    public static final CachedClass OBJECT_ARRAY_CLASS;
    
    public static Class autoboxType(final Class type) {
        final Class res = ReflectionCache.primitiveTypesMap.get(type);
        return (res == null) ? type : res;
    }
    
    public static String getMOPMethodName(final CachedClass declaringClass, final String name, final boolean useThis) {
        final TripleKeyHashMap.Entry mopNameEntry = ReflectionCache.mopNames.getOrPut(declaringClass, name, useThis);
        if (mopNameEntry.value == null) {
            mopNameEntry.value = new StringBuffer().append(useThis ? "this$" : "super$").append(declaringClass.getSuperClassDistance()).append("$").append(name).toString();
        }
        return (String)mopNameEntry.value;
    }
    
    public static boolean isArray(final Class klazz) {
        return klazz.getName().charAt(0) == '[';
    }
    
    static void setAssignableFrom(final Class klazz, final Class aClass) {
    }
    
    public static boolean isAssignableFrom(final Class klazz, final Class aClass) {
        return klazz == aClass || klazz.isAssignableFrom(aClass);
    }
    
    static boolean arrayContentsEq(final Object[] a1, final Object[] a2) {
        if (a1 == null) {
            return a2 == null || a2.length == 0;
        }
        if (a2 == null) {
            return a1.length == 0;
        }
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; ++i) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static CachedClass getCachedClass(final Class klazz) {
        if (klazz == null) {
            return null;
        }
        return ClassInfo.getClassInfo(klazz).getCachedClass();
    }
    
    static {
        (ReflectionCache.primitiveTypesMap = new HashMap()).put(Byte.TYPE, Byte.class);
        ReflectionCache.primitiveTypesMap.put(Boolean.TYPE, Boolean.class);
        ReflectionCache.primitiveTypesMap.put(Character.TYPE, Character.class);
        ReflectionCache.primitiveTypesMap.put(Double.TYPE, Double.class);
        ReflectionCache.primitiveTypesMap.put(Float.TYPE, Float.class);
        ReflectionCache.primitiveTypesMap.put(Integer.TYPE, Integer.class);
        ReflectionCache.primitiveTypesMap.put(Long.TYPE, Long.class);
        ReflectionCache.primitiveTypesMap.put(Short.TYPE, Short.class);
        ReflectionCache.mopNames = new TripleKeyHashMap();
        STRING_CLASS = getCachedClass(String.class);
        OBJECT_CLASS = getCachedClass(Object.class);
        OBJECT_ARRAY_CLASS = getCachedClass(Object[].class);
    }
}
