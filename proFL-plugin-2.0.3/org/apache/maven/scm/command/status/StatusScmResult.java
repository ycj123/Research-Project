// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.status;

import java.util.Collections;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class StatusScmResult extends ScmResult
{
    private static final long serialVersionUID = 7152442589455369403L;
    private List<ScmFile> changedFiles;
    
    public StatusScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.changedFiles = Collections.emptyList();
    }
    
    public StatusScmResult(final String commandLine, final List<ScmFile> changedFiles) {
        super(commandLine, null, null, true);
        if (changedFiles == null) {
            throw new NullPointerException("changedFiles can't be null.");
        }
        this.changedFiles = changedFiles;
    }
    
    public StatusScmResult(final List<ScmFile> changedFiles, final ScmResult result) {
        super(result);
        if (changedFiles == null) {
            throw new NullPointerException("changedFiles can't be null.");
        }
        this.changedFiles = changedFiles;
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
}
