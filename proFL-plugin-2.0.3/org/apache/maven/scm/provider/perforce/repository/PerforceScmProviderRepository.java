// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.repository;

import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class PerforceScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String protocol;
    private String path;
    
    public PerforceScmProviderRepository(final String host, final int port, final String path, final String user, final String password) {
        this.setHost(host);
        this.setPort(port);
        this.path = path;
        this.setUser(user);
        this.setPassword(password);
    }
    
    public PerforceScmProviderRepository(final String protocol, final String host, final int port, final String path, final String user, final String password) {
        this(host, port, path, user, password);
        this.protocol = protocol;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public String getProtocol() {
        return this.protocol;
    }
}
