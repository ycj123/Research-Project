// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

public class Priority
{
    private final String className;
    int priority;
    int totalRuntime;
    int minSuccessRate;
    
    public Priority(final String className) {
        this.totalRuntime = 0;
        this.minSuccessRate = Integer.MAX_VALUE;
        this.className = className;
    }
    
    public static Priority newTestClassPriority(final String className) {
        final Priority priority1 = new Priority(className);
        priority1.setPriority(0);
        priority1.minSuccessRate = 0;
        return priority1;
    }
    
    public void addItem(final RunEntryStatistics itemStat) {
        this.totalRuntime += itemStat.getRunTime();
        this.minSuccessRate = Math.min(this.minSuccessRate, itemStat.getSuccessfulBuilds());
    }
    
    public int getTotalRuntime() {
        return this.totalRuntime;
    }
    
    public int getMinSuccessRate() {
        return this.minSuccessRate;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
}
