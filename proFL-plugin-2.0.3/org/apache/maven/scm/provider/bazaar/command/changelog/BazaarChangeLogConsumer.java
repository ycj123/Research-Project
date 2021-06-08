// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.changelog;

import java.util.Date;
import org.apache.maven.scm.ChangeFile;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

public class BazaarChangeLogConsumer extends BazaarConsumer
{
    private static final String BAZAAR_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss Z";
    private static final String START_LOG_TAG = "-----";
    private static final String REVNO_TAG = "revno: ";
    private static final String AUTHOR_TAG = "committer: ";
    private static final String TIME_STAMP_TOKEN = "timestamp: ";
    private static final String MESSAGE_TOKEN = "message:";
    private static final String BRANCH_NICK_TOKEN = "branch nick: ";
    private static final String MERGED_TOKEN = "merged: ";
    private static final String RENAME_SEPARATOR = " => ";
    private List<ChangeSet> logEntries;
    private ChangeSet currentChange;
    private ChangeSet lastChange;
    private boolean isMergeEntry;
    private String currentRevision;
    private StringBuilder currentComment;
    private String userDatePattern;
    private ScmFileStatus currentStatus;
    
    public BazaarChangeLogConsumer(final ScmLogger logger, final String userDatePattern) {
        super(logger);
        this.logEntries = new ArrayList<ChangeSet>();
        this.currentStatus = null;
        this.userDatePattern = userDatePattern;
    }
    
    public List<ChangeSet> getModifications() {
        return this.logEntries;
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String line) {
        String tmpLine = line;
        if (line.startsWith("-----")) {
            if (this.isMergeEntry && this.lastChange != null) {
                String comment = this.lastChange.getComment();
                comment = comment + "\n[MAVEN]: Merged from " + this.currentChange.getAuthor();
                comment = comment + "\n[MAVEN]:    " + this.currentChange.getDateFormatted();
                comment = comment + "\n[MAVEN]:    " + this.currentChange.getComment();
                this.lastChange.setComment(comment);
            }
            (this.currentChange = new ChangeSet()).setFiles(new ArrayList<ChangeFile>());
            this.logEntries.add(this.currentChange);
            this.currentComment = new StringBuilder();
            this.currentStatus = null;
            this.currentRevision = "";
            this.isMergeEntry = false;
        }
        else if (line.startsWith("merged: ")) {
            this.isMergeEntry = true;
            this.logEntries.remove(this.currentChange);
            if (this.logEntries.size() > 0) {
                this.lastChange = this.logEntries.get(this.logEntries.size() - 1);
            }
            else {
                if (this.getLogger().isWarnEnabled()) {
                    this.getLogger().warn("First entry was unexpectedly a merged entry");
                }
                this.lastChange = null;
            }
        }
        else if (line.startsWith("revno: ")) {
            tmpLine = line.substring("revno: ".length());
            tmpLine = tmpLine.trim();
            this.currentRevision = tmpLine;
        }
        else if (line.startsWith("committer: ")) {
            tmpLine = line.substring("committer: ".length());
            tmpLine = tmpLine.trim();
            this.currentChange.setAuthor(tmpLine);
        }
        else if (line.startsWith("timestamp: ")) {
            tmpLine = line.substring("timestamp: ".length() + 3);
            tmpLine = tmpLine.trim();
            final Date date = this.parseDate(tmpLine, this.userDatePattern, "yyyy-MM-dd HH:mm:ss Z");
            this.currentChange.setDate(date);
        }
        else if (line.startsWith("message:")) {
            this.currentStatus = ScmFileStatus.UNKNOWN;
        }
        else if (status != null) {
            this.currentStatus = status;
        }
        else if (this.currentStatus == ScmFileStatus.UNKNOWN) {
            this.currentComment.append(line);
            this.currentChange.setComment(this.currentComment.toString());
            this.currentComment.append("\n");
        }
        else if (this.currentStatus != null) {
            tmpLine = tmpLine.trim();
            ChangeFile changeFile;
            if (this.currentStatus == ScmFileStatus.RENAMED) {
                final String[] parts = tmpLine.split(" => ");
                if (parts.length != 2) {
                    changeFile = new ChangeFile(tmpLine, this.currentRevision);
                }
                else {
                    changeFile = new ChangeFile(parts[1], this.currentRevision);
                    changeFile.setOriginalName(parts[0]);
                }
            }
            else {
                changeFile = new ChangeFile(tmpLine, this.currentRevision);
            }
            changeFile.setAction(this.currentStatus);
            this.currentChange.addFile(changeFile);
        }
        else if (!line.startsWith("branch nick: ")) {
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("Could not figure out of: " + line);
            }
        }
    }
}
