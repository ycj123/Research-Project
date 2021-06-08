// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.diff;

import org.apache.maven.scm.ScmFileStatus;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamDiffConsumer implements StreamConsumer
{
    private static final String WORKING_DIR_TOKEN = "(working dir: ";
    private static final String PATCH_SEPARATOR_TOKEN = "--------------";
    private static final String REVISION_TOKEN = " Revision: ";
    private static final String ONDISK_TOKEN = " (on disk)";
    private static final String ADDED_LINE_TOKEN = "+";
    private static final String REMOVED_LINE_TOKEN = "-";
    private static final String UNCHANGED_LINE_TOKEN = " ";
    private ScmLogger logger;
    private String currentDir;
    private boolean diffBlockProcessingStarted;
    private boolean revisionBlockStarted;
    private String currentFile;
    private StringBuilder currentDifference;
    private List<ScmFile> changedFiles;
    private Map<String, CharSequence> differences;
    private StringBuilder patch;
    
    public StarteamDiffConsumer(final ScmLogger logger, final File workingDirectory) {
        this.currentDir = "";
        this.diffBlockProcessingStarted = false;
        this.revisionBlockStarted = false;
        this.changedFiles = new ArrayList<ScmFile>();
        this.differences = new HashMap<String, CharSequence>();
        this.patch = new StringBuilder();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        int pos = 0;
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        this.patch.append(line).append("\n");
        if (line.trim().length() == 0) {
            return;
        }
        if ((pos = line.indexOf("(working dir: ")) != -1) {
            this.processGetDir(line, pos);
            return;
        }
        if (line.startsWith("--------------")) {
            this.diffBlockProcessingStarted = !this.diffBlockProcessingStarted;
            if (this.diffBlockProcessingStarted && this.revisionBlockStarted) {
                throw new IllegalStateException("Missing second Revision line or local copy line ");
            }
        }
        else {
            if ((pos = line.indexOf(" Revision: ")) != -1) {
                if (this.revisionBlockStarted) {
                    this.revisionBlockStarted = false;
                }
                else {
                    this.extractCurrentFile(line, pos);
                    this.revisionBlockStarted = true;
                }
                return;
            }
            if ((pos = line.indexOf(" (on disk)")) != -1) {
                if (this.revisionBlockStarted) {
                    this.revisionBlockStarted = false;
                    return;
                }
                throw new IllegalStateException("Working copy line found at the wrong state ");
            }
            else {
                if (!this.diffBlockProcessingStarted) {
                    if (this.logger.isWarnEnabled()) {
                        this.logger.warn("Unparseable line: '" + line + "'");
                    }
                    return;
                }
                if (line.startsWith("+") || line.startsWith("-") || line.startsWith(" ")) {
                    this.currentDifference.append(line).append("\n");
                }
                else if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Unparseable line: '" + line + "'");
                }
            }
        }
    }
    
    private void processGetDir(final String line, final int pos) {
        final String dirPath = line.substring(pos + "(working dir: ".length(), line.length() - 1).replace('\\', '/');
        this.currentDir = dirPath;
    }
    
    private void extractCurrentFile(final String line, final int pos) {
        this.currentFile = line.substring(0, pos);
        this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.MODIFIED));
        this.currentDifference = new StringBuilder();
        this.differences.put(this.currentFile, this.currentDifference);
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
    
    public Map<String, CharSequence> getDifferences() {
        return this.differences;
    }
    
    public String getPatch() {
        return this.patch.toString();
    }
}
