// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.File;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$555 extends GeneratedMetaMethod
{
    public dgm$555(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        DefaultGroovyMethods.setText((File)o, (String)array[0], (String)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        DefaultGroovyMethods.setText((File)o, (String)coerceArgumentsToClasses[0], (String)coerceArgumentsToClasses[1]);
        return null;
    }
}