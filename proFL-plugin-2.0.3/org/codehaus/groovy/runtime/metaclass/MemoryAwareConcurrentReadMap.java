// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import java.lang.ref.SoftReference;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;

public class MemoryAwareConcurrentReadMap
{
    protected final BarrierLock barrierLock;
    protected transient Object lastWrite;
    public static final int DEFAULT_INITIAL_CAPACITY = 32;
    private static final int MINIMUM_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected transient Entry[] table;
    protected transient int count;
    protected int threshold;
    protected float loadFactor;
    private ReferenceQueue queue;
    private static final Reference DUMMY_REF;
    
    protected final void recordModification(final Object x) {
        synchronized (this.barrierLock) {
            this.lastWrite = x;
        }
    }
    
    protected final Entry[] getTableForReading() {
        synchronized (this.barrierLock) {
            return this.table;
        }
    }
    
    private int p2capacity(final int initialCapacity) {
        final int cap = initialCapacity;
        int result;
        if (cap > 1073741824 || cap < 0) {
            result = 1073741824;
        }
        else {
            for (result = 4; result < cap; result <<= 1) {}
        }
        return result;
    }
    
    private static int hash(final Object x) {
        final int h = x.hashCode();
        return (h << 7) - h + (h >>> 9) + (h >>> 17);
    }
    
    protected boolean eq(final Object x, final Object y) {
        return x == y;
    }
    
    public MemoryAwareConcurrentReadMap(final int initialCapacity, final float loadFactor) {
        this.barrierLock = new BarrierLock();
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        final int cap = this.p2capacity(initialCapacity);
        this.table = new Entry[cap];
        this.threshold = (int)(cap * loadFactor);
        this.queue = new ReferenceQueue();
    }
    
    public MemoryAwareConcurrentReadMap(final int initialCapacity) {
        this(initialCapacity, 0.75f);
    }
    
    public MemoryAwareConcurrentReadMap() {
        this(32, 0.75f);
    }
    
    public synchronized int size() {
        return this.count;
    }
    
    public synchronized boolean isEmpty() {
        return this.count == 0;
    }
    
    public Object get(final Object key) {
        final int hash = hash(key);
        Entry[] tab = this.table;
        int index = hash & tab.length - 1;
        Entry e;
        Entry first = e = tab[index];
        while (true) {
            if (e == null) {
                final Entry[] reread = this.getTableForReading();
                if (tab == reread && first == tab[index]) {
                    return null;
                }
                tab = reread;
                first = (e = tab[index = (hash & tab.length - 1)]);
            }
            else {
                final Object eKey = e.getKey();
                final Object eValue = e.getValue();
                if (e.hash == hash && this.eq(key, eKey)) {
                    if (e.value != MemoryAwareConcurrentReadMap.DUMMY_REF) {
                        return eValue;
                    }
                    synchronized (this) {
                        if (eKey == null && eValue == null) {
                            this.expungeStaleEntries();
                        }
                        tab = this.table;
                    }
                    first = (e = tab[index = (hash & tab.length - 1)]);
                }
                else {
                    e = e.next;
                }
            }
        }
    }
    
    public Object put(final Object key, final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final int hash = hash(key);
        final Entry[] tab = this.table;
        final int index = hash & tab.length - 1;
        Entry e;
        Entry first;
        for (first = (e = tab[index]); e != null && (e.hash != hash || !this.eq(key, e.getKey())); e = e.next) {}
        synchronized (this) {
            if (tab == this.table) {
                if (e == null) {
                    if (first == tab[index]) {
                        final Entry newEntry = new Entry(hash, key, value, first, this.queue);
                        tab[index] = newEntry;
                        if (++this.count >= this.threshold) {
                            this.rehash();
                        }
                        else {
                            this.recordModification(newEntry);
                        }
                        return null;
                    }
                }
                else {
                    final Object oldValue = e.getValue();
                    if (first == tab[index] && oldValue != null) {
                        e.setValue(e.value);
                        return oldValue;
                    }
                }
            }
            return this.sput(key, value, hash);
        }
    }
    
    protected Object sput(final Object key, final Object value, final int hash) {
        this.expungeStaleEntries();
        final Entry[] tab = this.table;
        final int index = hash & tab.length - 1;
        Entry e;
        Entry first;
        for (first = (e = tab[index]); e != null; e = e.next) {
            if (e.hash == hash && this.eq(key, e.getKey())) {
                final Object oldValue = e.getValue();
                e.setValue(e.value);
                return oldValue;
            }
        }
        final Entry newEntry = new Entry(hash, key, value, first, this.queue);
        tab[index] = newEntry;
        if (++this.count >= this.threshold) {
            this.rehash();
        }
        else {
            this.recordModification(newEntry);
        }
        return null;
    }
    
