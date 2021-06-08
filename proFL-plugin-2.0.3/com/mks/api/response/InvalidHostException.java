// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidHostException extends APIConnectionException
{
    public InvalidHostException() {
    }
    
    public InvalidHostException(final String msg) {
        super(msg);
    }
    
    public InvalidHostException(final Throwable t) {
        super(t);
    }
}
