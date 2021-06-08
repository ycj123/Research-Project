// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.JMenuBar;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$821 extends GeneratedMetaMethod
{
    public dgm$821(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return DefaultTypeTransformation.box(SwingGroovyMethods.size((JMenuBar)o));
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return DefaultTypeTransformation.box(SwingGroovyMethods.size((JMenuBar)o));
    }
}
