// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$799 extends GeneratedMetaMethod
{
    public dgm$799(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return SwingGroovyMethods.leftShift((JMenu)o, (JMenuItem)array[0]);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return SwingGroovyMethods.leftShift((JMenu)o, (JMenuItem)coerceArgumentsToClasses[0]);
    }
}
