// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import javax.swing.JTabbedPane;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$762 extends GeneratedMetaMethod
{
    public dgm$762(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        SwingGroovyMethods.clear((JTabbedPane)o);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        SwingGroovyMethods.clear((JTabbedPane)o);
        return null;
    }
}
