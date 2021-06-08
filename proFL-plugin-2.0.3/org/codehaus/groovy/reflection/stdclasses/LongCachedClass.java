// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.ClassInfo;

public class LongCachedClass extends NumberCachedClass
{
    private boolean allowNull;
    
    public LongCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof Long) {
            return argument;
        }
        if (argument instanceof Number) {
            return ((Number)argument).longValue();
        }
        return argument;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Long;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == Integer.TYPE || classToTransformFrom == Long.TYPE || classToTransformFrom == Short.TYPE || classToTransformFrom == Byte.TYPE;
    }
}
