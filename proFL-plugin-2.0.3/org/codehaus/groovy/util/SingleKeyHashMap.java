// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public class SingleKeyHashMap extends ComplexKeyHashMap
{
    public SingleKeyHashMap() {
    }
    
    public SingleKeyHashMap(final boolean b) {
        super(false);
    }
    
    public boolean containsKey(final String name) {
        return this.get(name) != null;
    }
    
    public void put(final Object key, final Object value) {
        this.getOrPut(key).value = value;
    }
    
    public final Object get(final Object key) {
        final int h = ComplexKeyHashMap.hash(key.hashCode());
        for (ComplexKeyHashMap.Entry e = this.table[h & this.table.length - 1]; e != null; e = e.next) {
            if (e.hash == h && ((Entry)e).key.equals(key)) {
                return ((Entry)e).value;
            }
        }
        return null;
    }
    
    public Entry getOrPut(final Object key) {
        final int h = ComplexKeyHashMap.hash(key.hashCode());
        final ComplexKeyHashMap.Entry[] t = this.table;
        final int index = h & t.length - 1;
        for (ComplexKeyHashMap.Entry e = t[index]; e != null; e = e.next) {
            if (e.hash == h && ((Entry)e).key.equals(key)) {
                return (Entry)e;
            }
        }
        final Entry entry = new Entry();
        entry.next = t[index];
        entry.hash = h;
        entry.key = key;
        t[index] = entry;
        if (++this.size == this.threshold) {
            this.resize(2 * t.length);
        }
        return entry;
    }
    
    public Entry getOrPutEntry(final Entry element) {
        final Object key = element.key;
        final int h = element.hash;
        final ComplexKeyHashMap.Entry[] t = this.table;
        final int index = h & t.length - 1;
        for (ComplexKeyHashMap.Entry e = t[index]; e != null; e = e.next) {
            if (e.hash == h && ((Entry)e).key.equals(key)) {
                return (Entry)e;
            }
        }
        final Entry entry = new Entry();
        entry.next = t[index];
        entry.hash = h;
        entry.key = key;
        t[index] = entry;
        if (++this.size == this.threshold) {
            this.resize(2 * t.length);
        }
        return entry;
    }
    
    public Entry putCopyOfUnexisting(final Entry ee) {
        final int h = ee.hash;
        final ComplexKeyHashMap.Entry[] t = this.table;
        final int index = h & t.length - 1;
        final Entry entry = new Entry();
        entry.next = t[index];
        entry.hash = h;
        entry.key = ee.key;
        entry.value = ee.value;
        t[index] = entry;
        if (++this.size == this.threshold) {
            this.resize(2 * t.length);
        }
        return entry;
    }
    
    public final ComplexKeyHashMap.Entry remove(final Object key) {
        final int h = ComplexKeyHashMap.hash(key.hashCode());
        final int index = h & this.table.length - 1;
        ComplexKeyHashMap.Entry e = this.table[index];
        ComplexKeyHashMap.Entry prev = null;
        while (e != null) {
            if (e.hash == h && ((Entry)e).key.equals(key)) {
                if (prev == null) {
                    this.table[index] = e.next;
                }
                else {
                    prev.next = e.next;
                }
                --this.size;
                e.next = null;
                return e;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }
    
    public static SingleKeyHashMap copy(final SingleKeyHashMap dst, final SingleKeyHashMap src, final Copier copier) {
        dst.threshold = src.threshold;
        dst.size = src.size;
        final int len = src.table.length;
        final ComplexKeyHashMap.Entry[] t = new ComplexKeyHashMap.Entry[len];
        final ComplexKeyHashMap.Entry[] tt = src.table;
        for (int i = 0; i != len; ++i) {
            for (Entry e = (Entry)tt[i]; e != null; e = (Entry)e.next) {
                final Entry ee = new Entry();
                ee.hash = e.hash;
                ee.key = e.key;
                ee.value = copier.copy(e.value);
                ee.next = t[i];
                t[i] = ee;
            }
        }
        dst.table = t;
        return dst;
    }
    
    public static class Entry extends ComplexKeyHashMap.Entry
    {
        public Object key;
        
        public Object getKey() {
            return this.key;
        }
    }
    
    public interface Copier
    {
        Object copy(final Object p0);
    }
}
