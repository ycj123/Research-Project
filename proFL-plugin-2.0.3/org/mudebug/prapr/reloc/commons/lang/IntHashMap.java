// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang;

class IntHashMap
{
    private transient Entry[] table;
    private transient int count;
    private int threshold;
    private final float loadFactor;
    
    public IntHashMap() {
        this(20, 0.75f);
    }
    
    public IntHashMap(final int initialCapacity) {
        this(initialCapacity, 0.75f);
    }
    
    public IntHashMap(int initialCapacity, final float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        }
        if (initialCapacity == 0) {
            initialCapacity = 1;
        }
        this.loadFactor = loadFactor;
        this.table = new Entry[initialCapacity];
        this.threshold = (int)(initialCapacity * loadFactor);
    }
    
    public int size() {
        return this.count;
    }
    
    public boolean isEmpty() {
        return this.count == 0;
    }
    
    public boolean contains(final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final Entry[] tab = this.table;
        int i = tab.length;
        while (i-- > 0) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean containsValue(final Object value) {
        return this.contains(value);
    }
    
    public boolean containsKey(final int key) {
        final Entry[] tab = this.table;
        final int hash = key;
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                return true;
            }
        }
        return false;
    }
    
    public Object get(final int key) {
        final Entry[] tab = this.table;
        final int hash = key;
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                return e.value;
            }
        }
        return null;
    }
    
    protected void rehash() {
        final int oldCapacity = this.table.length;
        final Entry[] oldMap = this.table;
        final int newCapacity = oldCapacity * 2 + 1;
        final Entry[] newMap = new Entry[newCapacity];
        this.threshold = (int)(newCapacity * this.loadFactor);
        this.table = newMap;
        int i = oldCapacity;
        while (i-- > 0) {
            Entry e;
            int index;
            for (Entry old = oldMap[i]; old != null; old = old.next, index = (e.hash & Integer.MAX_VALUE) % newCapacity, e.next = newMap[index], newMap[index] = e) {
                e = old;
            }
        }
    }
    
    public Object put(final int key, final Object value) {
        Entry[] tab = this.table;
        final int hash = key;
        int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                final Object old = e.value;
                e.value = value;
                return old;
            }
        }
        if (this.count >= this.threshold) {
            this.rehash();
            tab = this.table;
            index = (hash & Integer.MAX_VALUE) % tab.length;
        }
        Entry e = new Entry(hash, key, value, tab[index]);
        tab[index] = e;
        ++this.count;
        return null;
    }
    
    public Object remove(final int key) {
        final Entry[] tab = this.table;
        final int hash = key;
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        Entry e = tab[index];
        Entry prev = null;
        while (e != null) {
            if (e.hash == hash) {
                if (prev != null) {
                    prev.next = e.next;
                }
                else {
                    tab[index] = e.next;
                }
                --this.count;
                final Object oldValue = e.value;
                e.value = null;
                return oldValue;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }
    
    public synchronized void clear() {
        final Entry[] tab = this.table;
        int index = tab.length;
        while (--index >= 0) {
            tab[index] = null;
        }
        this.count = 0;
    }
    
    private static class Entry
    {
        final int hash;
        final int key;
        Object value;
        Entry next;
        
        protected Entry(final int hash, final int key, final Object value, final Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
