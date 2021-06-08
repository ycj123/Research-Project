// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionUtils
{
    private static final Class[] NO_ARGS;
    private static final Object[] NO_ARGS_VALUES;
    
    public static Method getMethod(final Object instance, final String methodName, final Class[] parameters) {
        return getMethod(instance.getClass(), methodName, parameters);
    }
    
    public static Method getMethod(final Class clazz, final String methodName, final Class[] parameters) {
        try {
            return clazz.getMethod(methodName, (Class[])parameters);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException("When finding method " + methodName, e);
        }
    }
    
    public static Method tryGetMethod(final Class clazz, final String methodName, final Class[] parameters) {
        try {
            return clazz.getMethod(methodName, (Class[])parameters);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    public static Object invokeGetter(final Object instance, final String methodName) {
        final Method method = getMethod(instance, methodName, ReflectionUtils.NO_ARGS);
        return invokeMethodWithArray(instance, method, ReflectionUtils.NO_ARGS_VALUES);
    }
    
    public static Constructor getConstructor(final Class clazz, final Class[] arguments) {
        try {
            return clazz.getConstructor((Class[])arguments);
        }
        catch (NoSuchMethodException e) {
            throw new SurefireReflectionException(e);
        }
    }
    
    public static Object newInstance(final Constructor constructor, final Object[] params) {
        try {
            return constructor.newInstance(params);
        }
        catch (InvocationTargetException e) {
            throw new SurefireReflectionException(e);
        }
        catch (InstantiationException e2) {
            throw new SurefireReflectionException(e2);
        }
        catch (IllegalAccessException e3) {
            throw new SurefireReflectionException(e3);
        }
    }
    
    public static Object instantiate(final ClassLoader classLoader, final String classname) {
        try {
            final Class clazz = loadClass(classLoader, classname);
            return clazz.newInstance();
        }
        catch (InstantiationException e) {
            throw new SurefireReflectionException(e);
        }
        catch (IllegalAccessException e2) {
            throw new SurefireReflectionException(e2);
        }
    }
    
    public static Object instantiateOneArg(final ClassLoader classLoader, final String className, final Class param1Class, final Object param1) {
        try {
            final Class aClass = loadClass(classLoader, className);
            final Constructor constructor = getConstructor(aClass, new Class[] { param1Class });
            return constructor.newInstance(param1);
        }
        catch (InvocationTargetException e) {
            throw new SurefireReflectionException(e.getTargetException());
        }
        catch (InstantiationException e2) {
            throw new SurefireReflectionException(e2);
        }
        catch (IllegalAccessException e3) {
            throw new SurefireReflectionException(e3);
        }
    }
    
    public static Object instantiateTwoArgs(final ClassLoader classLoader, final String className, final Class param1Class, final Object param1, final Class param2Class, final Object param2) {
        try {
            final Class aClass = loadClass(classLoader, className);
            final Constructor constructor = getConstructor(aClass, new Class[] { param1Class, param2Class });
            return constructor.newInstance(param1, param2);
        }
        catch (InvocationTargetException e) {
            throw new SurefireReflectionException(e.getTargetException());
        }
        catch (InstantiationException e2) {
            throw new SurefireReflectionException(e2);
        }
        catch (IllegalAccessException e3) {
            throw new SurefireReflectionException(e3);
        }
    }
    
    public static void invokeSetter(final Object o, final String name, final Class value1clazz, final Object value) {
        final Method setter = getMethod(o, name, new Class[] { value1clazz });
        invokeSetter(o, setter, value);
    }
    
    public static Object invokeSetter(final Object target, final Method method, final Object value) {
        return invokeMethodWithArray(target, method, new Object[] { value });
    }
    
    public static Object invokeMethodWithArray(final Object target, final Method method, final Object[] args) {
        try {
            return method.invoke(target, args);
        }
        catch (IllegalAccessException e) {
            throw new SurefireReflectionException(e);
        }
        catch (InvocationTargetException e2) {
            throw new SurefireReflectionException(e2.getTargetException());
        }
    }
    
    public static Object invokeMethodWithArray2(final Object target, final Method method, final Object[] args) throws InvocationTargetException {
        try {
            return method.invoke(target, args);
        }
        catch (IllegalAccessException e) {
            throw new SurefireReflectionException(e);
        }
    }
    
    public static Object instantiateObject(final String className, final Class[] types, final Object[] params, final ClassLoader classLoader) {
        final Class clazz = loadClass(classLoader, className);
        final Constructor constructor = getConstructor(clazz, types);
        return newInstance(constructor, params);
    }
    
    public static Class tryLoadClass(final ClassLoader classLoader, final String className) {
        try {
            return classLoader.loadClass(className);
        }
        catch (NoClassDefFoundError ignore) {}
        catch (ClassNotFoundException ex) {}
        return null;
    }
    
    public static Class loadClass(final ClassLoader classLoader, final String className) {
        try {
            return classLoader.loadClass(className);
        }
        catch (NoClassDefFoundError e) {
            throw new SurefireReflectionException(e);
        }
        catch (ClassNotFoundException e2) {
            throw new SurefireReflectionException(e2);
        }
    }
    
    static {
        NO_ARGS = new Class[0];
        NO_ARGS_VALUES = new Object[0];
    }
}
