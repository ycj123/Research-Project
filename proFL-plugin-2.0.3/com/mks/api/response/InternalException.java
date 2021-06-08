// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InternalException extends APIException
{
    public InternalException() {
    }
    
    public InternalException(final String msg) {
        super(msg);
    }
    
    public InternalException(final Throwable t) {
        super(t);
    }
}
