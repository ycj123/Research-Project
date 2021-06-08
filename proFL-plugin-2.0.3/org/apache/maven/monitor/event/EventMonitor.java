// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

public interface EventMonitor
{
    void startEvent(final String p0, final String p1, final long p2);
    
    void endEvent(final String p0, final String p1, final long p2);
    
    void errorEvent(final String p0, final String p1, final long p2, final Throwable p3);
}
