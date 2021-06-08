// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$331 extends GeneratedMetaMethod
{
    public dgm$331(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object caseValue, final Object[] array) {
        return DefaultTypeTransformation.box(DefaultGroovyMethods.isCase(caseValue, array[0]));
    }
    
    public final Object doMethodInvoke(final Object caseValue, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultTypeTransformation.box(DefaultGroovyMethods.isCase(caseValue, coerceArgumentsToClasses[0]));
    }
}
