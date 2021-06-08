// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.mudebug.prapr.reloc.commons.collections.ResettableListIterator;
import java.util.ListIterator;

public class ArrayListIterator extends ArrayIterator implements ListIterator, ResettableListIterator
{
    protected int lastItemIndex;
    
    public ArrayListIterator() {
        this.lastItemIndex = -1;
    }
    
    public ArrayListIterator(final Object array) {
        super(array);
        this.lastItemIndex = -1;
    }
    
    public ArrayListIterator(final Object array, final int startIndex) {
        super(array, startIndex);
        this.lastItemIndex = -1;
        super.startIndex = startIndex;
    }
    
    public ArrayListIterator(final Object array, final int startIndex, final int endIndex) {
        super(array, startIndex, endIndex);
        this.lastItemIndex = -1;
        super.startIndex = startIndex;
    }
    
    public boolean hasPrevious() {
        return super.index > super.startIndex;
    }
    
    public Object previous() {
        if (!this.hasPrevious()) {
            throw new NoSuchElementException();
        }
        final int n = super.index - 1;
        super.index = n;
        this.lastItemIndex = n;
        return Array.get(super.array, super.index);
    }
    
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.lastItemIndex = super.index;
        return Array.get(super.array, super.index++);
    }
    
    public int nextIndex() {
        return super.index - super.startIndex;
    }
    
    public int previousIndex() {
        return super.index - super.startIndex - 1;
    }
    
    public void add(final Object o) {
        throw new UnsupportedOperationException("add() method is not supported");
    }
    
    public void set(final Object o) {
        if (this.lastItemIndex == -1) {
            throw new IllegalStateException("must call next() or previous() before a call to set()");
        }
        Array.set(super.array, this.lastItemIndex, o);
    }
    
    public void reset() {
        super.reset();
        this.lastItemIndex = -1;
    }
}
