// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class UnknownCommandException extends CommandException
{
    public UnknownCommandException() {
    }
    
    public UnknownCommandException(final String msg) {
        super(msg);
    }
    
    public UnknownCommandException(final Throwable t) {
        super(t);
    }
    
    public String getCommand() {
        if (this.contains("command")) {
            final Field f = this.getField("command");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
