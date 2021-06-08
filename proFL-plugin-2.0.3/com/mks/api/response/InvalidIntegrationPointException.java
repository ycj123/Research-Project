// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidIntegrationPointException extends APIConnectionException
{
    public InvalidIntegrationPointException() {
    }
    
    public InvalidIntegrationPointException(final String msg) {
        super(msg);
    }
    
    public InvalidIntegrationPointException(final Throwable t) {
        super(t);
    }
}
