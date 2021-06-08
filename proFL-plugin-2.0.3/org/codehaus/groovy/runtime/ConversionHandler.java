// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationTargetException;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.Method;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;

public abstract class ConversionHandler implements InvocationHandler, Serializable
{
    private Object delegate;
    private static final long serialVersionUID = 1162833717190835227L;
    
    public ConversionHandler(final Object delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate must not be null");
        }
        this.delegate = delegate;
    }
    
    public Object getDelegate() {
        return this.delegate;
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (!this.checkMethod(method)) {
            try {
                return this.invokeCustom(proxy, method, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        try {
            return method.invoke(this, args);
        }
        catch (InvocationTargetException ite) {
            throw ite.getTargetException();
        }
    }
    
    protected boolean checkMethod(final Method method) {
        return isCoreObjectMethod(method);
    }
    
    public abstract Object invokeCustom(final Object p0, final Method p1, final Object[] p2) throws Throwable;
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Proxy) {
            obj = Proxy.getInvocationHandler(obj);
        }
        return obj instanceof ConversionHandler && ((ConversionHandler)obj).getDelegate().equals(this.delegate);
    }
    
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }
    
    @Override
    public String toString() {
        return this.delegate.toString();
    }
    
    public static boolean isCoreObjectMethod(final Method method) {
        return Object.class.equals(method.getDeclaringClass());
    }
}
