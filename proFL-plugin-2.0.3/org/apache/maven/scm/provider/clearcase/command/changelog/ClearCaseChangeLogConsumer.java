// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.changelog;

import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class ClearCaseChangeLogConsumer extends AbstractConsumer
{
    private static final String CLEARCASE_TIMESTAMP_PATTERN = "yyyyMMdd.HHmmss";
    private static final String NAME_TAG = "NAME:";
    private static final String USER_TAG = "USER:";
    private static final String DATE_TAG = "DATE:";
    private static final String COMMENT_TAG = "COMM:";
    private static final String REVISION_TAG = "REVI:";
    private List<ChangeSet> entries;
    private static final int GET_FILE = 1;
    private static final int GET_DATE = 2;
    private static final int GET_COMMENT = 3;
    private static final int GET_REVISION = 4;
    private int status;
    private ChangeSet currentChange;
    private ChangeFile currentFile;
    private String userDatePattern;
    
    public ClearCaseChangeLogConsumer(final ScmLogger logger, final String userDatePattern) {
        super(logger);
        this.entries = new ArrayList<ChangeSet>();
        this.status = 1;
        this.currentChange = null;
        this.currentFile = null;
        this.userDatePattern = userDatePattern;
    }
    
    public List<ChangeSet> getModifications() {
        return this.entries;
    }
    
    public void consumeLine(final String line) {
        switch (this.getStatus()) {
            case 1: {
                this.processGetFile(line);
                break;
            }
            case 2: {
                this.processGetDate(line);
                break;
            }
            case 3: {
                this.processGetCommentAndUser(line);
                break;
            }
            case 4: {
                this.processGetRevision(line);
                break;
            }
            default: {
                if (this.getLogger().isWarnEnabled()) {
                    this.getLogger().warn("Unknown state: " + this.status);
                    break;
                }
                break;
            }
        }
    }
    
    private void processGetFile(final String line) {
        if (line.startsWith("NAME:")) {
            this.setCurrentChange(new ChangeSet());
            this.setCurrentFile(new ChangeFile(line.substring("NAME:".length(), line.length())));
            this.setStatus(2);
        }
    }
    
    private void processGetDate(final String line) {
        if (line.startsWith("DATE:")) {
            this.getCurrentChange().setDate(this.parseDate(line.substring("DATE:".length()), this.userDatePattern, "yyyyMMdd.HHmmss"));
            this.setStatus(3);
        }
    }
    
    private void processGetCommentAndUser(final String line) {
        if (line.startsWith("COMM:")) {
            final String comm = line.substring("COMM:".length());
            this.getCurrentChange().setComment(this.getCurrentChange().getComment() + comm + "\n");
        }
        else if (line.startsWith("USER:")) {
            this.getCurrentChange().setAuthor(line.substring("USER:".length()));
            this.getCurrentChange().addFile(this.getCurrentFile());
            this.entries.add(this.getCurrentChange());
            this.setStatus(4);
        }
        else {
            this.getCurrentChange().setComment(this.getCurrentChange().getComment() + line + "\n");
        }
    }
    
    private void processGetRevision(final String line) {
        if (line.startsWith("REVI:")) {
            this.getCurrentChange().setRevision(line.substring("REVI:".length()));
            this.setStatus(1);
        }
    }
    
    private ChangeFile getCurrentFile() {
        return this.currentFile;
    }
    
    private void setCurrentFile(final ChangeFile currentFile) {
        this.currentFile = currentFile;
    }
    
    private ChangeSet getCurrentChange() {
        return this.currentChange;
    }
    
    private void setCurrentChange(final ChangeSet currentChange) {
        this.currentChange = currentChange;
    }
    
    private int getStatus() {
        return this.status;
    }
    
    private void setStatus(final int status) {
        this.status = status;
    }
}
