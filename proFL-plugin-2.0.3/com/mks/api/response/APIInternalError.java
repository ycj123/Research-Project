// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class APIInternalError extends APIError
{
    public APIInternalError() {
    }
    
    public APIInternalError(final String msg) {
        super(msg);
    }
    
    public APIInternalError(final Throwable t) {
        super(t);
    }
}
