// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class CommandCancelledException extends CommandException
{
    public CommandCancelledException() {
    }
    
    public CommandCancelledException(final String msg) {
        super(msg);
    }
    
    public CommandCancelledException(final Throwable t) {
        super(t);
    }
}
