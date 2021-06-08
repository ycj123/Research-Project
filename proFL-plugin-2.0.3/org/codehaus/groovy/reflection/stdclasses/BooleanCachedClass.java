// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class BooleanCachedClass extends CachedClass
{
    private boolean allowNull;
    
    public BooleanCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Boolean;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Boolean.class || classToTransformFrom == Boolean.TYPE;
    }
}
