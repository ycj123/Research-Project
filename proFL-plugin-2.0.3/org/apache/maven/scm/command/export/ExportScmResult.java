// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.export;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class ExportScmResult extends ScmResult
{
    private static final long serialVersionUID = 8564643361304165292L;
    private List<ScmFile> exportedFiles;
    
    public ExportScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public ExportScmResult(final String commandLine, final List<ScmFile> updatedFiles) {
        super(commandLine, null, null, true);
        this.exportedFiles = updatedFiles;
    }
    
    public List<ScmFile> getExportedFiles() {
        return this.exportedFiles;
    }
}
