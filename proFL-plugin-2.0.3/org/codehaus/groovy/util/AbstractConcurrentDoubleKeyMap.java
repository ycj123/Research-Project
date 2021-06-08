// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public abstract class AbstractConcurrentDoubleKeyMap<K1, K2, V> extends AbstractConcurrentMapBase
{
    public AbstractConcurrentDoubleKeyMap(final Object segmentInfo) {
        super(segmentInfo);
    }
    
    static <K1, K2> int hash(final K1 key1, final K2 key2) {
        int h = 31 * key1.hashCode() + key2.hashCode();
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    public V get(final K1 key1, final K2 key2) {
        final int hash = hash(key1, key2);
        return this.segmentFor(hash).get(key1, key2, hash);
    }
    
    public Entry<K1, K2, V> getOrPut(final K1 key1, final K2 key2, final V value) {
        final int hash = hash(key1, key2);
        return this.segmentFor(hash).getOrPut(key1, key2, hash, value);
    }
    
    public void put(final K1 key1, final K2 key2, final V value) {
        final int hash = hash(key1, key2);
        this.segmentFor(hash).put(key1, key2, hash).setValue(value);
    }
    
    public void remove(final K1 key1, final K2 key2) {
        final int hash = hash(key1, key2);
        this.segmentFor(hash).remove(key1, key2, hash);
    }
    
    @Override
    public final Segment<K1, K2, V> segmentFor(final int hash) {
        return (Segment<K1, K2, V>)this.segments[hash >>> this.segmentShift & this.segmentMask];
    }
    
    abstract static class Segment<K1, K2, V> extends AbstractConcurrentMapBase.Segment
    {
        Segment(final int initialCapacity) {
            super(initialCapacity);
        }
        
        V get(final K1 key1, final K2 key2, final int hash) {
            final Object[] tab = this.table;
            final Object o = tab[hash & tab.length - 1];
            if (o != null) {
                if (o instanceof AbstractConcurrentDoubleKeyMap.Entry) {
                    final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)o;
                    if (e.isEqual(key1, key2, hash)) {
                        return e.getValue();
                    }
                }
                else {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i != arr.length; ++i) {
                        final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e2 = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)arr[i];
                        if (e2 != null && e2.isEqual(key1, key2, hash)) {
                            return e2.getValue();
                        }
                    }
                }
            }
            return null;
        }
        
        AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> getOrPut(final K1 key1, final K2 key2, final int hash, final V value) {
            final Object[] tab = this.table;
            final Object o = tab[hash & tab.length - 1];
            if (o != null) {
                if (o instanceof AbstractConcurrentDoubleKeyMap.Entry) {
                    final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)o;
                    if (e.isEqual(key1, key2, hash)) {
                        return e;
                    }
                }
                else {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i != arr.length; ++i) {
                        final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e2 = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)arr[i];
                        if (e2 != null && e2.isEqual(key1, key2, hash)) {
                            return e2;
                        }
                    }
                }
            }
            final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> kvEntry = this.put(key1, key2, hash);
            kvEntry.setValue(value);
            return kvEntry;
        }
        
        AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> put(final K1 key1, final K2 key2, final int hash) {
            this.lock();
            try {
                int c = this.count;
                if (c++ > this.threshold) {
                    this.rehash();
                }
                final Object[] tab = this.table;
                final int index = hash & tab.length - 1;
                final Object o = tab[index];
                if (o == null) {
                    final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> res = this.createEntry(key1, key2, hash);
                    tab[index] = res;
                    this.count = c;
                    return res;
                }
                if (!(o instanceof AbstractConcurrentDoubleKeyMap.Entry)) {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i != arr.length; ++i) {
                        final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)arr[i];
                        if (e != null && e.isEqual(key1, key2, hash)) {
                            return e;
                        }
                    }
                    final Object[] newArr = new Object[arr.length + 1];
                    final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> res2 = this.createEntry(key1, key2, hash);
                    arr[0] = res2;
                    System.arraycopy(arr, 0, newArr, 1, arr.length);
                    tab[index] = arr;
                    this.count = c;
                    return res2;
                }
                final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e2 = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)o;
                if (e2.isEqual(key1, key2, hash)) {
                    return e2;
                }
                final Object[] arr2 = new Object[2];
                final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> res2 = this.createEntry(key1, key2, hash);
                arr2[0] = res2;
                arr2[1] = e2;
                tab[index] = arr2;
                this.count = c;
                return res2;
            }
            finally {
                this.unlock();
            }
        }
        
        public void remove(final K1 key1, final K2 key2, final int hash) {
            this.lock();
            try {
                final int c = this.count - 1;
                final Object[] tab = this.table;
                final int index = hash & tab.length - 1;
                final Object o = tab[index];
                if (o != null) {
                    if (o instanceof AbstractConcurrentDoubleKeyMap.Entry) {
                        if (((AbstractConcurrentDoubleKeyMap.Entry)o).isEqual(key1, key2, hash)) {
                            tab[index] = null;
                            this.count = c;
                        }
                    }
                    else {
                        final Object[] arr = (Object[])o;
                        for (int i = 0; i < arr.length; ++i) {
                            final AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> e = (AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>)arr[i];
                            if (e != null && e.isEqual(key1, key2, hash)) {
                                arr[i] = null;
                                this.count = c;
                                break;
                            }
                        }
                    }
                }
            }
            finally {
                this.unlock();
            }
        }
        
        protected abstract AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> createEntry(final K1 p0, final K2 p1, final int p2);
    }
    
    interface Entry<K1, K2, V> extends AbstractConcurrentMapBase.Entry<V>
    {
        boolean isEqual(final K1 p0, final K2 p1, final int p2);
    }
}
