// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

public class PrioritizedTest
{
    private final Class clazz;
    private final Priority priority;
    
    public PrioritizedTest(final Class clazz, final Priority pri) {
        this.clazz = clazz;
        this.priority = pri;
    }
    
    public int getPriority() {
        return this.priority.getPriority();
    }
    
    public int getTotalRuntime() {
        return this.priority.getTotalRuntime();
    }
    
    public Class getClazz() {
        return this.clazz;
    }
}
