// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.annotate;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;

public class AnnotateLine
{
    private static final DateFormat DATE_FORMAT;
    private String author;
    private String revision;
    private Date date;
    private String dateString;
    private String content;
    private int lineNum;
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String revision) {
        this.revision = revision;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String getDateString() {
        return this.dateString;
    }
    
    public void setDateString(final String s) {
        this.dateString = s;
        try {
            this.date = AnnotateLine.DATE_FORMAT.parse(s);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public int getLineNum() {
        return this.lineNum;
    }
    
    public Integer getLineNumInteger() {
        return new Integer(this.lineNum);
    }
    
    public void setLineNum(final int lineNum) {
        this.lineNum = lineNum;
    }
    
    static {
        DATE_FORMAT = new SimpleDateFormat("dd-MMM-yy", Locale.US);
    }
}
