// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class Janitor implements HasCleanup
{
    private final Set pending;
    
    public Janitor() {
        this.pending = new HashSet();
    }
    
    public void register(final HasCleanup object) {
        this.pending.add(object);
    }
    
    public void cleanup() {
        for (final HasCleanup object : this.pending) {
            try {
                object.cleanup();
            }
            catch (Exception ex) {}
        }
        this.pending.clear();
    }
}
