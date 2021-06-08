// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.mkdir;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class MkdirScmResult extends ScmResult
{
    private static final long serialVersionUID = -8717329738246682608L;
    private String revision;
    private List<ScmFile> createdDirs;
    
    public MkdirScmResult(final ScmResult scmResult) {
        super(scmResult);
    }
    
    public MkdirScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public MkdirScmResult(final String commandLine, final String revision) {
        this(commandLine, null, null, true);
        this.revision = revision;
    }
    
    public MkdirScmResult(final String commandLine, final List<ScmFile> createdDirs) {
        this(commandLine, null, null, true);
        this.createdDirs = createdDirs;
    }
    
    public MkdirScmResult(final String revision, final ScmResult result) {
        super(result);
        this.revision = revision;
    }
    
    public MkdirScmResult(final List<ScmFile> createdDirs, final ScmResult result) {
        super(result);
        this.createdDirs = createdDirs;
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public List<ScmFile> getCreatedDirs() {
        return this.createdDirs;
    }
}
