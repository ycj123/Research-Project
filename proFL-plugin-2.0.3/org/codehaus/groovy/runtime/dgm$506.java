// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.BitSet;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$506 extends GeneratedMetaMethod
{
    public dgm$506(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DefaultGroovyMethods.putAt((BitSet)o, DefaultTypeTransformation.intUnbox(array[0]), DefaultTypeTransformation.booleanUnbox(array[1]));
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.putAt((BitSet)o, DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[0]), DefaultTypeTransformation.booleanUnbox(coerceArgumentsToClasses[1]));
        return null;
    }
}
