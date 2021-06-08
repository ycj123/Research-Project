// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

import java.util.Collections;
import java.util.ArrayList;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.plugin.surefire.report.TestSetStats;
import java.util.Collection;

public class RunStatistics
{
    private final Sources errorSources;
    private final Sources failureSources;
    private int completedCount;
    private int errors;
    private int failures;
    private int skipped;
    
    public RunStatistics() {
        this.errorSources = new Sources();
        this.failureSources = new Sources();
    }
    
    public void addErrorSource(final StackTraceWriter stackTraceWriter) {
        if (stackTraceWriter == null) {
            throw new IllegalArgumentException("Cant be null");
        }
        this.errorSources.addSource(stackTraceWriter);
    }
    
    public void addFailureSource(final StackTraceWriter stackTraceWriter) {
        if (stackTraceWriter == null) {
            throw new IllegalArgumentException("Cant be null");
        }
        this.failureSources.addSource(stackTraceWriter);
    }
    
    public Collection<String> getErrorSources() {
        return this.errorSources.getListOfSources();
    }
    
    public Collection<String> getFailureSources() {
        return this.failureSources.getListOfSources();
    }
    
    public synchronized boolean hadFailures() {
        return this.failures > 0;
    }
    
    public synchronized boolean hadErrors() {
        return this.errors > 0;
    }
    
    public synchronized int getCompletedCount() {
        return this.completedCount;
    }
    
    public synchronized int getSkipped() {
        return this.skipped;
    }
    
    public synchronized void add(final TestSetStats testSetStats) {
        this.completedCount += testSetStats.getCompletedCount();
        this.errors += testSetStats.getErrors();
        this.failures += testSetStats.getFailures();
        this.skipped += testSetStats.getSkipped();
    }
    
    public synchronized RunResult getRunResult() {
        return new RunResult(this.completedCount, this.errors, this.failures, this.skipped);
    }
    
    public synchronized String getSummary() {
        return "Tests run: " + this.completedCount + ", Failures: " + this.failures + ", Errors: " + this.errors + ", Skipped: " + this.skipped;
    }
    
    private static class Sources
    {
        private final Collection<String> listOfSources;
        
        private Sources() {
            this.listOfSources = new ArrayList<String>();
        }
        
        void addSource(final String source) {
            synchronized (this.listOfSources) {
                this.listOfSources.add(source);
            }
        }
        
        void addSource(final StackTraceWriter stackTraceWriter) {
            this.addSource(stackTraceWriter.smartTrimmedStackTrace());
        }
        
        Collection<String> getListOfSources() {
            synchronized (this.listOfSources) {
                return Collections.unmodifiableCollection((Collection<? extends String>)this.listOfSources);
            }
        }
    }
}
