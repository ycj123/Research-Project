// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.report.RunListener;
import java.io.PrintStream;
import org.apache.maven.surefire.report.ReporterFactory;

public class ForkingReporterFactory implements ReporterFactory
{
    private final Boolean isTrimstackTrace;
    private final PrintStream originalSystemOut;
    private volatile int testSetChannelId;
    
    public ForkingReporterFactory(final Boolean trimstackTrace, final PrintStream originalSystemOut) {
        this.testSetChannelId = 1;
        this.isTrimstackTrace = trimstackTrace;
        this.originalSystemOut = originalSystemOut;
    }
    
    public synchronized RunListener createReporter() {
        return new ForkingRunListener(this.originalSystemOut, this.testSetChannelId++, this.isTrimstackTrace);
    }
    
    public RunResult close() {
        return new RunResult(17, 17, 17, 17);
    }
}
