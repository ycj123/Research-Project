// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ItemException extends ApplicationException
{
    public ItemException() {
    }
    
    public ItemException(final String msg) {
        super(msg);
    }
    
    public ItemException(final Throwable t) {
        super(t);
    }
    
    public String getId() {
        if (this.contains("item-id")) {
            final Field f = this.getField("item-id");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
    
    public String getModelType() {
        if (this.contains("item-modelType")) {
            final Field f = this.getField("item-modelType");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
