// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.update;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.ScmFile;
import java.util.List;

public class UpdateScmResultWithRevision extends UpdateScmResult
{
    private static final long serialVersionUID = 7644079089026359667L;
    private String revision;
    
    public UpdateScmResultWithRevision(final String commandLine, final String providerMessage, final String commandOutput, final String revision, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.revision = revision;
    }
    
    public UpdateScmResultWithRevision(final String commandLine, final List<ScmFile> updatedFiles, final String revision) {
        super(commandLine, updatedFiles);
        this.revision = revision;
    }
    
    public UpdateScmResultWithRevision(final List<ScmFile> updatedFiles, final List<ChangeSet> changes, final String revision, final ScmResult result) {
        super(updatedFiles, changes, result);
        this.revision = revision;
    }
    
    public String getRevision() {
        return this.revision;
    }
}
