// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import org.apache.maven.surefire.report.ReporterException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.File;

public class FileReporter
{
    private final File reportsDirectory;
    private final boolean deleteOnStarting;
    private final String reportNameSuffix;
    
    public FileReporter(final File reportsDirectory, final String reportNameSuffix) {
        this.reportsDirectory = reportsDirectory;
        this.deleteOnStarting = false;
        this.reportNameSuffix = reportNameSuffix;
    }
    
    private PrintWriter testSetStarting(final ReportEntry report) throws ReporterException {
        final File reportFile = getReportFile(this.reportsDirectory, report.getName(), this.reportNameSuffix, ".txt");
        final File reportDir = reportFile.getParentFile();
        reportDir.mkdirs();
        if (this.deleteOnStarting && reportFile.exists()) {
            reportFile.delete();
        }
        try {
            final PrintWriter writer = new PrintWriter(new FileWriter(reportFile));
            writer.println("-------------------------------------------------------------------------------");
            writer.println("Test set: " + report.getName());
            writer.println("-------------------------------------------------------------------------------");
            return writer;
        }
        catch (IOException e) {
            throw new ReporterException("Unable to create file for report: " + e.getMessage(), e);
        }
    }
    
    public static File getReportFile(final File reportsDirectory, final String reportEntryName, final String reportNameSuffix, final String fileExtension) {
        File reportFile;
        if (reportNameSuffix != null && reportNameSuffix.length() > 0) {
            reportFile = new File(reportsDirectory, FileReporterUtils.stripIllegalFilenameChars(reportEntryName + "-" + reportNameSuffix + fileExtension));
        }
        else {
            reportFile = new File(reportsDirectory, FileReporterUtils.stripIllegalFilenameChars(reportEntryName + fileExtension));
        }
        return reportFile;
    }
    
    public void testSetCompleted(final WrappedReportEntry report, final TestSetStats testSetStats, final List<String> testResults) throws ReporterException {
        final PrintWriter writer = this.testSetStarting(report);
        writer.print(testSetStats.getTestSetSummary(report));
        if (testResults != null) {
            for (final String testResult : testResults) {
                writer.println(testResult);
            }
        }
        writer.flush();
        writer.close();
    }
}
