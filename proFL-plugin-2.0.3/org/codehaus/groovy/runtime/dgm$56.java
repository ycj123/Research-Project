// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$56 extends GeneratedMetaMethod
{
    public dgm$56(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object obj, final Object[] array) {
        return DefaultGroovyMethods.asType(obj, (Class)array[0]);
    }
    
    public final Object doMethodInvoke(final Object obj, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.asType(obj, (Class)coerceArgumentsToClasses[0]);
    }
}
