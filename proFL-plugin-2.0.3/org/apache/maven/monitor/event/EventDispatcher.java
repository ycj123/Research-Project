// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

public interface EventDispatcher
{
    void addEventMonitor(final EventMonitor p0);
    
    void dispatchStart(final String p0, final String p1);
    
    void dispatchEnd(final String p0, final String p1);
    
    void dispatchError(final String p0, final String p1, final Throwable p2);
}
