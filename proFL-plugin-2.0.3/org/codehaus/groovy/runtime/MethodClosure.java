// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Iterator;
import java.util.List;
import groovy.lang.MetaMethod;
import groovy.lang.Closure;

public class MethodClosure extends Closure
{
    private String method;
    
    public MethodClosure(final Object owner, final String method) {
        super(owner);
        this.method = method;
        final Class clazz = (Class)((owner.getClass() == Class.class) ? owner : owner.getClass());
        this.maximumNumberOfParameters = 0;
        this.parameterTypes = new Class[0];
        final List<MetaMethod> methods = InvokerHelper.getMetaClass(clazz).respondsTo(owner, method);
        for (final MetaMethod m : methods) {
            if (m.getParameterTypes().length > this.maximumNumberOfParameters) {
                final Class[] pt = m.getNativeParameterTypes();
                this.maximumNumberOfParameters = pt.length;
                this.parameterTypes = pt;
            }
        }
    }
    
    public String getMethod() {
        return this.method;
    }
    
    protected Object doCall(final Object arguments) {
        return InvokerHelper.invokeMethod(this.getOwner(), this.method, arguments);
    }
    
    @Override
    public Object getProperty(final String property) {
        if ("method".equals(property)) {
            return this.getMethod();
        }
        return super.getProperty(property);
    }
}
