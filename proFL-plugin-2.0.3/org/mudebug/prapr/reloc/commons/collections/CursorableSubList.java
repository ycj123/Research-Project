// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class CursorableSubList extends CursorableLinkedList implements List
{
    protected CursorableLinkedList _list;
    protected Listable _pre;
    protected Listable _post;
    
    CursorableSubList(final CursorableLinkedList list, final int from, final int to) {
        this._list = null;
        this._pre = null;
        this._post = null;
        if (0 > from || list.size() < to) {
            throw new IndexOutOfBoundsException();
        }
        if (from > to) {
            throw new IllegalArgumentException();
        }
        this._list = list;
        if (from < list.size()) {
            super._head.setNext(this._list.getListableAt(from));
            this._pre = ((null == super._head.next()) ? null : super._head.next().prev());
        }
        else {
            this._pre = this._list.getListableAt(from - 1);
        }
        if (from == to) {
            super._head.setNext(null);
            super._head.setPrev(null);
            if (to < list.size()) {
                this._post = this._list.getListableAt(to);
            }
            else {
                this._post = null;
            }
        }
        else {
            super._head.setPrev(this._list.getListableAt(to - 1));
            this._post = super._head.prev().next();
        }
        super._size = to - from;
        super._modCount = this._list._modCount;
    }
    
    public void clear() {
        this.checkForComod();
        final Iterator it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
    
    public Iterator iterator() {
        this.checkForComod();
        return super.iterator();
    }
    
    public int size() {
        this.checkForComod();
        return super.size();
    }
    
    public boolean isEmpty() {
        this.checkForComod();
        return super.isEmpty();
    }
    
    public Object[] toArray() {
        this.checkForComod();
        return super.toArray();
    }
    
    public Object[] toArray(final Object[] a) {
        this.checkForComod();
        return super.toArray(a);
    }
    
    public boolean contains(final Object o) {
        this.checkForComod();
        return super.contains(o);
    }
    
    public boolean remove(final Object o) {
        this.checkForComod();
        return super.remove(o);
    }
    
    public Object removeFirst() {
        this.checkForComod();
        return super.removeFirst();
    }
    
    public Object removeLast() {
        this.checkForComod();
        return super.removeLast();
    }
    
    public boolean addAll(final Collection c) {
        this.checkForComod();
        return super.addAll(c);
    }
    
    public boolean add(final Object o) {
        this.checkForComod();
        return super.add(o);
    }
    
    public boolean addFirst(final Object o) {
        this.checkForComod();
        return super.addFirst(o);
    }
    
    public boolean addLast(final Object o) {
        this.checkForComod();
        return super.addLast(o);
    }
    
    public boolean removeAll(final Collection c) {
        this.checkForComod();
        return super.removeAll(c);
    }
    
    public boolean containsAll(final Collection c) {
        this.checkForComod();
        return super.containsAll(c);
    }
    
    public boolean addAll(final int index, final Collection c) {
        this.checkForComod();
        return super.addAll(index, c);
    }
    
    public int hashCode() {
        this.checkForComod();
        return super.hashCode();
    }
    
    public boolean retainAll(final Collection c) {
        this.checkForComod();
        return super.retainAll(c);
    }
    
    public Object set(final int index, final Object element) {
        this.checkForComod();
        return super.set(index, element);
    }
    
    public boolean equals(final Object o) {
        this.checkForComod();
        return super.equals(o);
    }
    
    public Object get(final int index) {
        this.checkForComod();
        return super.get(index);
    }
    
    public Object getFirst() {
        this.checkForComod();
        return super.getFirst();
    }
    
    public Object getLast() {
        this.checkForComod();
        return super.getLast();
    }
    
    public void add(final int index, final Object element) {
        this.checkForComod();
        super.add(index, element);
    }
    
    public ListIterator listIterator(final int index) {
        this.checkForComod();
        return super.listIterator(index);
    }
    
    public Object remove(final int index) {
        this.checkForComod();
        return super.remove(index);
    }
    
    public int indexOf(final Object o) {
        this.checkForComod();
        return super.indexOf(o);
    }
    
    public int lastIndexOf(final Object o) {
        this.checkForComod();
        return super.lastIndexOf(o);
    }
    
    public ListIterator listIterator() {
        this.checkForComod();
        return super.listIterator();
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        this.checkForComod();
        return super.subList(fromIndex, toIndex);
    }
    
    protected Listable insertListable(final Listable before, final Listable after, final Object value) {
        ++super._modCount;
        ++super._size;
        final Listable elt = this._list.insertListable((null == before) ? this._pre : before, (null == after) ? this._post : after, value);
        if (null == super._head.next()) {
            super._head.setNext(elt);
            super._head.setPrev(elt);
        }
        if (before == super._head.prev()) {
            super._head.setPrev(elt);
        }
        if (after == super._head.next()) {
            super._head.setNext(elt);
        }
        this.broadcastListableInserted(elt);
        return elt;
    }
    
    protected void removeListable(final Listable elt) {
        ++super._modCount;
        --super._size;
        if (super._head.next() == elt && super._head.prev() == elt) {
            super._head.setNext(null);
            super._head.setPrev(null);
        }
        if (super._head.next() == elt) {
            super._head.setNext(elt.next());
        }
        if (super._head.prev() == elt) {
            super._head.setPrev(elt.prev());
        }
        this._list.removeListable(elt);
        this.broadcastListableRemoved(elt);
    }
    
    protected void checkForComod() throws ConcurrentModificationException {
        if (super._modCount != this._list._modCount) {
            throw new ConcurrentModificationException();
        }
    }
}
