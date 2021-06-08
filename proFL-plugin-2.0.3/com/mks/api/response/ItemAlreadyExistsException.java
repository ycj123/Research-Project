// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class ItemAlreadyExistsException extends ItemException
{
    public ItemAlreadyExistsException() {
    }
    
    public ItemAlreadyExistsException(final String msg) {
        super(msg);
    }
    
    public ItemAlreadyExistsException(final Throwable t) {
        super(t);
    }
}
