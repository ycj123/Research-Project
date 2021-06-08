// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.CachedMethod;
import groovy.lang.MetaMethod;

public class ReflectionMetaMethod extends MetaMethod
{
    protected final CachedMethod method;
    
    public ReflectionMetaMethod(final CachedMethod method) {
        this.method = method;
        this.setParametersTypes(method.getParameterTypes());
    }
    
    @Override
    public int getModifiers() {
        return this.method.getModifiers();
    }
    
    @Override
    public String getName() {
        return this.method.getName();
    }
    
    @Override
    public Class getReturnType() {
        return this.method.getReturnType();
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.method.cachedClass;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        try {
            return this.method.setAccessible().invoke(object, arguments);
        }
        catch (IllegalArgumentException e) {
            throw new InvokerInvocationException(e);
        }
        catch (IllegalAccessException e2) {
            throw new InvokerInvocationException(e2);
        }
        catch (InvocationTargetException e3) {
            throw (e3.getCause() instanceof RuntimeException) ? ((RuntimeException)e3.getCause()) : new InvokerInvocationException(e3);
        }
    }
    
    @Override
    public String toString() {
        return this.method.toString();
    }
    
    @Override
    protected Class[] getPT() {
        return this.method.getNativeParameterTypes();
    }
}
