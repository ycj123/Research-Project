// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.codehaus.groovy.reflection.ClassInfo;

public class FloatCachedClass extends NumberCachedClass
{
    private boolean allowNull;
    
    public FloatCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof Float) {
            return argument;
        }
        if (!(argument instanceof Number)) {
            return argument;
        }
        final Float res = ((Number)argument).floatValue();
        if (argument instanceof BigDecimal && res.isInfinite()) {
            throw new IllegalArgumentException(Float.class + " out of range while converting from BigDecimal");
        }
        return res;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Float;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Float.class || classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == Float.TYPE || classToTransformFrom == Integer.TYPE || classToTransformFrom == Long.TYPE || classToTransformFrom == Short.TYPE || classToTransformFrom == Byte.TYPE || classToTransformFrom == BigDecimal.class || classToTransformFrom == BigInteger.class;
    }
}
