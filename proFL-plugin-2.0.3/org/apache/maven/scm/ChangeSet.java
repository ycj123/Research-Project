// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.codehaus.plexus.util.StringUtils;
import java.util.Iterator;
import org.apache.maven.scm.util.FilenameUtils;
import org.apache.maven.scm.provider.ScmProviderRepository;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Date;
import org.apache.maven.scm.util.ThreadSafeDateFormat;
import java.io.Serializable;

public class ChangeSet implements Serializable
{
    private static final long serialVersionUID = 7097705862222539801L;
    public static final String LESS_THAN_ENTITY = "&lt;";
    public static final String GREATER_THAN_ENTITY = "&gt;";
    public static final String AMPERSAND_ENTITY = "&amp;";
    public static final String APOSTROPHE_ENTITY = "&apos;";
    public static final String QUOTE_ENTITY = "&quot;";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final ThreadSafeDateFormat DATE_FORMAT;
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final ThreadSafeDateFormat TIME_FORMAT;
    private static final ThreadSafeDateFormat TIMESTAMP_FORMAT_1;
    private static final ThreadSafeDateFormat TIMESTAMP_FORMAT_2;
    private static final ThreadSafeDateFormat TIMESTAMP_FORMAT_3;
    private static final ThreadSafeDateFormat TIMESTAMP_FORMAT_4;
    private Date date;
    private String author;
    private String comment;
    private List<ChangeFile> files;
    private String revision;
    private String parentRevision;
    private Set<String> mergedRevisions;
    
    public ChangeSet(final String strDate, final String userDatePattern, final String comment, final String author, final List<ChangeFile> files) {
        this(null, comment, author, files);
        this.setDate(strDate, userDatePattern);
    }
    
    public ChangeSet(final Date date, final String comment, final String author, final List<ChangeFile> files) {
        this.comment = "";
        this.setDate(date);
        this.setAuthor(author);
        this.setComment(comment);
        this.files = files;
    }
    
    public ChangeSet() {
        this.comment = "";
    }
    
    public List<ChangeFile> getFiles() {
        if (this.files == null) {
            return new ArrayList<ChangeFile>();
        }
        return this.files;
    }
    
    public void setFiles(final List<ChangeFile> files) {
        this.files = files;
    }
    
    public void addFile(final ChangeFile file) {
        if (this.files == null) {
            this.files = new ArrayList<ChangeFile>();
        }
        this.files.add(file);
    }
    
    @Deprecated
    public boolean containsFilename(final String filename, final ScmProviderRepository repository) {
        return this.containsFilename(filename);
    }
    
