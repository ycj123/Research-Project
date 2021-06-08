// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public class ScmTagParameters implements Serializable
{
    private static final long serialVersionUID = 7241536408630606807L;
    private String message;
    private boolean remoteTagging;
    private String scmRevision;
    
    public ScmTagParameters() {
        this.remoteTagging = false;
        this.remoteTagging = false;
    }
    
    public ScmTagParameters(final String message) {
        this.remoteTagging = false;
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public boolean isRemoteTagging() {
        return this.remoteTagging;
    }
    
    public void setRemoteTagging(final boolean remoteTagging) {
        this.remoteTagging = remoteTagging;
    }
    
    public String getScmRevision() {
        return this.scmRevision;
    }
    
    public void setScmRevision(final String scmRevision) {
        this.scmRevision = scmRevision;
    }
    
    @Override
    public String toString() {
        return "[" + this.scmRevision + "] " + this.message;
    }
}
