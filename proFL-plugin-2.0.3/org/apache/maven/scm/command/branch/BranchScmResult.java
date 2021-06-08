// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.branch;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class BranchScmResult extends ScmResult
{
    private static final long serialVersionUID = -4241972929129557932L;
    private List<ScmFile> branchedFiles;
    
    public BranchScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public BranchScmResult(final String commandLine, final List<ScmFile> branchedFiles) {
        super(commandLine, null, null, true);
        this.branchedFiles = branchedFiles;
    }
    
    public BranchScmResult(final List<ScmFile> branchedFiles, final ScmResult result) {
        super(result);
        this.branchedFiles = branchedFiles;
    }
    
    public List<ScmFile> getBranchedFiles() {
        return this.branchedFiles;
    }
}
