// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.diff;

import java.util.Map;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class DiffScmResult extends ScmResult
{
    private static final long serialVersionUID = 4036970486972633082L;
    private List<ScmFile> changedFiles;
    private Map<String, CharSequence> differences;
    private String patch;
    
    public DiffScmResult(final String commandLine, final List<ScmFile> changedFiles, final Map<String, CharSequence> differences, final String patch) {
        this(commandLine, null, null, true);
        this.changedFiles = changedFiles;
        this.differences = differences;
        this.patch = patch;
    }
    
    public DiffScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public DiffScmResult(final List<ScmFile> changedFiles, final Map<String, CharSequence> differences, final String patch, final ScmResult result) {
        super(result);
        this.changedFiles = changedFiles;
        this.differences = differences;
        this.patch = patch;
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
    
    public Map<String, CharSequence> getDifferences() {
        return this.differences;
    }
    
    public String getPatch() {
        return this.patch;
    }
}
