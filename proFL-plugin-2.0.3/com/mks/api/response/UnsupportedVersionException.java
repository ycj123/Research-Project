// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class UnsupportedVersionException extends APIConnectionException
{
    public UnsupportedVersionException() {
    }
    
    public UnsupportedVersionException(final String msg) {
        super(msg);
    }
    
    public UnsupportedVersionException(final Throwable t) {
        super(t);
    }
}
