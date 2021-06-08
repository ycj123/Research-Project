// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedMethod;

public class NewStaticMetaMethod extends NewMetaMethod
{
    public NewStaticMetaMethod(final CachedMethod method) {
        super(method);
    }
    
    @Override
    public boolean isStatic() {
        return true;
    }
    
    @Override
    public int getModifiers() {
        return 9;
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        final int size = arguments.length;
        final Object[] newArguments = new Object[size + 1];
        System.arraycopy(arguments, 0, newArguments, 1, size);
        newArguments[0] = null;
        return super.invoke(null, newArguments);
    }
}
