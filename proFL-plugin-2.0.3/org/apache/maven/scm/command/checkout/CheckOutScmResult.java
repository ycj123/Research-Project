// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.checkout;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class CheckOutScmResult extends ScmResult
{
    private static final long serialVersionUID = 3345964619749320210L;
    private List<ScmFile> checkedOutFiles;
    private String revision;
    protected String relativePathProjectDirectory;
    
    public CheckOutScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.relativePathProjectDirectory = "";
    }
    
    public CheckOutScmResult(final String commandLine, final List<ScmFile> checkedOutFiles) {
        this(commandLine, null, checkedOutFiles);
    }
    
    public CheckOutScmResult(final String commandLine, final String revision, final List<ScmFile> checkedOutFiles) {
        super(commandLine, null, null, true);
        this.relativePathProjectDirectory = "";
        this.revision = revision;
        this.checkedOutFiles = checkedOutFiles;
    }
    
    public CheckOutScmResult(final String commandLine, final List<ScmFile> checkedOutFiles, final String relativePathProjectDirectory) {
        this(commandLine, null, checkedOutFiles);
        if (relativePathProjectDirectory != null) {
            this.relativePathProjectDirectory = relativePathProjectDirectory;
        }
    }
    
    public CheckOutScmResult(final String commandLine, final String revision, final List<ScmFile> checkedOutFiles, final String relativePathProjectDirectory) {
        this(commandLine, revision, checkedOutFiles);
        if (relativePathProjectDirectory != null) {
            this.relativePathProjectDirectory = relativePathProjectDirectory;
        }
    }
    
    public CheckOutScmResult(final List<ScmFile> checkedOutFiles, final ScmResult result) {
        super(result);
        this.relativePathProjectDirectory = "";
        this.checkedOutFiles = checkedOutFiles;
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.checkedOutFiles;
    }
    
    public String getRelativePathProjectDirectory() {
        return this.relativePathProjectDirectory;
    }
    
    public String getRevision() {
        return this.revision;
    }
}
