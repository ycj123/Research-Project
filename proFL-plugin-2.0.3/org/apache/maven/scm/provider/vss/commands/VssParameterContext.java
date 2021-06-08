// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands;

import org.apache.maven.scm.ScmException;
import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import java.text.DateFormat;

public class VssParameterContext
{
    private String vssPath;
    private String autoResponse;
    private String ssDir;
    private String vssLogin;
    private String comment;
    private String user;
    private String fromLabel;
    private String toLabel;
    private boolean quiet;
    private boolean recursive;
    private boolean writable;
    private String label;
    private String style;
    private String version;
    private String date;
    private String localPath;
    private String timestamp;
    private String writableFiles;
    private String fromDate;
    private String toDate;
    private int numDays;
    private boolean getLocalCopy;
    private DateFormat dateFormat;
    private String outputFileName;
    
    public static VssParameterContext getInstance(final Object obj) {
        return new VssParameterContext((VssScmProviderRepository)obj);
    }
    
    public VssParameterContext(final VssScmProviderRepository repo) {
        this.vssPath = null;
        this.writableFiles = null;
        this.fromDate = null;
        this.toDate = null;
        this.numDays = Integer.MIN_VALUE;
        this.getLocalCopy = true;
        this.dateFormat = DateFormat.getDateInstance(3);
        this.autoResponse = System.getProperty("maven.scm.autoResponse");
        this.ssDir = repo.getVssdir();
        this.user = repo.getUser();
    }
    
    public String getGetLocalCopy() {
        return this.getLocalCopy ? "" : "-G-";
    }
    
    private String calcDate(final String startDate, final int daysToAdd) throws ParseException {
        Date currentDate = new Date();
        final Calendar calendar = new GregorianCalendar();
        currentDate = this.dateFormat.parse(startDate);
        calendar.setTime(currentDate);
        calendar.add(5, daysToAdd);
        return this.dateFormat.format(calendar.getTime());
    }
    
    public String getFileTimeStamp() {
        if (this.timestamp == null) {
            return "";
        }
        return this.timestamp;
    }
    
    public String getLocalpath() throws ScmException {
        String lclPath = "";
        if (this.localPath != null) {
            final File dir = new File(this.localPath);
            if (!dir.exists()) {
                final boolean done = dir.mkdirs();
                if (!done) {
                    final String msg = "Directory " + this.localPath + " creation was not " + "successful for an unknown reason";
                    throw new ScmException(msg);
                }
            }
            lclPath = "-GL" + this.localPath;
        }
        return lclPath;
    }
    
    public String getLabel() {
        String shortLabel = "";
        if (this.label != null && this.label.length() > 0) {
            shortLabel = "-L" + this.getShortLabel();
        }
        return shortLabel;
    }
    
    public String getVersionDateLabel() {
        String versionDateLabel = "";
        if (this.version != null) {
            versionDateLabel = "-V" + this.version;
        }
        else if (this.date != null) {
            versionDateLabel = "-Vd" + this.date;
        }
        else {
            final String shortLabel = this.getShortLabel();
            if (shortLabel != null && !shortLabel.equals("")) {
                versionDateLabel = "-VL" + shortLabel;
            }
        }
        return versionDateLabel;
    }
    
    public String getVersion() {
        return (this.version != null) ? ("-V" + this.version) : "";
    }
    
    private String getShortLabel() {
        String shortLabel;
        if (this.label != null && this.label.length() > 31) {
            shortLabel = this.label.substring(0, 30);
        }
        else {
            shortLabel = this.label;
        }
        return shortLabel;
    }
    
    public String getStyle() {
        return (this.style != null) ? this.style : "";
    }
    
    public String getRecursive() {
        return this.recursive ? "-R" : "";
    }
    
    public String getWritable() {
        return this.writable ? "-W" : "";
    }
    
    public String getQuiet() {
        return this.quiet ? "-O-" : "";
    }
    
    public String getVersionLabel() {
        if (this.fromLabel == null && this.toLabel == null) {
            return "";
        }
        if (this.fromLabel != null && this.toLabel != null) {
            if (this.fromLabel.length() > 31) {
                this.fromLabel = this.fromLabel.substring(0, 30);
            }
            if (this.toLabel.length() > 31) {
                this.toLabel = this.toLabel.substring(0, 30);
            }
            return "-VL" + this.toLabel + "~L" + this.fromLabel;
        }
        if (this.fromLabel != null) {
            if (this.fromLabel.length() > 31) {
                this.fromLabel = this.fromLabel.substring(0, 30);
            }
            return "-V~L" + this.fromLabel;
        }
        if (this.toLabel.length() > 31) {
            this.toLabel = this.toLabel.substring(0, 30);
        }
        return "-VL" + this.toLabel;
    }
    
    public String getUser() {
        return (this.user != null) ? ("-U" + this.user) : "";
    }
    
    public String getComment() {
        return (this.comment != null) ? ("-C" + this.comment) : "-C-";
    }
    
    public String getLogin() {
        return (this.vssLogin != null) ? ("-Y" + this.vssLogin) : "";
    }
    
    public String getAutoresponse() {
        if (this.autoResponse == null) {
            return "-I-";
        }
        if (this.autoResponse.equalsIgnoreCase("Y")) {
            return "-I-Y";
        }
        if (this.autoResponse.equalsIgnoreCase("N")) {
            return "-I-N";
        }
        return "-I-";
    }
    
    public String getSSCommand() {
        if (this.ssDir == null) {
            return "ss";
        }
        return this.ssDir.endsWith(File.separator) ? (this.ssDir + "ss") : (this.ssDir + File.separator + "ss");
    }
    
    public String getVssPath() {
        return this.vssPath;
    }
    
    public String getVersionDate() throws ScmException {
        if (this.fromDate == null && this.toDate == null && this.numDays == Integer.MIN_VALUE) {
            return "";
        }
        if (this.fromDate != null && this.toDate != null) {
            return "-Vd" + this.toDate + "~d" + this.fromDate;
        }
        if (this.toDate != null && this.numDays != Integer.MIN_VALUE) {
            try {
                return "-Vd" + this.toDate + "~d" + this.calcDate(this.toDate, this.numDays);
            }
            catch (ParseException ex) {
                final String msg = "Error parsing date: " + this.toDate;
                throw new ScmException(msg);
            }
        }
        if (this.fromDate != null && this.numDays != Integer.MIN_VALUE) {
            try {
                return "-Vd" + this.calcDate(this.fromDate, this.numDays) + "~d" + this.fromDate;
            }
            catch (ParseException ex) {
                final String msg = "Error parsing date: " + this.fromDate;
                throw new ScmException(msg);
            }
        }
        return (this.fromDate != null) ? ("-V~d" + this.fromDate) : ("-Vd" + this.toDate);
    }
    
    public String getOutput() {
        return (this.outputFileName != null) ? ("-O" + this.outputFileName) : "";
    }
    
    public String getWritableFiles() {
        if (this.writableFiles == null) {
            return "";
        }
        return this.writableFiles;
    }
}
