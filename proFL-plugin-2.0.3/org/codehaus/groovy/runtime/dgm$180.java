// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.io.Writer;
import java.io.Reader;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$180 extends GeneratedMetaMethod
{
    public dgm$180(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DefaultGroovyMethods.filterLine((Reader)o, (Writer)array[0], (Closure)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.filterLine((Reader)o, (Writer)coerceArgumentsToClasses[0], (Closure)coerceArgumentsToClasses[1]);
        return null;
    }
}
