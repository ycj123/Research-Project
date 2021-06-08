// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.changelog;

import java.util.Iterator;
import java.text.SimpleDateFormat;
import org.apache.maven.scm.ScmVersion;
import java.util.Date;
import org.apache.maven.scm.ChangeSet;
import java.util.List;

public class ChangeLogSet
{
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    private List<ChangeSet> entries;
    private Date startDate;
    private Date endDate;
    private ScmVersion startVersion;
    private ScmVersion endVersion;
    
    public ChangeLogSet(final Date startDate, final Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public ChangeLogSet(final List<ChangeSet> entries, final Date startDate, final Date endDate) {
        this(startDate, endDate);
        this.setChangeSets(entries);
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public ScmVersion getStartVersion() {
        return this.startVersion;
    }
    
    public void setStartVersion(final ScmVersion startVersion) {
        this.startVersion = startVersion;
    }
    
    public ScmVersion getEndVersion() {
        return this.endVersion;
    }
    
    public void setEndVersion(final ScmVersion endVersion) {
        this.endVersion = endVersion;
    }
    
    public List<ChangeSet> getChangeSets() {
        return this.entries;
    }
    
    public void setChangeSets(final List<ChangeSet> changeSets) {
        this.entries = changeSets;
    }
    
    public String toXML() {
        return this.toXML("ISO-8859-1");
    }
    
    public String toXML(final String encoding) {
        String encodingString = encoding;
        if (encodingString == null) {
            encodingString = "ISO-8859-1";
        }
        final StringBuilder buffer = new StringBuilder();
        final String pattern = "yyyyMMdd HH:mm:ss z";
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        buffer.append("<?xml version=\"1.0\" encoding=\"" + encodingString + "\"?>\n");
        buffer.append("<changeset datePattern=\"").append(pattern).append("\"");
        if (this.startDate != null) {
            buffer.append(" start=\"").append(formatter.format(this.getStartDate())).append("\"");
        }
        if (this.endDate != null) {
            buffer.append(" end=\"").append(formatter.format(this.getEndDate())).append("\"");
        }
        if (this.startVersion != null) {
            buffer.append(" startVersion=\"").append(this.getStartVersion()).append("\"");
        }
        if (this.endVersion != null) {
            buffer.append(" endVersion=\"").append(this.getEndVersion()).append("\"");
        }
        buffer.append(">\n");
        for (final ChangeSet changeSet : this.getChangeSets()) {
            buffer.append(changeSet.toXML());
        }
        buffer.append("</changeset>\n");
        return buffer.toString();
    }
}
