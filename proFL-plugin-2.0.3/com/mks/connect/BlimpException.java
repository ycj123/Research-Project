// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;

public class BlimpException extends IOException
{
    private Throwable cause;
    
    public BlimpException(final String msg) {
        super(msg);
    }
    
    public BlimpException(final Throwable cause) {
        this.cause = cause;
    }
    
    public void printStackTrace() {
        if (this.cause != null) {
            this.cause.printStackTrace();
        }
        else {
            super.printStackTrace();
        }
    }
    
    public void printStackTrace(final PrintStream s) {
        if (this.cause != null) {
            this.cause.printStackTrace(s);
        }
        else {
            super.printStackTrace(s);
        }
    }
    
    public void printStackTrace(final PrintWriter s) {
        if (this.cause != null) {
            this.cause.printStackTrace(s);
        }
        else {
            super.printStackTrace(s);
        }
    }
}
