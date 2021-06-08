// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import groovy.lang.Closure;
import groovy.lang.ClosureInvokingMethod;
import groovy.lang.MetaMethod;

public class ClosureStaticMetaMethod extends MetaMethod implements ClosureInvokingMethod
{
    private final Closure callable;
    private final CachedClass declaringClass;
    private final String name;
    
    public ClosureStaticMetaMethod(final String name, final Class declaringClass, final Closure c) {
        this(name, declaringClass, c, c.getParameterTypes());
    }
    
    public ClosureStaticMetaMethod(final String name, final Class declaringClass, final Closure c, final Class[] paramTypes) {
        super(paramTypes);
        this.callable = c;
        this.declaringClass = ReflectionCache.getCachedClass(declaringClass);
        this.name = name;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        final Closure cloned = (Closure)this.callable.clone();
        cloned.setDelegate(object);
        return cloned.call(arguments);
    }
    
    @Override
    public int getModifiers() {
        return 9;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Class getReturnType() {
        return Object.class;
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.declaringClass;
    }
    
    public Closure getClosure() {
        return this.callable;
    }
}
