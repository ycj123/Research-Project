// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle;

import org.apache.maven.plugin.version.PluginVersionNotFoundException;
import org.apache.maven.plugin.InvalidPluginException;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.plugin.version.PluginVersionResolutionException;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.lifecycle.mapping.LifecycleMapping;
import java.util.HashMap;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.plugin.lifecycle.Execution;
import org.apache.maven.plugin.lifecycle.Phase;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import org.apache.maven.reporting.MavenReport;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.model.ReportSet;
import org.apache.maven.model.ReportPlugin;
import java.util.StringTokenizer;
import java.util.Collection;
import org.apache.maven.plugin.PluginConfigurationException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.artifact.InvalidDependencyVersionException;
import org.apache.maven.plugin.PluginManagerException;
import org.apache.maven.plugin.MojoExecution;
import java.util.Stack;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.maven.plugin.PluginNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.codehaus.plexus.PlexusContainerException;
import org.apache.maven.model.Extension;
import org.apache.maven.project.MavenProject;
import org.apache.maven.BuildFailureException;
import java.util.Collections;
import org.apache.maven.monitor.event.EventDispatcher;
import org.apache.maven.execution.ReactorManager;
import org.apache.maven.execution.MavenSession;
import java.util.Map;
import org.apache.maven.artifact.handler.manager.ArtifactHandlerManager;
import java.util.List;
import org.apache.maven.extension.ExtensionManager;
import org.apache.maven.plugin.PluginManager;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultLifecycleExecutor extends AbstractLogEnabled implements LifecycleExecutor
{
    private PluginManager pluginManager;
    private ExtensionManager extensionManager;
    private List lifecycles;
    private ArtifactHandlerManager artifactHandlerManager;
    private List defaultReports;
    private Map phaseToLifecycleMap;
    
    public void execute(final MavenSession session, final ReactorManager rm, final EventDispatcher dispatcher) throws BuildFailureException, LifecycleExecutionException {
        final MavenProject rootProject = rm.getTopLevelProject();
        List goals = session.getGoals();
        if (goals.isEmpty() && rootProject != null) {
            final String goal = rootProject.getDefaultGoal();
            if (goal != null) {
                goals = Collections.singletonList(goal);
            }
        }
        if (goals.isEmpty()) {
            throw new BuildFailureException("\n\nYou must specify at least one goal. Try 'mvn install' to build or 'mvn -?' for options \nSee http://maven.apache.org for more information.\n\n");
        }
        final List taskSegments = this.segmentTaskListByAggregationNeeds(goals, session, rootProject);
        this.findExtensions(session);
        this.executeTaskSegments(taskSegments, rm, session, rootProject, dispatcher);
    }
    
    private void findExtensions(final MavenSession session) throws LifecycleExecutionException {
        for (final MavenProject project : session.getSortedProjects()) {
            for (final Extension extension : project.getBuildExtensions()) {
                try {
                    this.extensionManager.addExtension(extension, project, session.getLocalRepository());
                }
                catch (PlexusContainerException e) {
                    throw new LifecycleExecutionException("Unable to initialise extensions", e);
                }
                catch (ArtifactResolutionException e2) {
                    throw new LifecycleExecutionException(e2.getMessage(), e2);
                }
                catch (ArtifactNotFoundException e3) {
                    throw new LifecycleExecutionException(e3.getMessage(), e3);
                }
            }
            this.extensionManager.registerWagons();
            try {
                final Map handlers = this.findArtifactTypeHandlers(project, session.getSettings(), session.getLocalRepository());
                this.artifactHandlerManager.addHandlers(handlers);
            }
            catch (PluginNotFoundException e4) {
                throw new LifecycleExecutionException(e4.getMessage(), e4);
            }
        }
    }
    
    private void executeTaskSegments(final List taskSegments, final ReactorManager rm, final MavenSession session, final MavenProject rootProject, final EventDispatcher dispatcher) throws LifecycleExecutionException, BuildFailureException {
        for (final TaskSegment segment : taskSegments) {
            if (segment.aggregate()) {
                if (!rm.isBlackListed(rootProject)) {
                    this.line();
                    this.getLogger().info("Building " + rootProject.getName());
                    this.getLogger().info("  " + segment);
                    this.line();
                    final String event = "project-execute";
                    final long buildStartTime = System.currentTimeMillis();
                    final String target = rootProject.getId() + " ( " + segment + " )";
                    dispatcher.dispatchStart(event, target);
                    try {
                        session.setCurrentProject(rootProject);
                        for (final String task : segment.getTasks()) {
                            this.executeGoalAndHandleFailures(task, session, rootProject, dispatcher, event, rm, buildStartTime, target);
                        }
                        rm.registerBuildSuccess(rootProject, System.currentTimeMillis() - buildStartTime);
                    }
                    finally {
                        session.setCurrentProject(null);
                    }
                    dispatcher.dispatchEnd(event, target);
                }
                else {
                    this.line();
                    this.getLogger().info("SKIPPING " + rootProject.getName());
                    this.getLogger().info("  " + segment);
                    this.getLogger().info("This project has been banned from further executions due to previous failures.");
                    this.line();
                }
            }
            else {
                final List sortedProjects = session.getSortedProjects();
                for (final MavenProject currentProject : sortedProjects) {
                    if (!rm.isBlackListed(currentProject)) {
                        this.line();
                        this.getLogger().info("Building " + currentProject.getName());
                        this.getLogger().info("  " + segment);
                        this.line();
                        final String event2 = "project-execute";
                        final long buildStartTime2 = System.currentTimeMillis();
                        final String target2 = currentProject.getId() + " ( " + segment + " )";
                        dispatcher.dispatchStart(event2, target2);
                        try {
                            session.setCurrentProject(currentProject);
                            for (final String task2 : segment.getTasks()) {
                                this.executeGoalAndHandleFailures(task2, session, currentProject, dispatcher, event2, rm, buildStartTime2, target2);
                            }
                        }
                        finally {
                            session.setCurrentProject(null);
                        }
                        rm.registerBuildSuccess(currentProject, System.currentTimeMillis() - buildStartTime2);
                        dispatcher.dispatchEnd(event2, target2);
                    }
                    else {
                        this.line();
                        this.getLogger().info("SKIPPING " + currentProject.getName());
                        this.getLogger().info("  " + segment);
                        this.getLogger().info("This project has been banned from further executions due to previous failures.");
                        this.line();
                    }
                }
            }
        }
    }
    
    private void executeGoalAndHandleFailures(final String task, final MavenSession session, final MavenProject project, final EventDispatcher dispatcher, final String event, final ReactorManager rm, final long buildStartTime, final String target) throws BuildFailureException, LifecycleExecutionException {
        try {
            this.executeGoal(task, session, project);
        }
        catch (LifecycleExecutionException e) {
            dispatcher.dispatchError(event, target, e);
            if (this.handleExecutionFailure(rm, project, e, task, buildStartTime)) {
                throw e;
            }
        }
        catch (BuildFailureException e2) {
            dispatcher.dispatchError(event, target, e2);
            if (this.handleExecutionFailure(rm, project, e2, task, buildStartTime)) {
                throw e2;
            }
        }
    }
    
    private boolean handleExecutionFailure(final ReactorManager rm, final MavenProject project, final Exception e, final String task, final long buildStartTime) {
        rm.registerBuildFailure(project, e, task, System.currentTimeMillis() - buildStartTime);
        if ("fail-fast".equals(rm.getFailureBehavior())) {
            return true;
        }
        if ("fail-at-end".equals(rm.getFailureBehavior())) {
            rm.blackList(project);
        }
        return false;
    }
    
    private List segmentTaskListByAggregationNeeds(final List tasks, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException {
        final List segments = new ArrayList();
        if (project != null) {
            TaskSegment currentSegment = null;
            for (final String task : tasks) {
                if (this.getPhaseToLifecycleMap().containsKey(task)) {
                    if (currentSegment != null && currentSegment.aggregate()) {
                        segments.add(currentSegment);
                        currentSegment = null;
                    }
                    if (currentSegment == null) {
                        currentSegment = new TaskSegment();
                    }
                    currentSegment.add(task);
                }
                else {
                    MojoDescriptor mojo = null;
                    try {
                        mojo = this.getMojoDescriptor(task, session, project, task, true, false);
                    }
                    catch (PluginNotFoundException e) {
                        this.getLogger().info("Cannot find mojo descriptor for: '" + task + "' - Treating as non-aggregator.");
                        this.getLogger().debug("", e);
                    }
                    if (mojo != null && (mojo.isAggregator() || !mojo.isProjectRequired())) {
                        if (currentSegment != null && !currentSegment.aggregate()) {
                            segments.add(currentSegment);
                            currentSegment = null;
                        }
                        if (currentSegment == null) {
                            currentSegment = new TaskSegment(true);
                        }
                        currentSegment.add(task);
                    }
                    else {
                        if (currentSegment != null && currentSegment.aggregate()) {
                            segments.add(currentSegment);
                            currentSegment = null;
                        }
                        if (currentSegment == null) {
                            currentSegment = new TaskSegment();
                        }
                        currentSegment.add(task);
                    }
                }
            }
            segments.add(currentSegment);
        }
        else {
            final TaskSegment segment = new TaskSegment(false);
            final Iterator i = tasks.iterator();
            while (i.hasNext()) {
                segment.add(i.next());
            }
            segments.add(segment);
        }
        return segments;
    }
    
    private void executeGoal(final String task, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException {
        try {
            final Stack forkEntryPoints = new Stack();
            if (this.getPhaseToLifecycleMap().containsKey(task)) {
                final Lifecycle lifecycle = this.getLifecycleForPhase(task);
                final Map lifecycleMappings = this.constructLifecycleMappings(session, task, project, lifecycle);
                this.executeGoalWithLifecycle(task, forkEntryPoints, session, lifecycleMappings, project, lifecycle);
            }
            else {
                this.executeStandaloneGoal(task, forkEntryPoints, session, project);
            }
        }
        catch (PluginNotFoundException e) {
            throw new BuildFailureException("A required plugin was not found: " + e.getMessage(), e);
        }
    }
    
    private void executeGoalWithLifecycle(final String task, final Stack forkEntryPoints, final MavenSession session, final Map lifecycleMappings, final MavenProject project, final Lifecycle lifecycle) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        final List goals = this.processGoalChain(task, lifecycleMappings, lifecycle);
        if (!goals.isEmpty()) {
            this.executeGoals(goals, forkEntryPoints, session, project);
        }
        else {
            this.getLogger().info("No goals needed for project - skipping");
        }
    }
    
    private void executeStandaloneGoal(final String task, final Stack forkEntryPoints, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        final MojoDescriptor mojoDescriptor = this.getMojoDescriptor(task, session, project, task, true, false);
        this.executeGoals(Collections.singletonList(new MojoExecution(mojoDescriptor)), forkEntryPoints, session, project);
    }
    
    private void executeGoals(final List goals, final Stack forkEntryPoints, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        for (final MojoExecution mojoExecution : goals) {
            final MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();
            if (mojoDescriptor.getExecutePhase() != null || mojoDescriptor.getExecuteGoal() != null) {
                forkEntryPoints.push(mojoDescriptor);
                this.forkLifecycle(mojoDescriptor, forkEntryPoints, session, project);
                forkEntryPoints.pop();
            }
            if (mojoDescriptor.isRequiresReports()) {
                final List reports = this.getReports(project, forkEntryPoints, mojoExecution, session);
                mojoExecution.setReports(reports);
                for (final MojoExecution forkedExecution : mojoExecution.getForkedExecutions()) {
                    final MojoDescriptor descriptor = forkedExecution.getMojoDescriptor();
                    if (descriptor.getExecutePhase() != null) {
                        forkEntryPoints.push(descriptor);
                        this.forkLifecycle(descriptor, forkEntryPoints, session, project);
                        forkEntryPoints.pop();
                    }
                }
            }
            try {
                this.pluginManager.executeMojo(project, mojoExecution, session);
            }
            catch (PluginManagerException e) {
                throw new LifecycleExecutionException("Internal error in the plugin manager executing goal '" + mojoDescriptor.getId() + "': " + e.getMessage(), e);
            }
            catch (ArtifactNotFoundException e2) {
                throw new LifecycleExecutionException(e2.getMessage(), e2);
            }
            catch (InvalidDependencyVersionException e3) {
                throw new LifecycleExecutionException(e3.getMessage(), e3);
            }
            catch (ArtifactResolutionException e4) {
                throw new LifecycleExecutionException(e4.getMessage(), e4);
            }
            catch (MojoFailureException e5) {
                throw new BuildFailureException(e5.getMessage(), e5);
            }
            catch (MojoExecutionException e6) {
                throw new LifecycleExecutionException(e6.getMessage(), e6);
            }
            catch (PluginConfigurationException e7) {
                throw new LifecycleExecutionException(e7.getMessage(), e7);
            }
        }
    }
    
    private List getReports(final MavenProject project, final Stack forkEntryPoints, final MojoExecution mojoExecution, final MavenSession session) throws LifecycleExecutionException, PluginNotFoundException {
        List reportPlugins = project.getReportPlugins();
        if (project.getModel().getReports() != null) {
            this.getLogger().error("Plugin contains a <reports/> section: this is IGNORED - please use <reporting/> instead.");
        }
        if (project.getReporting() == null || !project.getReporting().isExcludeDefaults()) {
            if (reportPlugins == null) {
                reportPlugins = new ArrayList();
            }
            else {
                reportPlugins = new ArrayList(reportPlugins);
            }
            for (final String report : this.defaultReports) {
                final StringTokenizer tok = new StringTokenizer(report, ":");
                if (tok.countTokens() != 2) {
                    this.getLogger().warn("Invalid default report ignored: '" + report + "' (must be groupId:artifactId)");
                }
                else {
                    final String groupId = tok.nextToken();
                    final String artifactId = tok.nextToken();
                    boolean found = false;
                    for (Iterator j = reportPlugins.iterator(); j.hasNext() && !found; found = true) {
                        final ReportPlugin reportPlugin = j.next();
                        if (reportPlugin.getGroupId().equals(groupId) && reportPlugin.getArtifactId().equals(artifactId)) {}
                    }
                    if (found) {
                        continue;
                    }
                    final ReportPlugin reportPlugin2 = new ReportPlugin();
                    reportPlugin2.setGroupId(groupId);
                    reportPlugin2.setArtifactId(artifactId);
                    reportPlugins.add(reportPlugin2);
                }
            }
        }
        final List reports = new ArrayList();
        if (reportPlugins != null) {
            for (final ReportPlugin reportPlugin3 : reportPlugins) {
                final List reportSets = reportPlugin3.getReportSets();
                if (reportSets == null || reportSets.isEmpty()) {
                    reports.addAll(this.getReports(reportPlugin3, forkEntryPoints, null, project, session, mojoExecution));
                }
                else {
                    for (final ReportSet reportSet : reportSets) {
                        reports.addAll(this.getReports(reportPlugin3, forkEntryPoints, reportSet, project, session, mojoExecution));
                    }
                }
            }
        }
        return reports;
    }
    
    private List getReports(final ReportPlugin reportPlugin, final Stack forkEntryPoints, final ReportSet reportSet, final MavenProject project, final MavenSession session, final MojoExecution mojoExecution) throws LifecycleExecutionException, PluginNotFoundException {
        final PluginDescriptor pluginDescriptor = this.verifyReportPlugin(reportPlugin, project, session);
        final List reports = new ArrayList();
        for (final MojoDescriptor mojoDescriptor : pluginDescriptor.getMojos()) {
            if (forkEntryPoints.contains(mojoDescriptor)) {
                this.getLogger().debug("Omitting report: " + mojoDescriptor.getFullGoalName() + " from reports list. It initiated part of the fork currently executing.");
            }
            else {
                if (reportSet != null && !reportSet.getReports().contains(mojoDescriptor.getGoal())) {
                    continue;
                }
                String id = null;
                if (reportSet != null) {
                    id = reportSet.getId();
                }
                final MojoExecution reportExecution = new MojoExecution(mojoDescriptor, id);
                try {
                    final MavenReport reportMojo = this.pluginManager.getReport(project, reportExecution, session);
                    if (reportMojo == null) {
                        continue;
                    }
                    reports.add(reportMojo);
                    mojoExecution.addMojoExecution(reportExecution);
                }
                catch (PluginManagerException e) {
                    throw new LifecycleExecutionException("Error getting reports from the plugin '" + reportPlugin.getKey() + "': " + e.getMessage(), e);
                }
                catch (PluginConfigurationException e2) {
                    throw new LifecycleExecutionException("Error getting reports from the plugin '" + reportPlugin.getKey() + "'", e2);
                }
                catch (ArtifactNotFoundException e3) {
                    throw new LifecycleExecutionException(e3.getMessage(), e3);
                }
                catch (ArtifactResolutionException e4) {
                    throw new LifecycleExecutionException(e4.getMessage(), e4);
                }
            }
        }
        return reports;
    }
    
    private void forkLifecycle(final MojoDescriptor mojoDescriptor, final Stack ancestorLifecycleForkers, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        final PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
        this.getLogger().info("Preparing " + pluginDescriptor.getGoalPrefix() + ":" + mojoDescriptor.getGoal());
        if (mojoDescriptor.isAggregator()) {
            for (final MavenProject reactorProject : session.getSortedProjects()) {
                this.line();
                this.getLogger().info("Building " + reactorProject.getName());
                this.line();
                this.forkProjectLifecycle(mojoDescriptor, ancestorLifecycleForkers, session, reactorProject);
            }
        }
        else {
            this.forkProjectLifecycle(mojoDescriptor, ancestorLifecycleForkers, session, project);
        }
    }
    
    private void forkProjectLifecycle(final MojoDescriptor mojoDescriptor, final Stack forkEntryPoints, final MavenSession session, final MavenProject project) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        forkEntryPoints.push(mojoDescriptor);
        final PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
        final String targetPhase = mojoDescriptor.getExecutePhase();
        Map lifecycleMappings = null;
        if (targetPhase != null) {
            final Lifecycle lifecycle = this.getLifecycleForPhase(targetPhase);
            lifecycleMappings = this.constructLifecycleMappings(session, targetPhase, project, lifecycle);
            final String executeLifecycle = mojoDescriptor.getExecuteLifecycle();
            if (executeLifecycle != null) {
                org.apache.maven.plugin.lifecycle.Lifecycle lifecycleOverlay;
                try {
                    lifecycleOverlay = pluginDescriptor.getLifecycleMapping(executeLifecycle);
                }
                catch (IOException e) {
                    throw new LifecycleExecutionException("Unable to read lifecycle mapping file: " + e.getMessage(), e);
                }
                catch (XmlPullParserException e2) {
                    throw new LifecycleExecutionException("Unable to parse lifecycle mapping file: " + e2.getMessage(), e2);
                }
                if (lifecycleOverlay == null) {
                    throw new LifecycleExecutionException("Lifecycle '" + executeLifecycle + "' not found in plugin");
                }
                for (final Phase phase : lifecycleOverlay.getPhases()) {
                    for (final Execution exec : phase.getExecutions()) {
                        for (final String goal : exec.getGoals()) {
                            String lifecycleGoal;
                            PluginDescriptor lifecyclePluginDescriptor;
                            if (goal.indexOf(":") > 0) {
                                final String[] s = StringUtils.split(goal, ":");
                                final String groupId = s[0];
                                final String artifactId = s[1];
                                lifecycleGoal = s[2];
                                final Plugin plugin = new Plugin();
                                plugin.setGroupId(groupId);
                                plugin.setArtifactId(artifactId);
                                lifecyclePluginDescriptor = this.verifyPlugin(plugin, project, session.getSettings(), session.getLocalRepository());
                                if (lifecyclePluginDescriptor == null) {
                                    throw new LifecycleExecutionException("Unable to find plugin " + groupId + ":" + artifactId);
                                }
                            }
                            else {
                                lifecyclePluginDescriptor = pluginDescriptor;
                                lifecycleGoal = goal;
                            }
                            Xpp3Dom configuration = (Xpp3Dom)exec.getConfiguration();
                            if (phase.getConfiguration() != null) {
                                configuration = Xpp3Dom.mergeXpp3Dom(new Xpp3Dom((Xpp3Dom)phase.getConfiguration()), configuration);
                            }
                            final MojoDescriptor desc = this.getMojoDescriptor(lifecyclePluginDescriptor, lifecycleGoal);
                            final MojoExecution mojoExecution = new MojoExecution(desc, configuration);
                            this.addToLifecycleMappings(lifecycleMappings, phase.getId(), mojoExecution, session.getSettings());
                        }
                    }
                    if (phase.getConfiguration() != null) {
                        for (final List tasks : lifecycleMappings.values()) {
                            for (final MojoExecution exec2 : tasks) {
                                final Xpp3Dom configuration2 = Xpp3Dom.mergeXpp3Dom(new Xpp3Dom((Xpp3Dom)phase.getConfiguration()), exec2.getConfiguration());
                                exec2.setConfiguration(configuration2);
                            }
                        }
                    }
                }
            }
            this.removeFromLifecycle(forkEntryPoints, lifecycleMappings);
        }
        final MavenProject executionProject = new MavenProject(project);
        if (targetPhase != null) {
            final Lifecycle lifecycle2 = this.getLifecycleForPhase(targetPhase);
            this.executeGoalWithLifecycle(targetPhase, forkEntryPoints, session, lifecycleMappings, executionProject, lifecycle2);
        }
        else {
            final String goal2 = mojoDescriptor.getExecuteGoal();
            final MojoDescriptor desc2 = this.getMojoDescriptor(pluginDescriptor, goal2);
            this.executeGoals(Collections.singletonList(new MojoExecution(desc2)), forkEntryPoints, session, executionProject);
        }
        project.setExecutionProject(executionProject);
    }
    
    private Lifecycle getLifecycleForPhase(final String phase) throws BuildFailureException, LifecycleExecutionException {
        final Lifecycle lifecycle = this.getPhaseToLifecycleMap().get(phase);
        if (lifecycle == null) {
            throw new BuildFailureException("Unable to find lifecycle for phase '" + phase + "'");
        }
        return lifecycle;
    }
    
    private MojoDescriptor getMojoDescriptor(final PluginDescriptor pluginDescriptor, final String goal) throws LifecycleExecutionException {
        final MojoDescriptor desc = pluginDescriptor.getMojo(goal);
        if (desc == null) {
            String message = "Required goal '" + goal + "' not found in plugin '" + pluginDescriptor.getGoalPrefix() + "'";
            final int index = goal.indexOf(58);
            if (index >= 0) {
                final String prefix = goal.substring(index + 1);
                if (prefix.equals(pluginDescriptor.getGoalPrefix())) {
                    message = message + " (goals should not be prefixed - try '" + prefix + "')";
                }
            }
            throw new LifecycleExecutionException(message);
        }
        return desc;
    }
    
    private void removeFromLifecycle(final Stack lifecycleForkers, final Map lifecycleMappings) {
        for (final List tasks : lifecycleMappings.values()) {
            final Iterator taskIterator = tasks.iterator();
            while (taskIterator.hasNext()) {
                final MojoExecution execution = taskIterator.next();
                if (lifecycleForkers.contains(execution.getMojoDescriptor())) {
                    taskIterator.remove();
                    this.getLogger().warn("Removing: " + execution.getMojoDescriptor().getGoal() + " from forked lifecycle, to prevent recursive invocation.");
                }
            }
        }
    }
    
    private Map constructLifecycleMappings(final MavenSession session, final String selectedPhase, final MavenProject project, final Lifecycle lifecycle) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        final Map lifecycleMappings = this.bindLifecycleForPackaging(session, selectedPhase, project, lifecycle);
        for (final Plugin plugin : project.getBuildPlugins()) {
            this.bindPluginToLifecycle(plugin, session, lifecycleMappings, project);
        }
        return lifecycleMappings;
    }
    
    private Map bindLifecycleForPackaging(final MavenSession session, final String selectedPhase, final MavenProject project, final Lifecycle lifecycle) throws LifecycleExecutionException, BuildFailureException, PluginNotFoundException {
        final Map mappings = this.findMappingsForLifecycle(session, project, lifecycle);
        final List optionalMojos = this.findOptionalMojosForLifecycle(session, project, lifecycle);
        final Map lifecycleMappings = new HashMap();
        for (final String phase : lifecycle.getPhases()) {
            final String phaseTasks = mappings.get(phase);
            if (phaseTasks != null) {
                final StringTokenizer tok = new StringTokenizer(phaseTasks, ",");
                while (tok.hasMoreTokens()) {
                    final String goal = tok.nextToken().trim();
                    final MojoDescriptor mojoDescriptor = this.getMojoDescriptor(goal, session, project, selectedPhase, false, optionalMojos.contains(goal));
                    if (mojoDescriptor == null) {
                        continue;
                    }
                    if (mojoDescriptor.isDirectInvocationOnly()) {
                        throw new LifecycleExecutionException("Mojo: '" + goal + "' requires direct invocation. It cannot be used as part of lifecycle: '" + project.getPackaging() + "'.");
                    }
                    this.addToLifecycleMappings(lifecycleMappings, phase, new MojoExecution(mojoDescriptor), session.getSettings());
                }
            }
            if (phase.equals(selectedPhase)) {
                break;
            }
        }
        return lifecycleMappings;
    }
    
    private Map findMappingsForLifecycle(final MavenSession session, final MavenProject project, final Lifecycle lifecycle) throws LifecycleExecutionException, PluginNotFoundException {
        final String packaging = project.getPackaging();
        Map mappings = null;
        LifecycleMapping m = (LifecycleMapping)this.findExtension(project, LifecycleMapping.ROLE, packaging, session.getSettings(), session.getLocalRepository());
        if (m != null) {
            mappings = m.getPhases(lifecycle.getId());
        }
        final Map defaultMappings = lifecycle.getDefaultPhases();
        if (mappings == null) {
            try {
                m = (LifecycleMapping)session.lookup(LifecycleMapping.ROLE, packaging);
                mappings = m.getPhases(lifecycle.getId());
            }
            catch (ComponentLookupException e) {
                if (defaultMappings == null) {
                    throw new LifecycleExecutionException("Cannot find lifecycle mapping for packaging: '" + packaging + "'.", e);
                }
            }
        }
        if (mappings == null) {
            if (defaultMappings == null) {
                throw new LifecycleExecutionException("Cannot find lifecycle mapping for packaging: '" + packaging + "', and there is no default");
            }
            mappings = defaultMappings;
        }
        return mappings;
    }
    
    private List findOptionalMojosForLifecycle(final MavenSession session, final MavenProject project, final Lifecycle lifecycle) throws LifecycleExecutionException, PluginNotFoundException {
        final String packaging = project.getPackaging();
        List optionalMojos = null;
        LifecycleMapping m = (LifecycleMapping)this.findExtension(project, LifecycleMapping.ROLE, packaging, session.getSettings(), session.getLocalRepository());
        if (m != null) {
            optionalMojos = m.getOptionalMojos(lifecycle.getId());
        }
        if (optionalMojos == null) {
            try {
                m = (LifecycleMapping)session.lookup(LifecycleMapping.ROLE, packaging);
                optionalMojos = m.getOptionalMojos(lifecycle.getId());
            }
            catch (ComponentLookupException e) {
                this.getLogger().debug("Error looking up lifecycle mapping to retrieve optional mojos. Lifecycle ID: " + lifecycle.getId() + ". Error: " + e.getMessage(), e);
            }
        }
        if (optionalMojos == null) {
            optionalMojos = Collections.EMPTY_LIST;
        }
        return optionalMojos;
    }
    
    private Object findExtension(final MavenProject project, final String role, final String roleHint, final Settings settings, final ArtifactRepository localRepository) throws LifecycleExecutionException, PluginNotFoundException {
        Object pluginComponent = null;
        final Iterator i = project.getBuildPlugins().iterator();
        while (i.hasNext() && pluginComponent == null) {
            final Plugin plugin = i.next();
            if (plugin.isExtensions()) {
                this.verifyPlugin(plugin, project, settings, localRepository);
                try {
                    pluginComponent = this.pluginManager.getPluginComponent(plugin, role, roleHint);
                }
                catch (ComponentLookupException e) {
                    this.getLogger().debug("Unable to find the lifecycle component in the extension", e);
                }
                catch (PluginManagerException e2) {
                    throw new LifecycleExecutionException("Error getting extensions from the plugin '" + plugin.getKey() + "': " + e2.getMessage(), e2);
                }
            }
        }
        return pluginComponent;
    }
    
    private Map findArtifactTypeHandlers(final MavenProject project, final Settings settings, final ArtifactRepository localRepository) throws LifecycleExecutionException, PluginNotFoundException {
        final Map map = new HashMap();
        for (final Plugin plugin : project.getBuildPlugins()) {
            if (plugin.isExtensions()) {
                this.verifyPlugin(plugin, project, settings, localRepository);
                try {
                    final Map components = this.pluginManager.getPluginComponents(plugin, ArtifactHandler.ROLE);
                    map.putAll(components);
                }
                catch (ComponentLookupException e) {
                    this.getLogger().debug("Unable to find the lifecycle component in the extension", e);
                }
                catch (PluginManagerException e2) {
                    throw new LifecycleExecutionException("Error looking up available components from plugin '" + plugin.getKey() + "': " + e2.getMessage(), e2);
                }
                for (final ArtifactHandler handler : map.values()) {
                    if (project.getPackaging().equals(handler.getPackaging())) {
                        project.getArtifact().setArtifactHandler(handler);
                    }
                }
            }
        }
        return map;
    }
    
    private void bindPluginToLifecycle(final Plugin plugin, final MavenSession session, final Map phaseMap, final MavenProject project) throws LifecycleExecutionException, PluginNotFoundException {
        final Settings settings = session.getSettings();
        final PluginDescriptor pluginDescriptor = this.verifyPlugin(plugin, project, session.getSettings(), session.getLocalRepository());
        if (pluginDescriptor.getMojos() != null && !pluginDescriptor.getMojos().isEmpty() && (plugin.isInheritanceApplied() || pluginDescriptor.isInheritedByDefault())) {
            if (plugin.getGoals() != null) {
                this.getLogger().error("Plugin contains a <goals/> section: this is IGNORED - please use <executions/> instead.");
            }
            final List executions = plugin.getExecutions();
            if (executions != null) {
                for (final PluginExecution execution : executions) {
                    this.bindExecutionToLifecycle(pluginDescriptor, phaseMap, execution, settings);
                }
            }
        }
    }
    
    private PluginDescriptor verifyPlugin(final Plugin plugin, final MavenProject project, final Settings settings, final ArtifactRepository localRepository) throws LifecycleExecutionException, PluginNotFoundException {
        PluginDescriptor pluginDescriptor;
        try {
            pluginDescriptor = this.pluginManager.verifyPlugin(plugin, project, settings, localRepository);
        }
        catch (PluginManagerException e) {
            throw new LifecycleExecutionException("Internal error in the plugin manager getting plugin '" + plugin.getKey() + "': " + e.getMessage(), e);
        }
        catch (PluginVersionResolutionException e2) {
            throw new LifecycleExecutionException(e2.getMessage(), e2);
        }
        catch (InvalidVersionSpecificationException e3) {
            throw new LifecycleExecutionException(e3.getMessage(), e3);
        }
        catch (InvalidPluginException e4) {
            throw new LifecycleExecutionException(e4.getMessage(), e4);
        }
        catch (ArtifactNotFoundException e5) {
            throw new LifecycleExecutionException(e5.getMessage(), e5);
        }
        catch (ArtifactResolutionException e6) {
            throw new LifecycleExecutionException(e6.getMessage(), e6);
        }
        catch (PluginVersionNotFoundException e7) {
            throw new LifecycleExecutionException(e7.getMessage(), e7);
        }
        return pluginDescriptor;
    }
    
    private PluginDescriptor verifyReportPlugin(final ReportPlugin plugin, final MavenProject project, final MavenSession session) throws LifecycleExecutionException, PluginNotFoundException {
        PluginDescriptor pluginDescriptor;
        try {
            pluginDescriptor = this.pluginManager.verifyReportPlugin(plugin, project, session);
        }
        catch (PluginManagerException e) {
            throw new LifecycleExecutionException("Internal error in the plugin manager getting report '" + plugin.getKey() + "': " + e.getMessage(), e);
        }
        catch (PluginVersionResolutionException e2) {
            throw new LifecycleExecutionException(e2.getMessage(), e2);
        }
        catch (InvalidVersionSpecificationException e3) {
            throw new LifecycleExecutionException(e3.getMessage(), e3);
        }
        catch (InvalidPluginException e4) {
            throw new LifecycleExecutionException(e4.getMessage(), e4);
        }
        catch (ArtifactNotFoundException e5) {
            throw new LifecycleExecutionException(e5.getMessage(), e5);
        }
        catch (ArtifactResolutionException e6) {
            throw new LifecycleExecutionException(e6.getMessage(), e6);
        }
        catch (PluginVersionNotFoundException e7) {
            throw new LifecycleExecutionException(e7.getMessage(), e7);
        }
        return pluginDescriptor;
    }
    
    private void bindExecutionToLifecycle(final PluginDescriptor pluginDescriptor, final Map phaseMap, final PluginExecution execution, final Settings settings) throws LifecycleExecutionException {
        for (final String goal : execution.getGoals()) {
            final MojoDescriptor mojoDescriptor = pluginDescriptor.getMojo(goal);
            if (mojoDescriptor == null) {
                throw new LifecycleExecutionException("'" + goal + "' was specified in an execution, but not found in the plugin");
            }
            if (!execution.isInheritanceApplied() && !mojoDescriptor.isInheritedByDefault()) {
                continue;
            }
            final MojoExecution mojoExecution = new MojoExecution(mojoDescriptor, execution.getId());
            String phase = execution.getPhase();
            if (phase == null) {
                phase = mojoDescriptor.getPhase();
            }
            if (phase == null) {
                continue;
            }
            if (mojoDescriptor.isDirectInvocationOnly()) {
                throw new LifecycleExecutionException("Mojo: '" + goal + "' requires direct invocation. It cannot be used as part of the lifecycle (it was included via the POM).");
            }
            this.addToLifecycleMappings(phaseMap, phase, mojoExecution, settings);
        }
    }
    
    private void addToLifecycleMappings(final Map lifecycleMappings, final String phase, final MojoExecution mojoExecution, final Settings settings) {
        List goals = lifecycleMappings.get(phase);
        if (goals == null) {
            goals = new ArrayList();
            lifecycleMappings.put(phase, goals);
        }
        final MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();
        if (settings.isOffline() && mojoDescriptor.isOnlineRequired()) {
            final String goal = mojoDescriptor.getGoal();
            this.getLogger().warn(goal + " requires online mode, but maven is currently offline. Disabling " + goal + ".");
        }
        else {
            goals.add(mojoExecution);
        }
    }
    
    private List processGoalChain(final String task, final Map phaseMap, final Lifecycle lifecycle) {
        final List goals = new ArrayList();
        for (int index = lifecycle.getPhases().indexOf(task), i = 0; i <= index; ++i) {
            final String p = lifecycle.getPhases().get(i);
            final List phaseGoals = phaseMap.get(p);
            if (phaseGoals != null) {
                goals.addAll(phaseGoals);
            }
        }
        return goals;
    }
    
    private MojoDescriptor getMojoDescriptor(final String task, final MavenSession session, final MavenProject project, final String invokedVia, final boolean canUsePrefix, final boolean isOptionalMojo) throws BuildFailureException, LifecycleExecutionException, PluginNotFoundException {
        PluginDescriptor pluginDescriptor = null;
        try {
            final StringTokenizer tok = new StringTokenizer(task, ":");
            final int numTokens = tok.countTokens();
            String goal;
            Plugin plugin;
            if (numTokens == 2) {
                if (!canUsePrefix) {
                    final String msg = "Mapped-prefix lookup of mojos are only supported from direct invocation. Please use specification of the form groupId:artifactId[:version]:goal instead. (Offending mojo: '" + task + "', invoked via: '" + invokedVia + "')";
                    throw new LifecycleExecutionException(msg);
                }
                final String prefix = tok.nextToken();
                goal = tok.nextToken();
                pluginDescriptor = this.pluginManager.getPluginDescriptorForPrefix(prefix);
                if (pluginDescriptor == null) {
                    plugin = this.pluginManager.getPluginDefinitionForPrefix(prefix, session, project);
                }
                else {
                    plugin = new Plugin();
                    plugin.setGroupId(pluginDescriptor.getGroupId());
                    plugin.setArtifactId(pluginDescriptor.getArtifactId());
                    plugin.setVersion(pluginDescriptor.getVersion());
                }
                if (plugin == null) {
                    for (final Plugin buildPlugin : project.getBuildPlugins()) {
                        final PluginDescriptor desc = this.verifyPlugin(buildPlugin, project, session.getSettings(), session.getLocalRepository());
                        if (prefix.equals(desc.getGoalPrefix())) {
                            plugin = buildPlugin;
                        }
                    }
                }
                if (plugin == null) {
                    plugin = new Plugin();
                    plugin.setGroupId(PluginDescriptor.getDefaultPluginGroupId());
                    plugin.setArtifactId(PluginDescriptor.getDefaultPluginArtifactId(prefix));
                }
            }
            else {
                if (numTokens != 3 && numTokens != 4) {
                    final String message = "Invalid task '" + task + "': you must specify a valid lifecycle phase, or" + " a goal in the format plugin:goal or pluginGroupId:pluginArtifactId:pluginVersion:goal";
                    throw new BuildFailureException(message);
                }
                plugin = new Plugin();
                plugin.setGroupId(tok.nextToken());
                plugin.setArtifactId(tok.nextToken());
                if (numTokens == 4) {
                    plugin.setVersion(tok.nextToken());
                }
                goal = tok.nextToken();
            }
            if (plugin.getVersion() == null) {
                for (final Plugin buildPlugin2 : project.getBuildPlugins()) {
                    if (buildPlugin2.getKey().equals(plugin.getKey())) {
                        plugin = buildPlugin2;
                        break;
                    }
                }
                project.injectPluginManagementInfo(plugin);
            }
            if (pluginDescriptor == null) {
                pluginDescriptor = this.verifyPlugin(plugin, project, session.getSettings(), session.getLocalRepository());
            }
            project.addPlugin(plugin);
            final MojoDescriptor mojoDescriptor = pluginDescriptor.getMojo(goal);
            if (mojoDescriptor == null) {
                if (!isOptionalMojo) {
                    throw new BuildFailureException("Required goal not found: " + task + " in " + pluginDescriptor.getId());
                }
                this.getLogger().info("Skipping missing optional mojo: " + task);
            }
            return mojoDescriptor;
        }
        catch (PluginNotFoundException e) {
            if (isOptionalMojo) {
                this.getLogger().info("Skipping missing optional mojo: " + task);
                this.getLogger().debug("Mojo: " + task + " could not be found. Reason: " + e.getMessage(), e);
                return null;
            }
            throw e;
        }
    }
    
    protected void line() {
        this.getLogger().info("------------------------------------------------------------------------");
    }
    
    public Map getPhaseToLifecycleMap() throws LifecycleExecutionException {
        if (this.phaseToLifecycleMap == null) {
            this.phaseToLifecycleMap = new HashMap();
            for (final Lifecycle lifecycle : this.lifecycles) {
                for (final String phase : lifecycle.getPhases()) {
                    if (this.phaseToLifecycleMap.containsKey(phase)) {
                        final Lifecycle prevLifecycle = this.phaseToLifecycleMap.get(phase);
                        throw new LifecycleExecutionException("Phase '" + phase + "' is defined in more than one lifecycle: '" + lifecycle.getId() + "' and '" + prevLifecycle.getId() + "'");
                    }
                    this.phaseToLifecycleMap.put(phase, lifecycle);
                }
            }
        }
        return this.phaseToLifecycleMap;
    }
    
    private static class TaskSegment
    {
        private boolean aggregate;
        private List tasks;
        
        TaskSegment() {
            this.tasks = new ArrayList();
        }
        
        TaskSegment(final boolean aggregate) {
            this.tasks = new ArrayList();
            this.aggregate = aggregate;
        }
        
        public String toString() {
            final StringBuffer message = new StringBuffer();
            message.append(" task-segment: [");
            final Iterator it = this.tasks.iterator();
            while (it.hasNext()) {
                final String task = it.next();
                message.append(task);
                if (it.hasNext()) {
                    message.append(", ");
                }
            }
            message.append("]");
            if (this.aggregate) {
                message.append(" (aggregator-style)");
            }
            return message.toString();
        }
        
        boolean aggregate() {
            return this.aggregate;
        }
        
        void add(final String task) {
            this.tasks.add(task);
        }
        
        List getTasks() {
            return this.tasks;
        }
    }
}
