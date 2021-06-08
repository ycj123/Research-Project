// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.changelog;

import java.util.Date;
import java.util.Locale;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgChangeLogConsumer extends HgConsumer
{
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss Z";
    private static final String REVNO_TAG = "changeset:";
    private static final String TAG_TAG = "tag:";
    private static final String BRANCH_TAG = "branch:";
    private static final String AUTHOR_TAG = "user:";
    private static final String TIME_STAMP_TOKEN = "date:";
    private static final String MESSAGE_TOKEN = "description:";
    private static final String FILES_TOKEN = "files:";
    private List<ChangeSet> logEntries;
    private ChangeSet currentChange;
    private String currentRevision;
    private String currentTag;
    private String currentBranch;
    private String userDatePattern;
    
    public HgChangeLogConsumer(final ScmLogger logger, final String userDatePattern) {
        super(logger);
        this.logEntries = new ArrayList<ChangeSet>();
        this.userDatePattern = userDatePattern;
    }
    
    public List<ChangeSet> getModifications() {
        return this.logEntries;
    }
    
    @Override
    public void consumeLine(final String line) {
        final String trimmedLine = line.trim();
        this.doConsume(null, trimmedLine);
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String line) {
        if (line.startsWith("changeset:")) {
            (this.currentChange = new ChangeSet()).setFiles(new ArrayList<ChangeFile>(0));
            this.logEntries.add(this.currentChange);
            final String tmpLine = line.substring("changeset:".length()).trim();
            this.currentRevision = tmpLine.substring(tmpLine.indexOf(58) + 1);
            this.currentChange.setRevision(this.currentRevision);
        }
        else if (line.startsWith("branch:")) {
            final String tmpLine = line.substring("branch:".length()).trim();
            this.currentBranch = tmpLine;
        }
        else if (line.startsWith("user:")) {
            final String tmpLine = line.substring("user:".length()).trim();
            this.currentChange.setAuthor(tmpLine);
        }
        else if (line.startsWith("date:")) {
            final String tmpLine = line.substring("date:".length()).trim();
            final Date date = this.parseDate(tmpLine, this.userDatePattern, "yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH);
            this.currentChange.setDate(date);
        }
        else if (line.startsWith("tag:")) {
            final String tmpLine = line.substring("tag:".length()).trim();
            this.currentTag = tmpLine;
        }
        else if (line.startsWith("files:")) {
            final String tmpLine = line.substring("files:".length()).trim();
            final String[] files = tmpLine.split(" ");
            for (int i = 0; i < files.length; ++i) {
                final String file = files[i];
                final ChangeFile changeFile = new ChangeFile(file, this.currentRevision);
                this.currentChange.addFile(changeFile);
            }
        }
        else if (line.startsWith("description:")) {
            this.currentChange.setComment("");
        }
        else {
            final StringBuilder comment = new StringBuilder(this.currentChange.getComment());
            comment.append(line);
            comment.append('\n');
            this.currentChange.setComment(comment.toString());
        }
    }
}
