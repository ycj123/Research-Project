// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ManagedLinkedList<T>
{
    private Element<T> tail;
    private Element<T> head;
    private ReferenceBundle bundle;
    
    public ManagedLinkedList(final ReferenceBundle bundle) {
        this.bundle = bundle;
    }
    
    public void add(final T value) {
        final Element<T> element = new Element<T>(this.bundle, value);
        element.previous = this.tail;
        if (this.tail != null) {
            this.tail.next = element;
        }
        this.tail = element;
        if (this.head == null) {
            this.head = element;
        }
    }
    
    public Iterator<T> iterator() {
        return new Iter();
    }
    
    public T[] toArray(final T[] tArray) {
        final List<T> array = new ArrayList<T>(100);
        for (final T val : this) {
            if (val != null) {
                array.add(val);
            }
        }
        return array.toArray(tArray);
    }
    
    public boolean isEmpty() {
        return this.head == null;
    }
    
    private final class Element<V> extends ManagedReference<V>
    {
        Element next;
        Element previous;
        
        public Element(final ReferenceBundle bundle, final V value) {
            super(bundle, value);
        }
        
        @Override
        public void finalizeReference() {
            if (this.previous != null && this.previous.next != null) {
                this.previous.next = this.next;
            }
            if (this.next != null && this.next.previous != null) {
                this.next.previous = this.previous;
            }
            if (this == ManagedLinkedList.this.head) {
                ManagedLinkedList.this.head = (Element<T>)this.next;
            }
            this.next = null;
            if (this == ManagedLinkedList.this.tail) {
                ManagedLinkedList.this.tail = (Element<T>)this.previous;
            }
            this.previous = null;
            super.finalizeReference();
        }
    }
    
    private final class Iter implements Iterator<T>
    {
        private Element<T> current;
        private boolean currentHandled;
        
        Iter() {
            this.currentHandled = false;
            this.current = ManagedLinkedList.this.head;
        }
        
        public boolean hasNext() {
            if (this.current == null) {
                return false;
            }
            if (this.currentHandled) {
                return this.current.next != null;
            }
            return this.current != null;
        }
        
        public T next() {
            if (this.currentHandled) {
                this.current = this.current.next;
            }
            this.currentHandled = true;
            if (this.current == null) {
                return null;
            }
            return this.current.get();
        }
        
        public void remove() {
            if (this.current != null) {
                this.current.finalizeReference();
            }
        }
    }
}
