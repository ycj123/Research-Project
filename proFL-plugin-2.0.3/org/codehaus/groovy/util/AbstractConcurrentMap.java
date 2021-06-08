// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public abstract class AbstractConcurrentMap<K, V> extends AbstractConcurrentMapBase
{
    public AbstractConcurrentMap(final Object segmentInfo) {
        super(segmentInfo);
    }
    
    @Override
    public Segment segmentFor(final int hash) {
        return (Segment)super.segmentFor(hash);
    }
    
    public V get(final K key) {
        final int hash = AbstractConcurrentMapBase.hash(key);
        return this.segmentFor(hash).get(key, hash);
    }
    
    public Entry<K, V> getOrPut(final K key, final V value) {
        final int hash = AbstractConcurrentMapBase.hash(key);
        return this.segmentFor(hash).getOrPut(key, hash, value);
    }
    
    public void put(final K key, final V value) {
        final int hash = AbstractConcurrentMapBase.hash(key);
        this.segmentFor(hash).put(key, hash, value);
    }
    
    public void remove(final K key) {
        final int hash = AbstractConcurrentMapBase.hash(key);
        this.segmentFor(hash).remove(key, hash);
    }
    
    public abstract static class Segment<K, V> extends AbstractConcurrentMapBase.Segment
    {
        protected Segment(final int initialCapacity) {
            super(initialCapacity);
        }
        
        public final V get(final K key, final int hash) {
            final Object[] tab = this.table;
            final Object o = tab[hash & tab.length - 1];
            if (o != null) {
                if (o instanceof AbstractConcurrentMap.Entry) {
                    final AbstractConcurrentMap.Entry<K, V> e = (AbstractConcurrentMap.Entry<K, V>)o;
                    if (e.isEqual(key, hash)) {
                        return e.getValue();
                    }
                }
                else {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i < arr.length; ++i) {
                        final AbstractConcurrentMap.Entry<K, V> e2 = (AbstractConcurrentMap.Entry<K, V>)arr[i];
                        if (e2 != null && e2.isEqual(key, hash)) {
                            return e2.getValue();
                        }
                    }
                }
            }
            return null;
        }
        
        public final AbstractConcurrentMap.Entry<K, V> getOrPut(final K key, final int hash, final V value) {
            final Object[] tab = this.table;
            final Object o = tab[hash & tab.length - 1];
            if (o != null) {
                if (o instanceof AbstractConcurrentMap.Entry) {
                    final AbstractConcurrentMap.Entry<K, V> e = (AbstractConcurrentMap.Entry<K, V>)o;
                    if (e.isEqual(key, hash)) {
                        return e;
                    }
                }
                else {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i < arr.length; ++i) {
                        final AbstractConcurrentMap.Entry<K, V> e2 = (AbstractConcurrentMap.Entry<K, V>)arr[i];
                        if (e2 != null && e2.isEqual(key, hash)) {
                            return e2;
                        }
                    }
                }
            }
            return (AbstractConcurrentMap.Entry<K, V>)this.put(key, hash, value);
        }
        
        public final AbstractConcurrentMap.Entry put(final K key, final int hash, final V value) {
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
                    final AbstractConcurrentMap.Entry e = this.createEntry(key, hash, value);
                    tab[index] = e;
                    this.count = c;
                    return e;
                }
                if (!(o instanceof AbstractConcurrentMap.Entry)) {
                    final Object[] arr = (Object[])o;
                    for (int i = 0; i < arr.length; ++i) {
                        final AbstractConcurrentMap.Entry e2 = (AbstractConcurrentMap.Entry)arr[i];
                        if (e2 != null && e2.isEqual(key, hash)) {
                            e2.setValue(value);
                            return e2;
                        }
                    }
                    final AbstractConcurrentMap.Entry ee = this.createEntry(key, hash, value);
                    for (int j = 0; j < arr.length; ++j) {
                        final AbstractConcurrentMap.Entry e3 = (AbstractConcurrentMap.Entry)arr[j];
                        if (e3 == null) {
                            arr[j] = ee;
                            this.count = c;
                            return ee;
                        }
                    }
                    final Object[] newArr = new Object[arr.length + 1];
                    newArr[0] = ee;
                    System.arraycopy(arr, 0, newArr, 1, arr.length);
                    tab[index] = newArr;
                    this.count = c;
                    return ee;
                }
                final AbstractConcurrentMap.Entry e = (AbstractConcurrentMap.Entry)o;
                if (e.isEqual(key, hash)) {
                    e.setValue(value);
                    return e;
                }
                final Object[] arr2 = new Object[2];
                final AbstractConcurrentMap.Entry ee2 = this.createEntry(key, hash, value);
                arr2[0] = ee2;
                arr2[1] = e;
                tab[index] = arr2;
                this.count = c;
                return ee2;
            }
            finally {
                this.unlock();
            }
        }
        
        public void remove(final K key, final int hash) {
            this.lock();
            try {
                final int c = this.count - 1;
                final Object[] tab = this.table;
                final int index = hash & tab.length - 1;
                final Object o = tab[index];
                if (o != null) {
                    if (o instanceof AbstractConcurrentMap.Entry) {
                        if (((AbstractConcurrentMap.Entry)o).isEqual(key, hash)) {
                            tab[index] = null;
                            this.count = c;
                        }
                    }
                    else {
                        final Object[] arr = (Object[])o;
                        for (int i = 0; i < arr.length; ++i) {
                            final AbstractConcurrentMap.Entry<K, V> e = (AbstractConcurrentMap.Entry<K, V>)arr[i];
                            if (e != null && e.isEqual(key, hash)) {
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
        
        protected abstract AbstractConcurrentMap.Entry<K, V> createEntry(final K p0, final int p1, final V p2);
    }
    
    public interface Entry<K, V> extends AbstractConcurrentMapBase.Entry<V>
    {
        boolean isEqual(final K p0, final int p1);
    }
}
