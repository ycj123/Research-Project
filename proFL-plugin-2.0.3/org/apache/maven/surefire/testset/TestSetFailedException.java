// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.testset;

public class TestSetFailedException extends Exception
{
    public TestSetFailedException(final String message) {
        super(message);
    }
    
    public TestSetFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public TestSetFailedException(final Throwable cause) {
        super((cause == null) ? "" : cause.toString(), cause);
    }
}
