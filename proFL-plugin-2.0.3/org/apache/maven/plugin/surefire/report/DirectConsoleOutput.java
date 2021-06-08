// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import org.apache.maven.surefire.report.ReportEntry;
import java.io.PrintStream;

public class DirectConsoleOutput implements TestcycleConsoleOutputReceiver
{
    private final PrintStream sout;
    private final PrintStream serr;
    
    public DirectConsoleOutput(final PrintStream sout, final PrintStream serr) {
        this.sout = sout;
        this.serr = serr;
    }
    
    public void writeTestOutput(final byte[] buf, final int off, final int len, final boolean stdout) {
        final PrintStream stream = stdout ? this.sout : this.serr;
        stream.write(buf, off, len);
    }
    
    public void testSetStarting(final ReportEntry reportEntry) {
    }
    
    public void testSetCompleted(final ReportEntry report) {
    }
    
    public void close() {
    }
}
