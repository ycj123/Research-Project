// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.ObjectRange;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$259 extends GeneratedMetaMethod
{
    public dgm$259(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.getAt((int[])o, (ObjectRange)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.getAt((int[])o, (ObjectRange)coerceArgumentsToClasses[0]);
    }
}