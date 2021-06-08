// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import javax.swing.MutableComboBoxModel;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;

public class dgm$814 extends GeneratedMetaMethod
{
    public dgm$814(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        super(name, declaringClass, returnType, parameters);
    }
    
    public Object invoke(final Object o, final Object[] array) {
        SwingGroovyMethods.putAt((MutableComboBoxModel)o, DefaultTypeTransformation.intUnbox(array[0]), array[1]);
        return null;
    }
    
    public final Object doMethodInvoke(final Object o, Object[] coerceArgumentsToClasses) {
        coerceArgumentsToClasses = this.coerceArgumentsToClasses(coerceArgumentsToClasses);
        SwingGroovyMethods.putAt((MutableComboBoxModel)o, DefaultTypeTransformation.intUnbox(coerceArgumentsToClasses[0]), coerceArgumentsToClasses[1]);
        return null;
    }
}
