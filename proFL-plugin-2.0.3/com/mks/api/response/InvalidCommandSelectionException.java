// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidCommandSelectionException extends CommandException
{
    public InvalidCommandSelectionException() {
    }
    
    public InvalidCommandSelectionException(final String msg) {
        super(msg);
    }
    
    public InvalidCommandSelectionException(final Throwable t) {
        super(t);
    }
    
    public String getSelection() {
        if (this.contains("selection")) {
            final Field f = this.getField("selection");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
