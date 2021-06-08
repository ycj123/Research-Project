// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.status;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseStatusConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private File workingDirectory;
    private List<ScmFile> checkedOutFiles;
    
    public ClearCaseStatusConsumer(final ScmLogger logger, final File workingDirectory) {
        this.checkedOutFiles = new ArrayList<ScmFile>();
        this.logger = logger;
        this.workingDirectory = workingDirectory;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        this.checkedOutFiles.add(new ScmFile(this.workingDirectory.getAbsolutePath() + line.substring(1), ScmFileStatus.CHECKED_OUT));
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.checkedOutFiles;
    }
}
