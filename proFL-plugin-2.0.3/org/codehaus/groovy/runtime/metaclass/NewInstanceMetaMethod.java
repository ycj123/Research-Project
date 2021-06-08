// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedMethod;

public class NewInstanceMetaMethod extends NewMetaMethod
{
    public NewInstanceMetaMethod(final CachedMethod method) {
        super(method);
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
    
    @Override
    public int getModifiers() {
        return 1;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        final int size = arguments.length;
        final Object[] newArguments = new Object[size + 1];
        newArguments[0] = object;
        System.arraycopy(arguments, 0, newArguments, 1, size);
        return super.invoke(null, newArguments);
    }
}
