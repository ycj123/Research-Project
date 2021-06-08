// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.OutputStream;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$882 extends GeneratedMetaMethod
{
    public dgm$882(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        ProcessGroovyMethods.waitForProcessOutput((Process)o, (OutputStream)array[0], (OutputStream)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        ProcessGroovyMethods.waitForProcessOutput((Process)o, (OutputStream)coerceArgumentsToClasses[0], (OutputStream)coerceArgumentsToClasses[1]);
        return null;
    }
}
