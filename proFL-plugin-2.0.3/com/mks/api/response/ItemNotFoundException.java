// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ItemNotFoundException extends ItemException
{
    public ItemNotFoundException() {
    }
    
    public ItemNotFoundException(final String msg) {
        super(msg);
    }
    
    public ItemNotFoundException(final Throwable t) {
        super(t);
    }
}
