// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.util.Iterator;
import java.util.Locale;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.List;

public class TestSetStats
{
    private final boolean trimStackTrace;
    private final boolean plainFormat;
    private long testSetStartAt;
    private long testStartAt;
    private int completedCount;
    private int errors;
    private int failures;
    private int skipped;
    private long lastStartAt;
    private long elapsedForTestSet;
    private final List<WrappedReportEntry> reportEntries;
    private static final String TEST_SET_COMPLETED_PREFIX = "Tests run: ";
    private final NumberFormat numberFormat;
    private static final int MS_PER_SEC = 1000;
    
    public TestSetStats(final boolean trimStackTrace, final boolean plainFormat) {
        this.reportEntries = new ArrayList<WrappedReportEntry>();
        this.numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        this.trimStackTrace = trimStackTrace;
        this.plainFormat = plainFormat;
    }
    
    public int getElapsedSinceTestSetStart() {
        return (int)(System.currentTimeMillis() - this.testSetStartAt);
    }
    
    public int getElapsedSinceLastStart() {
        return (int)(System.currentTimeMillis() - this.lastStartAt);
    }
    
    public void testSetStart() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.testSetStartAt = currentTimeMillis;
        this.lastStartAt = currentTimeMillis;
    }
    
    public void testStart() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.testStartAt = currentTimeMillis;
        this.lastStartAt = currentTimeMillis;
    }
    
    private long finishTest(final WrappedReportEntry reportEntry) {
        this.reportEntries.add(reportEntry);
        this.incrementCompletedCount();
        final long testEndAt = System.currentTimeMillis();
        if (this.testStartAt == 0L) {
            this.testStartAt = testEndAt;
        }
        final long elapsedForThis = (long)((reportEntry.getElapsed() != null) ? reportEntry.getElapsed() : (testEndAt - this.testStartAt));
        this.elapsedForTestSet += elapsedForThis;
        return elapsedForThis;
    }
    
    public void testSucceeded(final WrappedReportEntry reportEntry) {
        this.finishTest(reportEntry);
    }
    
    public void testError(final WrappedReportEntry reportEntry) {
        ++this.errors;
        this.finishTest(reportEntry);
    }
    
    public void testFailure(final WrappedReportEntry reportEntry) {
        ++this.failures;
        this.finishTest(reportEntry);
    }
    
    public void testSkipped(final WrappedReportEntry reportEntry) {
        ++this.skipped;
        this.finishTest(reportEntry);
    }
    
    public void reset() {
        this.completedCount = 0;
        this.errors = 0;
        this.failures = 0;
        this.skipped = 0;
        this.elapsedForTestSet = 0L;
        for (final WrappedReportEntry entry : this.reportEntries) {
            entry.getStdout().free();
            entry.getStdErr().free();
        }
        this.reportEntries.clear();
    }
    
    public int getCompletedCount() {
        return this.completedCount;
    }
    
    public int getErrors() {
        return this.errors;
    }
    
    public int getFailures() {
        return this.failures;
    }
    
    public int getSkipped() {
        return this.skipped;
    }
    
    String elapsedTimeAsString(final long runTime) {
        return this.numberFormat.format(runTime / 1000.0);
    }
    
    public String getElapsedForTestSet() {
        return this.elapsedTimeAsString(this.elapsedForTestSet);
    }
    
    private void incrementCompletedCount() {
        ++this.completedCount;
    }
    
    public String getTestSetSummary(final WrappedReportEntry reportEntry) {
        final StringBuilder buf = new StringBuilder();
        buf.append("Tests run: ");
        buf.append(this.completedCount);
        buf.append(", Failures: ");
        buf.append(this.failures);
        buf.append(", Errors: ");
        buf.append(this.errors);
        buf.append(", Skipped: ");
        buf.append(this.skipped);
        buf.append(", Time elapsed: ");
        buf.append(reportEntry.elapsedTimeAsString());
        buf.append(" sec");
        if (this.failures > 0 || this.errors > 0) {
            buf.append(" <<< FAILURE!");
        }
        buf.append(" - in ");
        buf.append(reportEntry.getNameWithGroup());
        buf.append("\n");
        return buf.toString();
    }
    
    public List<String> getTestResults() {
        final List<String> result = new ArrayList<String>();
        for (final WrappedReportEntry testResult : this.reportEntries) {
            if (testResult.isErrorOrFailure()) {
                result.add(testResult.getOutput(this.trimStackTrace));
            }
            else if (this.plainFormat && testResult.isSkipped()) {
                result.add(testResult.getName() + " skipped");
            }
            else {
                if (!this.plainFormat || !testResult.isSucceeded()) {
                    continue;
                }
                result.add(testResult.getElapsedTimeSummary());
            }
        }
        return result;
    }
    
    public List<WrappedReportEntry> getReportEntries() {
        return this.reportEntries;
    }
}
