// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.xmlrules;

public class DigesterLoadingException extends Exception
{
    private Throwable cause;
    
    public DigesterLoadingException(final String msg) {
        super(msg);
        this.cause = null;
    }
    
    public DigesterLoadingException(final Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }
    
    public DigesterLoadingException(final String msg, final Throwable cause) {
        this(msg);
        this.cause = cause;
    }
}
