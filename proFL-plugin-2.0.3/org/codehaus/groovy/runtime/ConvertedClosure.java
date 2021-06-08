// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.lang.reflect.Method;
import groovy.lang.Closure;
import java.io.Serializable;

public class ConvertedClosure extends ConversionHandler implements Serializable
{
    private String methodName;
    private static final long serialVersionUID = 1162833713450835227L;
    
    public ConvertedClosure(final Closure closure, final String method) {
        super(closure);
        this.methodName = method;
    }
    
    public ConvertedClosure(final Closure closure) {
        this(closure, null);
    }
    
    @Override
    public Object invokeCustom(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (this.methodName != null && !this.methodName.equals(method.getName())) {
            return null;
        }
        return ((Closure)this.getDelegate()).call(args);
    }
}
