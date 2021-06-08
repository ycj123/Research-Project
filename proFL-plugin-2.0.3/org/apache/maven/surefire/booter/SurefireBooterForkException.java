// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

public class SurefireBooterForkException extends Exception
{
    public SurefireBooterForkException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public SurefireBooterForkException(final String msg) {
        super(msg);
    }
}
