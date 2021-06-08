// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import java.math.BigInteger;
import org.codehaus.groovy.reflection.ClassInfo;

public class IntegerCachedClass extends NumberCachedClass
{
    private boolean allowNull;
    
    public IntegerCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof Integer) {
            return argument;
        }
        if (argument instanceof Number) {
            return ((Number)argument).intValue();
        }
        return argument;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Integer;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Integer.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == BigInteger.class || classToTransformFrom == Integer.TYPE || classToTransformFrom == Short.TYPE || classToTransformFrom == Byte.TYPE;
    }
}
