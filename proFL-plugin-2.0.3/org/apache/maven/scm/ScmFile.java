// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public class ScmFile implements Comparable<ScmFile>, Serializable
{
    private static final long serialVersionUID = -9133015730693522690L;
    private String path;
    private ScmFileStatus status;
    
    public ScmFile(final String path, final ScmFileStatus status) {
        this.path = path;
        this.status = status;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public ScmFileStatus getStatus() {
        return this.status;
    }
    
    public int compareTo(final ScmFile other) {
        return other.getPath().compareTo(this.path);
    }
    
    @Override
    public boolean equals(final Object other) {
        return other instanceof ScmFile && ((ScmFile)other).getPath().equals(this.path);
    }
    
    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
    
    @Override
    public String toString() {
        return "[" + this.path + ":" + this.status + "]";
    }
}
