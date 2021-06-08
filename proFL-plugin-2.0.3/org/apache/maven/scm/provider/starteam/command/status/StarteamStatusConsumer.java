// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.status;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamStatusConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private String workingDirectory;
    private List<ScmFile> changedFiles;
    private static final String DIR_MARKER = "(working dir: ";
    private static final String FILE_MARKER = "History for: ";
    private static final String STATUS_MARKER = "Status: ";
    private static final String OUTDATE_MARKER = "Out of Date";
    private static final String MISSING_MARKER = "Missing";
    private static final String CURRENT_MARKER = "Current";
    private static final String MERGE_MARKER = "Merge";
    private static final String MODIFIED_MARKER = "Modified";
    private String currentDir;
    private String currentFile;
    
    public StarteamStatusConsumer(final ScmLogger logger, final File basedir) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.currentDir = "";
        this.currentFile = "";
        this.logger = logger;
        this.workingDirectory = basedir.getPath().replace('\\', '/');
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        int pos = 0;
        if ((pos = line.indexOf("(working dir: ")) != -1) {
            this.processGetDir(line, pos);
        }
        else if ((pos = line.indexOf("History for: ")) != -1) {
            this.processGetFile(line, pos);
        }
        else if ((pos = line.indexOf("Status: ")) != -1) {
            this.processStatus(line, pos);
        }
    }
    
    private void processGetDir(final String line, final int pos) {
        final String dirPath = line.substring(pos + "(working dir: ".length(), line.length() - 1).replace('\\', '/');
        this.currentDir = "." + dirPath.substring(this.workingDirectory.length());
    }
    
    private void processGetFile(final String line, final int pos) {
        final String fileName = line.substring(pos + "History for: ".length(), line.length());
        final String checkedOutFilePath = this.currentDir + "/" + fileName;
        this.currentFile = checkedOutFilePath;
    }
    
    private void processStatus(final String line, final int pos) {
        final String status = line.substring(pos + "Status: ".length(), line.length());
        if (status.equals("Out of Date")) {
            this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.MODIFIED));
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Out of Date file: " + this.currentFile);
            }
        }
        else if (status.equals("Modified")) {
            this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.MODIFIED));
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Modified file: " + this.currentFile);
            }
        }
        else if (status.equals("Missing")) {
            this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.ADDED));
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Missing file: " + this.currentFile);
            }
        }
        else if (status.equals("Merge")) {
            this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.CONFLICT));
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Conflict file: " + this.currentFile);
            }
        }
        else if (!status.equals("Current")) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("status unknown (" + status + "): " + this.currentFile);
            }
        }
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
}
