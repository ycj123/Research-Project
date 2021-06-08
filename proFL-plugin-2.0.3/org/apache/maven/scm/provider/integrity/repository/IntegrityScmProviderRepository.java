// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.repository;

import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.provider.integrity.Project;
import org.apache.maven.scm.provider.integrity.APISession;
import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class IntegrityScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String configurationPath;
    private APISession api;
    private Project siProject;
    private Sandbox siSandbox;
    
    public IntegrityScmProviderRepository(final String host, final int port, final String user, final String paswd, final String configPath, final ScmLogger logger) {
        this.setHost(host);
        this.setPort(port);
        this.setUser(user);
        this.setPassword(paswd);
        this.configurationPath = configPath;
        this.api = new APISession(logger);
        logger.debug("Configuration Path: " + this.configurationPath);
    }
    
    public Project getProject() {
        return this.siProject;
    }
    
    public void setProject(final Project project) {
        this.siProject = project;
    }
    
    public Sandbox getSandbox() {
        return this.siSandbox;
    }
    
    public void setSandbox(final Sandbox sandbox) {
        this.siSandbox = sandbox;
    }
    
    public APISession getAPISession() {
        return this.api;
    }
    
    public String getConfigruationPath() {
        return this.configurationPath;
    }
}
