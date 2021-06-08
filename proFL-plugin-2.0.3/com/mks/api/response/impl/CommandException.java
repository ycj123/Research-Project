// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

public class CommandException extends Exception
{
    public static final int VALUE_TOO_BIG = 202;
    public static final int UNKNOWN_EXCEPTION = 203;
    public static final int API_EXCEPTION = 204;
    private int returnCode;
    
    public CommandException(final String msg) {
        this(203, msg);
    }
    
    public CommandException(final Throwable cause) {
        this(203, cause);
    }
    
    public CommandException(final int returnCode, final String msg) {
        super(msg);
        this.returnCode = returnCode;
    }
    
    public CommandException(final int returnCode, final Throwable cause) {
        super(cause.getLocalizedMessage());
        this.returnCode = returnCode;
    }
    
    public int getReturnCode() {
        return this.returnCode;
    }
}
