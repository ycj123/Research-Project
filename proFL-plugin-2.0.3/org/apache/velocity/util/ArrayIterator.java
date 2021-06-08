// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayIterator implements Iterator
{
    private Object array;
    private int pos;
    private int size;
    
    public ArrayIterator(final Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Programmer error : internal ArrayIterator invoked w/o array");
        }
        this.array = array;
        this.pos = 0;
        this.size = Array.getLength(this.array);
    }
    
    public Object next() {
        if (this.pos < this.size) {
            return Array.get(this.array, this.pos++);
        }
        throw new NoSuchElementException("No more elements: " + this.pos + " / " + this.size);
    }
    
    public boolean hasNext() {
        return this.pos < this.size;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
