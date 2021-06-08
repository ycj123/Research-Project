// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

public class PluginInvalidInputException extends PluginException
{
    private Throwable cause;
    
    public PluginInvalidInputException(final Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }
    
    public PluginInvalidInputException(final String msg) {
        super(msg);
        this.cause = null;
    }
    
    public PluginInvalidInputException(final String msg, final Throwable cause) {
        this(msg);
        this.cause = cause;
    }
}
