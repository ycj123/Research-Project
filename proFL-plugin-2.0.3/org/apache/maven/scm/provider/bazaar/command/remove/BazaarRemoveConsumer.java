// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.remove;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

public class BazaarRemoveConsumer extends BazaarConsumer
{
    private final File workingDir;
    private final List<ScmFile> removedFiles;
    
    public BazaarRemoveConsumer(final ScmLogger logger, final File workingDir) {
        super(logger);
        this.removedFiles = new ArrayList<ScmFile>();
        this.workingDir = workingDir;
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        if (status != null && status == ScmFileStatus.DELETED) {
            final File tmpFile = new File(this.workingDir, trimmedLine);
            if (!tmpFile.exists()) {
                if (this.getLogger().isWarnEnabled()) {
                    this.getLogger().warn("Not a file: " + tmpFile + ". Ignored");
                }
            }
            else {
                final ScmFile scmFile = new ScmFile(trimmedLine, ScmFileStatus.DELETED);
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info(scmFile.toString());
                }
                this.removedFiles.add(scmFile);
            }
        }
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.removedFiles;
    }
}
