// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

import org.apache.velocity.app.event.EventCartridge;

public interface InternalEventContext
{
    EventCartridge attachEventCartridge(final EventCartridge p0);
    
    EventCartridge getEventCartridge();
}
