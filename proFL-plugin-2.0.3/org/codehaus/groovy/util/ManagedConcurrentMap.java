// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public class ManagedConcurrentMap<K, V> extends AbstractConcurrentMap<K, V>
{
    protected ReferenceBundle bundle;
    
    public ManagedConcurrentMap(final ReferenceBundle bundle) {
        super(bundle);
        this.bundle = bundle;
        if (bundle == null) {
            throw new IllegalArgumentException("bundle must not be null");
        }
    }
    
    @Override
    protected Segment<K, V> createSegment(final Object segmentInfo, final int cap) {
        final ReferenceBundle bundle = (ReferenceBundle)segmentInfo;
        if (bundle == null) {
            throw new IllegalArgumentException("bundle must not be null");
        }
        return new Segment<K, V>(bundle, cap);
    }
    
    public static class Segment<K, V> extends AbstractConcurrentMap.Segment<K, V>
    {
        protected final ReferenceBundle bundle;
        
        public Segment(final ReferenceBundle bundle, final int cap) {
            super(cap);
            this.bundle = bundle;
            if (bundle == null) {
                throw new IllegalArgumentException("bundle must not be null");
            }
        }
        
        @Override
        protected AbstractConcurrentMap.Entry<K, V> createEntry(final K key, final int hash, final V value) {
            if (this.bundle == null) {
                throw new IllegalArgumentException("bundle must not be null");
            }
            return new EntryWithValue<K, V>(this.bundle, this, key, hash, value);
        }
    }
    
    public static class Entry<K, V> extends ManagedReference<K> implements AbstractConcurrentMap.Entry<K, V>
    {
        private final ManagedConcurrentMap.Segment segment;
        private int hash;
        
        public Entry(final ReferenceBundle bundle, final ManagedConcurrentMap.Segment segment, final K key, final int hash) {
            super(bundle, key);
            this.segment = segment;
            this.hash = hash;
        }
        
        public boolean isValid() {
            return this.get() != null;
        }
        
        public boolean isEqual(final K key, final int hash) {
            return this.hash == hash && this.get() == key;
        }
        
        public V getValue() {
            return (V)this;
        }
        
        public void setValue(final V value) {
        }
        
        public int getHash() {
            return this.hash;
        }
        
        public void finalizeRef() {
            super.finalizeReference();
            this.segment.removeEntry(this);
        }
    }
    
    public static class EntryWithValue<K, V> extends Entry<K, V>
    {
        private V value;
        
        public EntryWithValue(final ReferenceBundle bundle, final ManagedConcurrentMap.Segment segment, final K key, final int hash, final V value) {
            super(bundle, segment, key, hash);
            this.setValue(value);
        }
        
        @Override
        public V getValue() {
            return this.value;
        }
        
        @Override
        public void setValue(final V value) {
            this.value = value;
        }
        
        @Override
        public void finalizeRef() {
            this.value = null;
            super.finalizeRef();
        }
    }
}
