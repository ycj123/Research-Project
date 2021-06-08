// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.add;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class VssAddConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> addedFiles;
    
    public VssAddConsumer(final ScmLogger logger) {
        this.addedFiles = new ArrayList<ScmFile>();
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
        if (statusString.equals("A")) {
            final ScmFileStatus status = ScmFileStatus.ADDED;
            this.addedFiles.add(new ScmFile(file, status));
            return;
        }
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Unknown file status: '" + statusString + "'.");
        }
    }
    
    public List<ScmFile> getAddedFiles() {
        return this.addedFiles;
    }
}
