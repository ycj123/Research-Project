// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.blame;

import java.util.Date;
import java.io.Serializable;

public class BlameLine implements Serializable
{
    private static final long serialVersionUID = 2675122069344705612L;
    private Date date;
    private String revision;
    private String author;
    private String committer;
    
    public BlameLine(final Date date, final String revision, final String author) {
        this(date, revision, author, author);
    }
    
    public BlameLine(final Date date, final String revision, final String author, final String committer) {
        this.setDate(date);
        this.setRevision(revision);
        this.setAuthor(author);
        this.setCommitter(committer);
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String revision) {
        this.revision = revision;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }
    
    public String getCommitter() {
        return this.committer;
    }
    
    public void setCommitter(final String committer) {
        this.committer = committer;
    }
    
    public Date getDate() {
        if (this.date != null) {
            return (Date)this.date.clone();
        }
        return null;
    }
    
    public void setDate(final Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        }
        else {
            this.date = null;
        }
    }
}
