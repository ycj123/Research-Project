// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

public class PluginConfigurationException extends RuntimeException
{
    private Throwable cause;
    
    public PluginConfigurationException(final Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }
    
    public PluginConfigurationException(final String msg) {
        super(msg);
        this.cause = null;
    }
    
    public PluginConfigurationException(final String msg, final Throwable cause) {
        this(msg);
        this.cause = cause;
    }
}
