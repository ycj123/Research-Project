// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.io.Writer;
import java.net.URL;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$184 extends GeneratedMetaMethod
{
    public dgm$184(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DefaultGroovyMethods.filterLine((URL)o, (Writer)array[0], (String)array[1], (Closure)array[2]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.filterLine((URL)o, (Writer)coerceArgumentsToClasses[0], (String)coerceArgumentsToClasses[1], (Closure)coerceArgumentsToClasses[2]);
        return null;
    }
}
