// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import org.apache.maven.artifact.resolver.MultipleArtifactsNotFoundException;
import java.util.HashSet;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.MavenArtifactFilterManager;
import org.codehaus.plexus.context.ContextException;
import org.apache.maven.monitor.logging.DefaultLog;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.component.configurator.ConfigurationListener;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.codehaus.plexus.component.configurator.ComponentConfigurator;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import org.apache.maven.plugin.descriptor.Parameter;
import java.net.URL;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.metadata.ResolutionGroup;
import org.codehaus.plexus.util.StringUtils;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import org.apache.maven.artifact.metadata.ArtifactMetadataRetrievalException;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluator;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.configuration.xml.XmlPlexusConfiguration;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.model.ReportPlugin;
import org.apache.maven.reporting.MavenReport;
import org.codehaus.classworlds.ClassRealm;
import org.apache.maven.monitor.event.EventDispatcher;
import java.util.Iterator;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import java.util.Set;
import org.apache.maven.project.artifact.InvalidDependencyVersionException;
import org.apache.maven.project.artifact.MavenMetadataSource;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.classworlds.NoSuchRealmException;
import java.util.Collections;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import java.io.File;
import org.apache.maven.artifact.Artifact;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.plugin.version.PluginVersionNotFoundException;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.plugin.version.PluginVersionResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.settings.Settings;
import java.util.List;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import java.util.HashMap;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.execution.RuntimeInformation;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.plugin.version.PluginVersionManager;
import org.apache.maven.project.path.PathTranslator;
import java.util.Map;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultPluginManager extends AbstractLogEnabled implements PluginManager, Initializable, Contextualizable
{
    protected PlexusContainer container;
    protected PluginDescriptorBuilder pluginDescriptorBuilder;
    protected ArtifactFilter artifactFilter;
    private Log mojoLogger;
    private Map resolvedCoreArtifactFiles;
    protected PathTranslator pathTranslator;
    protected MavenPluginCollector pluginCollector;
    protected PluginVersionManager pluginVersionManager;
    protected ArtifactFactory artifactFactory;
    protected ArtifactResolver artifactResolver;
    protected ArtifactMetadataSource artifactMetadataSource;
    protected RuntimeInformation runtimeInformation;
    protected MavenProjectBuilder mavenProjectBuilder;
    protected PluginMappingManager pluginMappingManager;
    
    public DefaultPluginManager() {
        this.resolvedCoreArtifactFiles = new HashMap();
        this.pluginDescriptorBuilder = new PluginDescriptorBuilder();
    }
    
    public PluginDescriptor getPluginDescriptorForPrefix(final String prefix) {
        return this.pluginCollector.getPluginDescriptorForPrefix(prefix);
    }
    
    public Plugin getPluginDefinitionForPrefix(final String prefix, final MavenSession session, final MavenProject project) {
        return this.pluginMappingManager.getByPrefix(prefix, session.getSettings().getPluginGroups(), project.getPluginArtifactRepositories(), session.getLocalRepository());
    }
    
    public PluginDescriptor verifyPlugin(final Plugin plugin, final MavenProject project, final Settings settings, final ArtifactRepository localRepository) throws ArtifactResolutionException, PluginVersionResolutionException, ArtifactNotFoundException, InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException, PluginVersionNotFoundException {
        if (plugin.getVersion() == null) {
            final String version = this.pluginVersionManager.resolvePluginVersion(plugin.getGroupId(), plugin.getArtifactId(), project, settings, localRepository);
            plugin.setVersion(version);
        }
        return this.verifyVersionedPlugin(plugin, project, localRepository);
    }
    
    private PluginDescriptor verifyVersionedPlugin(final Plugin plugin, final MavenProject project, final ArtifactRepository localRepository) throws PluginVersionResolutionException, ArtifactNotFoundException, ArtifactResolutionException, InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException {
        try {
            final VersionRange versionRange = VersionRange.createFromVersionSpec(plugin.getVersion());
            final List remoteRepositories = new ArrayList();
            remoteRepositories.addAll(project.getPluginArtifactRepositories());
            remoteRepositories.addAll(project.getRemoteArtifactRepositories());
            this.checkRequiredMavenVersion(plugin, localRepository, remoteRepositories);
            Artifact pluginArtifact = this.artifactFactory.createPluginArtifact(plugin.getGroupId(), plugin.getArtifactId(), versionRange);
            pluginArtifact = project.replaceWithActiveArtifact(pluginArtifact);
            this.artifactResolver.resolve(pluginArtifact, project.getPluginArtifactRepositories(), localRepository);
            final PlexusContainer pluginContainer = this.container.getChildContainer(plugin.getKey());
            final File pluginFile = pluginArtifact.getFile();
            if (!this.pluginCollector.isPluginInstalled(plugin) || pluginContainer == null) {
                this.addPlugin(plugin, pluginArtifact, project, localRepository);
            }
            else if (pluginFile.lastModified() > pluginContainer.getCreationDate().getTime()) {
                this.getLogger().info("Reloading plugin container for: " + plugin.getKey() + ". The plugin artifact has changed.");
                pluginContainer.dispose();
                this.pluginCollector.flushPluginDescriptor(plugin);
                this.addPlugin(plugin, pluginArtifact, project, localRepository);
            }
            project.addPlugin(plugin);
        }
        catch (ArtifactNotFoundException e) {
            final String groupId = plugin.getGroupId();
            final String artifactId = plugin.getArtifactId();
            final String version = plugin.getVersion();
            if (groupId == null || artifactId == null || version == null) {
                throw new PluginNotFoundException(e);
            }
            if (groupId.equals(e.getGroupId()) && artifactId.equals(e.getArtifactId()) && version.equals(e.getVersion()) && "maven-plugin".equals(e.getType())) {
                throw new PluginNotFoundException(e);
            }
            throw e;
        }
        return this.pluginCollector.getPluginDescriptor(plugin);
    }
    
    private void checkRequiredMavenVersion(final Plugin plugin, final ArtifactRepository localRepository, final List remoteRepositories) throws PluginVersionResolutionException, InvalidPluginException {
        try {
            final Artifact artifact = this.artifactFactory.createProjectArtifact(plugin.getGroupId(), plugin.getArtifactId(), plugin.getVersion());
            final MavenProject project = this.mavenProjectBuilder.buildFromRepository(artifact, remoteRepositories, localRepository, false);
            if (project.getPrerequisites() != null && project.getPrerequisites().getMaven() != null) {
                final DefaultArtifactVersion requiredVersion = new DefaultArtifactVersion(project.getPrerequisites().getMaven());
                if (this.runtimeInformation.getApplicationVersion().compareTo(requiredVersion) < 0) {
                    throw new PluginVersionResolutionException(plugin.getGroupId(), plugin.getArtifactId(), "Plugin requires Maven version " + requiredVersion);
                }
            }
        }
        catch (ProjectBuildingException e) {
            throw new InvalidPluginException("Unable to build project for plugin '" + plugin.getKey() + "': " + e.getMessage(), e);
        }
    }
    
    protected void addPlugin(final Plugin plugin, final Artifact pluginArtifact, final MavenProject project, final ArtifactRepository localRepository) throws PluginManagerException, InvalidPluginException {
        PlexusContainer child;
        try {
            child = this.container.createChildContainer(plugin.getKey(), Collections.singletonList(pluginArtifact.getFile()), Collections.EMPTY_MAP, Collections.singletonList(this.pluginCollector));
            try {
                child.getContainerRealm().importFrom("plexus.core", "org.codehaus.plexus.util.xml.Xpp3Dom");
                child.getContainerRealm().importFrom("plexus.core", "org.codehaus.plexus.util.xml.pull");
                child.getContainerRealm().importFrom("plexus.core", "/default-report.xml");
            }
            catch (NoSuchRealmException ex) {}
        }
        catch (PlexusContainerException e) {
            throw new PluginManagerException("Failed to create plugin container for plugin '" + plugin + "': " + e.getMessage(), e);
        }
        final PluginDescriptor addedPlugin = this.pluginCollector.getPluginDescriptor(plugin);
        if (addedPlugin == null) {
            throw new IllegalStateException("The PluginDescriptor for the plugin " + plugin + " was not found.");
        }
        addedPlugin.setClassRealm(child.getContainerRealm());
        addedPlugin.setArtifacts(Collections.singletonList(pluginArtifact));
        try {
            Plugin projectPlugin = project.getBuild().getPluginsAsMap().get(plugin.getKey());
            if (projectPlugin == null) {
                projectPlugin = plugin;
            }
            final Set artifacts = MavenMetadataSource.createArtifacts(this.artifactFactory, projectPlugin.getDependencies(), null, null, project);
            addedPlugin.setIntroducedDependencyArtifacts(artifacts);
        }
        catch (InvalidDependencyVersionException e2) {
            throw new InvalidPluginException("Plugin '" + plugin + "' is invalid: " + e2.getMessage(), e2);
        }
    }
    
    public void executeMojo(final MavenProject project, final MojoExecution mojoExecution, final MavenSession session) throws ArtifactResolutionException, MojoExecutionException, MojoFailureException, ArtifactNotFoundException, InvalidDependencyVersionException, PluginManagerException, PluginConfigurationException {
        final MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();
        if (mojoDescriptor.isProjectRequired() && !session.isUsingPOMsFromFilesystem()) {
            throw new MojoExecutionException("Cannot execute mojo: " + mojoDescriptor.getGoal() + ". It requires a project with an existing pom.xml, but the build is not using one.");
        }
        if (mojoDescriptor.isOnlineRequired() && session.getSettings().isOffline()) {
            throw new MojoExecutionException("Mojo: " + mojoDescriptor.getGoal() + " requires online mode for execution. Maven is currently offline.");
        }
        if (mojoDescriptor.isDependencyResolutionRequired() != null) {
            Collection projects;
            if (mojoDescriptor.isAggregator()) {
                projects = session.getSortedProjects();
            }
            else {
                projects = Collections.singleton(project);
            }
            for (final MavenProject p : projects) {
                this.resolveTransitiveDependencies(session, this.artifactResolver, mojoDescriptor.isDependencyResolutionRequired(), this.artifactFactory, p, mojoDescriptor.isAggregator());
            }
            this.downloadDependencies(project, session, this.artifactResolver);
        }
        final String goalName = mojoDescriptor.getFullGoalName();
        final PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
        final String goalId = mojoDescriptor.getGoal();
        final String groupId = pluginDescriptor.getGroupId();
        final String artifactId = pluginDescriptor.getArtifactId();
        final String executionId = mojoExecution.getExecutionId();
        Xpp3Dom dom = project.getGoalConfiguration(groupId, artifactId, executionId, goalId);
        final Xpp3Dom reportDom = project.getReportConfiguration(groupId, artifactId, executionId);
        dom = Xpp3Dom.mergeXpp3Dom(dom, reportDom);
        if (mojoExecution.getConfiguration() != null) {
            dom = Xpp3Dom.mergeXpp3Dom(dom, mojoExecution.getConfiguration());
        }
        final Mojo plugin = this.getConfiguredMojo(session, dom, project, false, mojoExecution);
        final String event = "mojo-execute";
        final EventDispatcher dispatcher = session.getEventDispatcher();
        String goalExecId = goalName;
        if (mojoExecution.getExecutionId() != null) {
            goalExecId = goalExecId + " {execution: " + mojoExecution.getExecutionId() + "}";
        }
        dispatcher.dispatchStart(event, goalExecId);
        final ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(mojoDescriptor.getPluginDescriptor().getClassRealm().getClassLoader());
            plugin.execute();
            dispatcher.dispatchEnd(event, goalExecId);
        }
        catch (MojoExecutionException e) {
            session.getEventDispatcher().dispatchError(event, goalExecId, e);
            throw e;
        }
        catch (MojoFailureException e2) {
            session.getEventDispatcher().dispatchError(event, goalExecId, e2);
            throw e2;
        }
        catch (LinkageError e3) {
            if (this.getLogger().isFatalErrorEnabled()) {
                this.getLogger().fatalError(plugin.getClass().getName() + "#execute() caused a linkage error (" + e3.getClass().getName() + ") and may be out-of-date. Check the realms:");
                final ClassRealm pluginRealm = mojoDescriptor.getPluginDescriptor().getClassRealm();
                StringBuffer sb = new StringBuffer();
                sb.append("Plugin realm = " + pluginRealm.getId()).append('\n');
                for (int j = 0; j < pluginRealm.getConstituents().length; ++j) {
                    sb.append("urls[" + j + "] = " + pluginRealm.getConstituents()[j]);
                    if (j != pluginRealm.getConstituents().length - 1) {
                        sb.append('\n');
                    }
                }
                this.getLogger().fatalError(sb.toString());
                final ClassRealm containerRealm = this.container.getContainerRealm();
                sb = new StringBuffer();
                sb.append("Container realm = " + containerRealm.getId()).append('\n');
                for (int k = 0; k < containerRealm.getConstituents().length; ++k) {
                    sb.append("urls[" + k + "] = " + containerRealm.getConstituents()[k]);
                    if (k != containerRealm.getConstituents().length - 1) {
                        sb.append('\n');
                    }
                }
                this.getLogger().fatalError(sb.toString());
            }
            session.getEventDispatcher().dispatchError(event, goalExecId, e3);
            throw e3;
        }
        finally {
            Thread.currentThread().setContextClassLoader(oldClassLoader);
            try {
                final PlexusContainer pluginContainer = this.getPluginContainer(mojoDescriptor.getPluginDescriptor());
                pluginContainer.release(plugin);
            }
            catch (ComponentLifecycleException e4) {
                if (this.getLogger().isErrorEnabled()) {
                    this.getLogger().error("Error releasing plugin - ignoring.", e4);
                }
            }
        }
    }
    
    public MavenReport getReport(final MavenProject project, final MojoExecution mojoExecution, final MavenSession session) throws ArtifactNotFoundException, PluginConfigurationException, PluginManagerException, ArtifactResolutionException {
        final MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();
        final PluginDescriptor descriptor = mojoDescriptor.getPluginDescriptor();
        Xpp3Dom dom = project.getReportConfiguration(descriptor.getGroupId(), descriptor.getArtifactId(), mojoExecution.getExecutionId());
        if (mojoExecution.getConfiguration() != null) {
            dom = Xpp3Dom.mergeXpp3Dom(dom, mojoExecution.getConfiguration());
        }
        return (MavenReport)this.getConfiguredMojo(session, dom, project, true, mojoExecution);
    }
    
    public PluginDescriptor verifyReportPlugin(final ReportPlugin reportPlugin, final MavenProject project, final MavenSession session) throws PluginVersionResolutionException, ArtifactResolutionException, ArtifactNotFoundException, InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException, PluginVersionNotFoundException {
        String version = reportPlugin.getVersion();
        if (version == null) {
            version = this.pluginVersionManager.resolveReportPluginVersion(reportPlugin.getGroupId(), reportPlugin.getArtifactId(), project, session.getSettings(), session.getLocalRepository());
            reportPlugin.setVersion(version);
        }
        final Plugin forLookup = new Plugin();
        forLookup.setGroupId(reportPlugin.getGroupId());
        forLookup.setArtifactId(reportPlugin.getArtifactId());
        forLookup.setVersion(version);
        return this.verifyVersionedPlugin(forLookup, project, session.getLocalRepository());
    }
    
    private PlexusContainer getPluginContainer(final PluginDescriptor pluginDescriptor) throws PluginManagerException {
        final String pluginKey = pluginDescriptor.getPluginLookupKey();
        final PlexusContainer pluginContainer = this.container.getChildContainer(pluginKey);
        if (pluginContainer == null) {
            throw new PluginManagerException("Cannot find Plexus container for plugin: " + pluginKey);
        }
        return pluginContainer;
    }
    
    private Mojo getConfiguredMojo(final MavenSession session, final Xpp3Dom dom, final MavenProject project, final boolean report, final MojoExecution mojoExecution) throws PluginConfigurationException, ArtifactNotFoundException, PluginManagerException, ArtifactResolutionException {
        final MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();
        final PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
        final PlexusContainer pluginContainer = this.getPluginContainer(pluginDescriptor);
        this.ensurePluginContainerIsComplete(pluginDescriptor, pluginContainer, project, session);
        Mojo plugin;
        try {
            plugin = (Mojo)pluginContainer.lookup(Mojo.ROLE, mojoDescriptor.getRoleHint());
            if (report && !(plugin instanceof MavenReport)) {
                return null;
            }
        }
        catch (ComponentLookupException e) {
            throw new PluginManagerException("Unable to find the mojo '" + mojoDescriptor.getRoleHint() + "' in the plugin '" + pluginDescriptor.getPluginLookupKey() + "'", e);
        }
        if (plugin instanceof ContextEnabled) {
            final Map pluginContext = session.getPluginContext(pluginDescriptor, project);
            ((ContextEnabled)plugin).setPluginContext(pluginContext);
        }
        plugin.setLog(this.mojoLogger);
        XmlPlexusConfiguration pomConfiguration;
        if (dom == null) {
            pomConfiguration = new XmlPlexusConfiguration("configuration");
        }
        else {
            pomConfiguration = new XmlPlexusConfiguration(dom);
        }
        this.validatePomConfiguration(mojoDescriptor, pomConfiguration);
        final PlexusConfiguration mergedConfiguration = this.mergeMojoConfiguration(pomConfiguration, mojoDescriptor);
        final ExpressionEvaluator expressionEvaluator = new PluginParameterExpressionEvaluator(session, mojoExecution, this.pathTranslator, this.getLogger(), project, session.getExecutionProperties());
        final PlexusConfiguration extractedMojoConfiguration = this.extractMojoConfiguration(mergedConfiguration, mojoDescriptor);
        this.checkRequiredParameters(mojoDescriptor, extractedMojoConfiguration, expressionEvaluator);
        this.populatePluginFields(plugin, mojoDescriptor, extractedMojoConfiguration, pluginContainer, expressionEvaluator);
        return plugin;
    }
    
    private void ensurePluginContainerIsComplete(final PluginDescriptor pluginDescriptor, final PlexusContainer pluginContainer, final MavenProject project, final MavenSession session) throws ArtifactNotFoundException, PluginManagerException, ArtifactResolutionException {
        if (pluginDescriptor.getArtifacts() != null && pluginDescriptor.getArtifacts().size() == 1) {
            final Artifact pluginArtifact = pluginDescriptor.getArtifacts().get(0);
            final ArtifactRepository localRepository = session.getLocalRepository();
            ResolutionGroup resolutionGroup;
            try {
                resolutionGroup = this.artifactMetadataSource.retrieve(pluginArtifact, localRepository, project.getPluginArtifactRepositories());
            }
            catch (ArtifactMetadataRetrievalException e) {
                throw new ArtifactResolutionException("Unable to download metadata from repository for plugin '" + pluginArtifact.getId() + "': " + e.getMessage(), pluginArtifact, e);
            }
            checkPlexusUtils(resolutionGroup, this.artifactFactory);
            final Map dependencyMap = new LinkedHashMap();
            final List all = new ArrayList();
            all.addAll(pluginDescriptor.getIntroducedDependencyArtifacts());
            all.addAll(resolutionGroup.getArtifacts());
            for (final Artifact artifact : all) {
                final String conflictId = artifact.getDependencyConflictId();
                if (!dependencyMap.containsKey(conflictId)) {
                    dependencyMap.put(conflictId, artifact);
                }
            }
            final Set dependencies = new LinkedHashSet(dependencyMap.values());
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Plugin dependencies for:\n\n" + pluginDescriptor.getId() + "\n\nare:\n\n" + StringUtils.join(dependencies.iterator(), "\n") + "\n\n");
            }
            final List repositories = new ArrayList();
            repositories.addAll(resolutionGroup.getResolutionRepositories());
            repositories.addAll(project.getRemoteArtifactRepositories());
            Map pluginManagedDependencies = new HashMap();
            try {
                final MavenProject pluginProject = this.mavenProjectBuilder.buildFromRepository(pluginArtifact, project.getRemoteArtifactRepositories(), localRepository);
                if (pluginProject != null) {
                    pluginManagedDependencies = pluginProject.getManagedVersionMap();
                }
            }
            catch (ProjectBuildingException ex) {}
            final ArtifactResolutionResult result = this.artifactResolver.resolveTransitively(dependencies, pluginArtifact, pluginManagedDependencies, localRepository, repositories, this.artifactMetadataSource, this.artifactFilter);
            final Set resolved = result.getArtifacts();
            for (Artifact artifact2 : resolved) {
                if (!artifact2.equals(pluginArtifact)) {
                    artifact2 = project.replaceWithActiveArtifact(artifact2);
                    try {
                        pluginContainer.addJarResource(artifact2.getFile());
                    }
                    catch (PlexusContainerException e2) {
                        throw new PluginManagerException("Error adding plugin dependency '" + artifact2.getDependencyConflictId() + "' into plugin manager: " + e2.getMessage(), e2);
                    }
                }
            }
            pluginDescriptor.setClassRealm(pluginContainer.getContainerRealm());
            final List unresolved = new ArrayList(dependencies);
            unresolved.removeAll(resolved);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(" The following artifacts were filtered out for plugin: " + pluginDescriptor.getId() + " because they're already in the core of Maven:\n\n" + StringUtils.join(unresolved.iterator(), "\n") + "\n\nThese will use the artifact files already in the core ClassRealm instead, to allow them to be included in PluginDescriptor.getArtifacts().\n\n");
            }
            this.resolveCoreArtifacts(unresolved, localRepository, resolutionGroup.getResolutionRepositories());
            final List allResolved = new ArrayList(resolved.size() + unresolved.size());
            allResolved.addAll(resolved);
            allResolved.addAll(unresolved);
            pluginDescriptor.setArtifacts(allResolved);
        }
    }
    
    public static void checkPlexusUtils(final ResolutionGroup resolutionGroup, final ArtifactFactory artifactFactory) {
        VersionRange vr = null;
        try {
            vr = VersionRange.createFromVersionSpec("[1.1,)");
        }
        catch (InvalidVersionSpecificationException ex) {}
        boolean plexusUtilsPresent = false;
        for (final Artifact a : resolutionGroup.getArtifacts()) {
            if (a.getArtifactId().equals("plexus-utils") && vr.containsVersion(new DefaultArtifactVersion(a.getVersion()))) {
                plexusUtilsPresent = true;
                break;
            }
        }
        if (!plexusUtilsPresent) {
            resolutionGroup.getArtifacts().add(artifactFactory.createArtifact("org.codehaus.plexus", "plexus-utils", "1.1", "runtime", "jar"));
        }
    }
    
    private void resolveCoreArtifacts(final List unresolved, final ArtifactRepository localRepository, final List resolutionRepositories) throws ArtifactResolutionException, ArtifactNotFoundException {
        for (final Artifact artifact : unresolved) {
            File artifactFile = this.resolvedCoreArtifactFiles.get(artifact.getId());
            if (artifactFile == null) {
                final String resource = "/META-INF/maven/" + artifact.getGroupId() + "/" + artifact.getArtifactId() + "/pom.xml";
                final URL resourceUrl = this.container.getContainerRealm().getResource(resource);
                if (resourceUrl == null) {
                    this.artifactResolver.resolve(artifact, resolutionRepositories, localRepository);
                    artifactFile = artifact.getFile();
                }
                else {
                    String artifactPath = resourceUrl.getPath();
                    if (artifactPath.startsWith("file:")) {
                        artifactPath = artifactPath.substring("file:".length());
                    }
                    artifactPath = artifactPath.substring(0, artifactPath.length() - resource.length());
                    if (artifactPath.endsWith("/")) {
                        artifactPath = artifactPath.substring(0, artifactPath.length() - 1);
                    }
                    if (artifactPath.endsWith("!")) {
                        artifactPath = artifactPath.substring(0, artifactPath.length() - 1);
                    }
                    artifactFile = new File(artifactPath).getAbsoluteFile();
                }
                this.resolvedCoreArtifactFiles.put(artifact.getId(), artifactFile);
            }
            artifact.setFile(artifactFile);
        }
    }
    
    private PlexusConfiguration extractMojoConfiguration(final PlexusConfiguration mergedConfiguration, final MojoDescriptor mojoDescriptor) {
        final Map parameterMap = mojoDescriptor.getParameterMap();
        final PlexusConfiguration[] mergedChildren = mergedConfiguration.getChildren();
        final XmlPlexusConfiguration extractedConfiguration = new XmlPlexusConfiguration("configuration");
        for (int i = 0; i < mergedChildren.length; ++i) {
            final PlexusConfiguration child = mergedChildren[i];
            if (parameterMap.containsKey(child.getName())) {
                extractedConfiguration.addChild(copyConfiguration(child));
            }
            else {
                this.getLogger().debug("*** WARNING: Configuration '" + child.getName() + "' is not used in goal '" + mojoDescriptor.getFullGoalName() + "; this may indicate a typo... ***");
            }
        }
        return extractedConfiguration;
    }
    
    private void checkRequiredParameters(final MojoDescriptor goal, final PlexusConfiguration configuration, final ExpressionEvaluator expressionEvaluator) throws PluginConfigurationException {
        final List parameters = goal.getParameters();
        if (parameters == null) {
            return;
        }
        final List invalidParameters = new ArrayList();
        for (int i = 0; i < parameters.size(); ++i) {
            final Parameter parameter = parameters.get(i);
            if (parameter.isRequired()) {
                final String key = parameter.getName();
                Object fieldValue = null;
                String expression = null;
                PlexusConfiguration value = configuration.getChild(key, false);
                try {
                    if (value != null) {
                        expression = value.getValue(null);
                        fieldValue = expressionEvaluator.evaluate(expression);
                        if (fieldValue == null) {
                            fieldValue = value.getAttribute("default-value", null);
                        }
                    }
                    if (fieldValue == null && StringUtils.isNotEmpty(parameter.getAlias())) {
                        value = configuration.getChild(parameter.getAlias(), false);
                        if (value != null) {
                            expression = value.getValue(null);
                            fieldValue = expressionEvaluator.evaluate(expression);
                            if (fieldValue == null) {
                                fieldValue = value.getAttribute("default-value", null);
                            }
                        }
                    }
                }
                catch (ExpressionEvaluationException e) {
                    throw new PluginConfigurationException(goal.getPluginDescriptor(), e.getMessage(), e);
                }
                if (fieldValue == null && (value == null || value.getChildCount() == 0)) {
                    parameter.setExpression(expression);
                    invalidParameters.add(parameter);
                }
            }
        }
        if (!invalidParameters.isEmpty()) {
            throw new PluginParameterException(goal, invalidParameters);
        }
    }
    
    private void validatePomConfiguration(final MojoDescriptor goal, final PlexusConfiguration pomConfiguration) throws PluginConfigurationException {
        final List parameters = goal.getParameters();
        if (parameters == null) {
            return;
        }
        for (int i = 0; i < parameters.size(); ++i) {
            final Parameter parameter = parameters.get(i);
            String key = parameter.getName();
            PlexusConfiguration value = pomConfiguration.getChild(key, false);
            if (value == null && StringUtils.isNotEmpty(parameter.getAlias())) {
                key = parameter.getAlias();
                value = pomConfiguration.getChild(key, false);
            }
            if (value != null) {
                if (!parameter.isEditable()) {
                    final StringBuffer errorMessage = new StringBuffer().append("ERROR: Cannot override read-only parameter: ");
                    errorMessage.append(key);
                    errorMessage.append(" in goal: ").append(goal.getFullGoalName());
                    throw new PluginConfigurationException(goal.getPluginDescriptor(), errorMessage.toString());
                }
                final String deprecated = parameter.getDeprecated();
                if (StringUtils.isNotEmpty(deprecated)) {
                    this.getLogger().warn("DEPRECATED [" + parameter.getName() + "]: " + deprecated);
                }
            }
        }
    }
    
    private PlexusConfiguration mergeMojoConfiguration(final XmlPlexusConfiguration fromPom, final MojoDescriptor mojoDescriptor) {
        final XmlPlexusConfiguration result = new XmlPlexusConfiguration(fromPom.getName());
        result.setValue(fromPom.getValue(null));
        if (mojoDescriptor.getParameters() != null) {
            final PlexusConfiguration fromMojo = mojoDescriptor.getMojoConfiguration();
            for (final Parameter parameter : mojoDescriptor.getParameters()) {
                final String paramName = parameter.getName();
                final String alias = parameter.getAlias();
                final String implementation = parameter.getImplementation();
                PlexusConfiguration pomConfig = fromPom.getChild(paramName);
                PlexusConfiguration aliased = null;
                if (alias != null) {
                    aliased = fromPom.getChild(alias);
                }
                final PlexusConfiguration mojoConfig = fromMojo.getChild(paramName, false);
                if (aliased != null) {
                    if (pomConfig == null) {
                        pomConfig = new XmlPlexusConfiguration(paramName);
                    }
                    pomConfig = this.buildTopDownMergedConfiguration(pomConfig, aliased);
                }
                PlexusConfiguration toAdd = null;
                if (pomConfig != null) {
                    pomConfig = this.buildTopDownMergedConfiguration(pomConfig, mojoConfig);
                    if (StringUtils.isNotEmpty(pomConfig.getValue(null)) || pomConfig.getChildCount() > 0) {
                        toAdd = pomConfig;
                    }
                }
                if (toAdd == null && mojoConfig != null) {
                    toAdd = copyConfiguration(mojoConfig);
                }
                if (toAdd != null) {
                    if (implementation != null && toAdd.getAttribute("implementation", null) == null) {
                        final XmlPlexusConfiguration implementationConf = new XmlPlexusConfiguration(paramName);
                        implementationConf.setAttribute("implementation", parameter.getImplementation());
                        toAdd = this.buildTopDownMergedConfiguration(toAdd, implementationConf);
                    }
                    result.addChild(toAdd);
                }
            }
        }
        return result;
    }
    
    private XmlPlexusConfiguration buildTopDownMergedConfiguration(final PlexusConfiguration dominant, final PlexusConfiguration recessive) {
        final XmlPlexusConfiguration result = new XmlPlexusConfiguration(dominant.getName());
        String value = dominant.getValue(null);
        if (StringUtils.isEmpty(value) && recessive != null) {
            value = recessive.getValue(null);
        }
        if (StringUtils.isNotEmpty(value)) {
            result.setValue(value);
        }
        String[] attributeNames = dominant.getAttributeNames();
        for (int i = 0; i < attributeNames.length; ++i) {
            final String attributeValue = dominant.getAttribute(attributeNames[i], null);
            result.setAttribute(attributeNames[i], attributeValue);
        }
        if (recessive != null) {
            attributeNames = recessive.getAttributeNames();
            for (int i = 0; i < attributeNames.length; ++i) {
                final String attributeValue = recessive.getAttribute(attributeNames[i], null);
                result.setAttribute(attributeNames[i], attributeValue);
            }
        }
        final PlexusConfiguration[] children = dominant.getChildren();
        for (int j = 0; j < children.length; ++j) {
            final PlexusConfiguration childDom = children[j];
            final PlexusConfiguration childRec = (recessive == null) ? null : recessive.getChild(childDom.getName(), false);
            if (childRec != null) {
                result.addChild(this.buildTopDownMergedConfiguration(childDom, childRec));
            }
            else {
                result.addChild(copyConfiguration(childDom));
            }
        }
        return result;
    }
    
    public static PlexusConfiguration copyConfiguration(final PlexusConfiguration src) {
        final XmlPlexusConfiguration dom = new XmlPlexusConfiguration(src.getName());
        dom.setValue(src.getValue(null));
        final String[] attributeNames = src.getAttributeNames();
        for (int i = 0; i < attributeNames.length; ++i) {
            final String attributeName = attributeNames[i];
            dom.setAttribute(attributeName, src.getAttribute(attributeName, null));
        }
        final PlexusConfiguration[] children = src.getChildren();
        for (int j = 0; j < children.length; ++j) {
            dom.addChild(copyConfiguration(children[j]));
        }
        return dom;
    }
    
    private void populatePluginFields(final Mojo plugin, final MojoDescriptor mojoDescriptor, final PlexusConfiguration configuration, final PlexusContainer pluginContainer, final ExpressionEvaluator expressionEvaluator) throws PluginConfigurationException {
        ComponentConfigurator configurator = null;
        try {
            final String configuratorId = mojoDescriptor.getComponentConfigurator();
            if (StringUtils.isNotEmpty(configuratorId)) {
                configurator = (ComponentConfigurator)pluginContainer.lookup(ComponentConfigurator.ROLE, configuratorId);
            }
            else {
                configurator = (ComponentConfigurator)pluginContainer.lookup(ComponentConfigurator.ROLE);
            }
            final ConfigurationListener listener = new DebugConfigurationListener(this.getLogger());
            this.getLogger().debug("Configuring mojo '" + mojoDescriptor.getId() + "' -->");
            configurator.configureComponent(plugin, configuration, expressionEvaluator, pluginContainer.getContainerRealm(), listener);
            this.getLogger().debug("-- end configuration --");
        }
        catch (ComponentConfigurationException e) {
            throw new PluginConfigurationException(mojoDescriptor.getPluginDescriptor(), "Unable to parse the created DOM for plugin configuration", e);
        }
        catch (ComponentLookupException e2) {
            throw new PluginConfigurationException(mojoDescriptor.getPluginDescriptor(), "Unable to retrieve component configurator for plugin configuration", e2);
        }
        catch (LinkageError e3) {
            if (this.getLogger().isFatalErrorEnabled()) {
                this.getLogger().fatalError(configurator.getClass().getName() + "#configureComponent(...) caused a linkage error (" + e3.getClass().getName() + ") and may be out-of-date. Check the realms:");
                final ClassRealm pluginRealm = mojoDescriptor.getPluginDescriptor().getClassRealm();
                StringBuffer sb = new StringBuffer();
                sb.append("Plugin realm = " + pluginRealm.getId()).append('\n');
                for (int i = 0; i < pluginRealm.getConstituents().length; ++i) {
                    sb.append("urls[" + i + "] = " + pluginRealm.getConstituents()[i]);
                    if (i != pluginRealm.getConstituents().length - 1) {
                        sb.append('\n');
                    }
                }
                this.getLogger().fatalError(sb.toString());
                final ClassRealm containerRealm = this.container.getContainerRealm();
                sb = new StringBuffer();
                sb.append("Container realm = " + containerRealm.getId()).append('\n');
                for (int j = 0; j < containerRealm.getConstituents().length; ++j) {
                    sb.append("urls[" + j + "] = " + containerRealm.getConstituents()[j]);
                    if (j != containerRealm.getConstituents().length - 1) {
                        sb.append('\n');
                    }
                }
                this.getLogger().fatalError(sb.toString());
            }
            throw new PluginConfigurationException(mojoDescriptor.getPluginDescriptor(), e3.getClass().getName() + ": " + e3.getMessage(), e3);
        }
        finally {
            if (configurator != null) {
                try {
                    pluginContainer.release(configurator);
                }
                catch (ComponentLifecycleException e4) {
                    this.getLogger().debug("Failed to release plugin container - ignoring.");
                }
            }
        }
    }
    
    public static String createPluginParameterRequiredMessage(final MojoDescriptor mojo, final Parameter parameter, final String expression) {
        final StringBuffer message = new StringBuffer();
        message.append("The '");
        message.append(parameter.getName());
        message.append("' parameter is required for the execution of the ");
        message.append(mojo.getFullGoalName());
        message.append(" mojo and cannot be null.");
        if (expression != null) {
            message.append(" The retrieval expression was: ").append(expression);
        }
        return message.toString();
    }
    
    public void contextualize(final Context context) throws ContextException {
        this.container = (PlexusContainer)context.get("plexus");
        this.mojoLogger = new DefaultLog(this.container.getLoggerManager().getLoggerForComponent(Mojo.ROLE));
    }
    
    public void initialize() {
        this.artifactFilter = MavenArtifactFilterManager.createStandardFilter();
    }
    
    private void resolveTransitiveDependencies(final MavenSession context, final ArtifactResolver artifactResolver, final String scope, final ArtifactFactory artifactFactory, final MavenProject project, final boolean isAggregator) throws ArtifactResolutionException, ArtifactNotFoundException, InvalidDependencyVersionException {
        final ArtifactFilter filter = new ScopeArtifactFilter(scope);
        final Artifact artifact = artifactFactory.createBuildArtifact(project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getPackaging());
        if (project.getDependencyArtifacts() == null) {
            project.setDependencyArtifacts(project.createArtifacts(artifactFactory, null, null));
        }
        Set resolvedArtifacts;
        try {
            final ArtifactResolutionResult result = artifactResolver.resolveTransitively(project.getDependencyArtifacts(), artifact, project.getManagedVersionMap(), context.getLocalRepository(), project.getRemoteArtifactRepositories(), this.artifactMetadataSource, filter);
            resolvedArtifacts = result.getArtifacts();
        }
        catch (MultipleArtifactsNotFoundException me) {
            if (!isAggregator || !this.checkMissingArtifactsInReactor(context.getSortedProjects(), me.getMissingArtifacts())) {
                throw me;
            }
            resolvedArtifacts = new HashSet(me.getResolvedArtifacts());
        }
        project.setArtifacts(resolvedArtifacts);
    }
    
    private boolean checkMissingArtifactsInReactor(final Collection projects, final Collection missing) {
        final Collection foundInReactor = new HashSet();
        for (final Artifact mArtifact : missing) {
            for (final MavenProject p : projects) {
                if (p.getArtifactId().equals(mArtifact.getArtifactId()) && p.getGroupId().equals(mArtifact.getGroupId()) && p.getVersion().equals(mArtifact.getVersion())) {
                    this.getLogger().warn("The dependency: " + p.getId() + " can't be resolved but has been found in the reactor.\nThis dependency has been excluded from the plugin execution. You should rerun this mojo after executing mvn install.\n");
                    foundInReactor.add(p);
                    break;
                }
            }
        }
        return foundInReactor.size() == missing.size();
    }
    
    private void downloadDependencies(final MavenProject project, final MavenSession context, final ArtifactResolver artifactResolver) throws ArtifactResolutionException, ArtifactNotFoundException {
        final ArtifactRepository localRepository = context.getLocalRepository();
        final List remoteArtifactRepositories = project.getRemoteArtifactRepositories();
        for (final Artifact artifact : project.getArtifacts()) {
            artifactResolver.resolve(artifact, remoteArtifactRepositories, localRepository);
        }
    }
    
    public Object getPluginComponent(final Plugin plugin, final String role, final String roleHint) throws PluginManagerException, ComponentLookupException {
        final PluginDescriptor pluginDescriptor = this.pluginCollector.getPluginDescriptor(plugin);
        final PlexusContainer pluginContainer = this.getPluginContainer(pluginDescriptor);
        return pluginContainer.lookup(role, roleHint);
    }
    
    public Map getPluginComponents(final Plugin plugin, final String role) throws ComponentLookupException, PluginManagerException {
        final PluginDescriptor pluginDescriptor = this.pluginCollector.getPluginDescriptor(plugin);
        final PlexusContainer pluginContainer = this.getPluginContainer(pluginDescriptor);
        return pluginContainer.lookupMap(role);
    }
}
