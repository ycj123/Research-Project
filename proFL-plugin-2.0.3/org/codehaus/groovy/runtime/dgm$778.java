// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import javax.swing.DefaultListModel;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$778 extends GeneratedMetaMethod
{
    public dgm$778(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        return SwingGroovyMethods.iterator((DefaultListModel)o);
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        return SwingGroovyMethods.iterator((DefaultListModel)o);
    }
}