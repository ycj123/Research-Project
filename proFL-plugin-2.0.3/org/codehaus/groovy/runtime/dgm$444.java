// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.InputStream;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$444 extends GeneratedMetaMethod
{
    public dgm$444(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultGroovyMethods.newReader((InputStream)o);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultGroovyMethods.newReader((InputStream)o);
    }
}
