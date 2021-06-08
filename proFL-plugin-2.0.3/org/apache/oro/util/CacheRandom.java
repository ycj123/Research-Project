// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

import java.util.Random;

public final class CacheRandom extends GenericCache
{
    private Random __random;
    
    public CacheRandom(final int n) {
        super(n);
        this.__random = new Random(System.currentTimeMillis());
    }
    
    public CacheRandom() {
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
        int numEntries;
        if (!this.isFull()) {
            numEntries = this._numEntries;
            ++this._numEntries;
        }
        else {
            numEntries = (int)(this._cache.length * this.__random.nextFloat());
            this._table.remove(this._cache[numEntries]._key);
        }
        this._cache[numEntries]._value = o2;
        this._cache[numEntries]._key = o;
        this._table.put(o, this._cache[numEntries]);
    }
}
