// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.changelog;

import org.apache.maven.scm.ScmFileStatus;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import org.apache.maven.scm.ChangeFile;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzListChangesetConsumer extends AbstractRepositoryConsumer
{
    private static final int STATE_CHANGE_SETS = 0;
    private static final int STATE_CHANGE_SET = 1;
    private static final int STATE_COMPONENT = 2;
    private static final int STATE_MODIFIED = 3;
    private static final int STATE_CHANGES = 4;
    private static final String HEADER_CHANGE_SETS = "Change sets:";
    private static final String HEADER_CHANGE_SET = "(";
    private static final String HEADER_COMPONENT = "Component:";
    private static final String HEADER_MODIFIED = "Modified:";
    private static final String HEADER_CHANGES = "Changes:";
    private static final String JAZZ_TIMESTAMP_PATTERN = "MMM d, yyyy h:mm a";
    private static final String JAZZ_TIMESTAMP_PATTERN_TIME = "h:mm a";
    private static final Pattern CHANGESET_PATTERN;
    private static final Pattern CHANGES_PATTERN;
    private List<ChangeSet> entries;
    private final String userDateFormat;
    private int currentChangeSetIndex;
    private int currentState;
    
    public JazzListChangesetConsumer(final ScmProviderRepository repo, final ScmLogger logger, final List<ChangeSet> entries, final String userDateFormat) {
        super(repo, logger);
        this.currentChangeSetIndex = -1;
        this.currentState = 0;
        this.entries = entries;
        this.userDateFormat = userDateFormat;
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        if (line.trim().startsWith("Change sets:")) {
            this.currentState = 0;
        }
        else if (line.trim().startsWith("(")) {
            this.currentState = 1;
        }
        else if (line.trim().startsWith("Component:")) {
            this.currentState = 2;
        }
        else if (line.trim().startsWith("Modified:")) {
            this.currentState = 3;
        }
        else if (line.trim().startsWith("Changes:")) {
            this.currentState = 4;
        }
        switch (this.currentState) {
            case 1: {
                this.processChangeSetLine(line);
            }
            case 3: {
                this.processModifiedLine(line);
                break;
            }
            case 4: {
                this.processChangesLine(line);
                break;
            }
        }
    }
    
    private void processChangeSetLine(final String line) {
        final Matcher matcher = JazzListChangesetConsumer.CHANGESET_PATTERN.matcher(line);
        if (matcher.find()) {
            ++this.currentChangeSetIndex;
            final ChangeSet currentChangeSet = this.entries.get(this.currentChangeSetIndex);
            final List<ChangeFile> files = new ArrayList<ChangeFile>();
            currentChangeSet.setFiles(files);
            final String changesetAlias = matcher.group(1);
            final String changeFlags = matcher.group(2);
            final String author = matcher.group(3);
            String comment = matcher.group(4);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("  Parsing ChangeSet Line : " + line);
                this.getLogger().debug("    changesetAlias : " + changesetAlias);
                this.getLogger().debug("    changeFlags    : " + changeFlags);
                this.getLogger().debug("    author         : " + author);
                this.getLogger().debug("    comment        : " + comment);
            }
            if (currentChangeSet.getRevision() != null && !currentChangeSet.getRevision().equals(changesetAlias)) {
                this.getLogger().warn("Warning! The indexes appear to be out of sequence! For currentChangeSetIndex = " + this.currentChangeSetIndex + ", we got '" + changesetAlias + "' and not '" + currentChangeSet.getRevision() + "' as expected.");
            }
            comment = this.stripDelimiters(comment);
            currentChangeSet.setAuthor(author);
            currentChangeSet.setComment(comment);
        }
    }
    
    private void processModifiedLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("  Parsing Modified Line : " + line);
        }
        final int colonPos = line.indexOf(":");
        final int parenPos = line.indexOf("(");
        String date = null;
        if (colonPos != -1 && parenPos != -1) {
            date = line.substring(colonPos + 2, parenPos - 1);
        }
        else if (colonPos != -1 && parenPos == -1) {
            date = line.substring(colonPos + 2);
        }
        if (date != null) {
            Date changesetDate = this.parseDate(date.toString(), this.userDateFormat, "MMM d, yyyy h:mm a");
            if (changesetDate == null) {
                changesetDate = this.parseDate(date.toString(), this.userDateFormat, "MMM d, yyyy h:mm a", Locale.ENGLISH);
            }
            if (changesetDate == null) {
                changesetDate = this.parseDate(date.toString(), this.userDateFormat, "h:mm a");
                final Calendar today = Calendar.getInstance();
                final Calendar changesetCal = Calendar.getInstance();
                changesetCal.setTimeInMillis(changesetDate.getTime());
                changesetCal.set(today.get(1), today.get(2), today.get(5));
                changesetDate = changesetCal.getTime();
            }
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("    date           : " + date);
                this.getLogger().debug("    changesetDate  : " + changesetDate);
            }
            final ChangeSet currentChangeSet = this.entries.get(this.currentChangeSetIndex);
            currentChangeSet.setDate(changesetDate);
        }
    }
    
    private void processChangesLine(final String line) {
        final Matcher matcher = JazzListChangesetConsumer.CHANGES_PATTERN.matcher(line);
        if (matcher.find()) {
            final ChangeSet currentChangeSet = this.entries.get(this.currentChangeSetIndex);
            final String changeFlags = matcher.group(1);
            final String fileAlias = matcher.group(2);
            final String file = matcher.group(3);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("  Parsing Changes Line : " + line);
                this.getLogger().debug("    changeFlags    : " + changeFlags + " Translated to : " + this.parseFileChangeState(changeFlags));
                this.getLogger().debug("    filetAlias     : " + fileAlias);
                this.getLogger().debug("    file           : " + file);
            }
            final ChangeFile changeFile = new ChangeFile(file);
            final ScmFileStatus status = this.parseFileChangeState(changeFlags);
            changeFile.setAction(status);
            currentChangeSet.getFiles().add(changeFile);
        }
    }
    
    protected String stripDelimiters(final String text) {
        if (text == null) {
            return null;
        }
        String workingText = text;
        if (workingText.startsWith("\"") || workingText.startsWith("<")) {
            workingText = workingText.substring(1);
        }
        if (workingText.endsWith("\"") || workingText.endsWith(">")) {
            workingText = workingText.substring(0, workingText.length() - 1);
        }
        return workingText;
    }
    
    private ScmFileStatus parseChangeSetChangeState(final String state) {
        if (state.length() != 4) {
            throw new IllegalArgumentException("Change State string must be 4 chars long!");
        }
        return ScmFileStatus.UNKNOWN;
    }
    
    private ScmFileStatus parseFileChangeState(final String state) {
        if (state.length() != 5) {
            throw new IllegalArgumentException("Change State string must be 5 chars long!");
        }
        ScmFileStatus status = ScmFileStatus.UNKNOWN;
        if (state.charAt(0) == '!') {
            status = ScmFileStatus.CONFLICT;
        }
        if (state.charAt(1) == '#') {
            status = ScmFileStatus.CONFLICT;
        }
        if (state.charAt(2) == 'a') {
            status = ScmFileStatus.ADDED;
        }
        else if (state.charAt(2) == 'd') {
            status = ScmFileStatus.DELETED;
        }
        else {
            if (state.charAt(2) == 'm') {
                status = ScmFileStatus.RENAMED;
            }
            if (state.charAt(3) == 'c') {
                status = ScmFileStatus.MODIFIED;
            }
            if (state.charAt(4) == 'p') {
                status = ScmFileStatus.MODIFIED;
            }
        }
        return status;
    }
    
    static {
        CHANGESET_PATTERN = Pattern.compile("\\((\\d+)\\)  (....) (\\w+) (.*)");
        CHANGES_PATTERN = Pattern.compile("(.....) \\((\\d+)\\) (.*)");
    }
}
