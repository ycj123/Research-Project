// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.execution;

import org.apache.maven.artifact.ArtifactUtils;
import java.util.Iterator;
import org.apache.maven.project.MavenProject;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.DuplicateProjectException;
import org.codehaus.plexus.util.dag.CycleDetectedException;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.maven.project.ProjectSorter;
import java.util.Map;
import java.util.List;

public class ReactorManager
{
    public static final String FAIL_FAST = "fail-fast";
    public static final String FAIL_AT_END = "fail-at-end";
    public static final String FAIL_NEVER = "fail-never";
    private List blackList;
    private Map buildFailuresByProject;
    private Map pluginContextsByProjectAndPluginKey;
    private String failureBehavior;
    private final ProjectSorter sorter;
    private Map buildSuccessesByProject;
    
    public ReactorManager(final List projects) throws CycleDetectedException, DuplicateProjectException {
        this.blackList = new ArrayList();
        this.buildFailuresByProject = new HashMap();
        this.pluginContextsByProjectAndPluginKey = new HashMap();
        this.failureBehavior = "fail-fast";
        this.buildSuccessesByProject = new HashMap();
        this.sorter = new ProjectSorter(projects);
    }
    
    public Map getPluginContext(final PluginDescriptor plugin, final MavenProject project) {
        Map pluginContextsByKey = this.pluginContextsByProjectAndPluginKey.get(project.getId());
        if (pluginContextsByKey == null) {
            pluginContextsByKey = new HashMap();
            this.pluginContextsByProjectAndPluginKey.put(project.getId(), pluginContextsByKey);
        }
        Map pluginContext = pluginContextsByKey.get(plugin.getPluginLookupKey());
        if (pluginContext == null) {
            pluginContext = new HashMap();
            pluginContextsByKey.put(plugin.getPluginLookupKey(), pluginContext);
        }
        return pluginContext;
    }
    
    public void setFailureBehavior(final String failureBehavior) {
        if ("fail-fast".equals(failureBehavior) || "fail-at-end".equals(failureBehavior) || "fail-never".equals(failureBehavior)) {
            this.failureBehavior = failureBehavior;
            return;
        }
        throw new IllegalArgumentException("Invalid failure behavior (must be one of: 'fail-fast', 'fail-at-end', 'fail-never').");
    }
    
    public String getFailureBehavior() {
        return this.failureBehavior;
    }
    
    public void blackList(final MavenProject project) {
        this.blackList(getProjectKey(project));
    }
    
    private void blackList(final String id) {
        if (!this.blackList.contains(id)) {
            this.blackList.add(id);
            final List dependents = this.sorter.getDependents(id);
            if (dependents != null && !dependents.isEmpty()) {
                for (final String dependentId : dependents) {
                    if (!this.buildSuccessesByProject.containsKey(dependentId) && !this.buildFailuresByProject.containsKey(dependentId)) {
                        this.blackList(dependentId);
                    }
                }
            }
        }
    }
    
    public boolean isBlackListed(final MavenProject project) {
        return this.blackList.contains(getProjectKey(project));
    }
    
    private static String getProjectKey(final MavenProject project) {
        return ArtifactUtils.versionlessKey(project.getGroupId(), project.getArtifactId());
    }
    
    public void registerBuildFailure(final MavenProject project, final Exception error, final String task, final long time) {
        this.buildFailuresByProject.put(getProjectKey(project), new BuildFailure(error, task, time));
    }
    
    public boolean hasBuildFailures() {
        return !this.buildFailuresByProject.isEmpty();
    }
    
    public boolean hasBuildFailure(final MavenProject project) {
        return this.buildFailuresByProject.containsKey(getProjectKey(project));
    }
    
    public boolean hasMultipleProjects() {
        return this.sorter.hasMultipleProjects();
    }
    
    public List getSortedProjects() {
        return this.sorter.getSortedProjects();
    }
    
    public MavenProject getTopLevelProject() {
        return this.sorter.getTopLevelProject();
    }
    
    public boolean hasBuildSuccess(final MavenProject project) {
        return this.buildSuccessesByProject.containsKey(getProjectKey(project));
    }
    
    public void registerBuildSuccess(final MavenProject project, final long time) {
        this.buildSuccessesByProject.put(getProjectKey(project), new BuildSuccess(project, time));
    }
    
    public BuildFailure getBuildFailure(final MavenProject project) {
        return this.buildFailuresByProject.get(getProjectKey(project));
    }
    
    public BuildSuccess getBuildSuccess(final MavenProject project) {
        return this.buildSuccessesByProject.get(getProjectKey(project));
    }
    
    public boolean executedMultipleProjects() {
        return this.buildFailuresByProject.size() + this.buildSuccessesByProject.size() > 1;
    }
}
