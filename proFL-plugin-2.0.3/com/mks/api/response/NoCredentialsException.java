// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class NoCredentialsException extends ApplicationConnectionException
{
    public NoCredentialsException() {
    }
    
    public NoCredentialsException(final String msg) {
        super(msg);
    }
    
    public NoCredentialsException(final Throwable t) {
        super(t);
    }
}
