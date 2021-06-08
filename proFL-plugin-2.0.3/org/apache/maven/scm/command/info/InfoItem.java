// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.info;

public class InfoItem
{
    private String path;
    private String url;
    private String repositoryRoot;
    private String repositoryUUID;
    private String revision;
    private String nodeKind;
    private String schedule;
    private String lastChangedAuthor;
    private String lastChangedRevision;
    private String lastChangedDate;
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public String getURL() {
        return this.url;
    }
    
    public void setURL(final String url) {
        this.url = url;
    }
    
    public String getRepositoryRoot() {
        return this.repositoryRoot;
    }
    
    public void setRepositoryRoot(final String repositoryRoot) {
        this.repositoryRoot = repositoryRoot;
    }
    
    public String getRepositoryUUID() {
        return this.repositoryUUID;
    }
    
    public void setRepositoryUUID(final String repositoryUUID) {
        this.repositoryUUID = repositoryUUID;
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String revision) {
        this.revision = revision;
    }
    
    public String getNodeKind() {
        return this.nodeKind;
    }
    
    public void setNodeKind(final String nodeKind) {
        this.nodeKind = nodeKind;
    }
    
    public String getSchedule() {
        return this.schedule;
    }
    
    public void setSchedule(final String schedule) {
        this.schedule = schedule;
    }
    
    public String getLastChangedAuthor() {
        return this.lastChangedAuthor;
    }
    
    public void setLastChangedAuthor(final String lastChangedAuthor) {
        this.lastChangedAuthor = lastChangedAuthor;
    }
    
    public String getLastChangedRevision() {
        return this.lastChangedRevision;
    }
    
    public void setLastChangedRevision(final String lastChangedRevision) {
        this.lastChangedRevision = lastChangedRevision;
    }
    
    public String getLastChangedDate() {
        return this.lastChangedDate;
    }
    
    public void setLastChangedDate(final String lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }
}
