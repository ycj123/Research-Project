// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class ExceptionUtils
{
    private static boolean causesAllowed;
    
    public static RuntimeException createRuntimeException(final String message, final Throwable cause) {
        return (RuntimeException)createWithCause(RuntimeException.class, message, cause);
    }
    
    public static Throwable createWithCause(final Class clazz, final String message, final Throwable cause) {
        Throwable re = null;
        if (ExceptionUtils.causesAllowed) {
            try {
                final Constructor constructor = clazz.getConstructor(String.class, Throwable.class);
                re = constructor.newInstance(message, cause);
            }
            catch (RuntimeException e) {
                throw e;
            }
            catch (Exception e2) {
                ExceptionUtils.causesAllowed = false;
            }
        }
        if (re == null) {
            try {
                final Constructor constructor = clazz.getConstructor(String.class);
                re = constructor.newInstance(message + " caused by " + cause);
            }
            catch (RuntimeException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new RuntimeException("Error caused " + e2);
            }
        }
        return re;
    }
    
    public static void setCause(final Throwable onObject, final Throwable cause) {
        if (ExceptionUtils.causesAllowed) {
            try {
                final Method method = onObject.getClass().getMethod("initCause", Throwable.class);
                method.invoke(onObject, cause);
            }
            catch (RuntimeException e) {
                throw e;
            }
            catch (Exception e2) {
                ExceptionUtils.causesAllowed = false;
            }
        }
    }
    
    static {
        ExceptionUtils.causesAllowed = true;
    }
}
