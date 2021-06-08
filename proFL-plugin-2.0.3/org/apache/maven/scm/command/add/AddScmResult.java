// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.add;

import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class AddScmResult extends ScmResult
{
    private static final long serialVersionUID = 1L;
    private List<ScmFile> addedFiles;
    
    public AddScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.addedFiles = new ArrayList<ScmFile>(0);
    }
    
    public AddScmResult(final String commandLine, final List<ScmFile> addedFiles) {
        this(commandLine, null, null, true);
        if (addedFiles == null) {
            throw new NullPointerException("addedFiles can't be null");
        }
        this.addedFiles = addedFiles;
    }
    
    public AddScmResult(final List<ScmFile> addedFiles, final ScmResult result) {
        super(result);
        if (addedFiles == null) {
            throw new NullPointerException("addedFiles can't be null");
        }
        this.addedFiles = addedFiles;
    }
    
    public List<ScmFile> getAddedFiles() {
        return this.addedFiles;
    }
}
