// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.remove;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseRemoveConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> removedFiles;
    
    public ClearCaseRemoveConsumer(final ScmLogger logger) {
        this.removedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        final int beginIndex = line.indexOf(34);
        if (beginIndex != -1) {
            final String fileName = line.substring(beginIndex + 1, line.indexOf(34, beginIndex + 1));
            this.removedFiles.add(new ScmFile(fileName, ScmFileStatus.DELETED));
        }
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.removedFiles;
    }
}
