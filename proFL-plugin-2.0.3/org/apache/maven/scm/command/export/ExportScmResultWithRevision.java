// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.export;

import org.apache.maven.scm.ScmFile;
import java.util.List;

public class ExportScmResultWithRevision extends ExportScmResult
{
    private static final long serialVersionUID = -7962912849216079039L;
    private String revision;
    
    public ExportScmResultWithRevision(final String commandLine, final String providerMessage, final String commandOutput, final String revision, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.revision = revision;
    }
    
    public ExportScmResultWithRevision(final String commandLine, final List<ScmFile> exportedFiles, final String revision) {
        super(commandLine, exportedFiles);
        this.revision = revision;
    }
    
    public String getRevision() {
        return this.revision;
    }
}
