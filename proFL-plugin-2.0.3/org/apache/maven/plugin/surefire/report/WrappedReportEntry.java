// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import org.apache.maven.surefire.report.StackTraceWriter;
import java.util.Locale;
import java.text.NumberFormat;
import org.apache.maven.surefire.report.ReportEntry;

public class WrappedReportEntry implements ReportEntry
{
    private final ReportEntry original;
    private final ReportEntryType reportEntryType;
    private final Integer elapsed;
    private final Utf8RecodingDeferredFileOutputStream stdout;
    private final Utf8RecodingDeferredFileOutputStream stdErr;
    private final NumberFormat numberFormat;
    private static final int MS_PER_SEC = 1000;
    static final String NL;
    
    public WrappedReportEntry(final ReportEntry original, final ReportEntryType reportEntryType, final Integer estimatedElapsed, final Utf8RecodingDeferredFileOutputStream stdout, final Utf8RecodingDeferredFileOutputStream stdErr) {
        this.numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        this.original = original;
        this.reportEntryType = reportEntryType;
        this.elapsed = estimatedElapsed;
        this.stdout = stdout;
        this.stdErr = stdErr;
    }
    
    public Integer getElapsed() {
        return this.elapsed;
    }
    
    public ReportEntryType getReportEntryType() {
        return this.reportEntryType;
    }
    
    public Utf8RecodingDeferredFileOutputStream getStdout() {
        return this.stdout;
    }
    
    public Utf8RecodingDeferredFileOutputStream getStdErr() {
        return this.stdErr;
    }
    
    public String getSourceName() {
        return this.original.getSourceName();
    }
    
    public String getName() {
        return this.original.getName();
    }
    
    public String getGroup() {
        return this.original.getGroup();
    }
    
    public StackTraceWriter getStackTraceWriter() {
        return this.original.getStackTraceWriter();
    }
    
    public String getMessage() {
        return this.original.getMessage();
    }
    
    public String getStackTrace(final boolean trimStackTrace) {
        final StackTraceWriter writer = this.original.getStackTraceWriter();
        if (writer == null) {
            return null;
        }
        return trimStackTrace ? writer.writeTrimmedTraceToString() : writer.writeTraceToString();
    }
    
    public String elapsedTimeAsString() {
        return this.elapsedTimeAsString(this.getElapsed());
    }
    
    String elapsedTimeAsString(final long runTime) {
        return this.numberFormat.format(runTime / 1000.0);
    }
    
    public String getReportName() {
        final int i = this.getName().lastIndexOf("(");
        return (i > 0) ? this.getName().substring(0, i) : this.getName();
    }
    
    public String getReportName(final String suffix) {
        return (suffix != null && suffix.length() > 0) ? (this.getReportName() + "(" + suffix + ")") : this.getReportName();
    }
    
    public String getOutput(final boolean trimStackTrace) {
        final StringBuilder buf = new StringBuilder();
        buf.append(this.getElapsedTimeSummary());
        buf.append("  <<< ").append(this.getReportEntryType().toString().toUpperCase()).append("!").append(WrappedReportEntry.NL);
        buf.append(this.getStackTrace(trimStackTrace));
        return buf.toString();
    }
    
    public String getElapsedTimeSummary() {
        final StringBuilder reportContent = new StringBuilder();
        reportContent.append(this.getName());
        reportContent.append("  Time elapsed: ");
        reportContent.append(this.elapsedTimeAsString());
        reportContent.append(" sec");
        return reportContent.toString();
    }
    
    public boolean isErrorOrFailure() {
        final ReportEntryType thisType = this.getReportEntryType();
        return ReportEntryType.failure == thisType || ReportEntryType.error == thisType;
    }
    
    public boolean isSkipped() {
        return ReportEntryType.skipped == this.getReportEntryType();
    }
    
    public boolean isSucceeded() {
        return ReportEntryType.success == this.getReportEntryType();
    }
    
    public String getNameWithGroup() {
        return this.original.getNameWithGroup();
    }
    
    static {
        NL = System.getProperty("line.separator");
    }
}
