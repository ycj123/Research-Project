// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.execution;

import java.util.Date;
import java.util.Properties;
import org.apache.maven.profiles.ProfileManager;
import org.apache.maven.settings.Settings;
import org.apache.maven.monitor.event.EventDispatcher;
import org.apache.maven.monitor.event.EventMonitor;
import java.util.List;
import org.apache.maven.artifact.repository.ArtifactRepository;

public interface MavenExecutionRequest
{
    ArtifactRepository getLocalRepository();
    
    List getGoals();
    
    void setSession(final MavenSession p0);
    
    MavenSession getSession();
    
    void addEventMonitor(final EventMonitor p0);
    
    EventDispatcher getEventDispatcher();
    
    Settings getSettings();
    
    String getBaseDirectory();
    
    void setRecursive(final boolean p0);
    
    boolean isRecursive();
    
    void setReactorActive(final boolean p0);
    
    boolean isReactorActive();
    
    void setPomFile(final String p0);
    
    String getPomFile();
    
    void setFailureBehavior(final String p0);
    
    String getFailureBehavior();
    
    ProfileManager getGlobalProfileManager();
    
    Properties getExecutionProperties();
    
    Properties getUserProperties();
    
    Date getStartTime();
    
    boolean isShowErrors();
}
