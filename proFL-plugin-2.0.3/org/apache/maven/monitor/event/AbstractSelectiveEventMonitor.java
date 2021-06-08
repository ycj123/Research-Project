// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractSelectiveEventMonitor implements EventMonitor
{
    private List boundStartEvents;
    private List boundErrorEvents;
    private List boundEndEvents;
    
    protected AbstractSelectiveEventMonitor(final String[] startEvents, final String[] endEvents, final String[] errorEvents) {
        this.boundStartEvents = Arrays.asList(startEvents);
        this.boundEndEvents = Arrays.asList(endEvents);
        this.boundErrorEvents = Arrays.asList(errorEvents);
    }
    
    public final void startEvent(final String eventName, final String target, final long timestamp) {
        if (this.boundStartEvents.contains(eventName)) {
            this.doStartEvent(eventName, target, timestamp);
        }
    }
    
    protected void doStartEvent(final String eventName, final String target, final long timestamp) {
    }
    
    public final void endEvent(final String eventName, final String target, final long timestamp) {
        if (this.boundEndEvents.contains(eventName)) {
            this.doEndEvent(eventName, target, timestamp);
        }
    }
    
    protected void doEndEvent(final String eventName, final String target, final long timestamp) {
    }
    
    public final void errorEvent(final String eventName, final String target, final long timestamp, final Throwable cause) {
        if (this.boundErrorEvents.contains(eventName)) {
            this.doErrorEvent(eventName, target, timestamp, cause);
        }
    }
    
    protected void doErrorEvent(final String eventName, final String target, final long timestamp, final Throwable cause) {
    }
}
