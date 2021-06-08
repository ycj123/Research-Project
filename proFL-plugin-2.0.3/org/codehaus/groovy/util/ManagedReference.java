// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.lang.ref.ReferenceQueue;

public class ManagedReference<T> implements Finalizable
{
    private static final ReferenceManager NULL_MANAGER;
    private final Reference<T, ManagedReference<T>> ref;
    private final ReferenceManager manager;
    
    public ManagedReference(final ReferenceType type, ReferenceManager rmanager, final T value) {
        if (rmanager == null) {
            rmanager = ManagedReference.NULL_MANAGER;
        }
        (this.manager = rmanager).afterReferenceCreation(this.ref = type.createReference(value, this, rmanager.getReferenceQueue()));
    }
    
    public ManagedReference(final ReferenceBundle bundle, final T value) {
        this(bundle.getType(), bundle.getManager(), value);
    }
    
    public final T get() {
        return this.ref.get();
    }
    
    public final void clear() {
        this.ref.clear();
        this.manager.removeStallEntries();
    }
    
    public void finalizeReference() {
        this.clear();
    }
    
    static {
        NULL_MANAGER = new ReferenceManager(null) {};
    }
}
