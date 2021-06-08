// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.changelog;

import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmFileStatus;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.Locale;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitChangeLogConsumer extends AbstractConsumer
{
    private static final String GIT_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss Z";
    private static final int STATUS_GET_HEADER = 1;
    private static final int STATUS_GET_AUTHOR = 2;
    private static final int STATUS_RAW_TREE = 21;
    private static final int STATUS_RAW_PARENT = 22;
    private static final int STATUS_RAW_AUTHOR = 23;
    private static final int STATUS_RAW_COMMITTER = 24;
    private static final int STATUS_GET_DATE = 3;
    private static final int STATUS_GET_FILE = 4;
    private static final int STATUS_GET_COMMENT = 5;
    private static final Pattern HEADER_PATTERN;
    private static final Pattern AUTHOR_PATTERN;
    private static final Pattern RAW_TREE_PATTERN;
    private static final Pattern RAW_PARENT_PATTERN;
    private static final Pattern RAW_AUTHOR_PATTERN;
    private static final Pattern RAW_COMMITTER_PATTERN;
    private static final Pattern DATE_PATTERN;
    private static final Pattern FILE_PATTERN;
    private int status;
    private List<ChangeSet> entries;
    private ChangeSet currentChange;
    private String currentRevision;
    private StringBuilder currentComment;
    private String userDateFormat;
    
    public GitChangeLogConsumer(final ScmLogger logger, final String userDateFormat) {
        super(logger);
        this.status = 1;
        this.entries = new ArrayList<ChangeSet>();
        this.userDateFormat = userDateFormat;
    }
    
    public List<ChangeSet> getModifications() {
        this.processGetFile("");
        return this.entries;
    }
    
    public void consumeLine(final String line) {
        switch (this.status) {
            case 1: {
                this.processGetHeader(line);
                break;
            }
            case 2: {
                this.processGetAuthor(line);
                break;
            }
            case 3: {
                this.processGetDate(line, null);
                break;
            }
            case 5: {
                this.processGetComment(line);
                break;
            }
            case 4: {
                this.processGetFile(line);
                break;
            }
            case 21: {
                this.processGetRawTree(line);
                break;
            }
            case 22: {
                this.processGetRawParent(line);
                break;
            }
            case 23: {
                this.processGetRawAuthor(line);
                break;
            }
            case 24: {
                this.processGetRawCommitter(line);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown state: " + this.status);
            }
        }
    }
    
    private void processGetHeader(final String line) {
        final Matcher matcher = GitChangeLogConsumer.HEADER_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return;
        }
        this.currentRevision = matcher.group(1);
        (this.currentChange = new ChangeSet()).setRevision(this.currentRevision);
        this.status = 2;
    }
    
    private void processGetAuthor(final String line) {
        if (GitChangeLogConsumer.RAW_TREE_PATTERN.matcher(line).matches()) {
            this.status = 21;
            this.processGetRawTree(line);
            return;
        }
        final Matcher matcher = GitChangeLogConsumer.AUTHOR_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return;
        }
        final String author = matcher.group(1);
        this.currentChange.setAuthor(author);
        this.status = 3;
    }
    
    private void processGetRawTree(final String line) {
        if (!GitChangeLogConsumer.RAW_TREE_PATTERN.matcher(line).matches()) {
            return;
        }
        this.status = 22;
    }
    
    private void processGetRawParent(final String line) {
        final Matcher matcher = GitChangeLogConsumer.RAW_PARENT_PATTERN.matcher(line);
        if (!matcher.matches()) {
            this.status = 23;
            this.processGetRawAuthor(line);
            return;
        }
        final String parentHash = matcher.group(1);
        this.addParentRevision(parentHash);
    }
    
    private void addParentRevision(final String hash) {
        if (this.currentChange.getParentRevision() == null) {
            this.currentChange.setParentRevision(hash);
        }
        else {
            this.currentChange.addMergedRevision(hash);
        }
    }
    
    private void processGetRawAuthor(final String line) {
        final Matcher matcher = GitChangeLogConsumer.RAW_AUTHOR_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return;
        }
        final String author = matcher.group(1);
        this.currentChange.setAuthor(author);
        final String datestring = matcher.group(2);
        final String tz = matcher.group(3);
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone(tz));
        c.setTimeInMillis(Long.parseLong(datestring) * 1000L);
        this.currentChange.setDate(c.getTime());
        this.status = 24;
    }
    
    private void processGetRawCommitter(final String line) {
        if (!GitChangeLogConsumer.RAW_COMMITTER_PATTERN.matcher(line).matches()) {
            return;
        }
        this.status = 5;
    }
    
    private void processGetDate(final String line, final Locale locale) {
        final Matcher matcher = GitChangeLogConsumer.DATE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return;
        }
        final String datestring = matcher.group(1);
        final Date date = this.parseDate(datestring.trim(), this.userDateFormat, "yyyy-MM-dd HH:mm:ss Z", locale);
        this.currentChange.setDate(date);
        this.status = 5;
    }
    
    private void processGetComment(final String line) {
        if (line.length() < 4) {
            if (this.currentComment == null) {
                this.currentComment = new StringBuilder();
            }
            else {
                this.currentChange.setComment(this.currentComment.toString());
                this.status = 4;
            }
        }
        else {
            if (this.currentComment.length() > 0) {
                this.currentComment.append('\n');
            }
            this.currentComment.append(line.substring(4));
        }
    }
    
    private void processGetFile(final String line) {
        if (line.length() == 0) {
            if (this.currentChange != null) {
                this.entries.add(this.currentChange);
            }
            this.resetChangeLog();
            this.status = 1;
        }
        else {
            final Matcher matcher = GitChangeLogConsumer.FILE_PATTERN.matcher(line);
            if (!matcher.matches()) {
                return;
            }
            final String actionChar = matcher.group(1);
            String name = matcher.group(2);
            String originalName = null;
            String originalRevision = null;
            ScmFileStatus action;
            if ("A".equals(actionChar)) {
                action = ScmFileStatus.ADDED;
            }
            else if ("M".equals(actionChar)) {
                action = ScmFileStatus.MODIFIED;
            }
            else if ("D".equals(actionChar)) {
                action = ScmFileStatus.DELETED;
            }
            else if ("R".equals(actionChar)) {
                action = ScmFileStatus.RENAMED;
                originalName = name;
                name = matcher.group(4);
                originalRevision = this.currentChange.getParentRevision();
            }
            else if ("C".equals(actionChar)) {
                action = ScmFileStatus.COPIED;
                originalName = name;
                name = matcher.group(4);
                originalRevision = this.currentChange.getParentRevision();
            }
            else {
                action = ScmFileStatus.UNKNOWN;
            }
            final ChangeFile changeFile = new ChangeFile(name, this.currentRevision);
            changeFile.setAction(action);
            changeFile.setOriginalName(originalName);
            changeFile.setOriginalRevision(originalRevision);
            this.currentChange.addFile(changeFile);
        }
    }
    
    private void resetChangeLog() {
        this.currentComment = null;
        this.currentChange = null;
    }
    
    static {
        HEADER_PATTERN = Pattern.compile("^commit (.*)");
        AUTHOR_PATTERN = Pattern.compile("^Author: (.*)");
        RAW_TREE_PATTERN = Pattern.compile("^tree ([A-Fa-f0-9]+)");
        RAW_PARENT_PATTERN = Pattern.compile("^parent ([A-Fa-f0-9]+)");
        RAW_AUTHOR_PATTERN = Pattern.compile("^author (.+ <.+>) ([0-9]+) (.*)");
        RAW_COMMITTER_PATTERN = Pattern.compile("^committer (.+ <.+>) ([0-9]+) (.*)");
        DATE_PATTERN = Pattern.compile("^Date:\\s*(.*)");
        FILE_PATTERN = Pattern.compile("^:\\d* \\d* [A-Fa-f0-9]*\\.* [A-Fa-f0-9]*\\.* ([A-Z])[0-9]*\\t([^\\t]*)(\\t(.*))?");
    }
}
