// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.util.Iterator;
import java.util.List;
import org.apache.maven.surefire.report.ReporterException;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ConsoleReporter
{
    public static final String BRIEF = "brief";
    public static final String PLAIN = "plain";
    private static final String TEST_SET_STARTING_PREFIX = "Running ";
    private static final int BUFFER_SIZE = 4096;
    private final PrintWriter writer;
    
    public ConsoleReporter(final PrintStream originalSystemOut) {
        final OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(originalSystemOut, 4096));
        this.writer = new PrintWriter(out);
    }
    
    public void testSetStarting(final ReportEntry report) throws ReporterException {
        this.writeMessage(getTestSetStartingMessage(report));
    }
    
    public void writeMessage(final String message) {
        if (this.writer != null) {
            this.writer.print(message);
            this.writer.flush();
        }
    }
    
    public void writeLnMessage(final String message) {
        if (this.writer != null) {
            this.writer.println(message);
            this.writer.flush();
        }
    }
    
    public void testSetCompleted(final WrappedReportEntry report, final TestSetStats testSetStats, final List<String> testResults) throws ReporterException {
        this.writeMessage(testSetStats.getTestSetSummary(report));
        if (testResults != null) {
            for (final String testResult : testResults) {
                this.writeLnMessage(testResult);
            }
        }
    }
    
    public void reset() {
        if (this.writer != null) {
            this.writer.flush();
        }
    }
    
    static String getTestSetStartingMessage(final ReportEntry report) {
        final StringBuilder message = new StringBuilder();
        message.append("Running ");
        message.append(report.getNameWithGroup());
        message.append("\n");
        return message.toString();
    }
}
