// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import java.util.Iterator;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.Vector;

class HoldingLogChute implements LogChute
{
    private Vector pendingMessages;
    
    HoldingLogChute() {
        this.pendingMessages = new Vector();
    }
    
    public void init(final RuntimeServices rs) throws Exception {
    }
    
    public void log(final int level, final String message) {
        synchronized (this) {
            final Object[] data = { new Integer(level), message };
            this.pendingMessages.addElement(data);
        }
    }
    
    public void log(final int level, final String message, final Throwable t) {
        synchronized (this) {
            final Object[] data = { new Integer(level), message, t };
            this.pendingMessages.addElement(data);
        }
    }
    
    public boolean isLevelEnabled(final int level) {
        return true;
    }
    
    public void transferTo(final LogChute newChute) {
        synchronized (this) {
            if (!this.pendingMessages.isEmpty()) {
                for (final Object[] data : this.pendingMessages) {
                    final int level = (int)data[0];
                    final String message = (String)data[1];
                    if (data.length == 2) {
                        newChute.log(level, message);
                    }
                    else {
                        newChute.log(level, message, (Throwable)data[2]);
                    }
                }
            }
        }
    }
}
