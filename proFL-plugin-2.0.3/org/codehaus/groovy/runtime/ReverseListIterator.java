// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

public class ReverseListIterator<T> implements Iterator<T>
{
    private ListIterator<T> delegate;
    
    public ReverseListIterator(final List<T> list) {
        this.delegate = list.listIterator(list.size());
    }
    
    public boolean hasNext() {
        return this.delegate.hasPrevious();
    }
    
    public T next() {
        return this.delegate.previous();
    }
    
    public void remove() {
        this.delegate.remove();
    }
}
