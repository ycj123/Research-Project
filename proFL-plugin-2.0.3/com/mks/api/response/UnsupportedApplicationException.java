// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public class UnsupportedApplicationException extends ApplicationException
{
    public UnsupportedApplicationException() {
    }
    
    public UnsupportedApplicationException(final String msg) {
        super(msg);
    }
    
    public UnsupportedApplicationException(final Throwable t) {
        super(t);
    }
    
    public String getApplicationName() {
        if (this.contains("applicationName")) {
            final Field f = this.getField("applicationName");
            if (f.getValue() != null) {
                return f.getValue().toString();
            }
        }
        return null;
    }
}
