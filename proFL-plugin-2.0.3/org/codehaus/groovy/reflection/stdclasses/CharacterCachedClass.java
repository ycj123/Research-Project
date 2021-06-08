// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class CharacterCachedClass extends CachedClass
{
    private boolean allowNull;
    
    public CharacterCachedClass(final Class klazz, final ClassInfo classInfo, final boolean allowNull) {
        super(klazz, classInfo);
        this.allowNull = allowNull;
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return (this.allowNull && argument == null) || argument instanceof Character;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return (this.allowNull && classToTransformFrom == null) || classToTransformFrom == Character.class || classToTransformFrom == Character.TYPE;
    }
}
