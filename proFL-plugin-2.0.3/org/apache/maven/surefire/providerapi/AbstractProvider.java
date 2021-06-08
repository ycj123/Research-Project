// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.providerapi;

public abstract class AbstractProvider implements SurefireProvider
{
    private final Thread creatingThread;
    
    public AbstractProvider() {
        this.creatingThread = Thread.currentThread();
    }
    
    public void cancel() {
        synchronized (this.creatingThread) {
            if (this.creatingThread.isAlive()) {
                this.creatingThread.interrupt();
            }
        }
    }
}
