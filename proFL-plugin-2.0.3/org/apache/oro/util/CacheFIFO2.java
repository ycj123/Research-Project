// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

public final class CacheFIFO2 extends GenericCache
{
    private int __current;
    private boolean[] __tryAgain;
    
    public CacheFIFO2(final int n) {
        super(n);
        this.__current = 0;
        this.__tryAgain = new boolean[this._cache.length];
    }
    
    public CacheFIFO2() {
        this(20);
    }
    
    public synchronized Object getElement(final Object key) {
        final GenericCacheEntry value = this._table.get(key);
        if (value != null) {
            final GenericCacheEntry genericCacheEntry = value;
            this.__tryAgain[genericCacheEntry._index] = true;
            return genericCacheEntry._value;
        }
        return null;
    }
    
    public final synchronized void addElement(final Object o, final Object o2) {
        final GenericCacheEntry value = this._table.get(o);
        if (value != null) {
            final GenericCacheEntry genericCacheEntry = value;
            genericCacheEntry._value = o2;
            genericCacheEntry._key = o;
            this.__tryAgain[genericCacheEntry._index] = true;
            return;
        }
        int n;
        if (!this.isFull()) {
            n = this._numEntries;
            ++this._numEntries;
        }
        else {
            for (n = this.__current; this.__tryAgain[n]; n = 0) {
                this.__tryAgain[n] = false;
                if (++n >= this.__tryAgain.length) {}
            }
            this.__current = n + 1;
            if (this.__current >= this._cache.length) {
                this.__current = 0;
            }
            this._table.remove(this._cache[n]._key);
        }
        this._cache[n]._value = o2;
        this._cache[n]._key = o;
        this._table.put(o, this._cache[n]);
    }
}
