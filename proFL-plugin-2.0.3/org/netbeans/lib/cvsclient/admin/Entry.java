// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.admin;

import java.text.ParseException;
import java.util.TimeZone;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

public final class Entry
{
    public static final String DUMMY_TIMESTAMP = "dummy timestamp";
    public static final String DUMMY_TIMESTAMP_NEW_ENTRY = "dummy timestamp from new-entry";
    public static final String MERGE_TIMESTAMP = "Result of merge";
    private static final String TAG = "T";
    private static final String DATE = "D";
    private static SimpleDateFormat stickyDateFormatter;
    private static final String BINARY_FILE = "-kb";
    private static final String NO_USER_FILE = "";
    private static final String NEW_USER_FILE = "0";
    private static final String REMOVE_USER_FILE = "-";
    private static SimpleDateFormat lastModifiedDateFormatter;
    public static final char HAD_CONFLICTS = '+';
    public static final char TIMESTAMP_MATCHES_FILE = '=';
    public static final String HAD_CONFLICTS_AND_TIMESTAMP_MATCHES_FILE = "+=";
    private static final String DIRECTORY_PREFIX = "D/";
    private String name;
    private String revision;
    private String conflict;
    private Date lastModified;
    private String options;
    private String tag;
    private Date date;
    private boolean directory;
    
    private static SimpleDateFormat getStickyDateFormatter() {
        if (Entry.stickyDateFormatter == null) {
            Entry.stickyDateFormatter = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
        }
        return Entry.stickyDateFormatter;
    }
    
    public static SimpleDateFormat getLastModifiedDateFormatter() {
        if (Entry.lastModifiedDateFormatter == null) {
            (Entry.lastModifiedDateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US)).setTimeZone(getTimeZone());
        }
        return Entry.lastModifiedDateFormatter;
    }
    
    public static TimeZone getTimeZone() {
        return TimeZone.getTimeZone("GMT");
    }
    
    public Entry(final String s) {
        this.init(s);
    }
    
    public Entry() {
    }
    
    protected void init(String substring) {
        if (substring.startsWith("D/")) {
            this.directory = true;
            substring = substring.substring(1);
        }
        final int[] array = new int[5];
        try {
            array[0] = 0;
            for (int i = 1; i < 5; ++i) {
                array[i] = substring.indexOf(47, array[i - 1] + 1);
            }
            if (array[1] > 0) {
                this.name = substring.substring(array[0] + 1, array[1]);
                this.revision = substring.substring(array[1] + 1, array[2]);
                if (array[3] - array[2] > 1) {
                    this.setConflict(substring.substring(array[2] + 1, array[3]));
                }
                if (array[4] - array[3] > 1) {
                    this.options = substring.substring(array[3] + 1, array[4]);
                }
                if (array[4] != substring.length() - 1) {
                    final String substring2 = substring.substring(array[4] + 1);
                    if (substring2.startsWith("T")) {
                        this.setTag(substring2.substring(1));
                    }
                    else if (substring2.startsWith("D")) {
                        try {
                            this.setDate(getStickyDateFormatter().parse(substring2.substring("D".length())));
                        }
                        catch (ParseException ex) {
                            System.err.println("We got another inconsistency in the library's date formatting.");
                        }
                    }
                }
            }
        }
        catch (Exception obj) {
            System.err.println("Error parsing entry line: " + obj);
            obj.printStackTrace();
            throw new IllegalArgumentException("Invalid entry line: " + substring);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String revision) {
        this.revision = revision;
    }
    
    public Date getLastModified() {
        return this.lastModified;
    }
    
    public String getConflict() {
        return this.conflict;
    }
    
    public void setConflict(final String conflict) {
        this.conflict = conflict;
        this.lastModified = null;
        if (conflict == null || conflict.equals("dummy timestamp") || conflict.equals("Result of merge") || conflict.equals("dummy timestamp from new-entry")) {
            return;
        }
        String substring = conflict;
        int a = substring.indexOf(43);
        if (a >= 0) {
            a = Math.max(a, substring.indexOf(61));
        }
        if (a >= 0) {
            substring = substring.substring(a + 1);
        }
        if (substring.length() == 0) {
            return;
        }
        try {
            this.lastModified = getLastModifiedDateFormatter().parse(substring);
        }
        catch (Exception ex) {
            this.lastModified = null;
        }
    }
    
    public String getOptions() {
        return this.options;
    }
    
    public void setOptions(final String options) {
        this.options = options;
    }
    
    public String getStickyInformation() {
        if (this.tag != null) {
            return this.tag;
        }
        return this.getDateFormatted();
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
        this.date = null;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String getDateFormatted() {
        if (this.getDate() == null) {
            return null;
        }
        return getStickyDateFormatter().format(this.getDate());
    }
    
    public void setDate(final Date date) {
        this.date = date;
        this.tag = null;
    }
    
    public boolean hasDate() {
        return this.date != null;
    }
    
    public boolean hasTag() {
        return this.tag != null;
    }
    
    public boolean isBinary() {
        return this.options != null && this.options.equals("-kb");
    }
    
    public boolean isNoUserFile() {
        return this.revision == null || this.revision.equals("");
    }
    
    public boolean isNewUserFile() {
        return this.revision != null && this.revision.startsWith("0");
    }
    
    public boolean isUserFileToBeRemoved() {
        return this.revision != null && this.revision.startsWith("-");
    }
    
    public boolean isValid() {
        return this.getName() != null && this.getName().length() > 0;
    }
    
    public boolean isDirectory() {
        return this.directory;
    }
    
    public void setDirectory(final boolean directory) {
        this.directory = directory;
    }
    
    public boolean hadConflicts() {
        return this.conflict != null && this.conflict.indexOf(43) >= 0;
    }
    
    public boolean timestampMatchesFile() {
        return this.conflict.charAt(1) == '=';
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.directory) {
            sb.append("D/");
        }
        else {
            sb.append('/');
        }
        if (this.name != null) {
            sb.append(this.name);
            sb.append('/');
            if (this.revision != null) {
                sb.append(this.revision);
            }
            sb.append('/');
            if (this.conflict != null) {
                sb.append(this.conflict);
            }
            sb.append('/');
            if (this.options != null) {
                sb.append(this.options);
            }
            sb.append('/');
            if (this.tag != null && this.date == null) {
                if (!"HEAD".equals(this.tag)) {
                    sb.append("T");
                    sb.append(this.getTag());
                }
            }
            else if (this.tag == null && this.date != null) {
                final String dateFormatted = this.getDateFormatted();
                sb.append("D");
                sb.append(dateFormatted);
            }
        }
        return sb.toString();
    }
}
