// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LockableObject extends AbstractQueuedSynchronizer
{
    transient Thread owner;
    
    @Override
    protected final boolean isHeldExclusively() {
        return this.getState() != 0 && this.owner == Thread.currentThread();
    }
    
    public final void lock() {
        if (this.compareAndSetState(0, 1)) {
            this.owner = Thread.currentThread();
        }
        else {
            this.acquire(1);
        }
    }
    
    public final void unlock() {
        this.release(1);
    }
    
    @Override
    protected final boolean tryAcquire(final int acquires) {
        final Thread current = Thread.currentThread();
        final int c = this.getState();
        if (c == 0) {
            if (this.compareAndSetState(0, acquires)) {
                this.owner = current;
                return true;
            }
        }
        else if (current == this.owner) {
            this.setState(c + acquires);
            return true;
        }
        return false;
    }
    
    @Override
    protected final boolean tryRelease(final int releases) {
        final int c = this.getState() - releases;
        if (Thread.currentThread() != this.owner) {
            throw new IllegalMonitorStateException();
        }
        boolean free = false;
        if (c == 0) {
            free = true;
            this.owner = null;
        }
        this.setState(c);
        return free;
    }
}
