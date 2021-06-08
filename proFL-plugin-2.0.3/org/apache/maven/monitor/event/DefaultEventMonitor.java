// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

import org.codehaus.plexus.logging.Logger;

public class DefaultEventMonitor extends AbstractSelectiveEventMonitor
{
    private static final String[] START_EVENTS;
    private final Logger logger;
    
    public DefaultEventMonitor(final Logger logger) {
        super(DefaultEventMonitor.START_EVENTS, MavenEvents.NO_EVENTS, MavenEvents.NO_EVENTS);
        this.logger = logger;
    }
    
    protected void doStartEvent(final String event, final String target, final long time) {
        this.logger.info("[" + target + "]");
    }
    
    static {
        START_EVENTS = new String[] { "mojo-execute" };
    }
}
