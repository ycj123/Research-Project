// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$880 extends GeneratedMetaMethod
{
    public dgm$880(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        ProcessGroovyMethods.waitForOrKill((Process)o, DefaultTypeTransformation.longUnbox(array[0]));
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        ProcessGroovyMethods.waitForOrKill((Process)o, DefaultTypeTransformation.longUnbox(coerceArgumentsToClasses[0]));
        return null;
    }
}
