// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$478 extends GeneratedMetaMethod
{
    public dgm$478(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.power((Long)o, (Integer)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.power((Long)o, (Integer)this.getParameterTypes()[0].coerceArgument(array[0]));
    }
    
    public boolean isValidMethod(final Class[] array) {
        return array == null || this.getParameterTypes()[0].isAssignableFrom(array[0]);
    }
}
