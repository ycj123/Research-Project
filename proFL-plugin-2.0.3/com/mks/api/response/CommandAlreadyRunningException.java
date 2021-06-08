// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class CommandAlreadyRunningException extends CommandException
{
    public CommandAlreadyRunningException() {
    }
    
    public CommandAlreadyRunningException(final String msg) {
        super(msg);
    }
    
    CommandAlreadyRunningException(final Throwable t) {
        super(t);
    }
}
