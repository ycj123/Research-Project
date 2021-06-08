// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public interface ReportEntry
{
    String getSourceName();
    
    String getName();
    
    String getGroup();
    
    StackTraceWriter getStackTraceWriter();
    
    Integer getElapsed();
    
    String getMessage();
    
    String getNameWithGroup();
}
