// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.io.File;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$747 extends GeneratedMetaMethod
{
    public dgm$747(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.withWriter((File)o, (String)array[0], (Closure)array[1]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.withWriter((File)o, (String)coerceArgumentsToClasses[0], (Closure)coerceArgumentsToClasses[1]);
    }
}