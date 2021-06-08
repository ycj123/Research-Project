// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

public class InvalidPluginException extends Exception
{
    public InvalidPluginException(final String message, final Exception e) {
        super(message, e);
    }
}
