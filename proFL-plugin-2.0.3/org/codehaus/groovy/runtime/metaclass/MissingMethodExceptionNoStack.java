// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import groovy.lang.MissingMethodException;

public class MissingMethodExceptionNoStack extends MissingMethodException
{
    public MissingMethodExceptionNoStack(final String method, final Class type, final Object[] arguments) {
        this(method, type, arguments, false);
    }
    
    public MissingMethodExceptionNoStack(final String method, final Class type, final Object[] arguments, final boolean isStatic) {
        super(method, type, arguments, isStatic);
    }
    
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
