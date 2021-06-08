// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.changelog;

import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class CvsChangeLogConsumer extends AbstractConsumer
{
    private List<ChangeSet> entries;
    private static final int GET_FILE = 1;
    private static final int GET_DATE = 2;
    private static final int GET_COMMENT = 3;
    private static final int GET_REVISION = 4;
    private static final String START_FILE = "Working file: ";
    private static final String END_FILE = "=============================================================================";
    private static final String START_REVISION = "----------------------------";
    private static final String REVISION_TAG = "revision ";
    private static final String DATE_TAG = "date: ";
    private int status;
    private ChangeSet currentChange;
    private ChangeFile currentFile;
    private String userDatePattern;
    
    public CvsChangeLogConsumer(final ScmLogger logger, final String userDatePattern) {
        super(logger);
        this.entries = new ArrayList<ChangeSet>();
        this.status = 1;
        this.currentChange = null;
        this.currentFile = null;
        this.userDatePattern = userDatePattern;
    }
    
    public List<ChangeSet> getModifications() {
        Collections.sort(this.entries, new Comparator<ChangeSet>() {
            public int compare(final ChangeSet set1, final ChangeSet set2) {
                return set1.getDate().compareTo(set2.getDate());
            }
        });
        final List<ChangeSet> fixedModifications = new ArrayList<ChangeSet>();
        ChangeSet currentEntry = null;
        for (final ChangeSet entry : this.entries) {
            if (currentEntry == null) {
                currentEntry = entry;
            }
            else if (this.areEqual(currentEntry, entry)) {
                currentEntry.addFile(entry.getFiles().get(0));
            }
            else {
                fixedModifications.add(currentEntry);
                currentEntry = entry;
            }
        }
        if (currentEntry != null) {
            fixedModifications.add(currentEntry);
        }
        return fixedModifications;
    }
    
    private boolean areEqual(final ChangeSet set1, final ChangeSet set2) {
        return set1.getAuthor().equals(set2.getAuthor()) && set1.getComment().equals(set2.getComment()) && set1.getDate().equals(set2.getDate());
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(line);
        }
        try {
            switch (this.getStatus()) {
                case 1: {
                    this.processGetFile(line);
                    break;
                }
                case 4: {
                    this.processGetRevision(line);
                    break;
                }
                case 2: {
                    this.processGetDate(line);
                    break;
                }
                case 3: {
                    this.processGetComment(line);
                    break;
                }
                default: {
                    throw new IllegalStateException("Unknown state: " + this.status);
                }
            }
        }
        catch (Throwable ex) {
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("Exception in the cvs changelog consumer.", ex);
            }
        }
    }
    
    private void addEntry(final ChangeSet entry, final ChangeFile file) {
        if (entry.getAuthor() == null) {
            return;
        }
        entry.addFile(file);
        this.entries.add(entry);
    }
    
    private void processGetFile(final String line) {
        if (line.startsWith("Working file: ")) {
            this.setCurrentChange(new ChangeSet());
            this.setCurrentFile(new ChangeFile(line.substring("Working file: ".length(), line.length())));
            this.setStatus(4);
        }
    }
    
    private void processGetRevision(final String line) {
        if (line.startsWith("revision ")) {
            this.getCurrentFile().setRevision(line.substring("revision ".length()));
            this.setStatus(2);
        }
        else if (line.startsWith("=============================================================================")) {
            this.setStatus(1);
            this.addEntry(this.getCurrentChange(), this.getCurrentFile());
        }
    }
    
    private void processGetDate(final String line) {
        if (line.startsWith("date: ")) {
            final StringTokenizer tokenizer = new StringTokenizer(line, ";");
            final String datePart = tokenizer.nextToken().trim();
            String dateTime = datePart.substring("date: ".length());
            final StringTokenizer dateTokenizer = new StringTokenizer(dateTime, " ");
            if (dateTokenizer.countTokens() == 2) {
                dateTime += " UTC";
            }
            this.getCurrentChange().setDate(dateTime, this.userDatePattern);
            final String authorPart = tokenizer.nextToken().trim();
            final String author = authorPart.substring("author: ".length());
            this.getCurrentChange().setAuthor(author);
            this.setStatus(3);
        }
    }
    
    private void processGetComment(final String line) {
        if (line.startsWith("----------------------------")) {
            this.addEntry(this.getCurrentChange(), this.getCurrentFile());
            this.setCurrentChange(new ChangeSet());
            this.setCurrentFile(new ChangeFile(this.getCurrentFile().getName()));
            this.setStatus(4);
        }
        else if (line.startsWith("=============================================================================")) {
            this.addEntry(this.getCurrentChange(), this.getCurrentFile());
            this.setStatus(1);
        }
        else {
            this.getCurrentChange().setComment(this.getCurrentChange().getComment() + line + "\n");
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
