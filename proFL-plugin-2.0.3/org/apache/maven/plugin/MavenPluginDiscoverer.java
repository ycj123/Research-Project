// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.component.repository.ComponentSetDescriptor;
import java.io.Reader;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.codehaus.plexus.component.discovery.AbstractComponentDiscoverer;

public class MavenPluginDiscoverer extends AbstractComponentDiscoverer
{
    private PluginDescriptorBuilder builder;
    
    public MavenPluginDiscoverer() {
        this.builder = new PluginDescriptorBuilder();
    }
    
    public String getComponentDescriptorLocation() {
        return "META-INF/maven/plugin.xml";
    }
    
    public ComponentSetDescriptor createComponentDescriptors(final Reader componentDescriptorConfiguration, final String source) throws PlexusConfigurationException {
        return this.builder.build(componentDescriptorConfiguration, source);
    }
}
