// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.io.IOException;
import org.apache.maven.surefire.report.ReporterException;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.FileOutputStream;
import java.io.File;

public class ConsoleOutputFileReporter implements TestcycleConsoleOutputReceiver
{
    private final File reportsDirectory;
    private final String reportNameSuffix;
    private String reportEntryName;
    private FileOutputStream fileOutputStream;
    
    public ConsoleOutputFileReporter(final File reportsDirectory, final String reportNameSuffix) {
        this.reportsDirectory = reportsDirectory;
        this.reportNameSuffix = reportNameSuffix;
    }
    
    public void testSetStarting(final ReportEntry reportEntry) {
        this.close();
        this.reportEntryName = reportEntry.getName();
    }
    
    public void testSetCompleted(final ReportEntry report) throws ReporterException {
    }
    
    public void close() {
        if (this.fileOutputStream != null) {
            try {
                this.fileOutputStream.flush();
                this.fileOutputStream.close();
            }
            catch (IOException ex) {}
            this.fileOutputStream = null;
        }
    }
    
    public void writeTestOutput(final byte[] buf, final int off, final int len, final boolean stdout) {
        try {
            if (this.fileOutputStream == null) {
                if (!this.reportsDirectory.exists()) {
                    this.reportsDirectory.mkdirs();
                }
                final File file = FileReporter.getReportFile(this.reportsDirectory, this.reportEntryName, this.reportNameSuffix, "-output.txt");
                this.fileOutputStream = new FileOutputStream(file);
            }
            this.fileOutputStream.write(buf, off, len);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
