// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import org.apache.maven.scm.provider.accurev.util.WorkspaceUtils;
import java.io.File;

public class AccuRevInfo
{
    private File basedir;
    private String user;
    private String workSpace;
    private String basis;
    private String top;
    private String server;
    private int port;
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public String getServer() {
        return this.server;
    }
    
    public void setServer(final String server) {
        this.server = server;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public void setUser(final String user) {
        this.user = user;
    }
    
    public String getWorkSpace() {
        return this.workSpace;
    }
    
    public void setWorkSpace(final String workSpace) {
        this.workSpace = workSpace;
    }
    
    public String getBasis() {
        return this.basis;
    }
    
    public void setBasis(final String basis) {
        this.basis = basis;
    }
    
    public String getTop() {
        return this.top;
    }
    
    public void setTop(final String top) {
        this.top = top;
    }
    
    public File getBasedir() {
        return this.basedir;
    }
    
    public AccuRevInfo(final File basedir) {
        this.basedir = basedir;
    }
    
    public boolean isWorkSpace() {
        return this.getWorkSpace() != null;
    }
    
    public boolean isLoggedIn() {
        return this.user != null && !"(not logged in)".equals(this.user);
    }
    
    public boolean isWorkSpaceTop() {
        return WorkspaceUtils.isSameFile(this.getBasedir(), this.getTop());
    }
}
