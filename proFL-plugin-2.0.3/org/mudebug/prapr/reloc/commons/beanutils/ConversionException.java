// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

public class ConversionException extends RuntimeException
{
    protected Throwable cause;
    
    public ConversionException(final String message) {
        super(message);
        this.cause = null;
    }
    
    public ConversionException(final String message, final Throwable cause) {
        super(message);
        this.cause = null;
        this.cause = cause;
    }
    
    public ConversionException(final Throwable cause) {
        super(cause.getMessage());
        this.cause = null;
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
}
