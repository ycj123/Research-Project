// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import java.math.BigInteger;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class NumberCachedClass extends CachedClass
{
    public NumberCachedClass(final Class klazz, final ClassInfo classInfo) {
        super(klazz, classInfo);
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof Number) {
            return this.coerceNumber(argument);
        }
        return argument;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return classToTransformFrom == null || Number.class.isAssignableFrom(classToTransformFrom) || classToTransformFrom == Byte.TYPE || classToTransformFrom == Short.TYPE || classToTransformFrom == Integer.TYPE || classToTransformFrom == Long.TYPE || classToTransformFrom == Float.TYPE || classToTransformFrom == Double.TYPE;
    }
    
    private Object coerceNumber(Object argument) {
        final Class param = this.getTheClass();
        if (param == Byte.class) {
            argument = new Byte(((Number)argument).byteValue());
        }
        else if (param == BigInteger.class) {
            argument = new BigInteger(String.valueOf(argument));
        }
        return argument;
    }
}
