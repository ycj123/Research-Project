// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.EmptyRange;
import java.util.List;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$280 extends GeneratedMetaMethod
{
    public dgm$280(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.getAt((List<Object>)o, (EmptyRange)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.getAt((List<Object>)o, (EmptyRange)coerceArgumentsToClasses[0]);
    }
}
