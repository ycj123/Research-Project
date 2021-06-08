// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public abstract class AbstractConcurrentMapBase
{
    protected static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final int RETRIES_BEFORE_LOCK = 2;
    final int segmentMask;
    final int segmentShift;
    protected final Segment[] segments;
    
    public AbstractConcurrentMapBase(final Object segmentInfo) {
        int sshift = 0;
        int ssize;
        for (ssize = 1; ssize < 16; ssize <<= 1) {
            ++sshift;
        }
        this.segmentShift = 32 - sshift;
        this.segmentMask = ssize - 1;
        this.segments = new Segment[ssize];
        int c = 512 / ssize;
        if (c * ssize < 512) {
            ++c;
        }
        int cap;
        for (cap = 1; cap < c; cap <<= 1) {}
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = this.createSegment(segmentInfo, cap);
        }
    }
    
    protected abstract Segment createSegment(final Object p0, final int p1);
    
    protected static <K> int hash(final K key) {
        int h = System.identityHashCode(key);
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    public Segment segmentFor(final int hash) {
        return this.segments[hash >>> this.segmentShift & this.segmentMask];
    }
    
    public int fullSize() {
        int count = 0;
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i].lock();
            try {
                for (int j = 0; j < this.segments[i].table.length; ++j) {
                    final Object o = this.segments[i].table[j];
                    if (o != null) {
                        if (o instanceof Entry) {
                            ++count;
                        }
                        else {
                            final Object[] arr = (Object[])o;
                            count += arr.length;
                        }
                    }
                }
            }
            finally {
                this.segments[i].unlock();
            }
        }
        return count;
    }
    
    public int size() {
        int count = 0;
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i].lock();
            try {
                for (int j = 0; j < this.segments[i].table.length; ++j) {
                    final Object o = this.segments[i].table[j];
                    if (o != null) {
                        if (o instanceof Entry) {
                            final Entry e = (Entry)o;
                            if (e.isValid()) {
                                ++count;
                            }
                        }
                        else {
                            final Object[] arr = (Object[])o;
                            for (int k = 0; k < arr.length; ++k) {
                                final Entry info = (Entry)arr[k];
                                if (info != null && info.isValid()) {
                                    ++count;
                                }
                            }
                        }
                    }
                }
            }
            finally {
                this.segments[i].unlock();
            }
        }
        return count;
    }
    
    public static class Segment extends LockableObject
    {
        volatile int count;
        int threshold;
        protected volatile Object[] table;
        
        protected Segment(final int initialCapacity) {
            this.setTable(new Object[initialCapacity]);
        }
        
        void setTable(final Object[] newTable) {
            this.threshold = (int)(newTable.length * 0.75f);
            this.table = newTable;
        }
        
        void removeEntry(final Entry e) {
            this.lock();
            int newCount = this.count;
            try {
                final Object[] tab = this.table;
                final int index = e.getHash() & tab.length - 1;
                final Object o = tab[index];
                if (o != null) {
                    if (o instanceof Entry) {
                        if (o == e) {
                            tab[index] = null;
                            --newCount;
                        }
                    }
                    else {
                        final Object[] arr = (Object[])o;
                        Object res = null;
                        for (int i = 0; i < arr.length; ++i) {
                            final Entry info = (Entry)arr[i];
                            if (info != null) {
                                if (info != e) {
                                    if (info.isValid()) {
                                        res = this.put(info, res);
                                    }
                                    else {
                                        --newCount;
                                    }
                                }
                                else {
                                    --newCount;
                                }
                            }
                        }
                        tab[index] = res;
                    }
                    this.count = newCount;
                }
            }
            finally {
                this.unlock();
            }
        }
        
        void rehash() {
            final Object[] oldTable = this.table;
            final int oldCapacity = oldTable.length;
            if (oldCapacity >= 1073741824) {
                return;
            }
            int newCount = 0;
            for (int i = 0; i < oldCapacity; ++i) {
                final Object o = oldTable[i];
                if (o != null) {
                    if (o instanceof Entry) {
                        final Entry e = (Entry)o;
                        if (e.isValid()) {
                            ++newCount;
                        }
                        else {
                            oldTable[i] = null;
                        }
                    }
                    else {
                        final Object[] arr = (Object[])o;
                        int localCount = 0;
                        for (int index = 0; index < arr.length; ++index) {
                            final Entry e2 = (Entry)arr[index];
                            if (e2 != null && e2.isValid()) {
                                ++localCount;
                            }
                            else {
                                arr[index] = null;
                            }
                        }
                        if (localCount == 0) {
                            oldTable[i] = null;
                        }
                        else {
                            newCount += localCount;
                        }
                    }
                }
            }
            final Object[] newTable = new Object[(newCount + 1 < this.threshold) ? oldCapacity : (oldCapacity << 1)];
            final int sizeMask = newTable.length - 1;
            newCount = 0;
            for (final Object o2 : oldTable) {
                if (o2 != null) {
                    if (o2 instanceof Entry) {
                        final Entry e3 = (Entry)o2;
                        if (e3.isValid()) {
                            final int index2 = e3.getHash() & sizeMask;
                            this.put(e3, index2, newTable);
                            ++newCount;
                        }
                    }
                    else {
                        final Object[] arr2 = (Object[])o2;
                        for (int k = 0; k < arr2.length; ++k) {
                            final Entry e4 = (Entry)arr2[k];
                            if (e4 != null && e4.isValid()) {
                                final int index3 = e4.getHash() & sizeMask;
                                this.put(e4, index3, newTable);
                                ++newCount;
                            }
                        }
                    }
                }
            }
            this.threshold = (int)(newTable.length * 0.75f);
            this.table = newTable;
            this.count = newCount;
        }
        
        private void put(final Entry ee, final int index, final Object[] tab) {
            final Object o = tab[index];
            if (o == null) {
                tab[index] = ee;
                return;
            }
            if (o instanceof Entry) {
                final Object[] arr = { ee, (Entry)o };
                tab[index] = arr;
                return;
            }
            final Object[] arr = (Object[])o;
            final Object[] newArr = new Object[arr.length + 1];
            newArr[0] = ee;
            System.arraycopy(arr, 0, newArr, 1, arr.length);
            tab[index] = newArr;
        }
        
        private Object put(final Entry ee, final Object o) {
            if (o == null) {
                return ee;
            }
            if (o instanceof Entry) {
                final Object[] arr = { ee, (Entry)o };
                return arr;
            }
            final Object[] arr = (Object[])o;
            final Object[] newArr = new Object[arr.length + 1];
            newArr[0] = ee;
            System.arraycopy(arr, 0, newArr, 1, arr.length);
            return newArr;
        }
    }
    
    public interface Entry<V>
    {
        V getValue();
        
        void setValue(final V p0);
        
        int getHash();
        
        boolean isValid();
    }
}
