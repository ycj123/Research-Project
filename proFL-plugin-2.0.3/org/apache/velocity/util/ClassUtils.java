// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

import java.io.InputStream;

public class ClassUtils
{
    private ClassUtils() {
    }
    
    public static Class getClass(final String clazz) throws ClassNotFoundException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader != null) {
            try {
                return Class.forName(clazz, true, loader);
            }
            catch (ClassNotFoundException ex) {}
        }
        return Class.forName(clazz);
    }
    
    public static Object getNewInstance(final String clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return getClass(clazz).newInstance();
    }
    
    public static InputStream getResourceAsStream(final Class claz, String name) {
        InputStream result = null;
        while (name.startsWith("/")) {
            name = name.substring(1);
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = claz.getClassLoader();
            result = classLoader.getResourceAsStream(name);
        }
        else {
            result = classLoader.getResourceAsStream(name);
            if (result == null) {
                classLoader = claz.getClassLoader();
                if (classLoader != null) {
                    result = classLoader.getResourceAsStream(name);
                }
            }
        }
        return result;
    }
}
