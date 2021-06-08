// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ApplicationOutOfMemoryException extends ApplicationRuntimeException
{
    public ApplicationOutOfMemoryException() {
    }
    
    public ApplicationOutOfMemoryException(final String msg) {
        super(msg);
    }
    
    public ApplicationOutOfMemoryException(final Throwable t) {
        super(t);
    }
}
