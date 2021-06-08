// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class APIOutOfMemoryError extends APIInternalError
{
    public APIOutOfMemoryError() {
    }
    
    public APIOutOfMemoryError(final String msg) {
        super(msg);
    }
    
    public APIOutOfMemoryError(final Throwable t) {
        super(t);
    }
}
