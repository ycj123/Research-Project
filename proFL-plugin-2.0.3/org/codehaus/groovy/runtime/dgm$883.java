// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$883 extends GeneratedMetaMethod
{
    public dgm$883(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        ProcessGroovyMethods.waitForProcessOutput((Process)o, (Appendable)array[0], (Appendable)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        ProcessGroovyMethods.waitForProcessOutput((Process)o, (Appendable)coerceArgumentsToClasses[0], (Appendable)coerceArgumentsToClasses[1]);
        return null;
    }
}
