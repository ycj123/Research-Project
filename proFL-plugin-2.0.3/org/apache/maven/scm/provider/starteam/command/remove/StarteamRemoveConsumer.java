// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.remove;

import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamRemoveConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private String workingDirectory;
    private String currentDir;
    private List<ScmFile> files;
    private static final String DIR_MARKER = "(working dir: ";
    private static final String ADDED_MARKER = ": removed";
    private static final String LINKTO_MARKER = ": linked to";
    
    public StarteamRemoveConsumer(final ScmLogger logger, final File basedir) {
        this.files = new ArrayList<ScmFile>();
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
        else if ((pos = line.indexOf(": removed")) != -1) {
            this.processRemovedFile(line, pos);
        }
        else if ((pos = line.indexOf(": linked to")) == -1) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unknown remove ouput: " + line);
            }
        }
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.files;
    }
    
    private void processDirectory(final String line, final int pos) {
        final String dirPath = line.substring(pos + "(working dir: ".length(), line.length() - 1).replace('\\', '/');
        try {
            this.currentDir = StarteamCommandLineUtils.getRelativeChildDirectory(this.workingDirectory, dirPath);
        }
        catch (IllegalStateException e) {
            final String error = "Working and checkout directories are not on the same tree";
            if (this.logger.isErrorEnabled()) {
                this.logger.error(error);
                this.logger.error("Working directory: " + this.workingDirectory);
                this.logger.error("Checked out directory: " + dirPath);
            }
            throw new IllegalStateException(error);
        }
    }
    
    private void processRemovedFile(final String line, final int pos) {
        final String addedFilePath = this.currentDir + "/" + line.substring(0, pos);
        this.files.add(new ScmFile(addedFilePath, ScmFileStatus.DELETED));
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Removed: " + addedFilePath);
        }
    }
}
