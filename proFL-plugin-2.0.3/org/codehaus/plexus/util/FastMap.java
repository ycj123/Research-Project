// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.util;

import java.util.AbstractSet;
import java.util.AbstractCollection;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Map;

public class FastMap implements Map, Cloneable, Serializable
{
    private transient EntryImpl[] _entries;
    private transient int _capacity;
    private transient int _mask;
    private transient EntryImpl _poolFirst;
    private transient EntryImpl _mapFirst;
    private transient EntryImpl _mapLast;
    private transient int _size;
    private transient Values _values;
    private transient EntrySet _entrySet;
    private transient KeySet _keySet;
    
    public FastMap() {
        this.initialize(256);
    }
    
    public FastMap(final Map map) {
        final int capacity = (map instanceof FastMap) ? ((FastMap)map).capacity() : map.size();
        this.initialize(capacity);
        this.putAll(map);
    }
    
    public FastMap(final int capacity) {
        this.initialize(capacity);
    }
    
    public int size() {
        return this._size;
    }
    
    public int capacity() {
        return this._capacity;
    }
    
    public boolean isEmpty() {
        return this._size == 0;
    }
    
    public boolean containsKey(final Object key) {
        for (EntryImpl entry = this._entries[keyHash(key) & this._mask]; entry != null; entry = entry._next) {
            if (key.equals(entry._key)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsValue(final Object value) {
        for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
            if (value.equals(entry._value)) {
                return true;
            }
        }
        return false;
    }
    
    public Object get(final Object key) {
        for (EntryImpl entry = this._entries[keyHash(key) & this._mask]; entry != null; entry = entry._next) {
            if (key.equals(entry._key)) {
                return entry._value;
            }
        }
        return null;
    }
    
    public Entry getEntry(final Object key) {
        for (EntryImpl entry = this._entries[keyHash(key) & this._mask]; entry != null; entry = entry._next) {
            if (key.equals(entry._key)) {
                return entry;
            }
        }
        return null;
    }
    
    public Object put(final Object key, final Object value) {
        for (EntryImpl entry = this._entries[keyHash(key) & this._mask]; entry != null; entry = entry._next) {
            if (key.equals(entry._key)) {
                final Object prevValue = entry._value;
                entry._value = value;
                return prevValue;
            }
        }
        this.addEntry(key, value);
        return null;
    }
    
    public void putAll(final Map map) {
        for (final Entry e : map.entrySet()) {
            this.addEntry(e.getKey(), e.getValue());
        }
    }
    
    public Object remove(final Object key) {
        for (EntryImpl entry = this._entries[keyHash(key) & this._mask]; entry != null; entry = entry._next) {
            if (key.equals(entry._key)) {
                final Object prevValue = entry._value;
                this.removeEntry(entry);
                return prevValue;
            }
        }
        return null;
    }
    
    public void clear() {
        for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
            entry._key = null;
            entry._value = null;
            entry._before = null;
            entry._next = null;
            if (entry._previous == null) {
                this._entries[entry._index] = null;
            }
            else {
                entry._previous = null;
            }
        }
        if (this._mapLast != null) {
            this._mapLast._after = this._poolFirst;
            this._poolFirst = this._mapFirst;
            this._mapFirst = null;
            this._mapLast = null;
            this._size = 0;
            this.sizeChanged();
        }
    }
    
    public void setCapacity(final int newCapacity) {
        if (newCapacity > this._capacity) {
            for (int i = this._capacity; i < newCapacity; ++i) {
                final EntryImpl entry = new EntryImpl();
                entry._after = this._poolFirst;
                this._poolFirst = entry;
            }
        }
        else if (newCapacity < this._capacity) {
            for (int i = newCapacity; i < this._capacity && this._poolFirst != null; ++i) {
                final EntryImpl entry = this._poolFirst;
                this._poolFirst = entry._after;
                entry._after = null;
            }
        }
        int tableLength;
        for (tableLength = 16; tableLength < newCapacity; tableLength <<= 1) {}
        if (this._entries.length != tableLength) {
            this._entries = new EntryImpl[tableLength];
            this._mask = tableLength - 1;
            for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
                final int index = keyHash(entry._key) & this._mask;
                entry._index = index;
                entry._previous = null;
                final EntryImpl next = this._entries[index];
                entry._next = next;
                if (next != null) {
                    next._previous = entry;
                }
                this._entries[index] = entry;
            }
        }
        this._capacity = newCapacity;
    }
    
