// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

import org.apache.velocity.util.ExceptionUtils;

public class VelocityException extends RuntimeException
{
    private static final long serialVersionUID = 1251243065134956045L;
    private final Throwable wrapped;
    
    public VelocityException(final String exceptionMessage) {
        super(exceptionMessage);
        this.wrapped = null;
    }
    
    public VelocityException(final String exceptionMessage, final Throwable wrapped) {
        super(exceptionMessage);
        ExceptionUtils.setCause(this, this.wrapped = wrapped);
    }
    
    public VelocityException(final Throwable wrapped) {
        ExceptionUtils.setCause(this, this.wrapped = wrapped);
    }
    
    public Throwable getWrappedThrowable() {
        return this.wrapped;
    }
}
