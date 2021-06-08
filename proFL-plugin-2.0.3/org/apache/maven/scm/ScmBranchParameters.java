// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public class ScmBranchParameters implements Serializable
{
    private static final long serialVersionUID = 7241536408630608707L;
    private String message;
    private boolean remoteBranching;
    private String scmRevision;
    
    public ScmBranchParameters() {
        this.remoteBranching = false;
        this.remoteBranching = false;
    }
    
    public ScmBranchParameters(final String message) {
        this.remoteBranching = false;
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public String getScmRevision() {
        return this.scmRevision;
    }
    
    public void setScmRevision(final String scmRevision) {
        this.scmRevision = scmRevision;
    }
    
    public boolean isRemoteBranching() {
        return this.remoteBranching;
    }
    
    public void setRemoteBranching(final boolean remoteBranching) {
        this.remoteBranching = remoteBranching;
    }
    
    @Override
    public String toString() {
        return "[" + this.scmRevision + "] " + this.message;
    }
}
