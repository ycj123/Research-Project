// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class CommandException extends APIException
{
    public CommandException() {
    }
    
    public CommandException(final String msg) {
        super(msg);
    }
    
    public CommandException(final Throwable t) {
        super(t);
    }
}
