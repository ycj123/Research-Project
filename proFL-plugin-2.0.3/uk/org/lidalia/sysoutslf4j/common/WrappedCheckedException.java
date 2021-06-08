// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

public class WrappedCheckedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public WrappedCheckedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public WrappedCheckedException(final Throwable cause) {
        super(cause);
    }
}
