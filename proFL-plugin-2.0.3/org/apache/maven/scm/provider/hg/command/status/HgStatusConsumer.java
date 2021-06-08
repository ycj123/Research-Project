// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.status;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

class HgStatusConsumer extends HgConsumer
{
    private final List<ScmFile> repositoryStatus;
    private final File workingDir;
    
    HgStatusConsumer(final ScmLogger logger, final File workingDir) {
        super(logger);
        this.repositoryStatus = new ArrayList<ScmFile>();
        this.workingDir = workingDir;
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        final File tmpFile = new File(this.workingDir, trimmedLine);
        if (!tmpFile.exists()) {
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("Not a file: " + tmpFile + ". Ignoring");
            }
        }
        else if (tmpFile.isDirectory()) {
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("New directory added: " + tmpFile);
            }
        }
        else {
            final ScmFile scmFile = new ScmFile(trimmedLine, status);
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info(scmFile.toString());
            }
            this.repositoryStatus.add(scmFile);
        }
    }
    
    List<ScmFile> getStatus() {
        return this.repositoryStatus;
    }
}
