// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import java.util.Iterator;
import java.util.Collections;
import org.mudebug.prapr.reloc.commons.collections.map.LRUMap;
import java.util.Hashtable;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.Map;

public class ResourceCacheImpl implements ResourceCache
{
    protected Map cache;
    protected RuntimeServices rsvc;
    
    public ResourceCacheImpl() {
        this.cache = new Hashtable();
        this.rsvc = null;
    }
    
    public void initialize(final RuntimeServices rs) {
        this.rsvc = rs;
        final int maxSize = this.rsvc.getInt("resource.manager.defaultcache.size", 89);
        if (maxSize > 0) {
            final Map lruCache = Collections.synchronizedMap((Map<Object, Object>)new LRUMap(maxSize));
            lruCache.putAll(this.cache);
            this.cache = lruCache;
        }
        this.rsvc.getLog().debug("ResourceCache: initialized (" + this.getClass() + ')');
    }
    
    public Resource get(final Object key) {
        return this.cache.get(key);
    }
    
    public Resource put(final Object key, final Resource value) {
        return this.cache.put(key, value);
    }
    
    public Resource remove(final Object key) {
        return this.cache.remove(key);
    }
    
    public Iterator enumerateKeys() {
        return this.cache.keySet().iterator();
    }
}
