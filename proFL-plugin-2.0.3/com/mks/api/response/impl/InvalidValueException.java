// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

public class InvalidValueException extends CommandException
{
    private static final int ERROR_CODE = 208;
    
    public InvalidValueException(final String msg) {
        super(208, msg);
    }
    
    public InvalidValueException(final Throwable cause) {
        super(208, cause);
    }
}
