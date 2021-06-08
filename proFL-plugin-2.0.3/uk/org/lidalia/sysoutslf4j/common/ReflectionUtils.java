// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Method;

public final class ReflectionUtils
{
    public static Object invokeMethod(final String methodName, final Object target) {
        final Method method = getMethod(methodName, target.getClass(), (Class<?>[])new Class[0]);
        return invokeMethod(method, target, new Object[0]);
    }
    
    public static Object invokeMethod(final String methodName, final Object target, final Class<?> argType, final Object arg) {
        final Method method = getMethod(methodName, target.getClass(), argType);
        return invokeMethod(method, target, arg);
    }
    
    public static Object invokeStaticMethod(final String methodName, final Class<?> targetClass) {
        final Method method = getMethod(methodName, targetClass, (Class<?>[])new Class[0]);
        return invokeMethod(method, targetClass, new Object[0]);
    }
    
    public static Object invokeStaticMethod(final String methodName, final Class<?> targetClass, final Class<?> argType, final Object arg) {
        final Method method = getMethod(methodName, targetClass, argType);
        return invokeMethod(method, targetClass, arg);
    }
    
    private static Method getMethod(final String methodName, final Class<?> classWithMethod, final Class<?>... argTypes) {
        try {
            return getMethod(methodName, classWithMethod, argTypes, null);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            throw new WrappedCheckedException(noSuchMethodException);
        }
    }
    
    private static Method getMethod(final String methodName, final Class<?> classWithMethod, final Class<?>[] argTypes, final NoSuchMethodException originalNoSuchMethodException) throws NoSuchMethodException {
        Method foundMethod;
        try {
            foundMethod = classWithMethod.getDeclaredMethod(methodName, argTypes);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            final Class<?> superclass = classWithMethod.getSuperclass();
            if (superclass == null) {
                throw originalNoSuchMethodException;
            }
            final NoSuchMethodException firstNoSuchMethodException = getDefault(originalNoSuchMethodException, noSuchMethodException);
            foundMethod = getMethod(methodName, superclass, argTypes, firstNoSuchMethodException);
        }
        return foundMethod;
    }
    
    private static <T> T getDefault(final T mightBeNull, final T useIfNull) {
        T result;
        if (mightBeNull == null) {
            result = useIfNull;
        }
        else {
            result = mightBeNull;
        }
        return result;
    }
    
    public static Object invokeMethod(final Method method, final Object target, final Object... args) {
        try {
            if (!method.isAccessible()) {
                AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        method.setAccessible(true);
                        return null;
                    }
                });
            }
            return method.invoke(target, args);
        }
        catch (Exception exception) {
            throw ExceptionUtils.asRuntimeException(exception);
        }
    }
    
    private ReflectionUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }
    
    public static <TypeInThisClassLoader> TypeInThisClassLoader wrap(final Object target, final Class<TypeInThisClassLoader> interfaceClass) {
        TypeInThisClassLoader result;
        if (interfaceClass.isAssignableFrom(target.getClass())) {
            result = (TypeInThisClassLoader)target;
        }
        else {
            result = (TypeInThisClassLoader)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { interfaceClass }, new ProxyingInvocationHandler(target, interfaceClass));
        }
        return result;
    }
}
