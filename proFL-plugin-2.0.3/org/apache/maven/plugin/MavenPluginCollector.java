// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.Iterator;
import org.codehaus.plexus.component.repository.ComponentSetDescriptor;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.codehaus.plexus.component.discovery.ComponentDiscoveryEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.codehaus.plexus.component.discovery.ComponentDiscoveryListener;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class MavenPluginCollector extends AbstractLogEnabled implements ComponentDiscoveryListener
{
    private Set pluginsInProcess;
    private Map pluginDescriptors;
    private Map pluginIdsByPrefix;
    
    public MavenPluginCollector() {
        this.pluginsInProcess = new HashSet();
        this.pluginDescriptors = new HashMap();
        this.pluginIdsByPrefix = new HashMap();
    }
    
    public void componentDiscovered(final ComponentDiscoveryEvent event) {
        final ComponentSetDescriptor componentSetDescriptor = event.getComponentSetDescriptor();
        if (componentSetDescriptor instanceof PluginDescriptor) {
            final PluginDescriptor pluginDescriptor = (PluginDescriptor)componentSetDescriptor;
            final String key = Plugin.constructKey(pluginDescriptor.getGroupId(), pluginDescriptor.getArtifactId());
            if (!this.pluginsInProcess.contains(key)) {
                this.pluginsInProcess.add(key);
                this.pluginDescriptors.put(key, pluginDescriptor);
                if (!this.pluginIdsByPrefix.containsKey(pluginDescriptor.getGoalPrefix())) {
                    this.pluginIdsByPrefix.put(pluginDescriptor.getGoalPrefix(), pluginDescriptor);
                }
            }
        }
    }
    
    public PluginDescriptor getPluginDescriptor(final Plugin plugin) {
        return this.pluginDescriptors.get(plugin.getKey());
    }
    
    public boolean isPluginInstalled(final Plugin plugin) {
        return this.pluginDescriptors.containsKey(plugin.getKey());
    }
    
    public PluginDescriptor getPluginDescriptorForPrefix(final String prefix) {
        return this.pluginIdsByPrefix.get(prefix);
    }
    
    public void flushPluginDescriptor(final Plugin plugin) {
        this.pluginsInProcess.remove(plugin.getKey());
        this.pluginDescriptors.remove(plugin.getKey());
        final Iterator it = this.pluginIdsByPrefix.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry entry = it.next();
            if (plugin.getKey().equals(entry.getValue())) {
                it.remove();
            }
        }
    }
}
