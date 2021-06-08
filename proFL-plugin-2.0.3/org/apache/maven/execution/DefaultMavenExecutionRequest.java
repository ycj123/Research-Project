// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.execution;

import org.apache.maven.monitor.event.EventMonitor;
import java.util.Date;
import java.util.Properties;
import org.apache.maven.profiles.ProfileManager;
import org.apache.maven.settings.Settings;
import org.apache.maven.monitor.event.EventDispatcher;
import java.util.List;
import org.apache.maven.artifact.repository.ArtifactRepository;

public class DefaultMavenExecutionRequest implements MavenExecutionRequest
{
    private final ArtifactRepository localRepository;
    private final List goals;
    protected MavenSession session;
    private final EventDispatcher eventDispatcher;
    private final Settings settings;
    private final String baseDirectory;
    private boolean recursive;
    private boolean reactorActive;
    private String pomFilename;
    private String failureBehavior;
    private final ProfileManager globalProfileManager;
    private final Properties executionProperties;
    private final Properties userProperties;
    private final Date startTime;
    private final boolean showErrors;
    
    public DefaultMavenExecutionRequest(final ArtifactRepository localRepository, final Settings settings, final EventDispatcher eventDispatcher, final List goals, final String baseDirectory, final ProfileManager globalProfileManager, final Properties executionProperties, final Properties userProperties, final boolean showErrors) {
        this.recursive = true;
        this.localRepository = localRepository;
        this.settings = settings;
        this.goals = goals;
        this.eventDispatcher = eventDispatcher;
        this.baseDirectory = baseDirectory;
        this.globalProfileManager = globalProfileManager;
        this.executionProperties = executionProperties;
        this.userProperties = userProperties;
        this.startTime = new Date();
        this.showErrors = showErrors;
    }
    
    public Settings getSettings() {
        return this.settings;
    }
    
    public String getBaseDirectory() {
        return this.baseDirectory;
    }
    
    public boolean isRecursive() {
        return this.recursive;
    }
    
    public void setRecursive(final boolean recursive) {
        this.recursive = false;
    }
    
    public ArtifactRepository getLocalRepository() {
        return this.localRepository;
    }
    
    public List getGoals() {
        return this.goals;
    }
    
    public Properties getExecutionProperties() {
        return this.executionProperties;
    }
    
    public MavenSession getSession() {
        return this.session;
    }
    
    public void setSession(final MavenSession session) {
        this.session = session;
    }
    
    public void addEventMonitor(final EventMonitor monitor) {
        this.eventDispatcher.addEventMonitor(monitor);
    }
    
    public EventDispatcher getEventDispatcher() {
        return this.eventDispatcher;
    }
    
    public void setReactorActive(final boolean reactorActive) {
        this.reactorActive = reactorActive;
    }
    
    public boolean isReactorActive() {
        return this.reactorActive;
    }
    
    public void setPomFile(final String pomFilename) {
        this.pomFilename = pomFilename;
    }
    
    public String getPomFile() {
        return this.pomFilename;
    }
    
    public void setFailureBehavior(final String failureBehavior) {
        this.failureBehavior = failureBehavior;
    }
    
    public String getFailureBehavior() {
        return this.failureBehavior;
    }
    
    public ProfileManager getGlobalProfileManager() {
        return this.globalProfileManager;
    }
    
    public Date getStartTime() {
        return this.startTime;
    }
    
    public boolean isShowErrors() {
        return this.showErrors;
    }
    
    public Properties getUserProperties() {
        return this.userProperties;
    }
}
