// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

public class InvalidSessionException extends BlimpException
{
    private static final long serialVersionUID = 1L;
    
    public InvalidSessionException() {
        this("The MKS API session has expired or is invalid");
    }
    
    public InvalidSessionException(final String msg) {
        super(msg);
    }
}
