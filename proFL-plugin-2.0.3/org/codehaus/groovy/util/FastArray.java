// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.AbstractList;
import java.util.List;
import java.util.Collection;

public class FastArray implements Cloneable
{
    private Object[] data;
    public int size;
    public static final FastArray EMPTY_LIST;
    
    public FastArray(final int initialCapacity) {
        this.data = new Object[initialCapacity];
    }
    
    public FastArray() {
        this(8);
    }
    
    public FastArray(final Collection c) {
        this(c.toArray());
    }
    
    public FastArray(final Object[] objects) {
        this.data = objects;
        this.size = objects.length;
    }
    
    public Object get(final int index) {
        return this.data[index];
    }
    
    public void add(final Object o) {
        if (this.size == this.data.length) {
            final Object[] newData = new Object[(this.size == 0) ? 8 : (this.size * 2)];
            System.arraycopy(this.data, 0, newData, 0, this.size);
            this.data = newData;
        }
        this.data[this.size++] = o;
    }
    
    public void set(final int index, final Object o) {
        this.data[index] = o;
    }
    
    public int size() {
        return this.size;
    }
    
    public void clear() {
        this.data = new Object[this.data.length];
        this.size = 0;
    }
    
    public void addAll(final FastArray newData) {
        this.addAll(newData.data, newData.size);
    }
    
    public void addAll(final Object[] newData, final int size) {
        if (size == 0) {
            return;
        }
        final int newSize = this.size + size;
        if (newSize > this.data.length) {
            final Object[] nd = new Object[newSize];
            System.arraycopy(this.data, 0, nd, 0, this.size);
            this.data = nd;
        }
        System.arraycopy(newData, 0, this.data, this.size, size);
        this.size = newSize;
    }
    
    public FastArray copy() {
        final Object[] newData = new Object[this.size];
        System.arraycopy(this.data, 0, newData, 0, this.size);
        return new FastArray(newData);
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void addAll(final List coll) {
        final Object[] newData = coll.toArray();
        this.addAll(newData, newData.length);
    }
    
    public void remove(final int index) {
        final int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.data, index + 1, this.data, index, numMoved);
        }
        this.data[--this.size] = null;
    }
    
    public List toList() {
        return new AbstractList() {
            @Override
            public Object get(final int index) {
                return FastArray.this.get(index);
            }
            
            @Override
            public int size() {
                return FastArray.this.size;
            }
        };
    }
    
    public Object[] getArray() {
        return this.data;
    }
    
    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        return this.toList().toString();
    }
    
    static {
        EMPTY_LIST = new FastArray(0);
    }
}
