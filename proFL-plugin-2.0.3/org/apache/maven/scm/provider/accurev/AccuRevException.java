// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

public class AccuRevException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public AccuRevException() {
    }
    
    public AccuRevException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public AccuRevException(final String message) {
        super(message);
    }
    
    public AccuRevException(final Throwable cause) {
        super(cause);
    }
}
