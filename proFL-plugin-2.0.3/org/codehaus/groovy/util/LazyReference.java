// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public abstract class LazyReference<T> extends LockableObject
{
    private static final ManagedReference INIT;
    private static final ManagedReference NULL_REFERENCE;
    private ManagedReference<T> reference;
    private final ReferenceBundle bundle;
    
    public LazyReference(final ReferenceBundle bundle) {
        this.reference = (ManagedReference<T>)LazyReference.INIT;
        this.bundle = bundle;
    }
    
    public T get() {
        final ManagedReference<T> resRef = this.reference;
        if (resRef == LazyReference.INIT) {
            return this.getLocked(false);
        }
        if (resRef == LazyReference.NULL_REFERENCE) {
            return null;
        }
        final T res = resRef.get();
        if (res == null) {
            return this.getLocked(true);
        }
        return res;
    }
    
    private T getLocked(final boolean force) {
        this.lock();
        try {
            final ManagedReference<T> resRef = this.reference;
            if (!force && resRef != LazyReference.INIT) {
                return resRef.get();
            }
            final T res = this.initValue();
            if (res == null) {
                this.reference = (ManagedReference<T>)LazyReference.NULL_REFERENCE;
            }
            else {
                this.reference = new ManagedReference<T>(this.bundle, res);
            }
            return res;
        }
        finally {
            this.unlock();
        }
    }
    
    public void clear() {
        this.reference = (ManagedReference<T>)LazyReference.INIT;
    }
    
    public abstract T initValue();
    
    @Override
    public String toString() {
        final T res = this.reference.get();
        if (res == null) {
            return "<null>";
        }
        return res.toString();
    }
    
    static {
        INIT = new ManagedReference(ReferenceType.HARD, null, null) {};
        NULL_REFERENCE = new ManagedReference(ReferenceType.HARD, null, null) {};
    }
}
