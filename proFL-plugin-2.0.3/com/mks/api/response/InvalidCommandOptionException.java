// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidCommandOptionException extends CommandException
{
    public InvalidCommandOptionException() {
    }
    
    public InvalidCommandOptionException(final String msg) {
        super(msg);
    }
    
    public InvalidCommandOptionException(final Throwable t) {
        super(t);
    }
    
    public String getOption() {
        if (this.contains("command-option")) {
            final Field f = this.getField("command-option");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
