// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.editors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.text.ParseException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.text.DateFormat;
import org.netbeans.lib.cvsclient.command.Builder;

public class EditorsBuilder implements Builder
{
    private static final DateFormat DATE_FORMAT;
    private final EventManager eventManager;
    private String editorsFileName;
    
    EditorsBuilder(final EventManager eventManager) {
        this.editorsFileName = null;
        this.eventManager = eventManager;
    }
    
    public void parseLine(final String s, final boolean b) {
        if (!b) {
            this.parseLine(s);
        }
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    public void outputDone() {
    }
    
    private boolean parseLine(final String str) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str, "\t");
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        if (!str.startsWith("\t")) {
            this.editorsFileName = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                return false;
            }
        }
        else if (this.editorsFileName == null) {
            return false;
        }
        final String nextToken = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        final String nextToken2 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        final String nextToken3 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        final String nextToken4 = stringTokenizer.nextToken();
        try {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.parseEntries(nextToken4, this.editorsFileName, nextToken, nextToken2, nextToken3)));
            return true;
        }
        catch (ParseException ex) {
            return false;
        }
    }
    
    private EditorsFileInfoContainer parseEntries(final String parent, String substring, final String s, final String s2, final String s3) throws ParseException {
        final int lastIndex = substring.lastIndexOf(47);
        if (lastIndex >= 0) {
            substring = substring.substring(lastIndex + 1);
        }
        return new EditorsFileInfoContainer(new File(parent, substring), s, this.parseDate(s2), s3);
    }
    
    private Date parseDate(String trim) throws ParseException {
        trim = trim.substring(Math.max(trim.indexOf(32), 0), Math.min(trim.lastIndexOf(32), trim.length())).trim();
        return EditorsBuilder.DATE_FORMAT.parse(trim);
    }
    
    static {
        DATE_FORMAT = new SimpleDateFormat("MMM dd hh:mm:ss yyyy");
    }
}
