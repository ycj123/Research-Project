// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ApplicationRuntimeException extends ApplicationException
{
    private Response response;
    
    public ApplicationRuntimeException() {
    }
    
    public ApplicationRuntimeException(final String msg) {
        super(msg);
    }
    
    public ApplicationRuntimeException(final Throwable t) {
        super(t);
    }
}
