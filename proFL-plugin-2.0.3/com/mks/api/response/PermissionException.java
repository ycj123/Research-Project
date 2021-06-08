// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class PermissionException extends ApplicationException
{
    public PermissionException() {
    }
    
    public PermissionException(final String msg) {
        super(msg);
    }
    
    public PermissionException(final Throwable t) {
        super(t);
    }
}
