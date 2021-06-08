// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import java.util.NoSuchElementException;
import org.mudebug.prapr.reloc.commons.collections.ResettableListIterator;
import java.util.ListIterator;

public class ObjectArrayListIterator extends ObjectArrayIterator implements ListIterator, ResettableListIterator
{
    protected int lastItemIndex;
    
    public ObjectArrayListIterator() {
        this.lastItemIndex = -1;
    }
    
    public ObjectArrayListIterator(final Object[] array) {
        super(array);
        this.lastItemIndex = -1;
    }
    
    public ObjectArrayListIterator(final Object[] array, final int start) {
        super(array, start);
        this.lastItemIndex = -1;
    }
    
    public ObjectArrayListIterator(final Object[] array, final int start, final int end) {
        super(array, start, end);
        this.lastItemIndex = -1;
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
        return super.array[super.index];
    }
    
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.lastItemIndex = super.index;
        return super.array[super.index++];
    }
    
    public int nextIndex() {
        return super.index - super.startIndex;
    }
    
    public int previousIndex() {
        return super.index - super.startIndex - 1;
    }
    
    public void add(final Object obj) {
        throw new UnsupportedOperationException("add() method is not supported");
    }
    
    public void set(final Object obj) {
        if (this.lastItemIndex == -1) {
            throw new IllegalStateException("must call next() or previous() before a call to set()");
        }
        super.array[this.lastItemIndex] = obj;
    }
    
    public void reset() {
        super.reset();
        this.lastItemIndex = -1;
    }
}
