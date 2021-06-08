// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidCommandRunnerStateException extends CommandException
{
    public InvalidCommandRunnerStateException() {
    }
    
    public InvalidCommandRunnerStateException(final String msg) {
        super(msg);
    }
    
    public InvalidCommandRunnerStateException(final Throwable t) {
        super(t);
    }
}
