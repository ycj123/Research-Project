// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Map;
import java.lang.reflect.InvocationHandler;

public class ProxyingInvocationHandler implements InvocationHandler
{
    private final Object target;
    private final Map<Method, Method> methods;
    
    ProxyingInvocationHandler(final Object target, final Class<?> interfaceClass) {
        this.methods = new HashMap<Method, Method>();
        this.target = target;
        Method[] methods;
        for (int length = (methods = interfaceClass.getMethods()).length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            try {
                final Method methodOnTarget = target.getClass().getMethod(method.getName(), method.getParameterTypes());
                this.methods.put(method, methodOnTarget);
            }
            catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Target " + target + " does not have methods to match all method signatures on class " + interfaceClass, e);
            }
        }
    }
    
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Method methodOnTarget = this.methods.get(method);
        return methodOnTarget.invoke(this.target, args);
    }
    
    public Object getTarget() {
        return this.target;
    }
}
