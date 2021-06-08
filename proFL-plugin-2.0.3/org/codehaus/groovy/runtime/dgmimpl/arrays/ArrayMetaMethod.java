// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.runtime.callsite.CallSiteAwareMetaMethod;

public abstract class ArrayMetaMethod extends CallSiteAwareMetaMethod
{
    protected static final CachedClass INTEGER_CLASS;
    protected static final CachedClass[] INTEGER_CLASS_ARR;
    
    protected static int normaliseIndex(int i, final int size) {
        final int temp = i;
        if (i < 0) {
            i += size;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative array index [" + temp + "] too large for array size " + size);
        }
        return i;
    }
    
    @Override
    public int getModifiers() {
        return 1;
    }
    
    static {
        INTEGER_CLASS = ReflectionCache.getCachedClass(Integer.class);
        INTEGER_CLASS_ARR = new CachedClass[] { ArrayMetaMethod.INTEGER_CLASS };
    }
}
