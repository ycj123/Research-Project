// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class UnknownException extends InternalException
{
    public UnknownException() {
    }
    
    public UnknownException(final String msg) {
        super(msg);
    }
    
    public UnknownException(final Throwable t) {
        super(t);
    }
}
