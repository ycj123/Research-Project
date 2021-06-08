// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

final class ReferenceHolder
{
    private static final Map<Object, Object> REFERENCES;
    
    static {
        REFERENCES = Collections.synchronizedMap(new IdentityHashMap<Object, Object>());
    }
    
    static void preventGarbageCollectionForLifeOfClassLoader(final Object objectToBeMaintained) {
        ReferenceHolder.REFERENCES.put(objectToBeMaintained, objectToBeMaintained);
    }
    
    private ReferenceHolder() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
