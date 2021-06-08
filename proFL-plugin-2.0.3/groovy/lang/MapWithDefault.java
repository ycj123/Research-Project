// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.Collection;
import java.util.Set;
import java.util.Map;

public final class MapWithDefault<K, V> implements Map<K, V>
{
    private final Map<K, V> delegate;
    private final Closure initClosure;
    
    private MapWithDefault(final Map<K, V> m, final Closure initClosure) {
        this.delegate = m;
        this.initClosure = initClosure;
    }
    
    public static <K, V> Map<K, V> newInstance(final Map<K, V> m, final Closure initClosure) {
        return new MapWithDefault<K, V>(m, initClosure);
    }
    
    public int size() {
        return this.delegate.size();
    }
    
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }
    
    public boolean containsKey(final Object key) {
        return this.delegate.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        return this.delegate.containsValue(value);
    }
    
    public V get(final Object key) {
        if (!this.delegate.containsKey(key)) {
            this.delegate.put((K)key, (V)this.initClosure.call(new Object[] { key }));
        }
        return this.delegate.get(key);
    }
    
    public V put(final K key, final V value) {
        return this.delegate.put(key, value);
    }
    
    public V remove(final Object key) {
        return this.delegate.remove(key);
    }
    
    public void putAll(final Map<? extends K, ? extends V> m) {
        this.delegate.putAll(m);
    }
    
    public void clear() {
        this.delegate.clear();
    }
    
    public Set<K> keySet() {
        return this.delegate.keySet();
    }
    
    public Collection<V> values() {
        return this.delegate.values();
    }
    
    public Set<Entry<K, V>> entrySet() {
        return this.delegate.entrySet();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.delegate.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }
}
