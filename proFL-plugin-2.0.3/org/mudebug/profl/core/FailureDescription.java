// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

public class FailureDescription
{
    private final String firstLine;
    private final String stackTrace;
    
    public FailureDescription(final String firstLine, final String stackTrace) {
        this.firstLine = firstLine;
        this.stackTrace = stackTrace;
    }
    
    @Override
    public String toString() {
        return "FailureDescription{firstLine='" + this.firstLine + '\'' + ", stackTrace='" + this.stackTrace + '\'' + '}';
    }
}
