// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.lang.reflect.Method;

public class ReflectionMethodInvoker
{
    public static Object invoke(final Object object, final String methodName, final Object[] parameters) {
        try {
            final Class[] classTypes = new Class[parameters.length];
            for (int i = 0; i < classTypes.length; ++i) {
                classTypes[i] = parameters[i].getClass();
            }
            final Method method = object.getClass().getMethod(methodName, (Class<?>[])classTypes);
            return method.invoke(object, parameters);
        }
        catch (Throwable t) {
            return InvokerHelper.invokeMethod(object, methodName, parameters);
        }
    }
}
