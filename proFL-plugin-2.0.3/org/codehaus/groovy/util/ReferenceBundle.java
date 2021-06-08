// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.lang.ref.ReferenceQueue;

public class ReferenceBundle
{
    private ReferenceManager manager;
    private ReferenceType type;
    private static final ReferenceBundle softReferences;
    private static final ReferenceBundle weakReferences;
    
    public ReferenceBundle(final ReferenceManager manager, final ReferenceType type) {
        this.manager = manager;
        this.type = type;
    }
    
    public ReferenceType getType() {
        return this.type;
    }
    
    public ReferenceManager getManager() {
        return this.manager;
    }
    
    public static ReferenceBundle getSoftBundle() {
        return ReferenceBundle.softReferences;
    }
    
    public static ReferenceBundle getWeakBundle() {
        return ReferenceBundle.weakReferences;
    }
    
    static {
        final ReferenceQueue queue = new ReferenceQueue();
        final ReferenceManager callBack = ReferenceManager.createCallBackedManager(queue);
        final ReferenceManager manager = ReferenceManager.createThresholdedIdlingManager(queue, callBack, 5000);
        softReferences = new ReferenceBundle(manager, ReferenceType.SOFT);
        weakReferences = new ReferenceBundle(manager, ReferenceType.WEAK);
    }
}
