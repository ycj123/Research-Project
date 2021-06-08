// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class InvalidItemException extends ItemException
{
    public InvalidItemException() {
    }
    
    public InvalidItemException(final String msg) {
        super(msg);
    }
    
    public InvalidItemException(final Throwable t) {
        super(t);
    }
    
    public String getReason() {
        if (this.contains("reason")) {
            final Field f = this.getField("reason");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
