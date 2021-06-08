// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

public class PluginAssertionFailure extends RuntimeException
{
    private Throwable cause;
    
    public PluginAssertionFailure(final Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }
    
    public PluginAssertionFailure(final String msg) {
        super(msg);
        this.cause = null;
    }
    
    public PluginAssertionFailure(final String msg, final Throwable cause) {
        this(msg);
        this.cause = cause;
    }
}
