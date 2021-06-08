// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class IncompatibleVersionException extends ApplicationConnectionException
{
    public IncompatibleVersionException() {
    }
    
    public IncompatibleVersionException(final String msg) {
        super(msg);
    }
    
    public IncompatibleVersionException(final Throwable t) {
        super(t);
    }
}
