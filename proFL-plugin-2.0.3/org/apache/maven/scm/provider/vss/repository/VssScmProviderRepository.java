// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.repository;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.ScmProviderRepository;

public class VssScmProviderRepository extends ScmProviderRepository
{
    private String vssdir;
    private String project;
    
    public VssScmProviderRepository(final String user, final String password, final String vssdir, final String project) {
        this.setUser(user);
        this.setPassword(password);
        this.vssdir = StringUtils.replace(vssdir, "/", "\\");
        this.project = project;
    }
    
    public String getProject() {
        return this.project;
    }
    
    public String getVssdir() {
        return this.vssdir;
    }
    
    public String getUserPassword() {
        String userPassword = null;
        if (!StringUtils.isEmpty(this.getUser())) {
            userPassword = this.getUser();
            if (!StringUtils.isEmpty(this.getPassword())) {
                userPassword = userPassword + "," + this.getPassword();
            }
        }
        return userPassword;
    }
}
