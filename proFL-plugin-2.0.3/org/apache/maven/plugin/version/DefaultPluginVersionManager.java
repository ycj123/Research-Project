// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.version;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import java.util.List;
import org.apache.maven.artifact.metadata.ResolutionGroup;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.artifact.metadata.ArtifactMetadataRetrievalException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.Writer;
import java.io.File;
import org.codehaus.plexus.util.IOUtil;
import org.apache.maven.plugin.registry.io.xpp3.PluginRegistryXpp3Writer;
import org.codehaus.plexus.util.WriterFactory;
import org.apache.maven.plugin.registry.PluginRegistryUtils;
import java.util.Iterator;
import org.apache.maven.model.ReportPlugin;
import java.io.IOException;
import java.util.Date;
import org.apache.maven.plugin.registry.Plugin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.maven.settings.RuntimeInfo;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.artifact.ArtifactUtils;
import org.apache.maven.plugin.InvalidPluginException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.settings.Settings;
import org.apache.maven.project.MavenProject;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.execution.RuntimeInformation;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.plugin.registry.PluginRegistry;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.codehaus.plexus.components.interactivity.InputHandler;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.plugin.registry.MavenPluginRegistryBuilder;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultPluginVersionManager extends AbstractLogEnabled implements PluginVersionManager
{
    private MavenPluginRegistryBuilder mavenPluginRegistryBuilder;
    private ArtifactFactory artifactFactory;
    private InputHandler inputHandler;
    private ArtifactMetadataSource artifactMetadataSource;
    private PluginRegistry pluginRegistry;
    private MavenProjectBuilder mavenProjectBuilder;
    private RuntimeInformation runtimeInformation;
    private Map resolvedMetaVersions;
    
    public DefaultPluginVersionManager() {
        this.resolvedMetaVersions = new HashMap();
    }
    
    public String resolvePluginVersion(final String groupId, final String artifactId, final MavenProject project, final Settings settings, final ArtifactRepository localRepository) throws PluginVersionResolutionException, InvalidPluginException, PluginVersionNotFoundException {
        return this.resolvePluginVersion(groupId, artifactId, project, settings, localRepository, false);
    }
    
    public String resolveReportPluginVersion(final String groupId, final String artifactId, final MavenProject project, final Settings settings, final ArtifactRepository localRepository) throws PluginVersionResolutionException, InvalidPluginException, PluginVersionNotFoundException {
        return this.resolvePluginVersion(groupId, artifactId, project, settings, localRepository, true);
    }
    
    private String resolvePluginVersion(final String groupId, final String artifactId, final MavenProject project, final Settings settings, final ArtifactRepository localRepository, final boolean resolveAsReportPlugin) throws PluginVersionResolutionException, InvalidPluginException, PluginVersionNotFoundException {
        String version = this.getVersionFromPluginConfig(groupId, artifactId, project, resolveAsReportPlugin);
        if (version == null && project.getProjectReferences() != null) {
            final String refId = ArtifactUtils.versionlessKey(groupId, artifactId);
            final MavenProject ref = project.getProjectReferences().get(refId);
            if (ref != null) {
                version = ref.getVersion();
            }
        }
        String updatedVersion = null;
        boolean promptToPersist = false;
        final RuntimeInfo settingsRTInfo = settings.getRuntimeInfo();
        final Boolean pluginUpdateOverride = settingsRTInfo.getPluginUpdateOverride();
        if (StringUtils.isEmpty(version) && settings.isUsePluginRegistry()) {
            version = this.resolveExistingFromPluginRegistry(groupId, artifactId);
            if (StringUtils.isNotEmpty(version) && (Boolean.TRUE.equals(pluginUpdateOverride) || (!Boolean.FALSE.equals(pluginUpdateOverride) && this.shouldCheckForUpdates(groupId, artifactId)))) {
                updatedVersion = this.resolveMetaVersion(groupId, artifactId, project, localRepository, "LATEST");
                if (StringUtils.isNotEmpty(updatedVersion) && !updatedVersion.equals(version)) {
                    final boolean isRejected = this.checkForRejectedStatus(groupId, artifactId, updatedVersion);
                    promptToPersist = !isRejected;
                    if (isRejected) {
                        updatedVersion = null;
                    }
                    else {
                        this.getLogger().info("Plugin '" + this.constructPluginKey(groupId, artifactId) + "' has updates.");
                    }
                }
            }
        }
        boolean forcePersist = false;
        if (StringUtils.isEmpty(version)) {
            version = this.resolveMetaVersion(groupId, artifactId, project, localRepository, "LATEST");
            if (version != null) {
                updatedVersion = version;
                forcePersist = true;
                promptToPersist = false;
            }
        }
        if (StringUtils.isEmpty(version)) {
            version = this.resolveMetaVersion(groupId, artifactId, project, localRepository, "RELEASE");
            if (version != null) {
                updatedVersion = version;
                forcePersist = true;
                promptToPersist = false;
            }
        }
        if (StringUtils.isEmpty(version) && project.getGroupId().equals(groupId) && project.getArtifactId().equals(artifactId)) {
            version = project.getVersion();
        }
        if (StringUtils.isEmpty(version)) {
            throw new PluginVersionNotFoundException(groupId, artifactId);
        }
        if (settings.isUsePluginRegistry()) {
            final boolean inInteractiveMode = settings.isInteractiveMode();
            final String s = this.getPluginRegistry(groupId, artifactId).getAutoUpdate();
            boolean autoUpdate = true;
            if (s != null) {
                autoUpdate = Boolean.valueOf(s);
            }
            boolean persistUpdate = forcePersist || (promptToPersist && !Boolean.FALSE.equals(pluginUpdateOverride) && (inInteractiveMode || autoUpdate));
            final Boolean applyToAll = settings.getRuntimeInfo().getApplyToAllPluginUpdates();
            promptToPersist = (promptToPersist && pluginUpdateOverride == null && applyToAll == null && inInteractiveMode);
            if (promptToPersist) {
                persistUpdate = this.promptToPersistPluginUpdate(version, updatedVersion, groupId, artifactId, settings);
            }
            if (!Boolean.FALSE.equals(applyToAll) && persistUpdate) {
                this.updatePluginVersionInRegistry(groupId, artifactId, updatedVersion);
                version = updatedVersion;
            }
            else if (promptToPersist) {
                this.addNewVersionToRejectedListInExisting(groupId, artifactId, updatedVersion);
            }
        }
        return version;
    }
    
    private boolean shouldCheckForUpdates(final String groupId, final String artifactId) throws PluginVersionResolutionException {
        final PluginRegistry pluginRegistry = this.getPluginRegistry(groupId, artifactId);
        final Plugin plugin = this.getPlugin(groupId, artifactId, pluginRegistry);
        if (plugin == null) {
            return true;
        }
        final String lastChecked = plugin.getLastChecked();
        if (StringUtils.isEmpty(lastChecked)) {
            return true;
        }
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss Z");
        try {
            final Date lastCheckedDate = format.parse(lastChecked);
            return IntervalUtils.isExpired(pluginRegistry.getUpdateInterval(), lastCheckedDate);
        }
        catch (ParseException e) {
            this.getLogger().warn("Last-checked date for plugin {" + this.constructPluginKey(groupId, artifactId) + "} is invalid. Checking for updates.");
            return true;
        }
    }
    
    private boolean checkForRejectedStatus(final String groupId, final String artifactId, final String version) throws PluginVersionResolutionException {
        final PluginRegistry pluginRegistry = this.getPluginRegistry(groupId, artifactId);
        final Plugin plugin = this.getPlugin(groupId, artifactId, pluginRegistry);
        return plugin.getRejectedVersions().contains(version);
    }
    
    private boolean promptToPersistPluginUpdate(final String version, final String updatedVersion, final String groupId, final String artifactId, final Settings settings) throws PluginVersionResolutionException {
        try {
            final StringBuffer message = new StringBuffer();
            if (version != null && version.equals(updatedVersion)) {
                message.append("Unregistered plugin detected.\n\n");
            }
            else {
                message.append("New plugin version detected.\n\n");
            }
            message.append("Group ID: ").append(groupId).append("\n");
            message.append("Artifact ID: ").append(artifactId).append("\n");
            message.append("\n");
            if (version != null && !version.equals(updatedVersion)) {
                message.append("Registered Version: ").append(version).append("\n");
            }
            message.append("Detected plugin version: ").append(updatedVersion).append("\n");
            message.append("\n");
            message.append("Would you like to use this new version from now on? ( [Y]es, [n]o, [a]ll, n[o]ne ) ");
            this.getLogger().info(message.toString());
            String persistAnswer = this.inputHandler.readLine();
            boolean shouldPersist = true;
            if (!StringUtils.isEmpty(persistAnswer)) {
                persistAnswer = persistAnswer.toLowerCase();
                if (persistAnswer.startsWith("y")) {
                    shouldPersist = true;
                }
                else if (persistAnswer.startsWith("a")) {
                    shouldPersist = true;
                    settings.getRuntimeInfo().setApplyToAllPluginUpdates(Boolean.TRUE);
                }
                else if (persistAnswer.indexOf("o") > -1) {
                    settings.getRuntimeInfo().setApplyToAllPluginUpdates(Boolean.FALSE);
                }
                else {
                    shouldPersist = !persistAnswer.startsWith("n");
                }
            }
            if (shouldPersist) {
                this.getLogger().info("Updating plugin version to " + updatedVersion);
            }
            else {
                this.getLogger().info("NOT updating plugin version to " + updatedVersion);
            }
            return shouldPersist;
        }
        catch (IOException e) {
            throw new PluginVersionResolutionException(groupId, artifactId, "Can't read user input.", e);
        }
    }
    
    private void addNewVersionToRejectedListInExisting(final String groupId, final String artifactId, final String rejectedVersion) throws PluginVersionResolutionException {
        final PluginRegistry pluginRegistry = this.getPluginRegistry(groupId, artifactId);
        final Plugin plugin = this.getPlugin(groupId, artifactId, pluginRegistry);
        final String pluginKey = this.constructPluginKey(groupId, artifactId);
        if (plugin != null && !"global-level".equals(plugin.getSourceLevel())) {
            plugin.addRejectedVersion(rejectedVersion);
            this.writeUserRegistry(groupId, artifactId, pluginRegistry);
            this.getLogger().warn("Plugin version: " + rejectedVersion + " added to your rejectedVersions list.\n" + "You will not be prompted for this version again.\n\nPlugin: " + pluginKey);
        }
        else {
            this.getLogger().warn("Cannot add rejectedVersion entry for: " + rejectedVersion + ".\n\nPlugin: " + pluginKey);
        }
    }
    
    private String resolveExistingFromPluginRegistry(final String groupId, final String artifactId) throws PluginVersionResolutionException {
        final PluginRegistry pluginRegistry = this.getPluginRegistry(groupId, artifactId);
        final Plugin plugin = this.getPlugin(groupId, artifactId, pluginRegistry);
        String version = null;
        if (plugin != null) {
            version = plugin.getUseVersion();
        }
        return version;
    }
    
    private Plugin getPlugin(final String groupId, final String artifactId, final PluginRegistry pluginRegistry) {
        Map pluginsByKey;
        if (pluginRegistry != null) {
            pluginsByKey = pluginRegistry.getPluginsByKey();
        }
        else {
            pluginsByKey = new HashMap();
        }
        final String pluginKey = this.constructPluginKey(groupId, artifactId);
        return pluginsByKey.get(pluginKey);
    }
    
    private String constructPluginKey(final String groupId, final String artifactId) {
        return groupId + ":" + artifactId;
    }
    
    private String getVersionFromPluginConfig(final String groupId, final String artifactId, final MavenProject project, final boolean resolveAsReportPlugin) {
        String version = null;
        if (resolveAsReportPlugin) {
            if (project.getReportPlugins() != null) {
                ReportPlugin plugin;
                for (Iterator it = project.getReportPlugins().iterator(); it.hasNext() && version == null; version = plugin.getVersion()) {
                    plugin = it.next();
                    if (groupId.equals(plugin.getGroupId()) && artifactId.equals(plugin.getArtifactId())) {}
                }
            }
        }
        else if (project.getBuildPlugins() != null) {
            org.apache.maven.model.Plugin plugin2;
            for (Iterator it = project.getBuildPlugins().iterator(); it.hasNext() && version == null; version = plugin2.getVersion()) {
                plugin2 = it.next();
                if (groupId.equals(plugin2.getGroupId()) && artifactId.equals(plugin2.getArtifactId())) {}
            }
        }
        return version;
    }
    
    private void updatePluginVersionInRegistry(final String groupId, final String artifactId, final String version) throws PluginVersionResolutionException {
        final PluginRegistry pluginRegistry = this.getPluginRegistry(groupId, artifactId);
        Plugin plugin = this.getPlugin(groupId, artifactId, pluginRegistry);
        if (plugin != null) {
            if ("global-level".equals(plugin.getSourceLevel())) {
                this.getLogger().warn("Cannot update registered version for plugin {" + groupId + ":" + artifactId + "}; it is specified in the global registry.");
            }
            else {
                plugin.setUseVersion(version);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss Z");
                plugin.setLastChecked(format.format(new Date()));
            }
        }
        else {
            plugin = new Plugin();
            plugin.setGroupId(groupId);
            plugin.setArtifactId(artifactId);
            plugin.setUseVersion(version);
            pluginRegistry.addPlugin(plugin);
            pluginRegistry.flushPluginsByKey();
        }
        this.writeUserRegistry(groupId, artifactId, pluginRegistry);
    }
    
    private void writeUserRegistry(final String groupId, final String artifactId, final PluginRegistry pluginRegistry) {
        final File pluginRegistryFile = pluginRegistry.getRuntimeInfo().getFile();
        final PluginRegistry extractedUserRegistry = PluginRegistryUtils.extractUserPluginRegistry(pluginRegistry);
        if (extractedUserRegistry != null) {
            Writer fWriter = null;
            try {
                pluginRegistryFile.getParentFile().mkdirs();
                fWriter = WriterFactory.newXmlWriter(pluginRegistryFile);
                final PluginRegistryXpp3Writer writer = new PluginRegistryXpp3Writer();
                writer.write(fWriter, extractedUserRegistry);
            }
            catch (IOException e) {
                this.getLogger().warn("Cannot rewrite user-level plugin-registry.xml with new plugin version of plugin: '" + groupId + ":" + artifactId + "'.", e);
            }
            finally {
                IOUtil.close(fWriter);
            }
        }
    }
    
    private PluginRegistry getPluginRegistry(final String groupId, final String artifactId) throws PluginVersionResolutionException {
        if (this.pluginRegistry == null) {
            try {
                this.pluginRegistry = this.mavenPluginRegistryBuilder.buildPluginRegistry();
            }
            catch (IOException e) {
                throw new PluginVersionResolutionException(groupId, artifactId, "Error reading plugin registry: " + e.getMessage(), e);
            }
            catch (XmlPullParserException e2) {
                throw new PluginVersionResolutionException(groupId, artifactId, "Error parsing plugin registry: " + e2.getMessage(), e2);
            }
            if (this.pluginRegistry == null) {
                this.pluginRegistry = this.mavenPluginRegistryBuilder.createUserPluginRegistry();
            }
        }
        return this.pluginRegistry;
    }
    
    private String resolveMetaVersion(final String groupId, final String artifactId, final MavenProject project, final ArtifactRepository localRepository, final String metaVersionId) throws PluginVersionResolutionException, InvalidPluginException {
        Artifact artifact = this.artifactFactory.createProjectArtifact(groupId, artifactId, metaVersionId);
        final String key = artifact.getDependencyConflictId();
        if (this.resolvedMetaVersions.containsKey(key)) {
            return this.resolvedMetaVersions.get(key);
        }
        String version = null;
        try {
            final ResolutionGroup resolutionGroup = this.artifactMetadataSource.retrieve(artifact, localRepository, project.getPluginArtifactRepositories());
            artifact = resolutionGroup.getPomArtifact();
        }
        catch (ArtifactMetadataRetrievalException e) {
            throw new PluginVersionResolutionException(groupId, artifactId, e.getMessage(), e);
        }
        String artifactVersion = artifact.getVersion();
        if (artifact.getFile() != null) {
            boolean pluginValid = false;
            while (!pluginValid && artifactVersion != null) {
                pluginValid = true;
                MavenProject pluginProject;
                try {
                    artifact = this.artifactFactory.createProjectArtifact(groupId, artifactId, artifactVersion);
                    pluginProject = this.mavenProjectBuilder.buildFromRepository(artifact, project.getPluginArtifactRepositories(), localRepository, false);
                }
                catch (ProjectBuildingException e2) {
                    throw new InvalidPluginException("Unable to build project information for plugin '" + ArtifactUtils.versionlessKey(groupId, artifactId) + "': " + e2.getMessage(), e2);
                }
                if (pluginProject.getPrerequisites() != null && pluginProject.getPrerequisites().getMaven() != null) {
                    final DefaultArtifactVersion requiredVersion = new DefaultArtifactVersion(pluginProject.getPrerequisites().getMaven());
                    if (this.runtimeInformation.getApplicationVersion().compareTo(requiredVersion) >= 0) {
                        continue;
                    }
                    this.getLogger().info("Ignoring available plugin update: " + artifactVersion + " as it requires Maven version " + requiredVersion);
                    VersionRange vr;
                    try {
                        vr = VersionRange.createFromVersionSpec("(," + artifactVersion + ")");
                    }
                    catch (InvalidVersionSpecificationException e3) {
                        throw new PluginVersionResolutionException(groupId, artifactId, "Error getting available plugin versions: " + e3.getMessage(), e3);
                    }
                    this.getLogger().debug("Trying " + vr);
                    try {
                        final List versions = this.artifactMetadataSource.retrieveAvailableVersions(artifact, localRepository, project.getPluginArtifactRepositories());
                        final ArtifactVersion v = vr.matchVersion(versions);
                        artifactVersion = ((v != null) ? v.toString() : null);
                    }
                    catch (ArtifactMetadataRetrievalException e4) {
                        throw new PluginVersionResolutionException(groupId, artifactId, "Error getting available plugin versions: " + e4.getMessage(), e4);
                    }
                    if (artifactVersion == null) {
                        continue;
                    }
                    this.getLogger().debug("Found " + artifactVersion);
                    pluginValid = false;
                }
            }
        }
        if (!metaVersionId.equals(artifactVersion)) {
            version = artifactVersion;
            this.resolvedMetaVersions.put(key, version);
        }
        return version;
    }
}
