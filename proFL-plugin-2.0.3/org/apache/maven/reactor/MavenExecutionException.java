// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reactor;

public class MavenExecutionException extends Exception
{
    public MavenExecutionException() {
    }
    
    public MavenExecutionException(final String message) {
        super(message);
    }
    
    public MavenExecutionException(final Throwable cause) {
        super(cause);
    }
    
    public MavenExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
