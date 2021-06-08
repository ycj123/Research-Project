// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.changelog;

import java.util.regex.Matcher;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmException;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class PerforceDescribeConsumer extends AbstractConsumer
{
    private List<ChangeSet> entries;
    private static final int GET_REVISION = 1;
    private static final int GET_COMMENT_BEGIN = 2;
    private static final int GET_COMMENT = 3;
    private static final int GET_AFFECTED_FILES = 4;
    private static final int GET_FILES_BEGIN = 5;
    private static final int GET_FILE = 6;
    private int status;
    private String currentRevision;
    private ChangeSet currentChange;
    private String currentFile;
    private String repoPath;
    private String userDatePattern;
    private static final Pattern REVISION_PATTERN;
    private static final String COMMENT_DELIMITER = "";
    private static final String CHANGELIST_DELIMITER = "";
    private static final Pattern FILE_PATTERN;
    
    public PerforceDescribeConsumer(final String repoPath, final String userDatePattern, final ScmLogger logger) {
        super(logger);
        this.entries = new ArrayList<ChangeSet>();
        this.status = 1;
        this.repoPath = repoPath;
        this.userDatePattern = userDatePattern;
    }
    
    public List<ChangeSet> getModifications() throws ScmException {
        return this.entries;
    }
    
    public void consumeLine(final String line) {
        switch (this.status) {
            case 1: {
                this.processGetRevision(line);
                break;
            }
            case 2: {
                this.status = 3;
                break;
            }
            case 3: {
                this.processGetComment(line);
                break;
            }
            case 4: {
                this.processGetAffectedFiles(line);
                break;
            }
            case 5: {
                this.status = 6;
                break;
            }
            case 6: {
                this.processGetFile(line);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown state: " + this.status);
            }
        }
    }
    
    private void addEntry(final ChangeSet entry, final ChangeFile file) {
        entry.addFile(file);
    }
    
    private void processGetFile(final String line) {
        if (line.equals("")) {
            this.entries.add(0, this.currentChange);
            this.status = 1;
            return;
        }
        final Matcher matcher = PerforceDescribeConsumer.FILE_PATTERN.matcher(line);
        if (!matcher.find()) {
            return;
        }
        this.currentFile = matcher.group(1);
        if (this.currentFile.startsWith(this.repoPath)) {
            this.currentFile = this.currentFile.substring(this.repoPath.length() + 1);
            this.addEntry(this.currentChange, new ChangeFile(this.currentFile, matcher.group(2)));
        }
    }
    
    private void processGetRevision(final String line) {
        final Matcher matcher = PerforceDescribeConsumer.REVISION_PATTERN.matcher(line);
        if (!matcher.find()) {
            return;
        }
        this.currentChange = new ChangeSet();
        this.currentRevision = matcher.group(1);
        this.currentChange.setAuthor(matcher.group(2));
        this.currentChange.setDate(matcher.group(3), this.userDatePattern);
        this.status = 2;
    }
    
    private void processGetComment(final String line) {
        if (line.equals("")) {
            this.status = 4;
        }
        else {
            this.currentChange.setComment(this.currentChange.getComment() + line.substring(1) + "\n");
        }
    }
    
    private void processGetAffectedFiles(final String line) {
        if (!line.equals("Affected files ...")) {
            return;
        }
        this.status = 5;
    }
    
    static {
        REVISION_PATTERN = Pattern.compile("^Change (\\d+) by (.*)@[^ ]+ on (.*)");
        FILE_PATTERN = Pattern.compile("^\\.\\.\\. (.*)#(\\d+) ");
    }
}
