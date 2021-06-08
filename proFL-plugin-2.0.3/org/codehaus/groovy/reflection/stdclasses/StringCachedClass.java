// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import groovy.lang.GString;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class StringCachedClass extends CachedClass
{
    private static final Class STRING_CLASS;
    private static final Class GSTRING_CLASS;
    
    public StringCachedClass(final ClassInfo classInfo) {
        super(StringCachedClass.STRING_CLASS, classInfo);
    }
    
    @Override
    public boolean isDirectlyAssignable(final Object argument) {
        return argument instanceof String;
    }
    
    @Override
    public boolean isAssignableFrom(final Class classToTransformFrom) {
        return classToTransformFrom == null || classToTransformFrom == StringCachedClass.STRING_CLASS || ReflectionCache.isAssignableFrom(StringCachedClass.GSTRING_CLASS, classToTransformFrom);
    }
    
    @Override
    public Object coerceArgument(final Object argument) {
        return (argument instanceof GString) ? argument.toString() : argument;
    }
    
    static {
        STRING_CLASS = String.class;
        GSTRING_CLASS = GString.class;
    }
}
