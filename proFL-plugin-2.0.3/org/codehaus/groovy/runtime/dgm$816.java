// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.tree.MutableTreeNode;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$816 extends GeneratedMetaMethod
{
    public dgm$816(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        SwingGroovyMethods.putAt((MutableTreeNode)o, DefaultTypeTransformation.intUnbox(array[0]), (MutableTreeNode)array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        SwingGroovyMethods.putAt((MutableTreeNode)o, DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[0]), (MutableTreeNode)coerceArgumentsToClasses[1]);
        return null;
    }
}
