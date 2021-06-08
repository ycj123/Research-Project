// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.execution;

public class BuildFailure
{
    private final Exception cause;
    private final String task;
    private final long time;
    
    BuildFailure(final Exception cause, final String task, final long time) {
        this.cause = cause;
        this.task = task;
        this.time = time;
    }
    
    public String getTask() {
        return this.task;
    }
    
    public Exception getCause() {
        return this.cause;
    }
    
    public long getTime() {
        return this.time;
    }
}
