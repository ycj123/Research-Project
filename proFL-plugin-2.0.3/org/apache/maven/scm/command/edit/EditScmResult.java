// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.edit;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class EditScmResult extends ScmResult
{
    private static final long serialVersionUID = -6274938710679161288L;
    private List<ScmFile> editFiles;
    
    public EditScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public EditScmResult(final String commandLine, final List<ScmFile> editFiles) {
        super(commandLine, null, null, true);
        this.editFiles = editFiles;
    }
    
    public EditScmResult(final List<ScmFile> editFiles, final ScmResult result) {
        super(result);
        this.editFiles = editFiles;
    }
    
    public List<ScmFile> getEditFiles() {
        return this.editFiles;
    }
}
