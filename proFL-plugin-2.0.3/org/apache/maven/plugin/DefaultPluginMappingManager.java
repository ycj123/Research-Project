// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.apache.maven.artifact.repository.metadata.RepositoryMetadata;
import org.apache.maven.artifact.repository.metadata.GroupRepositoryMetadata;
import java.util.Iterator;
import org.apache.maven.artifact.repository.metadata.RepositoryMetadataResolutionException;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.maven.model.Plugin;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.artifact.repository.metadata.RepositoryMetadataManager;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultPluginMappingManager extends AbstractLogEnabled implements PluginMappingManager
{
    protected RepositoryMetadataManager repositoryMetadataManager;
    private Map pluginDefinitionsByPrefix;
    
    public DefaultPluginMappingManager() {
        this.pluginDefinitionsByPrefix = new HashMap();
    }
    
    public Plugin getByPrefix(final String pluginPrefix, final List groupIds, final List pluginRepositories, final ArtifactRepository localRepository) {
        if (!this.pluginDefinitionsByPrefix.containsKey(pluginPrefix)) {
            this.getLogger().info("Searching repository for plugin with prefix: '" + pluginPrefix + "'.");
            this.loadPluginMappings(groupIds, pluginRepositories, localRepository);
        }
        return this.pluginDefinitionsByPrefix.get(pluginPrefix);
    }
    
    private void loadPluginMappings(final List groupIds, final List pluginRepositories, final ArtifactRepository localRepository) {
        final List pluginGroupIds = new ArrayList(groupIds);
        if (!pluginGroupIds.contains("org.apache.maven.plugins")) {
            pluginGroupIds.add("org.apache.maven.plugins");
        }
        if (!pluginGroupIds.contains("org.codehaus.mojo")) {
            pluginGroupIds.add("org.codehaus.mojo");
        }
        for (final String groupId : pluginGroupIds) {
            this.getLogger().debug("Loading plugin prefixes from group: " + groupId);
            try {
                this.loadPluginMappings(groupId, pluginRepositories, localRepository);
            }
            catch (RepositoryMetadataResolutionException e) {
                this.getLogger().warn("Cannot resolve plugin-mapping metadata for groupId: " + groupId + " - IGNORING.");
                this.getLogger().debug("Error resolving plugin-mapping metadata for groupId: " + groupId + ".", e);
            }
        }
    }
    
    private void loadPluginMappings(final String groupId, final List pluginRepositories, final ArtifactRepository localRepository) throws RepositoryMetadataResolutionException {
        final RepositoryMetadata metadata = new GroupRepositoryMetadata(groupId);
        this.repositoryMetadataManager.resolve(metadata, pluginRepositories, localRepository);
        final Metadata repoMetadata = metadata.getMetadata();
        if (repoMetadata != null) {
            for (final org.apache.maven.artifact.repository.metadata.Plugin mapping : repoMetadata.getPlugins()) {
                final String prefix = mapping.getPrefix();
                if (!this.pluginDefinitionsByPrefix.containsKey(prefix)) {
                    final String artifactId = mapping.getArtifactId();
                    final Plugin plugin = new Plugin();
                    plugin.setGroupId(metadata.getGroupId());
                    plugin.setArtifactId(artifactId);
                    this.pluginDefinitionsByPrefix.put(prefix, plugin);
                }
            }
        }
    }
}