    public Object clone() {
        try {
            final FastMap clone = (FastMap)super.clone();
            clone.initialize(this._capacity);
            clone.putAll(this);
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        final Map that = (Map)obj;
        if (this.size() == that.size()) {
            for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
                if (!that.entrySet().contains(entry)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        int code = 0;
        for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
            code += entry.hashCode();
        }
        return code;
    }
    
    public String toString() {
        return this.entrySet().toString();
    }
    
    public Collection values() {
        return this._values;
    }
    
    public Set entrySet() {
        return this._entrySet;
    }
    
    public Set keySet() {
        return this._keySet;
    }
    
    protected void sizeChanged() {
        if (this.size() > this.capacity()) {
            this.setCapacity(this.capacity() * 2);
        }
    }
    
    private static int keyHash(final Object key) {
        int hashCode = key.hashCode();
        hashCode += ~(hashCode << 9);
        hashCode ^= hashCode >>> 14;
        hashCode += hashCode << 4;
        hashCode ^= hashCode >>> 10;
        return hashCode;
    }
    
    private void addEntry(final Object key, final Object value) {
        EntryImpl entry = this._poolFirst;
        if (entry != null) {
            this._poolFirst = entry._after;
            entry._after = null;
        }
        else {
            entry = new EntryImpl();
        }
        entry._key = key;
        entry._value = value;
        final int index = keyHash(key) & this._mask;
        entry._index = index;
        final EntryImpl next = this._entries[index];
        entry._next = next;
        if (next != null) {
            next._previous = entry;
        }
        this._entries[index] = entry;
        if (this._mapLast != null) {
            entry._before = this._mapLast;
            this._mapLast._after = entry;
        }
        else {
            this._mapFirst = entry;
        }
        this._mapLast = entry;
        ++this._size;
        this.sizeChanged();
    }
    
    private void removeEntry(final EntryImpl entry) {
        final EntryImpl previous = entry._previous;
        final EntryImpl next = entry._next;
        if (previous != null) {
            previous._next = next;
            entry._previous = null;
        }
        else {
            this._entries[entry._index] = next;
        }
        if (next != null) {
            next._previous = previous;
            entry._next = null;
        }
        final EntryImpl before = entry._before;
        final EntryImpl after = entry._after;
        if (before != null) {
            before._after = after;
            entry._before = null;
        }
        else {
            this._mapFirst = after;
        }
        if (after != null) {
            after._before = before;
        }
        else {
            this._mapLast = before;
        }
        entry._key = null;
        entry._value = null;
        entry._after = this._poolFirst;
        this._poolFirst = entry;
        --this._size;
        this.sizeChanged();
    }
    
    private void initialize(final int capacity) {
        int tableLength;
        for (tableLength = 16; tableLength < capacity; tableLength <<= 1) {}
        this._entries = new EntryImpl[tableLength];
        this._mask = tableLength - 1;
        this._capacity = capacity;
        this._size = 0;
        this._values = new Values();
        this._entrySet = new EntrySet();
        this._keySet = new KeySet();
        this._poolFirst = null;
        this._mapFirst = null;
        this._mapLast = null;
        for (int i = 0; i < capacity; ++i) {
            final EntryImpl entry = new EntryImpl();
            entry._after = this._poolFirst;
            this._poolFirst = entry;
        }
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        final int capacity = stream.readInt();
        this.initialize(capacity);
        for (int size = stream.readInt(), i = 0; i < size; ++i) {
            final Object key = stream.readObject();
            final Object value = stream.readObject();
            this.addEntry(key, value);
        }
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.writeInt(this._capacity);
        stream.writeInt(this._size);
        int count = 0;
        for (EntryImpl entry = this._mapFirst; entry != null; entry = entry._after) {
            stream.writeObject(entry._key);
            stream.writeObject(entry._value);
            ++count;
        }
        if (count != this._size) {
            throw new IOException("FastMap Corrupted");
        }
    }
    
    private class Values extends AbstractCollection
    {
        public Iterator iterator() {
            return new Iterator() {
                EntryImpl after = FastMap.this._mapFirst;
                EntryImpl before;
                
                public void remove() {
                    FastMap.this.removeEntry(this.before);
                }
                
                public boolean hasNext() {
                    return this.after != null;
                }
                
                public Object next() {
                    this.before = this.after;
                    this.after = this.after._after;
                    return this.before._value;
                }
            };
        }
        
        public int size() {
            return FastMap.this._size;
        }
        
        public boolean contains(final Object o) {
            return FastMap.this.containsValue(o);
        }
        
        public void clear() {
            FastMap.this.clear();
        }
    }
    
    private class EntrySet extends AbstractSet
    {
        public Iterator iterator() {
            return new Iterator() {
                EntryImpl after = FastMap.this._mapFirst;
                EntryImpl before;
                
                public void remove() {
                    FastMap.this.removeEntry(this.before);
                }
                
                public boolean hasNext() {
                    return this.after != null;
                }
                
                public Object next() {
                    this.before = this.after;
                    this.after = this.after._after;
                    return this.before;
                }
            };
        }
        
        public int size() {
            return FastMap.this._size;
        }
        
        public boolean contains(final Object obj) {
            if (obj instanceof Entry) {
                final Entry entry = (Entry)obj;
                final Entry mapEntry = FastMap.this.getEntry(entry.getKey());
                return entry.equals(mapEntry);
            }
            return false;
        }
        
        public boolean remove(final Object obj) {
            if (obj instanceof Entry) {
                final Entry entry = (Entry)obj;
                final EntryImpl mapEntry = (EntryImpl)FastMap.this.getEntry(entry.getKey());
                if (mapEntry != null && entry.getValue().equals(mapEntry._value)) {
                    FastMap.this.removeEntry(mapEntry);
                    return true;
                }
            }
            return false;
        }
    }
    
    private class KeySet extends AbstractSet
    {
        public Iterator iterator() {
            return new Iterator() {
                EntryImpl after = FastMap.this._mapFirst;
                EntryImpl before;
                
                public void remove() {
                    FastMap.this.removeEntry(this.before);
                }
                
                public boolean hasNext() {
                    return this.after != null;
                }
                
                public Object next() {
                    this.before = this.after;
                    this.after = this.after._after;
                    return this.before._key;
                }
            };
        }
        
        public int size() {
            return FastMap.this._size;
        }
        
        public boolean contains(final Object obj) {
            return FastMap.this.containsKey(obj);
        }
        
        public boolean remove(final Object obj) {
            return FastMap.this.remove(obj) != null;
        }
        
        public void clear() {
            FastMap.this.clear();
        }
    }
    
    private static final class EntryImpl implements Entry
    {
        private Object _key;
        private Object _value;
        private int _index;
        private EntryImpl _previous;
        private EntryImpl _next;
        private EntryImpl _before;
        private EntryImpl _after;
        
        public Object getKey() {
            return this._key;
        }
        
        public Object getValue() {
            return this._value;
        }
        
        public Object setValue(final Object value) {
            final Object old = this._value;
            this._value = value;
            return old;
        }
        
        public boolean equals(final Object that) {
            if (that instanceof Entry) {
                final Entry entry = (Entry)that;
                return this._key.equals(entry.getKey()) && ((this._value == null) ? (entry.getValue() == null) : this._value.equals(entry.getValue()));
            }
            return false;
        }
        
        public int hashCode() {
            return this._key.hashCode() ^ ((this._value != null) ? this._value.hashCode() : 0);
        }
        
        public String toString() {
            return this._key + "=" + this._value;
        }
    }
}
