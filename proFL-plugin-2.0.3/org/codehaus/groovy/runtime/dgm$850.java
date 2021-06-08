// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.sql.Date;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$850 extends GeneratedMetaMethod
{
    public dgm$850(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DateGroovyMethods.minus((Date)o, DefaultTypeTransformation.intUnbox(array[0]));
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DateGroovyMethods.minus((Date)o, DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[0]));
    }
}
