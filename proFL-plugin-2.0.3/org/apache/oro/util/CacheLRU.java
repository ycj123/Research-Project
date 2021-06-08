// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

public final class CacheLRU extends GenericCache
{
    private int __head;
    private int __tail;
    private int[] __next;
    private int[] __prev;
    
    public CacheLRU(final int n) {
        super(n);
        this.__head = 0;
        this.__tail = 0;
        this.__next = new int[this._cache.length];
        this.__prev = new int[this._cache.length];
        for (int i = 0; i < this.__next.length; ++i) {
            this.__next[i] = (this.__prev[i] = -1);
        }
    }
    
    public CacheLRU() {
        this(20);
    }
    
    private void __moveToFront(final int _head) {
        if (this.__head != _head) {
            final int n = this.__next[_head];
            final int _tail = this.__prev[_head];
            if ((this.__next[_tail] = n) >= 0) {
                this.__prev[n] = _tail;
            }
            else {
                this.__tail = _tail;
            }
            this.__prev[_head] = -1;
            this.__next[_head] = this.__head;
            this.__prev[this.__head] = _head;
            this.__head = _head;
        }
    }
    
    public synchronized Object getElement(final Object key) {
        final GenericCacheEntry value = this._table.get(key);
        if (value != null) {
            final GenericCacheEntry genericCacheEntry = value;
            this.__moveToFront(genericCacheEntry._index);
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
            this.__moveToFront(genericCacheEntry._index);
            return;
        }
        if (!this.isFull()) {
            if (this._numEntries > 0) {
                this.__prev[this._numEntries] = this.__tail;
                this.__next[this._numEntries] = -1;
                this.__moveToFront(this._numEntries);
            }
            ++this._numEntries;
        }
        else {
            this._table.remove(this._cache[this.__tail]._key);
            this.__moveToFront(this.__tail);
        }
        this._cache[this.__head]._value = o2;
        this._cache[this.__head]._key = o;
        this._table.put(o, this._cache[this.__head]);
    }
}
