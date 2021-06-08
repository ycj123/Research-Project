// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

public class InvalidDirectiveException extends CommandException
{
    private static final int ERROR_CODE = 206;
    
    public InvalidDirectiveException(final String msg) {
        super(206, msg);
    }
    
    public InvalidDirectiveException(final Throwable cause) {
        super(206, cause);
    }
}
