// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.NoSuchElementException;

public class ComplexKeyHashMap
{
    protected Entry[] table;
    protected static final int DEFAULT_CAPACITY = 32;
    protected static final int MINIMUM_CAPACITY = 4;
    protected static final int MAXIMUM_CAPACITY = 268435456;
    protected int size;
    protected transient int threshold;
    
    public ComplexKeyHashMap() {
        this.init(32);
    }
    
    public ComplexKeyHashMap(final boolean b) {
    }
    
    public ComplexKeyHashMap(final int expectedMaxSize) {
        this.init(this.capacity(expectedMaxSize));
    }
    
    public static int hash(int h) {
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        final Object[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            tab[i] = null;
        }
        this.size = 0;
    }
    
    public void init(final int initCapacity) {
        this.threshold = initCapacity * 6 / 8;
        this.table = new Entry[initCapacity];
    }
    
    public void resize(final int newLength) {
        final Entry[] oldTable = this.table;
        final int oldLength = this.table.length;
        final Entry[] newTable = new Entry[newLength];
        for (Entry e : oldTable) {
            while (e != null) {
                final Entry next = e.next;
                final int index = e.hash & newLength - 1;
                e.next = newTable[index];
                newTable[index] = e;
                e = next;
            }
        }
        this.table = newTable;
        this.threshold = 6 * newLength / 8;
    }
    
    private int capacity(final int expectedMaxSize) {
        final int minCapacity = 8 * expectedMaxSize / 6;
        int result;
        if (minCapacity > 268435456 || minCapacity < 0) {
            result = 268435456;
        }
        else {
            for (result = 4; result < minCapacity; result <<= 1) {}
        }
        return result;
    }
    
    public Entry[] getTable() {
        return this.table;
    }
    
    public EntryIterator getEntrySetIterator() {
        return new EntryIterator() {
            Entry next;
            int index;
            Entry current;
            
            {
                final Entry[] t = ComplexKeyHashMap.this.table;
                int i = t.length;
                Entry n = null;
                if (ComplexKeyHashMap.this.size != 0) {
                    while (i > 0 && (n = t[--i]) == null) {}
                }
                this.next = n;
                this.index = i;
            }
            
            public boolean hasNext() {
                return this.next != null;
            }
            
            public Entry next() {
                return this.nextEntry();
            }
            
            Entry nextEntry() {
                final Entry e = this.next;
                if (e == null) {
                    throw new NoSuchElementException();
                }
                Entry n;
                Entry[] t;
                int i;
                for (n = e.next, t = ComplexKeyHashMap.this.table, i = this.index; n == null && i > 0; n = t[--i]) {}
                this.index = i;
                this.next = n;
                return this.current = e;
            }
        };
    }
    
    public static class Entry
    {
        public int hash;
        public Entry next;
        public Object value;
        
        public Object getValue() {
            return this.value;
        }
        
        public void setValue(final Object value) {
            this.value = value;
        }
    }
    
    public interface EntryIterator
    {
        boolean hasNext();
        
        Entry next();
    }
}
