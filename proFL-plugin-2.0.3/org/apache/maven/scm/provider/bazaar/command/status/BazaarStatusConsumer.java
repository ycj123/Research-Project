// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.status;

import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

class BazaarStatusConsumer extends BazaarConsumer
{
    private final List<ScmFile> repositoryStatus;
    private final File workingDir;
    private ScmFileStatus currentState;
    
    BazaarStatusConsumer(final ScmLogger logger, final File workingDir) {
        super(logger);
        this.repositoryStatus = new ArrayList<ScmFile>();
        this.currentState = null;
        this.workingDir = workingDir;
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        if (status != null) {
            this.currentState = status;
            return;
        }
        if (this.currentState == null) {
            return;
        }
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
            final ScmFile scmFile = new ScmFile(trimmedLine, this.currentState);
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
