// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

import java.util.StringTokenizer;
import org.apache.maven.surefire.report.ReportEntry;

public class RunEntryStatistics
{
    private final int runTime;
    private final int successfulBuilds;
    private final String testName;
    
    private RunEntryStatistics(final int runTime, final int successfulBuilds, final String testName) {
        this.runTime = runTime;
        this.successfulBuilds = successfulBuilds;
        this.testName = testName;
    }
    
    public static RunEntryStatistics fromReportEntry(final ReportEntry previous) {
        final Integer elapsed = previous.getElapsed();
        return new RunEntryStatistics((elapsed != null) ? elapsed : 0, 0, previous.getName());
    }
    
    public static RunEntryStatistics fromValues(final int runTime, final int successfulBuilds, final Class clazz, final String testName) {
        return new RunEntryStatistics(runTime, successfulBuilds, testName + "(" + clazz.getName() + ")");
    }
    
    public RunEntryStatistics nextGeneration(final int runTime) {
        return new RunEntryStatistics(runTime, this.successfulBuilds + 1, this.testName);
    }
    
    public RunEntryStatistics nextGenerationFailure(final int runTime) {
        return new RunEntryStatistics(runTime, 0, this.testName);
    }
    
    public String getTestName() {
        return this.testName;
    }
    
    public int getRunTime() {
        return this.runTime;
    }
    
    public int getSuccessfulBuilds() {
        return this.successfulBuilds;
    }
    
    public static RunEntryStatistics fromString(final String line) {
        final StringTokenizer tok = new StringTokenizer(line, ",");
        final int successfulBuilds = Integer.parseInt(tok.nextToken());
        final int runTime = Integer.parseInt(tok.nextToken());
        final String className = tok.nextToken();
        return new RunEntryStatistics(runTime, successfulBuilds, className);
    }
    
    public String getAsString() {
        final StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(this.successfulBuilds);
        stringBuffer.append(",");
        stringBuffer.append(this.runTime);
        stringBuffer.append(",");
        stringBuffer.append(this.testName);
        return stringBuffer.toString();
    }
}
