// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.remoteinfo;

import java.util.HashMap;
import java.util.Map;
import org.apache.maven.scm.ScmResult;

public class RemoteInfoScmResult extends ScmResult
{
    private Map<String, String> branches;
    private Map<String, String> tags;
    
    public RemoteInfoScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.branches = new HashMap<String, String>();
        this.tags = new HashMap<String, String>();
    }
    
    public RemoteInfoScmResult(final String commandLine, final Map<String, String> branches, final Map<String, String> tags) {
        super(commandLine, null, null, true);
        this.branches = new HashMap<String, String>();
        this.tags = new HashMap<String, String>();
        this.branches = branches;
        this.tags = tags;
    }
    
    public Map<String, String> getBranches() {
        return this.branches;
    }
    
    public void setBranches(final Map<String, String> branches) {
        this.branches = branches;
    }
    
    public Map<String, String> getTags() {
        return this.tags;
    }
    
    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RemoteInfoScmResult");
        sb.append("{branches=").append(this.branches);
        sb.append(", tags=").append(this.tags);
        sb.append('}');
        return sb.toString();
    }
}
