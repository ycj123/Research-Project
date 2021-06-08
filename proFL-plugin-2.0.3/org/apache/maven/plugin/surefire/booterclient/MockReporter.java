// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import org.apache.maven.surefire.report.ReportEntry;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import org.apache.maven.surefire.report.ConsoleOutputReceiver;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.RunListener;

public class MockReporter implements RunListener, ConsoleLogger, ConsoleOutputReceiver
{
    private final List<String> events;
    private final List<Object> data;
    public static final String SET_STARTING = "SET_STARTED";
    public static final String SET_COMPLETED = "SET_COMPLETED";
    public static final String TEST_STARTING = "TEST_STARTED";
    public static final String TEST_SUCCEEDED = "TEST_COMPLETED";
    public static final String TEST_FAILED = "TEST_FAILED";
    public static final String TEST_ERROR = "TEST_ERROR";
    public static final String TEST_SKIPPED = "TEST_SKIPPED";
    public static final String TEST_ASSUMPTION_FAIL = "TEST_ASSUMPTION_SKIPPED";
    public static final String CONSOLE_OUTPUT = "CONSOLE_OUTPUT";
    public static final String STDOUT = "STDOUT";
    public static final String STDERR = "STDERR";
    private final AtomicInteger testSucceeded;
    private final AtomicInteger testIgnored;
    private final AtomicInteger testFailed;
    
    public MockReporter() {
        this.events = new ArrayList<String>();
        this.data = new ArrayList<Object>();
        this.testSucceeded = new AtomicInteger();
        this.testIgnored = new AtomicInteger();
        this.testFailed = new AtomicInteger();
    }
    
    public void testSetStarting(final ReportEntry report) {
        this.events.add("SET_STARTED");
        this.data.add(report);
    }
    
    public void testSetCompleted(final ReportEntry report) {
        this.events.add("SET_COMPLETED");
        this.data.add(report);
    }
    
    public void testStarting(final ReportEntry report) {
        this.events.add("TEST_STARTED");
        this.data.add(report);
    }
    
    public void testSucceeded(final ReportEntry report) {
        this.events.add("TEST_COMPLETED");
        this.testSucceeded.incrementAndGet();
        this.data.add(report);
    }
    
    public void testError(final ReportEntry report) {
        this.events.add("TEST_ERROR");
        this.data.add(report);
        this.testFailed.incrementAndGet();
    }
    
    public void testFailed(final ReportEntry report) {
        this.events.add("TEST_FAILED");
        this.data.add(report);
        this.testFailed.incrementAndGet();
    }
    
    public void testSkipped(final ReportEntry report) {
        this.events.add("TEST_SKIPPED");
        this.data.add(report);
        this.testIgnored.incrementAndGet();
    }
    
    public List<String> getEvents() {
        return this.events;
    }
    
    public List getData() {
        return this.data;
    }
    
    public String getFirstEvent() {
        return this.events.get(0);
    }
    
    public ReportEntry getFirstData() {
        return this.data.get(0);
    }
    
    public void testAssumptionFailure(final ReportEntry report) {
        this.events.add("TEST_ASSUMPTION_SKIPPED");
        this.data.add(report);
        this.testIgnored.incrementAndGet();
    }
    
    public void info(final String message) {
        this.events.add("CONSOLE_OUTPUT");
        this.data.add(message);
    }
    
    public void writeTestOutput(final byte[] buf, final int off, final int len, final boolean stdout) {
        this.events.add(stdout ? "STDOUT" : "STDERR");
        this.data.add(new String(buf, off, len));
    }
}
