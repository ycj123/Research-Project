// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

public class PluginManagerException extends Exception
{
    public PluginManagerException(final String message) {
        super(message);
    }
    
    public PluginManagerException(final String message, final Exception e) {
        super(message, e);
    }
}
