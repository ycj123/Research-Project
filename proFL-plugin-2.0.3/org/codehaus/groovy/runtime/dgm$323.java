// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$323 extends GeneratedMetaMethod
{
    public dgm$323(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object object, final Object[] array) {
        return DefaultGroovyMethods.invokeMethod(object, (String)array[0], array[1]);
    }
    
    public final Object doMethodInvoke(final Object object, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.invokeMethod(object, (String)coerceArgumentsToClasses[0], coerceArgumentsToClasses[1]);
    }
}
