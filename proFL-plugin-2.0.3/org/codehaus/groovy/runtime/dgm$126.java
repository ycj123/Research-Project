// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import groovy.io.FileType;
import java.io.File;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$126 extends GeneratedMetaMethod
{
    public dgm$126(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DefaultGroovyMethods.eachFileMatch((File)o, (FileType)array[0], array[1], (Closure)array[2]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.eachFileMatch((File)o, (FileType)coerceArgumentsToClasses[0], coerceArgumentsToClasses[1], (Closure)coerceArgumentsToClasses[2]);
        return null;
    }
}
