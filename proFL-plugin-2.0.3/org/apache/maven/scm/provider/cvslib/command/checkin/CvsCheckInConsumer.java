// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.checkin;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class CvsCheckInConsumer implements StreamConsumer
{
    private List<ScmFile> checkedInFiles;
    private String remotePath;
    private ScmLogger logger;
    
    public CvsCheckInConsumer(final String remotePath, final ScmLogger logger) {
        this.checkedInFiles = new ArrayList<ScmFile>();
        this.remotePath = remotePath;
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        final int end = line.indexOf(",v  <--  ");
        if (end == -1) {
            return;
        }
        String fileName = line.substring(0, end);
        if (!fileName.startsWith(this.remotePath)) {
            return;
        }
        fileName = fileName.substring(this.remotePath.length());
        this.checkedInFiles.add(new ScmFile(fileName, ScmFileStatus.CHECKED_IN));
    }
    
    public List<ScmFile> getCheckedInFiles() {
        return this.checkedInFiles;
    }
}
