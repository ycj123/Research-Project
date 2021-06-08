// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

public class DefaultMethodKey extends MethodKey
{
    private final Class[] parameterTypes;
    
    public DefaultMethodKey(final Class sender, final String name, final Class[] parameterTypes, final boolean isCallToSuper) {
        super(sender, name, isCallToSuper);
        this.parameterTypes = parameterTypes;
    }
    
    @Override
    public int getParameterCount() {
        return this.parameterTypes.length;
    }
    
    @Override
    public Class getParameterType(final int index) {
        final Class c = this.parameterTypes[index];
        if (c == null) {
            return Object.class;
        }
        return c;
    }
}
