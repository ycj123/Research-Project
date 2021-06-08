// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public interface StackTraceWriter
{
    String writeTraceToString();
    
    String writeTrimmedTraceToString();
    
    String smartTrimmedStackTrace();
    
    SafeThrowable getThrowable();
}
