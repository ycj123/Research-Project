// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$705 extends GeneratedMetaMethod
{
    public dgm$705(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object value, final Object[] array) {
        DefaultGroovyMethods.upto(DefaultTypeTransformation.doubleUnbox(value), (Number)array[0], (Closure)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object value, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.upto(DefaultTypeTransformation.doubleUnbox(value), (Number)coerceArgumentsToClasses[0], (Closure)coerceArgumentsToClasses[1]);
        return null;
    }
}
