// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Collection;
import java.util.List;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$409 extends GeneratedMetaMethod
{
    public dgm$409(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.minus((List<Object>)o, (Collection<Object>)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.minus((List<Object>)o, (Collection<Object>)coerceArgumentsToClasses[0]);
    }
}
