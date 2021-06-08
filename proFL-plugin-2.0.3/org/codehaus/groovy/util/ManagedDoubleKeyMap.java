// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public class ManagedDoubleKeyMap<K1, K2, V> extends AbstractConcurrentDoubleKeyMap<K1, K2, V>
{
    public ManagedDoubleKeyMap(final ReferenceBundle bundle) {
        super(bundle);
    }
    
    @Override
    protected AbstractConcurrentDoubleKeyMap.Segment<K1, K2, V> createSegment(final Object segmentInfo, final int cap) {
        final ReferenceBundle bundle = (ReferenceBundle)segmentInfo;
        return new Segment<K1, K2, V>(bundle, cap);
    }
    
    static class Segment<K1, K2, V> extends AbstractConcurrentDoubleKeyMap.Segment<K1, K2, V>
    {
        private ReferenceBundle bundle;
        
        public Segment(final ReferenceBundle bundle, final int cap) {
            super(cap);
            this.bundle = bundle;
        }
        
        @Override
        protected AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V> createEntry(final K1 key1, final K2 key2, final int hash) {
            return new EntryWithValue<K1, K2, V>(this.bundle, key1, key2, hash, this);
        }
    }
    
    static class Ref<K> extends ManagedReference<K>
    {
        final Entry entry;
        
        public Ref(final ReferenceBundle bundle, final K referent, final Entry entry) {
            super(bundle, referent);
            this.entry = entry;
        }
        
        public void finalizeRef() {
            this.entry.clean();
        }
    }
    
    public static class Entry<K1, K2, V> implements AbstractConcurrentDoubleKeyMap.Entry<K1, K2, V>
    {
        private final int hash;
        final Ref<K1> ref1;
        final Ref<K2> ref2;
        final ManagedDoubleKeyMap.Segment segment;
        
        public Entry(final ReferenceBundle bundle, final K1 key1, final K2 key2, final int hash, final ManagedDoubleKeyMap.Segment segment) {
            this.hash = hash;
            this.segment = segment;
            this.ref1 = new Ref<K1>(bundle, key1, this);
            this.ref2 = new Ref<K2>(bundle, key2, this);
        }
        
        public boolean isValid() {
            return this.ref1.get() != null && this.ref2.get() != null;
        }
        
        public boolean isEqual(final K1 key1, final K2 key2, final int hash) {
            return this.hash == hash && this.ref1.get() == key1 && this.ref2.get() == key2;
        }
        
        public V getValue() {
            return (V)this;
        }
        
        public void setValue(final V value) {
        }
        
        public int getHash() {
            return this.hash;
        }
        
        public void clean() {
            this.segment.removeEntry(this);
            this.ref1.clear();
            this.ref2.clear();
        }
    }
    
    private static class EntryWithValue<K1, K2, V> extends Entry<K1, K2, V>
    {
        private V value;
        
        public EntryWithValue(final ReferenceBundle bundle, final K1 key1, final K2 key2, final int hash, final ManagedDoubleKeyMap.Segment segment) {
            super(bundle, key1, key2, hash, segment);
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
        public void clean() {
            super.clean();
            this.value = null;
        }
    }
}
