// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.BoundedMap;

public class LRUMap extends AbstractLinkedMap implements BoundedMap, Serializable, Cloneable
{
    private static final long serialVersionUID = -612114643488955218L;
    protected static final int DEFAULT_MAX_SIZE = 100;
    private transient int maxSize;
    private boolean scanUntilRemovable;
    
    public LRUMap() {
        this(100, 0.75f, false);
    }
    
    public LRUMap(final int maxSize) {
        this(maxSize, 0.75f);
    }
    
    public LRUMap(final int maxSize, final boolean scanUntilRemovable) {
        this(maxSize, 0.75f, scanUntilRemovable);
    }
    
    public LRUMap(final int maxSize, final float loadFactor) {
        this(maxSize, loadFactor, false);
    }
    
    public LRUMap(final int maxSize, final float loadFactor, final boolean scanUntilRemovable) {
        super((maxSize < 1) ? 16 : maxSize, loadFactor);
        if (maxSize < 1) {
            throw new IllegalArgumentException("LRUMap max size must be greater than 0");
        }
        this.maxSize = maxSize;
        this.scanUntilRemovable = scanUntilRemovable;
    }
    
    public LRUMap(final Map map) {
        this(map, false);
    }
    
    public LRUMap(final Map map, final boolean scanUntilRemovable) {
        this(map.size(), 0.75f, scanUntilRemovable);
        this.putAll(map);
    }
    
    public Object get(final Object key) {
        final LinkEntry entry = (LinkEntry)this.getEntry(key);
        if (entry == null) {
            return null;
        }
        this.moveToMRU(entry);
        return entry.getValue();
    }
    
    protected void moveToMRU(final LinkEntry entry) {
        if (entry.after != super.header) {
            ++super.modCount;
            entry.before.after = entry.after;
            entry.after.before = entry.before;
            entry.after = super.header;
            entry.before = super.header.before;
            super.header.before.after = entry;
            super.header.before = entry;
        }
        else if (entry == super.header) {
            throw new IllegalStateException("Can't move header to MRU (please report this to commons-dev@jakarta.apache.org)");
        }
    }
    
    protected void updateEntry(final HashEntry entry, final Object newValue) {
        this.moveToMRU((LinkEntry)entry);
        entry.setValue(newValue);
    }
    
    protected void addMapping(final int hashIndex, final int hashCode, final Object key, final Object value) {
        if (this.isFull()) {
            LinkEntry reuse = super.header.after;
            boolean removeLRUEntry = false;
            if (this.scanUntilRemovable) {
                while (reuse != super.header && reuse != null) {
                    if (this.removeLRU(reuse)) {
                        removeLRUEntry = true;
                        break;
                    }
                    reuse = reuse.after;
                }
                if (reuse == null) {
                    throw new IllegalStateException("Entry.after=null, header.after" + super.header.after + " header.before" + super.header.before + " key=" + key + " value=" + value + " size=" + super.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug.");
                }
            }
            else {
                removeLRUEntry = this.removeLRU(reuse);
            }
            if (removeLRUEntry) {
                if (reuse == null) {
                    throw new IllegalStateException("reuse=null, header.after=" + super.header.after + " header.before" + super.header.before + " key=" + key + " value=" + value + " size=" + super.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug.");
                }
                this.reuseMapping(reuse, hashIndex, hashCode, key, value);
            }
            else {
                super.addMapping(hashIndex, hashCode, key, value);
            }
        }
        else {
            super.addMapping(hashIndex, hashCode, key, value);
        }
    }
    
    protected void reuseMapping(final LinkEntry entry, final int hashIndex, final int hashCode, final Object key, final Object value) {
        try {
            final int removeIndex = this.hashIndex(entry.hashCode, super.data.length);
            final HashEntry[] tmp = super.data;
            HashEntry loop = tmp[removeIndex];
            HashEntry previous = null;
            while (loop != entry && loop != null) {
                previous = loop;
                loop = loop.next;
            }
            if (loop == null) {
                throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + super.data[removeIndex] + " previous=" + previous + " key=" + key + " value=" + value + " size=" + super.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug.");
            }
            ++super.modCount;
            this.removeEntry(entry, removeIndex, previous);
            this.reuseEntry(entry, hashIndex, hashCode, key, value);
            this.addEntry(entry, hashIndex);
        }
        catch (NullPointerException ex) {
            throw new IllegalStateException("NPE, entry=" + entry + " entryIsHeader=" + (entry == super.header) + " key=" + key + " value=" + value + " size=" + super.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug.");
        }
    }
    
    protected boolean removeLRU(final LinkEntry entry) {
        return true;
    }
    
    public boolean isFull() {
        return super.size >= this.maxSize;
    }
    
    public int maxSize() {
        return this.maxSize;
    }
    
    public boolean isScanUntilRemovable() {
        return this.scanUntilRemovable;
    }
    
    public Object clone() {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        this.doWriteObject(out);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.doReadObject(in);
    }
    
    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        out.writeInt(this.maxSize);
        super.doWriteObject(out);
    }
    
    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.maxSize = in.readInt();
        super.doReadObject(in);
    }
}
