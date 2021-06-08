// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle;

public class LifecycleExecutionException extends Exception
{
    public LifecycleExecutionException(final String message) {
        super(message);
    }
    
    public LifecycleExecutionException(final Throwable cause) {
        super(cause);
    }
    
    public LifecycleExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
