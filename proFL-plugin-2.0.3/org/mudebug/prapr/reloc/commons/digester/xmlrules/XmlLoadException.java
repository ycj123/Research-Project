// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.xmlrules;

public class XmlLoadException extends RuntimeException
{
    private Throwable cause;
    
    public XmlLoadException(final Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }
    
    public XmlLoadException(final String msg) {
        super(msg);
        this.cause = null;
    }
    
    public XmlLoadException(final String msg, final Throwable cause) {
        this(msg);
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
}
