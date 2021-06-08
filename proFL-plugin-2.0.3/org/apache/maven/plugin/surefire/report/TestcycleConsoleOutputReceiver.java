// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import org.apache.maven.surefire.report.ReportEntry;
import org.apache.maven.surefire.report.ConsoleOutputReceiver;

public interface TestcycleConsoleOutputReceiver extends ConsoleOutputReceiver
{
    void testSetStarting(final ReportEntry p0);
    
    void testSetCompleted(final ReportEntry p0);
    
    void close();
}
