// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.ClassInfo;

public class ByteCachedClass extends NumberCachedClass
{
    private boolean allowNull;
    
    public ByteCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof Byte) {
            return argument;
        }
        if (argument instanceof Number) {
            return ((Number)argument).byteValue();
        }
        return argument;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Byte;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Byte.class || classToTransformFrom == Byte.TYPE;
    }
}
