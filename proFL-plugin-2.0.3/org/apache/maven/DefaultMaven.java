// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven;

import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.context.Context;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Mirror;
import org.apache.maven.wagon.repository.RepositoryPermissions;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.settings.Server;
import org.apache.maven.project.ProjectBuilderConfiguration;
import org.apache.maven.project.DefaultProjectBuilderConfiguration;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import java.util.Collections;
import org.apache.maven.monitor.event.DefaultEventDispatcher;
import java.util.Properties;
import org.apache.maven.settings.Settings;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.Collection;
import org.codehaus.plexus.util.Os;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import java.io.File;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import org.apache.maven.profiles.activation.ProfileActivationException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import java.io.IOException;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.execution.MavenSession;
import java.util.List;
import org.apache.maven.profiles.ProfileManager;
import org.apache.maven.project.DuplicateProjectException;
import org.codehaus.plexus.util.dag.CycleDetectedException;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.usability.SystemWarnings;
import org.apache.maven.execution.BuildFailure;
import java.util.Iterator;
import org.apache.maven.project.MavenProject;
import org.apache.maven.execution.ReactorManager;
import org.apache.maven.monitor.event.EventDispatcher;
import org.apache.maven.lifecycle.LifecycleExecutionException;
import org.apache.maven.reactor.MavenExecutionException;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.RuntimeInformation;
import org.apache.maven.usability.diagnostics.ErrorDiagnostics;
import org.codehaus.plexus.PlexusContainer;
import org.apache.maven.lifecycle.LifecycleExecutor;
import org.apache.maven.project.MavenProjectBuilder;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultMaven extends AbstractLogEnabled implements Maven, Contextualizable
{
    protected MavenProjectBuilder projectBuilder;
    protected LifecycleExecutor lifecycleExecutor;
    protected PlexusContainer container;
    protected ErrorDiagnostics errorDiagnostics;
    protected RuntimeInformation runtimeInformation;
    private static final long MB = 1048576L;
    private static final int MS_PER_SEC = 1000;
    private static final int SEC_PER_MIN = 60;
    
    public void execute(final MavenExecutionRequest request) throws MavenExecutionException {
        final EventDispatcher dispatcher = request.getEventDispatcher();
        final String event = "reactor-execute";
        dispatcher.dispatchStart(event, request.getBaseDirectory());
        ReactorManager rm;
        try {
            rm = this.doExecute(request, dispatcher);
        }
        catch (LifecycleExecutionException e) {
            dispatcher.dispatchError(event, request.getBaseDirectory(), e);
            this.logError(e, request.isShowErrors());
            this.stats(request.getStartTime());
            this.line();
            throw new MavenExecutionException(e.getMessage(), e);
        }
        catch (BuildFailureException e2) {
            dispatcher.dispatchError(event, request.getBaseDirectory(), e2);
            this.logFailure(e2, request.isShowErrors());
            this.stats(request.getStartTime());
            this.line();
            throw new MavenExecutionException(e2.getMessage(), e2);
        }
        catch (Throwable t) {
            dispatcher.dispatchError(event, request.getBaseDirectory(), t);
            this.logFatal(t);
            this.stats(request.getStartTime());
            this.line();
            throw new MavenExecutionException("Error executing project within the reactor", t);
        }
        this.logReactorSummary(rm);
        if (rm.hasBuildFailures()) {
            this.logErrors(rm, request.isShowErrors());
            if (!"fail-never".equals(rm.getFailureBehavior())) {
                dispatcher.dispatchError(event, request.getBaseDirectory(), null);
                this.getLogger().info("BUILD ERRORS");
                this.line();
                this.stats(request.getStartTime());
                this.line();
                throw new MavenExecutionException("Some builds failed");
            }
            this.getLogger().info(" + Ignoring failures");
        }
        this.logSuccess(rm);
        this.stats(request.getStartTime());
        this.line();
        dispatcher.dispatchEnd(event, request.getBaseDirectory());
    }
    
    private void logErrors(final ReactorManager rm, final boolean showErrors) {
        for (final MavenProject project : rm.getSortedProjects()) {
            if (rm.hasBuildFailure(project)) {
                final BuildFailure buildFailure = rm.getBuildFailure(project);
                this.getLogger().info("Error for project: " + project.getName() + " (during " + buildFailure.getTask() + ")");
                this.line();
                this.logDiagnostics(buildFailure.getCause());
                this.logTrace(buildFailure.getCause(), showErrors);
            }
        }
        if (!showErrors) {
            this.getLogger().info("For more information, run Maven with the -e switch");
            this.line();
        }
    }
    
    private ReactorManager doExecute(final MavenExecutionRequest request, final EventDispatcher dispatcher) throws MavenExecutionException, BuildFailureException, LifecycleExecutionException {
        if (request.getSettings().isOffline()) {
            this.getLogger().info(SystemWarnings.getOfflineWarning());
            WagonManager wagonManager = null;
            try {
                wagonManager = (WagonManager)this.container.lookup(WagonManager.ROLE);
                wagonManager.setOnline(false);
            }
            catch (ComponentLookupException e) {
                throw new MavenExecutionException("Cannot retrieve WagonManager in order to set offline mode.", e);
            }
            finally {
                try {
                    this.container.release(wagonManager);
                }
                catch (ComponentLifecycleException e2) {
                    this.getLogger().warn("Cannot release WagonManager.", e2);
                }
            }
        }
        try {
            this.resolveParameters(request.getSettings());
        }
        catch (ComponentLookupException e3) {
            throw new MavenExecutionException("Unable to configure Maven for execution", e3);
        }
        catch (ComponentLifecycleException e4) {
            throw new MavenExecutionException("Unable to configure Maven for execution", e4);
        }
        catch (SettingsConfigurationException e5) {
            throw new MavenExecutionException("Unable to configure Maven for execution", e5);
        }
        final ProfileManager globalProfileManager = request.getGlobalProfileManager();
        globalProfileManager.loadSettingsProfiles(request.getSettings());
        this.getLogger().info("Scanning for projects...");
        boolean foundProjects = true;
        final List projects = this.getProjects(request);
        if (projects.isEmpty()) {
            projects.add(this.getSuperProject(request));
            foundProjects = false;
        }
        ReactorManager rm;
        try {
            rm = new ReactorManager(projects);
            final String requestFailureBehavior = request.getFailureBehavior();
            if (requestFailureBehavior != null) {
                rm.setFailureBehavior(requestFailureBehavior);
            }
        }
        catch (CycleDetectedException e6) {
            throw new BuildFailureException("The projects in the reactor contain a cyclic reference: " + e6.getMessage(), e6);
        }
        catch (DuplicateProjectException e7) {
            throw new BuildFailureException(e7.getMessage(), e7);
        }
        if (rm.hasMultipleProjects()) {
            this.getLogger().info("Reactor build order: ");
            for (final MavenProject project : rm.getSortedProjects()) {
                this.getLogger().info("  " + project.getName());
            }
        }
        final MavenSession session = this.createSession(request, rm);
        session.setUsingPOMsFromFilesystem(foundProjects);
        this.lifecycleExecutor.execute(session, rm, dispatcher);
        return rm;
    }
    
    private MavenProject getSuperProject(final MavenExecutionRequest request) throws MavenExecutionException {
        MavenProject superProject;
        try {
            superProject = this.projectBuilder.buildStandaloneSuperProject(request.getLocalRepository(), request.getGlobalProfileManager());
        }
        catch (ProjectBuildingException e) {
            throw new MavenExecutionException(e.getMessage(), e);
        }
        return superProject;
    }
    
    private List getProjects(final MavenExecutionRequest request) throws MavenExecutionException, BuildFailureException {
        List projects;
        try {
            final List files = this.getProjectFiles(request);
            projects = this.collectProjects(files, request, !request.isReactorActive());
        }
        catch (IOException e) {
            throw new MavenExecutionException("Error processing projects for the reactor: " + e.getMessage(), e);
        }
        catch (ArtifactResolutionException e2) {
            throw new MavenExecutionException(e2.getMessage(), e2);
        }
        catch (ProjectBuildingException e3) {
            throw new MavenExecutionException(e3.getMessage(), e3);
        }
        catch (ProfileActivationException e4) {
            throw new MavenExecutionException(e4.getMessage(), e4);
        }
        return projects;
    }
    
    private void logReactorSummaryLine(final String name, final String status) {
        this.logReactorSummaryLine(name, status, -1L);
    }
    
    private void logReactorSummaryLine(final String name, final String status, final long time) {
        final StringBuffer messageBuffer = new StringBuffer();
        messageBuffer.append(name);
        int dotCount = 54;
        dotCount -= name.length();
        messageBuffer.append(" ");
        for (int i = 0; i < dotCount; ++i) {
            messageBuffer.append('.');
        }
        messageBuffer.append(" ");
        messageBuffer.append(status);
        if (time >= 0L) {
            messageBuffer.append(" [");
            messageBuffer.append(getFormattedTime(time));
            messageBuffer.append("]");
        }
        this.getLogger().info(messageBuffer.toString());
    }
    
    private static String getFormattedTime(final long time) {
        String pattern = "s.SSS's'";
        if (time / 60000L > 0L) {
            pattern = "m:s" + pattern;
            if (time / 3600000L > 0L) {
                pattern = "H:m" + pattern;
            }
        }
        final DateFormat fmt = new SimpleDateFormat(pattern);
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return fmt.format(new Date(time));
    }
    
    private List collectProjects(final List files, final MavenExecutionRequest request, final boolean isRoot) throws ArtifactResolutionException, ProjectBuildingException, ProfileActivationException, MavenExecutionException, BuildFailureException {
        final List projects = new ArrayList(files.size());
        for (final File file : files) {
            boolean usingReleasePom = false;
            if ("release-pom.xml".equals(file.getName())) {
                this.getLogger().info("NOTE: Using release-pom: " + file + " in reactor build.");
                usingReleasePom = true;
            }
            final MavenProject project = this.getProject(file, request);
            if (isRoot) {
                project.setExecutionRoot(true);
            }
            if (project.getPrerequisites() != null && project.getPrerequisites().getMaven() != null) {
                final DefaultArtifactVersion version = new DefaultArtifactVersion(project.getPrerequisites().getMaven());
                if (this.runtimeInformation.getApplicationVersion().compareTo(version) < 0) {
                    throw new BuildFailureException("Unable to build project '" + project.getFile() + "; it requires Maven version " + version.toString());
                }
            }
            if (project.getModules() != null && !project.getModules().isEmpty() && request.isRecursive()) {
                project.setPackaging("pom");
                final File basedir = file.getParentFile();
                final List moduleFiles = new ArrayList(project.getModules().size());
                for (final String name : project.getModules()) {
                    if (StringUtils.isEmpty(StringUtils.trim(name))) {
                        this.getLogger().warn("Empty module detected. Please check you don't have any empty module definitions in your POM.");
                    }
                    else {
                        File moduleFile = new File(basedir, name);
                        if (moduleFile.exists() && moduleFile.isDirectory()) {
                            if (usingReleasePom) {
                                moduleFile = new File(basedir, name + "/" + "release-pom.xml");
                            }
                            else {
                                moduleFile = new File(basedir, name + "/" + "pom.xml");
                            }
                        }
                        Label_0527: {
                            if (Os.isFamily("windows")) {
                                try {
                                    moduleFile = moduleFile.getCanonicalFile();
                                    break Label_0527;
                                }
                                catch (IOException e) {
                                    throw new MavenExecutionException("Unable to canonicalize file name " + moduleFile, e);
                                }
                            }
                            moduleFile = new File(moduleFile.toURI().normalize());
                        }
                        moduleFiles.add(moduleFile);
                    }
                }
                final List collectedProjects = this.collectProjects(moduleFiles, request, false);
                projects.addAll(collectedProjects);
                project.setCollectedProjects(collectedProjects);
            }
            projects.add(project);
        }
        return projects;
    }
    
    public MavenProject getProject(final File pom, final ArtifactRepository localRepository, final Settings settings, final Properties userProperties, final ProfileManager globalProfileManager) throws ProjectBuildingException, ArtifactResolutionException, ProfileActivationException {
        final MavenExecutionRequest request = new DefaultMavenExecutionRequest(localRepository, settings, new DefaultEventDispatcher(), Collections.EMPTY_LIST, pom.getParentFile().getAbsolutePath(), globalProfileManager, globalProfileManager.getRequestProperties(), new Properties(), false);
        return this.getProject(pom, request);
    }
    
    public MavenProject getProject(final File pom, final MavenExecutionRequest request) throws ProjectBuildingException, ArtifactResolutionException, ProfileActivationException {
        if (pom.exists() && pom.length() == 0L) {
            throw new ProjectBuildingException("unknown", "The file " + pom.getAbsolutePath() + " you specified has zero length.");
        }
        final ProjectBuilderConfiguration config = new DefaultProjectBuilderConfiguration();
        config.setLocalRepository(request.getLocalRepository()).setGlobalProfileManager(request.getGlobalProfileManager()).setUserProperties(request.getUserProperties());
        return this.projectBuilder.build(pom, config);
    }
    
    protected MavenSession createSession(final MavenExecutionRequest request, final ReactorManager rpm) {
        return new MavenSession(this.container, request.getSettings(), request.getLocalRepository(), request.getEventDispatcher(), rpm, request.getGoals(), request.getBaseDirectory(), request.getExecutionProperties(), request.getStartTime());
    }
    
    private void resolveParameters(final Settings settings) throws ComponentLookupException, ComponentLifecycleException, SettingsConfigurationException {
        final WagonManager wagonManager = (WagonManager)this.container.lookup(WagonManager.ROLE);
        try {
            final Proxy proxy = settings.getActiveProxy();
            if (proxy != null) {
                if (proxy.getHost() == null) {
                    throw new SettingsConfigurationException("Proxy in settings.xml has no host");
                }
                wagonManager.addProxy(proxy.getProtocol(), proxy.getHost(), proxy.getPort(), proxy.getUsername(), proxy.getPassword(), proxy.getNonProxyHosts());
            }
            for (final Server server : settings.getServers()) {
                wagonManager.addAuthenticationInfo(server.getId(), server.getUsername(), server.getPassword(), server.getPrivateKey(), server.getPassphrase());
                wagonManager.addPermissionInfo(server.getId(), server.getFilePermissions(), server.getDirectoryPermissions());
                if (server.getConfiguration() != null) {
                    wagonManager.addConfiguration(server.getId(), (Xpp3Dom)server.getConfiguration());
                }
            }
            final RepositoryPermissions defaultPermissions = new RepositoryPermissions();
            defaultPermissions.setDirectoryMode("775");
            defaultPermissions.setFileMode("664");
            wagonManager.setDefaultRepositoryPermissions(defaultPermissions);
            for (final Mirror mirror : settings.getMirrors()) {
                wagonManager.addMirror(mirror.getId(), mirror.getMirrorOf(), mirror.getUrl());
            }
        }
        finally {
            this.container.release(wagonManager);
        }
    }
    
    public void contextualize(final Context context) throws ContextException {
        this.container = (PlexusContainer)context.get("plexus");
    }
    
    protected void logFatal(final Throwable error) {
        this.line();
        this.getLogger().error("FATAL ERROR");
        this.line();
        this.logDiagnostics(error);
        this.logTrace(error, true);
    }
    
    protected void logError(final Exception e, final boolean showErrors) {
        this.line();
        this.getLogger().error("BUILD ERROR");
        this.line();
        this.logDiagnostics(e);
        this.logTrace(e, showErrors);
        if (!showErrors) {
            this.getLogger().info("For more information, run Maven with the -e switch");
            this.line();
        }
    }
    
    protected void logFailure(final BuildFailureException e, final boolean showErrors) {
        this.line();
        this.getLogger().error("BUILD FAILURE");
        this.line();
        this.logDiagnostics(e);
        this.logTrace(e, showErrors);
        if (!showErrors) {
            this.getLogger().info("For more information, run Maven with the -e switch");
            this.line();
        }
    }
    
    private void logTrace(final Throwable t, final boolean showErrors) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Trace", t);
            this.line();
        }
        else if (showErrors) {
            this.getLogger().info("Trace", t);
            this.line();
        }
    }
    
    private void logDiagnostics(final Throwable t) {
        String message = null;
        if (this.errorDiagnostics != null) {
            message = this.errorDiagnostics.diagnose(t);
        }
        if (message == null) {
            message = t.getMessage();
        }
        this.getLogger().info(message);
        this.line();
    }
    
    protected void logSuccess(final ReactorManager rm) {
        this.line();
        this.getLogger().info("BUILD SUCCESSFUL");
        this.line();
    }
    
    private void logReactorSummary(final ReactorManager rm) {
        if (rm.hasMultipleProjects() && rm.executedMultipleProjects()) {
            this.getLogger().info("");
            this.getLogger().info("");
            this.line();
            this.getLogger().info("Reactor Summary:");
            this.line();
            for (final MavenProject project : rm.getSortedProjects()) {
                if (rm.hasBuildFailure(project)) {
                    this.logReactorSummaryLine(project.getName(), "FAILED", rm.getBuildFailure(project).getTime());
                }
                else if (rm.isBlackListed(project)) {
                    this.logReactorSummaryLine(project.getName(), "SKIPPED (dependency build failed or was skipped)");
                }
                else if (rm.hasBuildSuccess(project)) {
                    this.logReactorSummaryLine(project.getName(), "SUCCESS", rm.getBuildSuccess(project).getTime());
                }
                else {
                    this.logReactorSummaryLine(project.getName(), "NOT BUILT");
                }
            }
            this.line();
        }
    }
    
    protected void stats(final Date start) {
        final Date finish = new Date();
        final long time = finish.getTime() - start.getTime();
        this.getLogger().info("Total time: " + formatTime(time));
        this.getLogger().info("Finished at: " + finish);
        System.gc();
        final Runtime r = Runtime.getRuntime();
        this.getLogger().info("Final Memory: " + (r.totalMemory() - r.freeMemory()) / 1048576L + "M/" + r.totalMemory() / 1048576L + "M");
    }
    
    protected void line() {
        this.getLogger().info("------------------------------------------------------------------------");
    }
    
    protected static String formatTime(final long ms) {
        long secs = ms / 1000L;
        final long min = secs / 60L;
        secs %= 60L;
        String msg = "";
        if (min > 1L) {
            msg = min + " minutes ";
        }
        else if (min == 1L) {
            msg = "1 minute ";
        }
        if (secs > 1L) {
            msg = msg + secs + " seconds";
        }
        else if (secs == 1L) {
            msg += "1 second";
        }
        else if (min == 0L) {
            msg += "< 1 second";
        }
        return msg;
    }
    
    private List getProjectFiles(final MavenExecutionRequest request) throws IOException {
        List files = Collections.EMPTY_LIST;
        final File userDir = new File(System.getProperty("user.dir"));
        if (request.isReactorActive()) {
            final String includes = System.getProperty("maven.reactor.includes", "**/pom.xml,**/release-pom.xml");
            final String excludes = System.getProperty("maven.reactor.excludes", "pom.xml,release-pom.xml");
            files = FileUtils.getFiles(userDir, includes, excludes);
            this.filterOneProjectFilePerDirectory(files);
            Collections.sort((List<Comparable>)files);
        }
        else if (request.getPomFile() != null) {
            final File projectFile = new File(request.getPomFile()).getAbsoluteFile();
            if (projectFile.exists()) {
                files = Collections.singletonList(projectFile);
            }
        }
        else {
            File projectFile = new File(userDir, "release-pom.xml");
            if (!projectFile.exists()) {
                projectFile = new File(userDir, "pom.xml");
            }
            if (projectFile.exists()) {
                files = Collections.singletonList(projectFile);
            }
        }
        return files;
    }
    
    private void filterOneProjectFilePerDirectory(final List files) {
        final List releaseDirs = new ArrayList();
        for (final File projectFile : files) {
            if ("release-pom.xml".equals(projectFile.getName())) {
                releaseDirs.add(projectFile.getParentFile());
            }
        }
        final Iterator it = files.iterator();
        while (it.hasNext()) {
            final File projectFile = it.next();
            if (!"release-pom.xml".equals(projectFile.getName()) && releaseDirs.contains(projectFile.getParentFile())) {
                it.remove();
            }
        }
    }
}
