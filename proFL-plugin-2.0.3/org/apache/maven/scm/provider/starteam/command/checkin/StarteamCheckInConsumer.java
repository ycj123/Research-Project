// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.checkin;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamCheckInConsumer implements StreamConsumer
{
    private String workingDirectory;
    private ScmLogger logger;
    private List<ScmFile> files;
    private String currentDir;
    private static final String DIR_MARKER = "(working dir: ";
    private static final String CHECKIN_MARKER = ": checked in";
    private static final String SKIPPED_MARKER = ": skipped";
    private static final String LINKTO_MARKER = ": linked to";
    
    public StarteamCheckInConsumer(final ScmLogger logger, final File basedir) {
        this.files = new ArrayList<ScmFile>();
        this.currentDir = "";
        this.logger = logger;
        this.workingDirectory = basedir.getPath().replace('\\', '/');
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        int pos = 0;
        if ((pos = line.indexOf("(working dir: ")) != -1) {
            this.processDirectory(line, pos);
        }
        else if ((pos = line.indexOf(": checked in")) != -1) {
            this.processCheckedInFile(line, pos);
        }
        else if ((pos = line.indexOf(": skipped")) != -1) {
            this.processSkippedFile(line, pos);
        }
        else if ((pos = line.indexOf(": linked to")) == -1) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unknown checkin ouput: " + line);
            }
        }
    }
    
    public List<ScmFile> getCheckedInFiles() {
        return this.files;
    }
    
    private void processDirectory(final String line, final int pos) {
        final String dirPath = line.substring(pos + "(working dir: ".length(), line.length() - 1).replace('\\', '/');
        if (!dirPath.startsWith(this.workingDirectory)) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Working directory: " + this.workingDirectory);
                this.logger.info("Checkin directory: " + dirPath);
            }
            throw new IllegalStateException("Working and checkin directories are not on the same tree");
        }
        this.currentDir = "." + dirPath.substring(this.workingDirectory.length());
    }
    
    private void processCheckedInFile(final String line, final int pos) {
        final String checkedInFilePath = this.currentDir + "/" + line.substring(0, pos);
        this.files.add(new ScmFile(checkedInFilePath, ScmFileStatus.CHECKED_OUT));
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Checked in: " + checkedInFilePath);
        }
    }
    
    private void processSkippedFile(final String line, final int pos) {
        final String skippedFilePath = this.currentDir + "/" + line.substring(0, pos);
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Skipped: " + skippedFilePath);
        }
    }
}
