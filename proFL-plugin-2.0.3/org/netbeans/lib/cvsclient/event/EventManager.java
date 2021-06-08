// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

import org.netbeans.lib.cvsclient.ClientServices;

public class EventManager
{
    private CVSListener[] listeners;
    private boolean fireEnhancedEventSet;
    private final ClientServices services;
    
    public EventManager(final ClientServices services) {
        this.fireEnhancedEventSet = true;
        this.services = services;
    }
    
    public ClientServices getClientServices() {
        return this.services;
    }
    
    public synchronized void addCVSListener(final CVSListener cvsListener) {
        if (this.listeners == null || this.listeners.length == 0) {
            this.listeners = new CVSListener[1];
        }
        else {
            final CVSListener[] listeners = new CVSListener[this.listeners.length + 1];
            for (int i = 0; i < this.listeners.length; ++i) {
                listeners[i] = this.listeners[i];
            }
            this.listeners = listeners;
        }
        this.listeners[this.listeners.length - 1] = cvsListener;
    }
    
    public synchronized void removeCVSListener(final CVSListener cvsListener) {
        if (this.listeners.length == 1) {
            this.listeners = null;
        }
        else {
            final CVSListener[] listeners = new CVSListener[this.listeners.length - 1];
            for (int i = 0; i < listeners.length; ++i) {
                if (this.listeners[i] == cvsListener) {
                    for (int j = i + 1; j < this.listeners.length; ++j) {
                        listeners[j - 1] = this.listeners[j];
                    }
                    break;
                }
                listeners[i] = this.listeners[i];
            }
            this.listeners = listeners;
        }
    }
    
    public void fireCVSEvent(final CVSEvent cvsEvent) {
        if (this.listeners == null || this.listeners.length == 0) {
            return;
        }
        if (cvsEvent instanceof FileInfoEvent && this.services.getGlobalOptions().isExcluded(((FileInfoEvent)cvsEvent).getInfoContainer().getFile())) {
            return;
        }
        Object o = null;
        synchronized (this.listeners) {
            o = new CVSListener[this.listeners.length];
            System.arraycopy(this.listeners, 0, o, 0, o.length);
        }
        for (int i = 0; i < o.length; ++i) {
            cvsEvent.fireEvent(o[i]);
        }
    }
    
    public boolean isFireEnhancedEventSet() {
        return this.fireEnhancedEventSet;
    }
    
    public void setFireEnhancedEventSet(final boolean fireEnhancedEventSet) {
        this.fireEnhancedEventSet = fireEnhancedEventSet;
    }
}
