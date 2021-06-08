// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import java.math.BigDecimal;
import org.codehaus.groovy.reflection.ClassInfo;

public class BigDecimalCachedClass extends DoubleCachedClass
{
    public BigDecimalCachedClass(final Class klazz, final ClassInfo classInfo) {
        super(klazz, classInfo, true);
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return argument instanceof BigDecimal;
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        if (argument instanceof BigDecimal) {
            return argument;
        }
        if (argument instanceof Number) {
            return new BigDecimal(((Number)argument).doubleValue());
        }
        return argument;
    }
}
