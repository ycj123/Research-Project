// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

import org.apache.maven.surefire.report.ReportEntry;
import java.io.FileNotFoundException;
import java.io.File;

public class StatisticsReporter
{
    private final RunEntryStatisticsMap existing;
    private final RunEntryStatisticsMap newResults;
    private final File dataFile;
    
    public StatisticsReporter(final File dataFile) {
        this.dataFile = dataFile;
        this.existing = RunEntryStatisticsMap.fromFile(this.dataFile);
        this.newResults = new RunEntryStatisticsMap();
    }
    
    public void testSetCompleted() {
        try {
            this.newResults.serialize(this.dataFile);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void testSucceeded(final ReportEntry report) {
        this.newResults.add(this.existing.createNextGeneration(report));
    }
    
    public void testSkipped(final ReportEntry report) {
        this.newResults.add(this.existing.createNextGeneration(report));
    }
    
    public void testError(final ReportEntry report) {
        this.newResults.add(this.existing.createNextGenerationFailure(report));
    }
    
    public void testFailed(final ReportEntry report) {
        this.newResults.add(this.existing.createNextGenerationFailure(report));
    }
}
