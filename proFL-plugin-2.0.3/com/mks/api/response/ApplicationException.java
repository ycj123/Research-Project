// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ApplicationException extends APIException
{
    public ApplicationException() {
    }
    
    public ApplicationException(final String msg) {
        super(msg);
    }
    
    public ApplicationException(final Throwable t) {
        super(t);
    }
}
