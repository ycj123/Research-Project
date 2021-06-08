// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.MissingMethodException;
import org.codehaus.groovy.reflection.CachedMethod;

public class Reflector
{
    public Object invoke(final CachedMethod method, final Object object, final Object[] arguments) {
        return this.noSuchMethod(method, object, arguments);
    }
    
    protected Object noSuchMethod(final CachedMethod method, final Object object, final Object[] arguments) {
        throw new MissingMethodException(method.getName(), method.getDeclaringClass().getTheClass(), arguments, false);
    }
}
