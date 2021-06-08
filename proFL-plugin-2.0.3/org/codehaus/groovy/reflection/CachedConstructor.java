// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Constructor;

public class CachedConstructor extends ParameterTypes
{
    CachedClass clazz;
    public final Constructor cachedConstructor;
    
    public CachedConstructor(final CachedClass clazz, final Constructor c) {
        this.cachedConstructor = c;
        this.clazz = clazz;
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    c.setAccessible(true);
                    return null;
                }
            });
        }
        catch (SecurityException ex) {}
    }
    
    public CachedConstructor(final Constructor c) {
        this(ReflectionCache.getCachedClass(c.getDeclaringClass()), c);
    }
    
    @Override
    protected Class[] getPT() {
        return this.cachedConstructor.getParameterTypes();
    }
    
    public static CachedConstructor find(final Constructor constructor) {
        final CachedConstructor[] constructors = ReflectionCache.getCachedClass(constructor.getDeclaringClass()).getConstructors();
        for (int i = 0; i < constructors.length; ++i) {
            final CachedConstructor cachedConstructor = constructors[i];
            if (cachedConstructor.cachedConstructor.equals(constructor)) {
                return cachedConstructor;
            }
        }
        throw new RuntimeException("Couldn't find method: " + constructor);
    }
    
    public Object doConstructorInvoke(Object[] argumentArray) {
        argumentArray = this.coerceArgumentsToClasses(argumentArray);
        return this.invoke(argumentArray);
    }
    
    public Object invoke(final Object[] argumentArray) {
        final Constructor constr = this.cachedConstructor;
        try {
            return constr.newInstance(argumentArray);
        }
        catch (InvocationTargetException e) {
            throw (e.getCause() instanceof RuntimeException) ? ((RuntimeException)e.getCause()) : new InvokerInvocationException(e);
        }
        catch (IllegalArgumentException e2) {
            throw createExceptionText("failed to invoke constructor: ", constr, argumentArray, e2, false);
        }
        catch (IllegalAccessException e3) {
            throw createExceptionText("could not access constructor: ", constr, argumentArray, e3, false);
        }
        catch (Exception e4) {
            if (e4 instanceof RuntimeException) {
                throw (RuntimeException)e4;
            }
            throw createExceptionText("failed to invoke constructor: ", constr, argumentArray, e4, true);
        }
    }
    
    private static GroovyRuntimeException createExceptionText(final String init, final Constructor constructor, final Object[] argumentArray, final Throwable e, final boolean setReason) {
        throw new GroovyRuntimeException(init + constructor + " with arguments: " + InvokerHelper.toString(argumentArray) + " reason: " + e, setReason ? e : null);
    }
    
    public int getModifiers() {
        return this.cachedConstructor.getModifiers();
    }
}
