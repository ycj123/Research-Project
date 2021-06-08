// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

import org.codehaus.plexus.configuration.PlexusConfigurationException;

public class InvalidPluginDescriptorException extends PlexusConfigurationException
{
    public InvalidPluginDescriptorException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public InvalidPluginDescriptorException(final String message) {
        super(message);
    }
}
