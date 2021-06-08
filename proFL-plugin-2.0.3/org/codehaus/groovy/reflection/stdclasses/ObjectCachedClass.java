// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class ObjectCachedClass extends CachedClass
{
    public ObjectCachedClass(final ClassInfo classInfo) {
        super(Object.class, classInfo);
    }
    
    @Override
    public synchronized CachedClass getCachedSuperClass() {
        return null;
    }
    
    @Override
    public boolean isAssignableFrom(final Class argument) {
        return true;
    }
}
