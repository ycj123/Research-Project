// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ItemModificationException extends ItemException
{
    public ItemModificationException() {
    }
    
    public ItemModificationException(final String msg) {
        super(msg);
    }
    
    public ItemModificationException(final Throwable t) {
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
