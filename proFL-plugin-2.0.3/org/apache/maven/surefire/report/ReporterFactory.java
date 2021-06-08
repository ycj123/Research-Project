// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

import org.apache.maven.surefire.suite.RunResult;

public interface ReporterFactory
{
    RunListener createReporter();
    
    RunResult close();
}
