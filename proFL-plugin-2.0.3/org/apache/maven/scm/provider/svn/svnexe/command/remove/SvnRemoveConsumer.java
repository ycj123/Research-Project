// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.remove;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SvnRemoveConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> removedFiles;
    
    public SvnRemoveConsumer(final ScmLogger logger) {
        this.removedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (line.length() <= 3) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unexpected input, the line must be at least three characters long. Line: '" + line + "'.");
            }
            return;
        }
        final String statusString = line.substring(0, 1);
        final String file = line.substring(3);
        if (statusString.equals("D")) {
            final ScmFileStatus status = ScmFileStatus.DELETED;
            this.removedFiles.add(new ScmFile(file, status));
            return;
        }
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Unknown file status: '" + statusString + "'.");
        }
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.removedFiles;
    }
}
