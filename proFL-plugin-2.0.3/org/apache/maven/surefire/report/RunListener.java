// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public interface RunListener
{
    void testSetStarting(final ReportEntry p0);
    
    void testSetCompleted(final ReportEntry p0);
    
    void testStarting(final ReportEntry p0);
    
    void testSucceeded(final ReportEntry p0);
    
    void testAssumptionFailure(final ReportEntry p0);
    
    void testError(final ReportEntry p0);
    
    void testFailed(final ReportEntry p0);
    
    void testSkipped(final ReportEntry p0);
}
