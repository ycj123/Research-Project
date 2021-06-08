// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.command.diff;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class GitDiffConsumer implements StreamConsumer
{
    private static final Pattern DIFF_FILES_PATTERN;
    private static final String START_REVISION_TOKEN = "---";
    private static final String END_REVISION_TOKEN = "+++";
    private static final String ADDED_LINE_TOKEN = "+";
    private static final String REMOVED_LINE_TOKEN = "-";
    private static final String UNCHANGED_LINE_TOKEN = " ";
    private static final String CHANGE_SEPARATOR_TOKEN = "@@";
    private static final String NO_NEWLINE_TOKEN = "\\ No newline at end of file";
    private static final String INDEX_LINE_TOKEN = "index ";
    private static final String NEW_FILE_MODE_TOKEN = "new file mode ";
    private static final String DELETED_FILE_MODE_TOKEN = "deleted file mode ";
    private ScmLogger logger;
    private String currentFile;
    private StringBuilder currentDifference;
    private List<ScmFile> changedFiles;
    private Map<String, CharSequence> differences;
    private StringBuilder patch;
    
    public GitDiffConsumer(final ScmLogger logger, final File workingDirectory) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.differences = new HashMap<String, CharSequence>();
        this.patch = new StringBuilder();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = GitDiffConsumer.DIFF_FILES_PATTERN.matcher(line);
        if (matcher.matches()) {
            this.currentFile = matcher.group(1);
            this.changedFiles.add(new ScmFile(this.currentFile, ScmFileStatus.MODIFIED));
            this.currentDifference = new StringBuilder();
            this.differences.put(this.currentFile, this.currentDifference);
            this.patch.append(line).append("\n");
            return;
        }
        if (this.currentFile == null) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unparseable line: '" + line + "'");
            }
            this.patch.append(line).append("\n");
            return;
        }
        if (line.startsWith("index ")) {
            this.patch.append(line).append("\n");
        }
        else if (line.startsWith("new file mode ") || line.startsWith("deleted file mode ")) {
            this.patch.append(line).append("\n");
        }
        else if (line.startsWith("---")) {
            this.patch.append(line).append("\n");
        }
        else if (line.startsWith("+++")) {
            this.patch.append(line).append("\n");
        }
        else if (line.startsWith("+") || line.startsWith("-") || line.startsWith(" ") || line.startsWith("@@") || line.equals("\\ No newline at end of file")) {
            this.currentDifference.append(line).append("\n");
            this.patch.append(line).append("\n");
        }
        else {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unparseable line: '" + line + "'");
            }
            this.patch.append(line).append("\n");
            this.currentFile = null;
            this.currentDifference = null;
        }
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
    
    static {
        DIFF_FILES_PATTERN = Pattern.compile("^diff --git\\sa/(.*)\\sb/(.*)");
    }
}
