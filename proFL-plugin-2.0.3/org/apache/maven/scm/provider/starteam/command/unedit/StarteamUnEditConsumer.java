// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.unedit;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamUnEditConsumer implements StreamConsumer
{
    private String workingDirectory;
    private ScmLogger logger;
    private List<ScmFile> files;
    private String currentDir;
    private static final String DIR_MARKER = "(working dir: ";
    private static final String UNLOCKED_MARKER = ": unlocked";
    
    public StarteamUnEditConsumer(final ScmLogger logger, final File basedir) {
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
        else if ((pos = line.indexOf(": unlocked")) != -1) {
            this.processUnLockedFile(line, pos);
        }
        else if (this.logger.isWarnEnabled()) {
            this.logger.warn("Unknown unedit ouput: " + line);
        }
    }
    
    public List<ScmFile> getUnEditFiles() {
        return this.files;
    }
    
    private void processDirectory(final String line, final int pos) {
        final String dirPath = line.substring(pos + "(working dir: ".length(), line.length() - 1).replace('\\', '/');
        if (!dirPath.startsWith(this.workingDirectory)) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Working directory: " + this.workingDirectory);
                this.logger.info("unedit directory: " + dirPath);
            }
            throw new IllegalStateException("Working and unedit directories are not on the same tree");
        }
        this.currentDir = "." + dirPath.substring(this.workingDirectory.length());
    }
    
    private void processUnLockedFile(final String line, final int pos) {
        final String lockedFilePath = this.currentDir + "/" + line.substring(0, pos);
        this.files.add(new ScmFile(lockedFilePath, ScmFileStatus.UNKNOWN));
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Unlocked: " + lockedFilePath);
        }
    }
}
