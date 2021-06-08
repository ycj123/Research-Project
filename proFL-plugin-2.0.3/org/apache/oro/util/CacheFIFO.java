// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

public final class CacheFIFO extends GenericCache
{
    private int __curent;
    
    public CacheFIFO(final int n) {
        super(n);
        this.__curent = 0;
    }
    
    public CacheFIFO() {
        this(20);
    }
    
    public final synchronized void addElement(final Object o, final Object o2) {
        final GenericCacheEntry value = this._table.get(o);
        if (value != null) {
            final GenericCacheEntry genericCacheEntry = value;
            genericCacheEntry._value = o2;
            genericCacheEntry._key = o;
            return;
        }
        int n;
        if (!this.isFull()) {
            n = this._numEntries;
            ++this._numEntries;
        }
        else {
            n = this.__curent;
            if (++this.__curent >= this._cache.length) {
                this.__curent = 0;
            }
            this._table.remove(this._cache[n]._key);
        }
        this._cache[n]._value = o2;
        this._cache[n]._key = o;
        this._table.put(o, this._cache[n]);
    }
}
