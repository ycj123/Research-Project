// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.unedit;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class UnEditScmResult extends ScmResult
{
    private static final long serialVersionUID = 257465331122587798L;
    private List<ScmFile> unEditFiles;
    
    public UnEditScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public UnEditScmResult(final String commandLine, final List<ScmFile> unEditFiles) {
        super(commandLine, null, null, true);
        this.unEditFiles = unEditFiles;
    }
    
    public UnEditScmResult(final List<ScmFile> unEditFiles, final ScmResult result) {
        super(result);
        this.unEditFiles = unEditFiles;
    }
    
    public List<ScmFile> getUnEditFiles() {
        return this.unEditFiles;
    }
}
