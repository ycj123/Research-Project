// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$490 extends GeneratedMetaMethod
{
    public dgm$490(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object self, final Object[] array) {
        DefaultGroovyMethods.printf(self, (String)array[0], (Object[])array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object self, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.printf(self, (String)coerceArgumentsToClasses[0], (Object[])coerceArgumentsToClasses[1]);
        return null;
    }
}
