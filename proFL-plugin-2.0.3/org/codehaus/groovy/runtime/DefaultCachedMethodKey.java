// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.CachedClass;

public class DefaultCachedMethodKey extends MethodKey
{
    private final CachedClass[] parameterTypes;
    
    public DefaultCachedMethodKey(final Class sender, final String name, final CachedClass[] parameterTypes, final boolean isCallToSuper) {
        super(sender, name, isCallToSuper);
        this.parameterTypes = parameterTypes;
    }
    
    @Override
    public int getParameterCount() {
        return this.parameterTypes.length;
    }
    
    @Override
    public Class getParameterType(final int index) {
        final CachedClass c = this.parameterTypes[index];
        if (c == null) {
            return Object.class;
        }
        return c.getTheClass();
    }
}
