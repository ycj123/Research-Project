// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.list;

import java.util.List;
import org.apache.maven.scm.ScmFile;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.git.gitexe.command.AbstractFileCheckingConsumer;

public class GitListConsumer extends AbstractFileCheckingConsumer
{
    private ScmFileStatus fileStatus;
    
    public GitListConsumer(final ScmLogger logger, final File workingDirectory, final ScmFileStatus fileStatus) {
        super(logger, workingDirectory);
        this.fileStatus = fileStatus;
    }
    
    @Override
    protected void parseLine(final String line) {
        final String file = line;
        this.addFile(new ScmFile(file, this.fileStatus));
    }
    
    public List<ScmFile> getListedFiles() {
        return this.getFiles();
    }
}
