// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import groovy.lang.GString;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class ArrayCachedClass extends CachedClass
{
    public ArrayCachedClass(final Class klazz, final ClassInfo classInfo) {
        super(klazz, classInfo);
    }
    
    @Override
    public Object coerceArgument(Object argument) {
        final Class argumentClass = argument.getClass();
        if (argumentClass.getName().charAt(0) != '[') {
            return argument;
        }
        final Class argumentComponent = argumentClass.getComponentType();
        final Class paramComponent = this.getTheClass().getComponentType();
        if (paramComponent.isPrimitive()) {
            argument = DefaultTypeTransformation.convertToPrimitiveArray(argument, paramComponent);
        }
        else if (paramComponent == String.class && argument instanceof GString[]) {
            final GString[] strings = (GString[])argument;
            final String[] ret = new String[strings.length];
            for (int i = 0; i < strings.length; ++i) {
                ret[i] = strings[i].toString();
            }
            argument = ret;
        }
        else if (paramComponent == Object.class && argumentComponent.isPrimitive()) {
            argument = DefaultTypeTransformation.primitiveArrayBox(argument);
        }
        return argument;
    }
}
