// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.repository;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.SvnTagBranchUtils;
import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class SvnScmProviderRepository extends ScmProviderRepositoryWithHost
{
    private String url;
    private String protocol;
    private String tagBase;
    private String branchBase;
    
    public SvnScmProviderRepository(final String url) {
        this.parseUrl(url);
        this.tagBase = SvnTagBranchUtils.resolveTagBase(url);
        this.branchBase = SvnTagBranchUtils.resolveBranchBase(url);
    }
    
    public SvnScmProviderRepository(final String url, final String user, final String password) {
        this(url);
        this.setUser(user);
        this.setPassword(password);
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public String getTagBase() {
        return this.tagBase;
    }
    
    public void setTagBase(final String tagBase) {
        this.tagBase = tagBase;
    }
    
    public String getBranchBase() {
        return this.branchBase;
    }
    
    public void setBranchBase(final String branchBase) {
        this.branchBase = branchBase;
    }
    
    private void setProtocol(final String protocol) {
        this.protocol = protocol;
    }
    
    public String getProtocol() {
        return this.protocol;
    }
    
    private void parseUrl(final String url) {
        if (url.startsWith("file")) {
            this.setProtocol("file://");
        }
        else if (url.startsWith("https")) {
            this.setProtocol("https://");
        }
        else if (url.startsWith("http")) {
            this.setProtocol("http://");
        }
        else if (url.startsWith("svn+")) {
            this.setProtocol(url.substring(0, url.indexOf("://") + 3));
        }
        else if (url.startsWith("svn")) {
            this.setProtocol("svn://");
        }
        if (this.getProtocol() == null) {
            return;
        }
        String urlPath = url.substring(this.getProtocol().length());
        final int indexAt = urlPath.indexOf(64);
        if (indexAt > 0 && !this.getProtocol().startsWith("svn+")) {
            final String userPassword = urlPath.substring(0, indexAt);
            if (userPassword.indexOf(58) < 0) {
                this.setUser(userPassword);
            }
            else {
                this.setUser(userPassword.substring(0, userPassword.indexOf(58)));
                this.setPassword(userPassword.substring(userPassword.indexOf(58) + 1));
            }
            urlPath = urlPath.substring(indexAt + 1);
            this.url = this.getProtocol() + urlPath;
        }
        else {
            this.url = this.getProtocol() + urlPath;
        }
        if (!"file://".equals(this.getProtocol())) {
            final int indexSlash = urlPath.indexOf(47);
            String hostPort = urlPath;
            if (indexSlash > 0) {
                hostPort = urlPath.substring(0, indexSlash);
            }
            final int indexColon = hostPort.indexOf(58);
            if (indexColon > 0) {
                this.setHost(hostPort.substring(0, indexColon));
                this.setPort(Integer.parseInt(hostPort.substring(indexColon + 1)));
            }
            else {
                this.setHost(hostPort);
            }
        }
    }
    
    @Override
    public ScmProviderRepository getParent() {
        String newUrl;
        for (newUrl = this.getUrl().substring(this.getProtocol().length()); newUrl.endsWith("/."); newUrl = newUrl.substring(0, newUrl.length() - 2)) {}
        while (newUrl.endsWith("/")) {
            newUrl = newUrl.substring(0, newUrl.length() - 1);
        }
        final int i = newUrl.lastIndexOf(47);
        if (i < 0) {
            return null;
        }
        newUrl = newUrl.substring(0, i);
        return new SvnScmProviderRepository(this.getProtocol() + newUrl, this.getUser(), this.getPassword());
    }
    
    @Override
    public String getRelativePath(final ScmProviderRepository ancestor) {
        if (ancestor instanceof SvnScmProviderRepository) {
            final SvnScmProviderRepository svnAncestor = (SvnScmProviderRepository)ancestor;
            final String path = this.getUrl().replaceFirst(svnAncestor.getUrl() + "/", "");
            if (!path.equals(this.getUrl())) {
                return path;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.getUrl();
    }
}
