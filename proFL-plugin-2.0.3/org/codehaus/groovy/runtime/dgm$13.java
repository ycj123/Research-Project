// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$13 extends GeneratedMetaMethod
{
    public dgm$13(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object self, final Object[] array) {
        return DefaultTypeTransformation.box(DefaultGroovyMethods.any(self, (Closure)array[0]));
    }
    
    public final Object doMethodInvoke(final Object self, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultTypeTransformation.box(DefaultGroovyMethods.any(self, (Closure)coerceArgumentsToClasses[0]));
    }
}
