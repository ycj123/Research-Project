// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven;

public class BuildFailureException extends Exception
{
    public BuildFailureException(final String message) {
        super(message);
    }
    
    public BuildFailureException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
