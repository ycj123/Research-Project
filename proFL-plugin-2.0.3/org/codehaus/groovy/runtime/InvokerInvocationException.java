// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.lang.reflect.InvocationTargetException;
import groovy.lang.GroovyRuntimeException;

public class InvokerInvocationException extends GroovyRuntimeException
{
    public InvokerInvocationException(final InvocationTargetException e) {
        super(e.getTargetException());
    }
    
    public InvokerInvocationException(final Throwable cause) {
        super(cause);
    }
    
    @Override
    public String getMessage() {
        final Throwable cause = this.getCause();
        return (cause == null) ? "java.lang.NullPointerException" : cause.toString();
    }
}
