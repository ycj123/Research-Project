// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;
import java.lang.ref.ReferenceQueue;

public enum ReferenceType
{
    SOFT {
        @Override
        protected <T, V extends Finalizable> Reference<T, V> createReference(final T value, final V handler, final ReferenceQueue queue) {
            return new SoftRef<T, V>(value, handler, queue);
        }
    }, 
    WEAK {
        @Override
        protected <T, V extends Finalizable> Reference<T, V> createReference(final T value, final V handler, final ReferenceQueue queue) {
            return new WeakRef<T, V>(value, handler, queue);
        }
    }, 
    PHANTOM {
        @Override
        protected <T, V extends Finalizable> Reference<T, V> createReference(final T value, final V handler, final ReferenceQueue queue) {
            return new PhantomRef<T, V>(value, handler, queue);
        }
    }, 
    HARD {
        @Override
        protected <T, V extends Finalizable> Reference<T, V> createReference(final T value, final V handler, final ReferenceQueue queue) {
            return new HardRef<T, V>(value, handler, queue);
        }
    };
    
    protected abstract <T, V extends Finalizable> Reference<T, V> createReference(final T p0, final V p1, final ReferenceQueue p2);
    
    private static class SoftRef<TT, V extends Finalizable> extends SoftReference<TT> implements Reference<TT, V>
    {
        private final V handler;
        
        public SoftRef(final TT referent, final V handler, final ReferenceQueue<? super TT> q) {
            super(referent, q);
            this.handler = handler;
        }
        
        public V getHandler() {
            return this.handler;
        }
    }
    
    private static class WeakRef<TT, V extends Finalizable> extends WeakReference<TT> implements Reference<TT, V>
    {
        private final V handler;
        
        public WeakRef(final TT referent, final V handler, final ReferenceQueue<? super TT> q) {
            super(referent, q);
            this.handler = handler;
        }
        
        public V getHandler() {
            return this.handler;
        }
    }
    
    private static class PhantomRef<TT, V extends Finalizable> extends PhantomReference<TT> implements Reference<TT, V>
    {
        private final V handler;
        
        public PhantomRef(final TT referent, final V handler, final ReferenceQueue<? super TT> q) {
            super(referent, q);
            this.handler = handler;
        }
        
        public V getHandler() {
            return this.handler;
        }
    }
    
    private static class HardRef<TT, V extends Finalizable> implements Reference<TT, V>
    {
        private TT ref;
        private final V handler;
        
        public HardRef(final TT referent, final V handler, final ReferenceQueue<? super TT> q) {
            this.ref = referent;
            this.handler = handler;
        }
        
        public V getHandler() {
            return this.handler;
        }
        
        public TT get() {
            return this.ref;
        }
        
        public void clear() {
            this.ref = null;
        }
    }
}
