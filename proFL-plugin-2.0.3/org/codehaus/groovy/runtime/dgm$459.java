// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$459 extends GeneratedMetaMethod
{
    public dgm$459(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.or((Number)o, (Number)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.or((Number)o, (Number)this.getParameterTypes()[0].coerceArgument(array[0]));
    }
    
    public boolean isValidMethod(final Class[] array) {
        return array == null || this.getParameterTypes()[0].isAssignableFrom(array[0]);
    }
}
