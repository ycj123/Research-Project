// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.update;

import java.util.ArrayList;
import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class UpdateScmResult extends ScmResult
{
    private static final long serialVersionUID = 1L;
    private List<ScmFile> updatedFiles;
    private List<ChangeSet> changes;
    
    public UpdateScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public UpdateScmResult(final String commandLine, final List<ScmFile> updatedFiles) {
        super(commandLine, null, null, true);
        this.updatedFiles = updatedFiles;
    }
    
    public UpdateScmResult(final List<ScmFile> updatedFiles, final List<ChangeSet> changes, final ScmResult result) {
        super(result);
        this.updatedFiles = updatedFiles;
        this.changes = changes;
    }
    
    public List<ScmFile> getUpdatedFiles() {
        return this.updatedFiles;
    }
    
    public List<ChangeSet> getChanges() {
        if (this.changes == null) {
            return new ArrayList<ChangeSet>();
        }
        return this.changes;
    }
    
    public void setChanges(final List<ChangeSet> changes) {
        this.changes = changes;
    }
}
