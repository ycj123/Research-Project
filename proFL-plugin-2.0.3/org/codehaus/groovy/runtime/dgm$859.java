// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Date;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$859 extends GeneratedMetaMethod
{
    public dgm$859(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DateGroovyMethods.putAt((Date)o, DefaultTypeTransformation.intUnbox(array[0]), DefaultTypeTransformation.intUnbox(array[1]));
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DateGroovyMethods.putAt((Date)o, DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[0]), DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[1]));
        return null;
    }
}
