// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import org.apache.velocity.runtime.log.Log;

public final class IntrospectorCacheImpl implements IntrospectorCache
{
    private final Log log;
    private final Map classMapCache;
    private final Set classNameCache;
    private final Set listeners;
    
    public IntrospectorCacheImpl(final Log log) {
        this.classMapCache = new HashMap();
        this.classNameCache = new HashSet();
        this.listeners = new HashSet();
        this.log = log;
    }
    
    public synchronized void clear() {
        this.classMapCache.clear();
        this.classNameCache.clear();
        final Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().triggerClear();
        }
    }
    
    public synchronized ClassMap get(final Class c) {
        if (c == null) {
            throw new IllegalArgumentException("class is null!");
        }
        final ClassMap classMap = this.classMapCache.get(c);
        if (classMap == null && this.classNameCache.contains(c.getName())) {
            this.clear();
        }
        final Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().triggerGet(c, classMap);
        }
        return classMap;
    }
    
    public synchronized ClassMap put(final Class c) {
        final ClassMap classMap = new ClassMap(c, this.log);
        this.classMapCache.put(c, classMap);
        this.classNameCache.add(c.getName());
        final Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().triggerPut(c, classMap);
        }
        return classMap;
    }
    
    public void addListener(final IntrospectorCacheListener listener) {
        this.listeners.add(listener);
    }
    
    public void removeListener(final IntrospectorCacheListener listener) {
        this.listeners.remove(listener);
    }
}
