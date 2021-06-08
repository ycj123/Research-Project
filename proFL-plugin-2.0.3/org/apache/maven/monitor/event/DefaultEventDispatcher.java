// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class DefaultEventDispatcher implements EventDispatcher
{
    private List eventMonitors;
    
    public DefaultEventDispatcher() {
        this.eventMonitors = new ArrayList();
    }
    
    public void addEventMonitor(final EventMonitor monitor) {
        this.eventMonitors.add(monitor);
    }
    
    public void dispatchStart(final String event, final String target) {
        for (final EventMonitor monitor : this.eventMonitors) {
            monitor.startEvent(event, target, System.currentTimeMillis());
        }
    }
    
    public void dispatchEnd(final String event, final String target) {
        for (final EventMonitor monitor : this.eventMonitors) {
            monitor.endEvent(event, target, System.currentTimeMillis());
        }
    }
    
    public void dispatchError(final String event, final String target, final Throwable cause) {
        for (final EventMonitor monitor : this.eventMonitors) {
            monitor.errorEvent(event, target, System.currentTimeMillis(), cause);
        }
    }
}
