// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.OutputStream;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$364 extends GeneratedMetaMethod
{
    public dgm$364(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.leftShift((OutputStream)o, (byte[])array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.leftShift((OutputStream)o, (byte[])coerceArgumentsToClasses[0]);
    }
}
