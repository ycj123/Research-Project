// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.remove;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class RemoveScmResult extends ScmResult
{
    private static final long serialVersionUID = 8852310735079996771L;
    private List<ScmFile> removedFiles;
    
    public RemoveScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public RemoveScmResult(final String commandLine, final List<ScmFile> removedFiles) {
        super(commandLine, null, null, true);
        this.removedFiles = removedFiles;
    }
    
    public RemoveScmResult(final List<ScmFile> removedFiles, final ScmResult result) {
        super(result);
        this.removedFiles = removedFiles;
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.removedFiles;
    }
}
