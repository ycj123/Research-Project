// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.checkin;

import java.util.List;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.svn.svnexe.command.AbstractFileCheckingConsumer;

public class SvnCheckInConsumer extends AbstractFileCheckingConsumer
{
    private static final String SENDING_TOKEN = "Sending        ";
    private static final String ADDING_TOKEN = "Adding         ";
    private static final String ADDING_BIN_TOKEN = "Adding  (bin)  ";
    private static final String DELETING_TOKEN = "Deleting       ";
    private static final String TRANSMITTING_TOKEN = "Transmitting file data";
    private static final String COMMITTED_REVISION_TOKEN = "Committed revision";
    
    public SvnCheckInConsumer(final ScmLogger logger, final File workingDirectory) {
        super(logger, workingDirectory);
    }
    
    @Override
    protected void parseLine(final String line) {
        if (line.startsWith("Committed revision")) {
            final String revisionString = line.substring("Committed revision".length() + 1, line.length() - 1);
            this.revision = this.parseInt(revisionString);
            return;
        }
        String file;
        if (line.startsWith("Sending        ")) {
            file = line.substring("Sending        ".length());
        }
        else if (line.startsWith("Adding         ")) {
            file = line.substring("Adding         ".length());
        }
        else if (line.startsWith("Adding  (bin)  ")) {
            file = line.substring("Adding  (bin)  ".length());
        }
        else if (line.startsWith("Deleting       ")) {
            file = line.substring("Deleting       ".length());
        }
        else {
            if (line.startsWith("Transmitting file data")) {
                return;
            }
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Unknown line: '" + line + "'");
            }
            return;
        }
        this.addFile(new ScmFile(file, ScmFileStatus.CHECKED_IN));
    }
    
    public List<ScmFile> getCheckedInFiles() {
        return this.getFiles();
    }
}
