// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import org.mudebug.prapr.reloc.commons.collections.keyvalue.MultiKey;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.IterableMap;

public class MultiKeyMap implements IterableMap, Serializable
{
    private static final long serialVersionUID = -1788199231038721040L;
    protected final AbstractHashedMap map;
    
    public static MultiKeyMap decorate(final AbstractHashedMap map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        if (map.size() > 0) {
            throw new IllegalArgumentException("Map must be empty");
        }
        return new MultiKeyMap(map);
    }
    
    public MultiKeyMap() {
        this.map = new HashedMap();
    }
    
    protected MultiKeyMap(final AbstractHashedMap map) {
        this.map = map;
    }
    
    public Object get(final Object key1, final Object key2) {
        final int hashCode = this.hash(key1, key2);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    public boolean containsKey(final Object key1, final Object key2) {
        final int hashCode = this.hash(key1, key2);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                return true;
            }
        }
        return false;
    }
    
    public Object put(final Object key1, final Object key2, final Object value) {
        final int hashCode = this.hash(key1, key2);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        for (AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                final Object oldValue = entry.getValue();
                this.map.updateEntry(entry, value);
                return oldValue;
            }
        }
        this.map.addMapping(index, hashCode, new MultiKey(key1, key2), value);
        return null;
    }
    
    public Object remove(final Object key1, final Object key2) {
        final int hashCode = this.hash(key1, key2);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        AbstractHashedMap.HashEntry entry = this.map.data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                final Object oldValue = entry.getValue();
                this.map.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }
    
    protected int hash(final Object key1, final Object key2) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    protected boolean isEqualKey(final AbstractHashedMap.HashEntry entry, final Object key1, final Object key2) {
        final MultiKey multi = (MultiKey)entry.getKey();
        return multi.size() == 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1)));
    }
    
    public Object get(final Object key1, final Object key2, final Object key3) {
        final int hashCode = this.hash(key1, key2, key3);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    public boolean containsKey(final Object key1, final Object key2, final Object key3) {
        final int hashCode = this.hash(key1, key2, key3);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                return true;
            }
        }
        return false;
    }
    
    public Object put(final Object key1, final Object key2, final Object key3, final Object value) {
        final int hashCode = this.hash(key1, key2, key3);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        for (AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                final Object oldValue = entry.getValue();
                this.map.updateEntry(entry, value);
                return oldValue;
            }
        }
        this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3), value);
        return null;
    }
    
    public Object remove(final Object key1, final Object key2, final Object key3) {
        final int hashCode = this.hash(key1, key2, key3);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        AbstractHashedMap.HashEntry entry = this.map.data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                final Object oldValue = entry.getValue();
                this.map.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }
    
    protected int hash(final Object key1, final Object key2, final Object key3) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    protected boolean isEqualKey(final AbstractHashedMap.HashEntry entry, final Object key1, final Object key2, final Object key3) {
        final MultiKey multi = (MultiKey)entry.getKey();
        return multi.size() == 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2)));
    }
    
    public Object get(final Object key1, final Object key2, final Object key3, final Object key4) {
        final int hashCode = this.hash(key1, key2, key3, key4);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    public boolean containsKey(final Object key1, final Object key2, final Object key3, final Object key4) {
        final int hashCode = this.hash(key1, key2, key3, key4);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                return true;
            }
        }
        return false;
    }
    
    public Object put(final Object key1, final Object key2, final Object key3, final Object key4, final Object value) {
        final int hashCode = this.hash(key1, key2, key3, key4);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        for (AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                final Object oldValue = entry.getValue();
                this.map.updateEntry(entry, value);
                return oldValue;
            }
        }
        this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4), value);
        return null;
    }
    
    public Object remove(final Object key1, final Object key2, final Object key3, final Object key4) {
        final int hashCode = this.hash(key1, key2, key3, key4);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        AbstractHashedMap.HashEntry entry = this.map.data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                final Object oldValue = entry.getValue();
                this.map.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }
    
    protected int hash(final Object key1, final Object key2, final Object key3, final Object key4) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    protected boolean isEqualKey(final AbstractHashedMap.HashEntry entry, final Object key1, final Object key2, final Object key3, final Object key4) {
        final MultiKey multi = (MultiKey)entry.getKey();
        return multi.size() == 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3)));
    }
    
    public Object get(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        final int hashCode = this.hash(key1, key2, key3, key4, key5);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    public boolean containsKey(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        final int hashCode = this.hash(key1, key2, key3, key4, key5);
        for (AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                return true;
            }
        }
        return false;
    }
    
    public Object put(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5, final Object value) {
        final int hashCode = this.hash(key1, key2, key3, key4, key5);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        for (AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                final Object oldValue = entry.getValue();
                this.map.updateEntry(entry, value);
                return oldValue;
            }
        }
        this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4, key5), value);
        return null;
    }
    
    public Object remove(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        final int hashCode = this.hash(key1, key2, key3, key4, key5);
        final int index = this.map.hashIndex(hashCode, this.map.data.length);
        AbstractHashedMap.HashEntry entry = this.map.data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                final Object oldValue = entry.getValue();
                this.map.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }
    
    protected int hash(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        if (key5 != null) {
            h ^= key5.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    protected boolean isEqualKey(final AbstractHashedMap.HashEntry entry, final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        final MultiKey multi = (MultiKey)entry.getKey();
        return multi.size() == 5 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3))) && ((key5 == null) ? (multi.getKey(4) == null) : key5.equals(multi.getKey(4)));
    }
    
    public boolean removeAll(final Object key1) {
        boolean modified = false;
        final MapIterator it = this.mapIterator();
        while (it.hasNext()) {
            final MultiKey multi = (MultiKey)it.next();
            if (multi.size() >= 1 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0)))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    public boolean removeAll(final Object key1, final Object key2) {
        boolean modified = false;
        final MapIterator it = this.mapIterator();
        while (it.hasNext()) {
            final MultiKey multi = (MultiKey)it.next();
            if (multi.size() >= 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1)))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    public boolean removeAll(final Object key1, final Object key2, final Object key3) {
        boolean modified = false;
        final MapIterator it = this.mapIterator();
        while (it.hasNext()) {
            final MultiKey multi = (MultiKey)it.next();
            if (multi.size() >= 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2)))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    public boolean removeAll(final Object key1, final Object key2, final Object key3, final Object key4) {
        boolean modified = false;
        final MapIterator it = this.mapIterator();
        while (it.hasNext()) {
            final MultiKey multi = (MultiKey)it.next();
            if (multi.size() >= 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3)))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    protected void checkKey(final Object key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }
        if (!(key instanceof MultiKey)) {
            throw new ClassCastException("Key must be a MultiKey");
        }
    }
    
    public Object clone() {
        return new MultiKeyMap((AbstractHashedMap)this.map.clone());
    }
    
    public Object put(final Object key, final Object value) {
        this.checkKey(key);
        return this.map.put(key, value);
    }
    
    public void putAll(final Map mapToCopy) {
        for (final Object key : mapToCopy.keySet()) {
            this.checkKey(key);
        }
        this.map.putAll(mapToCopy);
    }
    
    public MapIterator mapIterator() {
        return this.map.mapIterator();
    }
    
    public int size() {
        return this.map.size();
    }
    
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    public boolean containsKey(final Object key) {
        return this.map.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        return this.map.containsValue(value);
    }
    
    public Object get(final Object key) {
        return this.map.get(key);
    }
    
    public Object remove(final Object key) {
        return this.map.remove(key);
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public Set keySet() {
        return this.map.keySet();
    }
    
    public Collection values() {
        return this.map.values();
    }
    
    public Set entrySet() {
        return this.map.entrySet();
    }
    
    public boolean equals(final Object obj) {
        return obj == this || this.map.equals(obj);
    }
    
    public int hashCode() {
        return this.map.hashCode();
    }
    
    public String toString() {
        return this.map.toString();
    }
}
