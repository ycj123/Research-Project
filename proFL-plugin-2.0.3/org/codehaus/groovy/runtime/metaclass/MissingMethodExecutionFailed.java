// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

public class MissingMethodExecutionFailed extends MissingMethodExceptionNoStack
{
    private Throwable cause;
    
    public MissingMethodExecutionFailed(final String method, final Class type, final Object[] arguments, final boolean isStatic, final Throwable cause) {
        super(method, type, arguments, isStatic);
        this.cause = cause;
    }
    
    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
