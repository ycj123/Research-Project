// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import java.math.BigInteger;
import org.codehaus.groovy.reflection.ClassInfo;

public class BigIntegerCachedClass extends NumberCachedClass
{
    public BigIntegerCachedClass(final Class klazz, final ClassInfo classInfo) {
        super(klazz, classInfo);
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return argument instanceof BigInteger;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return classToTransformFrom == null || classToTransformFrom == Integer.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == BigInteger.class || classToTransformFrom == Long.class || classToTransformFrom == Integer.TYPE || classToTransformFrom == Short.TYPE || classToTransformFrom == Byte.TYPE || classToTransformFrom == Long.TYPE;
    }
}