    protected void rehash() {
        final Entry[] oldTable = this.table;
        final int oldCapacity = oldTable.length;
        if (oldCapacity >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        final int newCapacity = oldCapacity << 1;
        final int mask = newCapacity - 1;
        this.threshold = (int)(newCapacity * this.loadFactor);
        final Entry[] newTable = new Entry[newCapacity];
        for (final Entry e : oldTable) {
            if (e != null) {
                final int idx = e.hash & mask;
                final Entry next = e.next;
                if (next == null) {
                    newTable[idx] = e;
                }
                else {
                    Entry lastRun = e;
                    int lastIdx = idx;
                    for (Entry last = next; last != null; last = last.next) {
                        final int k = last.hash & mask;
                        if (k != lastIdx) {
                            lastIdx = k;
                            lastRun = last;
                        }
                    }
                    newTable[lastIdx] = lastRun;
                    for (Entry p = e; p != lastRun; p = p.next) {
                        final int k = p.hash & mask;
                        newTable[k] = new Entry(p.hash, p.getKey(), p.getValue(), newTable[k], this.queue);
                    }
                }
            }
        }
        this.recordModification(this.table = newTable);
    }
    
    public Object remove(final Object key) {
        final int hash = hash(key);
        final Entry[] tab = this.table;
        final int index = hash & tab.length - 1;
        Entry e;
        Entry first;
        for (first = (e = (e = tab[index])); e != null && (e.hash != hash || !this.eq(key, e.getKey())); e = e.next) {}
        synchronized (this) {
            if (tab == this.table) {
                if (e == null) {
                    if (first == tab[index]) {
                        return null;
                    }
                }
                else {
                    final Object oldValue = e.getValue();
                    if (first == tab[index] && oldValue != null) {
                        e.setValue(null);
                        --this.count;
                        Entry head = e.next;
                        for (Entry p = first; p != e; p = p.next) {
                            head = new Entry(p.hash, p.key, p.value, head);
                        }
                        this.recordModification(tab[index] = head);
                        return oldValue;
                    }
                }
            }
            return this.sremove(key, hash);
        }
    }
    
    protected Object sremove(final Object key, final int hash) {
        this.expungeStaleEntries();
        final Entry[] tab = this.table;
        final int index = hash & tab.length - 1;
        Entry e;
        for (Entry first = e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && this.eq(key, e.getKey())) {
                final Object oldValue = e.getValue();
                e.setValue(null);
                --this.count;
                Entry head = e.next;
                for (Entry p = first; p != e; p = p.next) {
                    head = new Entry(p.hash, p.getKey(), p.getValue(), head, this.queue);
                }
                this.recordModification(tab[index] = head);
                return oldValue;
            }
        }
        return null;
    }
    
    public synchronized void clear() {
        final Entry[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                e.setValue(null);
            }
            tab[i] = null;
        }
        this.count = 0;
        this.recordModification(tab);
    }
    
    private void expungeStaleEntries() {
        final Entry[] tab = this.table;
        SoftRef ref;
        while ((ref = (SoftRef)this.queue.poll()) != null) {
            final Entry entry = ref.entry;
            if (entry == null) {
                continue;
            }
            ref.entry = null;
            if (entry.key != ref && entry.value != ref) {
                continue;
            }
            final int hash = entry.hash;
            final int index = hash & tab.length - 1;
            Entry e;
            for (Entry first = e = tab[index]; e != null; e = e.next) {
                if (e == entry) {
                    entry.key.clear();
                    entry.setValue(null);
                    --this.count;
                    Entry head = e.next;
                    for (Entry p = first; p != e; p = p.next) {
                        head = new Entry(p.hash, p.key, p.value, head);
                    }
                    this.recordModification(tab[index] = head);
                    break;
                }
            }
        }
    }
    
    static {
        DUMMY_REF = new DummyRef();
    }
    
    protected static class BarrierLock implements Serializable
    {
    }
    
    private static class DummyRef implements Reference
    {
        public Object get() {
            return null;
        }
    }
    
    private static class SoftRef extends SoftReference implements MemoryAwareConcurrentReadMap.Reference
    {
        private volatile Entry entry;
        
        public SoftRef(final Entry e, final Object v, final ReferenceQueue q) {
            super(v, q);
            this.entry = e;
        }
        
        @Override
        public void clear() {
            super.clear();
            this.entry = null;
        }
    }
    
    private static class Entry
    {
        private final int hash;
        private final SoftRef key;
        private final Entry next;
        private volatile Reference value;
        
        Entry(final int hash, final Object key, final Object value, final Entry next, final ReferenceQueue queue) {
            this.hash = hash;
            this.key = new SoftRef(this, key, queue);
            this.next = next;
            this.value = new SoftRef(this, value, queue);
        }
        
        Entry(final int hash, final SoftRef key, final Reference value, final Entry next) {
            this.hash = hash;
            (this.key = key).entry = this;
            this.next = next;
            this.value = MemoryAwareConcurrentReadMap.DUMMY_REF;
            this.setValue(value);
        }
        
        public Object getKey() {
            return this.key.get();
        }
        
        public Object getValue() {
            return this.value.get();
        }
        
        public Object setValue(final Reference value) {
            final Object oldValue = this.value.get();
            if (value == null || value == MemoryAwareConcurrentReadMap.DUMMY_REF) {
                this.value = MemoryAwareConcurrentReadMap.DUMMY_REF;
            }
            else {
                final SoftRef ref = (SoftRef)value;
                ref.entry = this;
                this.value = value;
            }
            return oldValue;
        }
    }
    
    private interface Reference
    {
        Object get();
    }
}
