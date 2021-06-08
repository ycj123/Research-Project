// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.PrintWriter;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$488 extends GeneratedMetaMethod
{
    public dgm$488(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object self, final Object[] array) {
        DefaultGroovyMethods.print(self, (PrintWriter)array[0]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object self, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.print(self, (PrintWriter)coerceArgumentsToClasses[0]);
        return null;
    }
}
