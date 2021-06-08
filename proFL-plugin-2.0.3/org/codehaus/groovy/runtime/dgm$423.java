// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.math.BigDecimal;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$423 extends GeneratedMetaMethod
{
    public dgm$423(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.multiply((BigDecimal)o, (Double)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.multiply((BigDecimal)o, (Double)this.getParameterTypes()[0].coerceArgument(array[0]));
    }
    
    public boolean isValidMethod(final Class[] array) {
        return array == null || this.getParameterTypes()[0].isAssignableFrom(array[0]);
    }
}
