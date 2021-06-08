// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class UnsupportedFunctionException extends CommandException
{
    public UnsupportedFunctionException() {
    }
    
    public UnsupportedFunctionException(final String msg) {
        super(msg);
    }
    
    public UnsupportedFunctionException(final Throwable t) {
        super(t);
    }
}
