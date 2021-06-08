// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.repository;

import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class StarteamScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String path;
    
    public StarteamScmProviderRepository(final String user, final String password, final String host, final int port, final String path) {
        this.setUser(user);
        this.setPassword(password);
        this.setHost(host);
        this.setPort(port);
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("The path must be start with a slash?");
        }
        this.path = path;
    }
    
    public String getUrl() {
        return this.getHost() + ":" + this.getPort() + this.path;
    }
    
    public String getFullUrl() {
        String fullUrl = this.getUser() + ":";
        if (this.getPassword() != null) {
            fullUrl += this.getPassword();
        }
        fullUrl = fullUrl + "@" + this.getUrl();
        return fullUrl;
    }
    
    public String getPath() {
        return this.path;
    }
}
