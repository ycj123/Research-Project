// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.Log;

public class ClassMap
{
    private static final boolean debugReflection = false;
    private final Log log;
    private final Class clazz;
    private final MethodCache methodCache;
    
    public ClassMap(final Class clazz, final Log log) {
        this.clazz = clazz;
        this.log = log;
        this.methodCache = new MethodCache(log);
        this.populateMethodCache();
    }
    
    public Class getCachedClass() {
        return this.clazz;
    }
    
    public Method findMethod(final String name, final Object[] params) throws MethodMap.AmbiguousException {
        return this.methodCache.get(name, params);
    }
    
    private void populateMethodCache() {
        final List classesToReflect = new ArrayList();
        for (Class classToReflect = this.getCachedClass(); classToReflect != null; classToReflect = classToReflect.getSuperclass()) {
            if (Modifier.isPublic(classToReflect.getModifiers())) {
                classesToReflect.add(classToReflect);
            }
            final Class[] interfaces = classToReflect.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (Modifier.isPublic(interfaces[i].getModifiers())) {
                    classesToReflect.add(interfaces[i]);
                }
            }
        }
        for (final Class classToReflect2 : classesToReflect) {
            try {
                final Method[] methods = classToReflect2.getMethods();
                for (int j = 0; j < methods.length; ++j) {
                    final int modifiers = methods[j].getModifiers();
                    if (Modifier.isPublic(modifiers) && (classToReflect2.isInterface() || !Modifier.isAbstract(modifiers))) {
                        this.methodCache.put(methods[j]);
                    }
                }
            }
            catch (SecurityException se) {
                if (!this.log.isDebugEnabled()) {
                    continue;
                }
                this.log.debug("While accessing methods of " + classToReflect2 + ": ", se);
            }
        }
    }
    
    private static final class MethodCache
    {
        private static final CacheMiss CACHE_MISS;
        private static final Object OBJECT;
        private static final Map convertPrimitives;
        private final Log log;
        private final Map cache;
        private final MethodMap methodMap;
        
        private MethodCache(final Log log) {
            this.cache = new HashMap();
            this.methodMap = new MethodMap();
            this.log = log;
        }
        
        public synchronized Method get(final String name, final Object[] params) throws MethodMap.AmbiguousException {
            final String methodKey = this.makeMethodKey(name, params);
            Object cacheEntry = this.cache.get(methodKey);
            if (cacheEntry == MethodCache.CACHE_MISS) {
                return null;
            }
            if (cacheEntry == null) {
                try {
                    cacheEntry = this.methodMap.find(name, params);
                }
                catch (MethodMap.AmbiguousException ae) {
                    this.cache.put(methodKey, MethodCache.CACHE_MISS);
                    throw ae;
                }
                this.cache.put(methodKey, (cacheEntry != null) ? cacheEntry : MethodCache.CACHE_MISS);
            }
            return (Method)cacheEntry;
        }
        
        public synchronized void put(final Method method) {
            final String methodKey = this.makeMethodKey(method);
            if (this.cache.get(methodKey) == null) {
                this.cache.put(methodKey, method);
                this.methodMap.add(method);
            }
        }
        
        private String makeMethodKey(final Method method) {
            final Class[] parameterTypes = method.getParameterTypes();
            final StringBuffer methodKey = new StringBuffer(method.getName());
            for (int j = 0; j < parameterTypes.length; ++j) {
                if (parameterTypes[j].isPrimitive()) {
                    methodKey.append(MethodCache.convertPrimitives.get(parameterTypes[j]));
                }
                else {
                    methodKey.append(parameterTypes[j].getName());
                }
            }
            return methodKey.toString();
        }
        
        private String makeMethodKey(final String method, final Object[] params) {
            final StringBuffer methodKey = new StringBuffer().append(method);
            for (int j = 0; j < params.length; ++j) {
                Object arg = params[j];
                if (arg == null) {
                    arg = MethodCache.OBJECT;
                }
                methodKey.append(arg.getClass().getName());
            }
            return methodKey.toString();
        }
        
        static {
            CACHE_MISS = new CacheMiss();
            OBJECT = new Object();
            (convertPrimitives = new HashMap()).put(Boolean.TYPE, Boolean.class.getName());
            MethodCache.convertPrimitives.put(Byte.TYPE, Byte.class.getName());
            MethodCache.convertPrimitives.put(Character.TYPE, Character.class.getName());
            MethodCache.convertPrimitives.put(Double.TYPE, Double.class.getName());
            MethodCache.convertPrimitives.put(Float.TYPE, Float.class.getName());
            MethodCache.convertPrimitives.put(Integer.TYPE, Integer.class.getName());
            MethodCache.convertPrimitives.put(Long.TYPE, Long.class.getName());
            MethodCache.convertPrimitives.put(Short.TYPE, Short.class.getName());
        }
        
        private static final class CacheMiss
        {
        }
    }
}