    public boolean containsFilename(final String filename) {
        if (this.files != null) {
            for (final ChangeFile file : this.files) {
                final String f1 = FilenameUtils.normalizeFilename(file.getName());
                final String f2 = FilenameUtils.normalizeFilename(filename);
                if (f1.indexOf(f2) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(final String comment) {
        this.comment = comment;
    }
    
    public Date getDate() {
        if (this.date != null) {
            return (Date)this.date.clone();
        }
        return null;
    }
    
    public void setDate(final Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        }
    }
    
    public void setDate(final String date) {
        this.setDate(date, null);
    }
    
    public void setDate(final String date, final String userDatePattern) {
        try {
            if (!StringUtils.isEmpty(userDatePattern)) {
                final SimpleDateFormat format = new SimpleDateFormat(userDatePattern);
                this.date = format.parse(date);
            }
            else {
                this.date = ChangeSet.TIMESTAMP_FORMAT_3.parse(date);
            }
        }
        catch (ParseException e) {
            if (!StringUtils.isEmpty(userDatePattern)) {
                try {
                    this.date = ChangeSet.TIMESTAMP_FORMAT_3.parse(date);
                }
                catch (ParseException pe) {
                    try {
                        this.date = ChangeSet.TIMESTAMP_FORMAT_4.parse(date);
                    }
                    catch (ParseException pe2) {
                        try {
                            this.date = ChangeSet.TIMESTAMP_FORMAT_1.parse(date);
                        }
                        catch (ParseException pe3) {
                            try {
                                this.date = ChangeSet.TIMESTAMP_FORMAT_2.parse(date);
                            }
                            catch (ParseException pe4) {
                                throw new IllegalArgumentException("Unable to parse date: " + date);
                            }
                        }
                    }
                }
            }
            else {
                try {
                    this.date = ChangeSet.TIMESTAMP_FORMAT_4.parse(date);
                }
                catch (ParseException pe5) {
                    try {
                        this.date = ChangeSet.TIMESTAMP_FORMAT_1.parse(date);
                    }
                    catch (ParseException pe6) {
                        try {
                            this.date = ChangeSet.TIMESTAMP_FORMAT_2.parse(date);
                        }
                        catch (ParseException pe7) {
                            throw new IllegalArgumentException("Unable to parse date: " + date);
                        }
                    }
                }
            }
        }
    }
    
    public String getDateFormatted() {
        return ChangeSet.DATE_FORMAT.format(this.getDate());
    }
    
    public String getTimeFormatted() {
        return ChangeSet.TIME_FORMAT.format(this.getDate());
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String revision) {
        this.revision = revision;
    }
    
    public String getParentRevision() {
        return this.parentRevision;
    }
    
    public void setParentRevision(final String parentRevision) {
        this.parentRevision = parentRevision;
    }
    
    public void addMergedRevision(final String mergedRevision) {
        if (this.mergedRevisions == null) {
            this.mergedRevisions = new LinkedHashSet<String>();
        }
        this.mergedRevisions.add(mergedRevision);
    }
    
    public Set<String> getMergedRevisions() {
        return (this.mergedRevisions == null) ? Collections.emptySet() : this.mergedRevisions;
    }
    
    public void setMergedRevisions(final Set<String> mergedRevisions) {
        this.mergedRevisions = mergedRevisions;
    }
    
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder((this.author == null) ? " null " : this.author);
        result.append("\n").append((this.date == null) ? "null " : this.date.toString()).append("\n");
        if (this.parentRevision != null) {
            result.append("parent: ").append(this.parentRevision);
            if (!this.mergedRevisions.isEmpty()) {
                result.append(" + ");
                result.append(this.mergedRevisions);
            }
            result.append("\n");
        }
        if (this.files != null) {
            for (final ChangeFile file : this.files) {
                result.append((file == null) ? " null " : file.toString()).append("\n");
            }
        }
        result.append((this.comment == null) ? " null " : this.comment);
        return result.toString();
    }
    
    public String toXML() {
        final StringBuilder buffer = new StringBuilder("\t<changelog-entry>\n");
        if (this.getDate() != null) {
            buffer.append("\t\t<date pattern=\"yyyy-MM-dd\">").append(this.getDateFormatted()).append("</date>\n").append("\t\t<time pattern=\"HH:mm:ss\">").append(this.getTimeFormatted()).append("</time>\n");
        }
        buffer.append("\t\t<author><![CDATA[").append(this.author).append("]]></author>\n");
        if (this.parentRevision != null) {
            buffer.append("\t\t<parent>").append(this.getParentRevision()).append("</parent>\n");
        }
        for (final String mergedRevision : this.getMergedRevisions()) {
            buffer.append("\t\t<merge>").append(mergedRevision).append("</merge>\n");
        }
        if (this.files != null) {
            for (final ChangeFile file : this.files) {
                buffer.append("\t\t<file>\n");
                if (file.getAction() != null) {
                    buffer.append("\t\t\t<action>").append(file.getAction()).append("</action>\n");
                }
                buffer.append("\t\t\t<name>").append(escapeValue(file.getName())).append("</name>\n");
                buffer.append("\t\t\t<revision>").append(file.getRevision()).append("</revision>\n");
                if (file.getOriginalName() != null) {
                    buffer.append("\t\t\t<orig-name>");
                    buffer.append(escapeValue(file.getOriginalName()));
                    buffer.append("</orig-name>\n");
                }
                if (file.getOriginalRevision() != null) {
                    buffer.append("\t\t\t<orig-revision>");
                    buffer.append(file.getOriginalRevision());
                    buffer.append("</orig-revision>\n");
                }
                buffer.append("\t\t</file>\n");
            }
        }
        buffer.append("\t\t<msg><![CDATA[").append(this.removeCDataEnd(this.comment)).append("]]></msg>\n");
        buffer.append("\t</changelog-entry>\n");
        return buffer.toString();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof ChangeSet) {
            final ChangeSet changeSet = (ChangeSet)obj;
            if (this.toString().equals(changeSet.toString())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.author == null) ? 0 : this.author.hashCode());
        result = 31 * result + ((this.comment == null) ? 0 : this.comment.hashCode());
        result = 31 * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = 31 * result + ((this.parentRevision == null) ? 0 : this.parentRevision.hashCode());
        result = 31 * result + ((this.mergedRevisions == null) ? 0 : this.mergedRevisions.hashCode());
        result = 31 * result + ((this.files == null) ? 0 : this.files.hashCode());
        return result;
    }
    
    private String removeCDataEnd(String message) {
        int endCdata;
        while (message != null && (endCdata = message.indexOf("]]>")) > -1) {
            message = message.substring(0, endCdata) + "] ] >" + message.substring(endCdata + 3, message.length());
        }
        return message;
    }
    
    public static String escapeValue(final Object value) {
        final StringBuilder buffer = new StringBuilder(value.toString());
        for (int i = 0, size = buffer.length(); i < size; ++i) {
            switch (buffer.charAt(i)) {
                case '<': {
                    buffer.replace(i, i + 1, "&lt;");
                    size += 3;
                    i += 3;
                    break;
                }
                case '>': {
                    buffer.replace(i, i + 1, "&gt;");
                    size += 3;
                    i += 3;
                    break;
                }
                case '&': {
                    buffer.replace(i, i + 1, "&amp;");
                    size += 4;
                    i += 4;
                    break;
                }
                case '\'': {
                    buffer.replace(i, i + 1, "&apos;");
                    size += 5;
                    i += 5;
                    break;
                }
                case '\"': {
                    buffer.replace(i, i + 1, "&quot;");
                    size += 5;
                    i += 5;
                    break;
                }
            }
        }
        return buffer.toString();
    }
    
    static {
        DATE_FORMAT = new ThreadSafeDateFormat("yyyy-MM-dd");
        TIME_FORMAT = new ThreadSafeDateFormat("HH:mm:ss");
        TIMESTAMP_FORMAT_1 = new ThreadSafeDateFormat("yyyy/MM/dd HH:mm:ss");
        TIMESTAMP_FORMAT_2 = new ThreadSafeDateFormat("yyyy-MM-dd HH:mm:ss");
        TIMESTAMP_FORMAT_3 = new ThreadSafeDateFormat("yyyy/MM/dd HH:mm:ss z");
        TIMESTAMP_FORMAT_4 = new ThreadSafeDateFormat("yyyy-MM-dd HH:mm:ss z");
    }
}
