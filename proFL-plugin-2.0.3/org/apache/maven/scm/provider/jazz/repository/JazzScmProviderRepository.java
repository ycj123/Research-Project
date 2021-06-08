// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.repository;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class JazzScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String fRepositoryURI;
    private String fRepositoryWorkspace;
    private int fWorkspaceAlias;
    private String fWorkspace;
    private int fFlowTargetAlias;
    private String fFlowTarget;
    private String fComponent;
    private String fBaseline;
    
    public JazzScmProviderRepository(final String repositoryURI, final String userName, final String password, final String hostName, final int port, final String repositoryWorkspace) {
        this.fRepositoryURI = repositoryURI;
        this.setUser(userName);
        this.setPassword(password);
        this.setHost(hostName);
        this.setPort(port);
        this.fRepositoryWorkspace = repositoryWorkspace;
    }
    
    public boolean isPushChangesAndHaveFlowTargets() {
        if (!this.isPushChanges()) {
            return this.isPushChanges();
        }
        return this.isHaveFlowTargets();
    }
    
    public boolean isHaveFlowTargets() {
        return StringUtils.isNotEmpty(this.getWorkspace()) && StringUtils.isNotEmpty(this.getFlowTarget()) && !this.getWorkspace().equals(this.getFlowTarget()) && this.getWorkspaceAlias() != this.getFlowTargetAlias();
    }
    
    public String getRepositoryURI() {
        return this.fRepositoryURI;
    }
    
    public String getRepositoryWorkspace() {
        return this.fRepositoryWorkspace;
    }
    
    public int getWorkspaceAlias() {
        return this.fWorkspaceAlias;
    }
    
    public void setWorkspaceAlias(final int workspaceAlias) {
        this.fWorkspaceAlias = workspaceAlias;
    }
    
    public String getWorkspace() {
        return this.fWorkspace;
    }
    
    public void setWorkspace(final String fWorkspace) {
        this.fWorkspace = fWorkspace;
    }
    
    public int getFlowTargetAlias() {
        return this.fFlowTargetAlias;
    }
    
    public void setFlowTargetAlias(final int flowTargetAlias) {
        this.fFlowTargetAlias = flowTargetAlias;
    }
    
    public String getFlowTarget() {
        return this.fFlowTarget;
    }
    
    public void setFlowTarget(final String flowTarget) {
        this.fFlowTarget = flowTarget;
    }
    
    public String getComponent() {
        return this.fComponent;
    }
    
    public void setComponent(final String component) {
        this.fComponent = component;
    }
    
    public String getBaseline() {
        return this.fBaseline;
    }
    
    public void setBaseline(final String baseline) {
        this.fBaseline = baseline;
    }
    
    @Override
    public String toString() {
        return this.getRepositoryURI() + ":" + this.getRepositoryWorkspace();
    }
}
