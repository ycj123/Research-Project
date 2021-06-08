// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

public final class SimplePool
{
    private Object[] pool;
    private int max;
    private int current;
    
    public SimplePool(final int max) {
        this.current = -1;
        this.max = max;
        this.pool = new Object[max];
    }
    
    public void put(final Object o) {
        int idx = -1;
        synchronized (this) {
            if (this.current < this.max - 1) {
                idx = ++this.current;
            }
            if (idx >= 0) {
                this.pool[idx] = o;
            }
        }
    }
    
    public Object get() {
        synchronized (this) {
            if (this.current >= 0) {
                final Object o = this.pool[this.current];
                this.pool[this.current] = null;
                --this.current;
                return o;
            }
        }
        return null;
    }
    
    public int getMax() {
        return this.max;
    }
    
    Object[] getPool() {
        return this.pool;
    }
}
