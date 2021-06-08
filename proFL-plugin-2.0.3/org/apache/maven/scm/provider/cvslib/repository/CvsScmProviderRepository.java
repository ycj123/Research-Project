// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.repository;

import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class CvsScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String cvsroot;
    private String transport;
    private String path;
    private String module;
    
    public CvsScmProviderRepository(final String cvsroot, final String transport, final String user, final String password, final String host, final String path, final String module) {
        this(cvsroot, transport, user, password, host, -1, path, module);
    }
    
    public CvsScmProviderRepository(final String cvsroot, final String transport, String user, final String password, final String host, final int port, final String path, final String module) {
        this.cvsroot = cvsroot;
        this.transport = transport;
        if (user == null && "ext".equals(transport)) {
            user = System.getProperty("user.name");
        }
        this.setUser(user);
        this.setPassword(password);
        this.setHost(host);
        this.setPort(port);
        this.path = path;
        this.module = module;
    }
    
    public String getCvsRoot() {
        final String root = this.getCvsRootForCvsPass();
        return this.removeDefaultPortFromCvsRoot(root);
    }
    
    private String removeDefaultPortFromCvsRoot(String root) {
        if (root != null && root.indexOf(":2401") > 0) {
            root = root.substring(0, root.indexOf(":2401")) + ":" + root.substring(root.indexOf(":2401") + 5);
        }
        return root;
    }
    
    public String getCvsRootForCvsPass() {
        final String transport = this.getTransport();
        String result;
        if ("local".equals(transport)) {
            result = ":" + transport + ":" + this.cvsroot;
        }
        else {
            if (this.getUser() == null) {
                throw new IllegalArgumentException("Username isn't defined.");
            }
            result = this.getCvsRootWithCorrectUser(this.getUser());
        }
        return result;
    }
    
    public String getTransport() {
        return this.transport;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public String getModule() {
        return this.module;
    }
    
    private String getCvsRootWithCorrectUser() {
        return this.getCvsRootWithCorrectUser(null);
    }
    
    private String getCvsRootWithCorrectUser(final String user) {
        final int indexOfUsername = this.getTransport().length() + 2;
        final int indexOfAt = this.cvsroot.indexOf(64);
        final String userString = (user == null) ? "" : (":" + user);
        if (indexOfAt > 0) {
            this.cvsroot = ":" + this.getTransport() + userString + this.cvsroot.substring(indexOfAt);
        }
        else {
            this.cvsroot = ":" + this.getTransport() + userString + "@" + this.cvsroot.substring(indexOfUsername);
        }
        return this.cvsroot;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.getUser() == null) {
            if ("local".equals(this.getTransport())) {
                sb.append(this.getCvsRoot());
            }
            else {
                sb.append(this.removeDefaultPortFromCvsRoot(this.getCvsRootWithCorrectUser()));
            }
        }
        else {
            sb.append(this.getCvsRoot());
        }
        sb.append(":");
        sb.append(this.getModule());
        if (sb.charAt(0) == ':') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
