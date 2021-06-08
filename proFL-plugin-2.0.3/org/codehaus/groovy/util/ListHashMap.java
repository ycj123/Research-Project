// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class ListHashMap<K, V> implements Map<K, V>
{
    private int listFill;
    private Object[] listKeys;
    private Object[] listValues;
    private int size;
    private Map<K, V> innerMap;
    private final int maxListFill;
    
    public ListHashMap() {
        this(3);
    }
    
    public ListHashMap(final int listSize) {
        this.listFill = 0;
        this.size = 0;
        this.listKeys = new Object[listSize];
        this.listValues = new Object[listSize];
        this.maxListFill = listSize;
    }
    
    public void clear() {
        this.listFill = 0;
        this.innerMap = null;
        for (int i = 0; i < this.maxListFill; ++i) {
            this.listValues[i] = null;
            this.listKeys[i] = null;
        }
    }
    
    public boolean containsKey(final Object key) {
        if (this.size < this.maxListFill) {
            for (int i = 0; i < this.listFill; ++i) {
                if (this.listKeys[i].equals(key)) {
                    return true;
                }
            }
            return false;
        }
        return this.innerMap.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        if (this.size < this.maxListFill) {
            for (int i = 0; i < this.listFill; ++i) {
                if (this.listValues[i].equals(value)) {
                    return true;
                }
            }
            return false;
        }
        return this.innerMap.containsValue(value);
    }
    
    private Map<K, V> makeMap() {
        final Map<K, V> m = new HashMap<K, V>();
        for (int i = 0; i < this.size; ++i) {
            m.put((K)this.listKeys[i], (V)this.listValues[i]);
        }
        return m;
    }
    
    public Set<Entry<K, V>> entrySet() {
        Map m;
        if (this.size > this.maxListFill) {
            m = this.innerMap;
        }
        else {
            m = this.makeMap();
        }
        return m.entrySet();
    }
    
    public V get(final Object key) {
        if (this.size == 0) {
            return null;
        }
        if (this.size < this.maxListFill) {
            for (int i = 0; i < this.maxListFill; ++i) {
                if (this.listKeys[i].equals(key)) {
                    return (V)this.listValues[i];
                }
            }
            return null;
        }
        return this.innerMap.get(key);
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public Set<K> keySet() {
        Map m;
        if (this.size >= this.maxListFill) {
            m = this.innerMap;
        }
        else {
            m = this.makeMap();
        }
        return m.keySet();
    }
    
    public V put(final K key, final V value) {
        if (this.size < this.maxListFill) {
            for (int i = 0; i < this.listFill; ++i) {
                if (this.listKeys[i].equals(key)) {
                    final V old = (V)this.listValues[i];
                    this.listValues[i] = value;
                    return old;
                }
            }
            if (this.size < this.maxListFill - 1) {
                ++this.size;
                this.listKeys[this.listFill] = key;
                this.listValues[this.listFill] = value;
                ++this.listFill;
                return null;
            }
            this.innerMap = this.makeMap();
        }
        return this.innerMap.put(key, value);
    }
    
    public void putAll(final Map<? extends K, ? extends V> m) {
        if (this.size + m.size() < this.maxListFill) {
            for (final Entry<? extends K, ? extends V> entry : m.entrySet()) {
                this.listKeys[this.listFill] = entry.getKey();
                this.listValues[this.listFill] = entry.getValue();
                ++this.listFill;
            }
            this.size += m.size();
            return;
        }
        if (this.size < this.maxListFill) {
            this.innerMap = this.makeMap();
        }
        this.innerMap.putAll(m);
        this.size += m.size();
    }
    
    public V remove(final Object key) {
        if (this.size < this.maxListFill) {
            for (int i = 0; i < this.listFill; ++i) {
                if (this.listKeys[i].equals(key)) {
                    final V old = (V)this.listValues[i];
                    --this.listFill;
                    --this.size;
                    this.listValues[i] = this.listValues[this.listFill];
                    this.listKeys[i] = this.listValues[this.listFill];
                    return old;
                }
            }
            return null;
        }
        final V old2 = this.innerMap.remove(key);
        if (old2 != null) {
            --this.size;
        }
        if (this.size < this.maxListFill) {
            this.mapToList();
        }
        return old2;
    }
    
    private void mapToList() {
        int i = 0;
        for (final Entry<? extends K, ? extends V> entry : this.innerMap.entrySet()) {
            this.listKeys[i] = entry.getKey();
            this.listValues[i] = entry.getValue();
            ++i;
        }
        this.listFill = this.innerMap.size();
        this.innerMap = null;
    }
    
    public int size() {
        return this.size;
    }
    
    public Collection<V> values() {
        if (this.size < this.maxListFill) {
            final ArrayList<V> list = new ArrayList<V>(this.size);
            for (int i = 0; i < this.listFill; ++i) {
                list.add((V)this.listValues[i]);
            }
            return list;
        }
        return this.innerMap.values();
    }
}
