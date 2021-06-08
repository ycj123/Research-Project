// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.tools;

public class SiteToolException extends Exception
{
    static final long serialVersionUID = 2331441332996055959L;
    
    public SiteToolException(final String message, final Exception cause) {
        super(message, cause);
    }
    
    public SiteToolException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public SiteToolException(final String message) {
        super(message);
    }
}
