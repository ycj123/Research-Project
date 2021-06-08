// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.tag;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class TagScmResult extends ScmResult
{
    private static final long serialVersionUID = -5068975000282095635L;
    private List<ScmFile> taggedFiles;
    
    public TagScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public TagScmResult(final String commandLine, final List<ScmFile> taggedFiles) {
        super(commandLine, null, null, true);
        this.taggedFiles = taggedFiles;
    }
    
    public TagScmResult(final List<ScmFile> taggedFiles, final ScmResult result) {
        super(result);
        this.taggedFiles = taggedFiles;
    }
    
    public List<ScmFile> getTaggedFiles() {
        return this.taggedFiles;
    }
}
