// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import java.util.ConcurrentModificationException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class CursorableLinkedList extends AbstractLinkedList implements Serializable
{
    private static final long serialVersionUID = 8836393098519411393L;
    protected transient List cursors;
    
    public CursorableLinkedList() {
        this.cursors = new ArrayList();
        this.init();
    }
    
    public CursorableLinkedList(final Collection coll) {
        super(coll);
        this.cursors = new ArrayList();
    }
    
    protected void init() {
        super.init();
        this.cursors = new ArrayList();
    }
    
    public Iterator iterator() {
        return super.listIterator(0);
    }
    
    public ListIterator listIterator() {
        return this.cursor(0);
    }
    
    public ListIterator listIterator(final int fromIndex) {
        return this.cursor(fromIndex);
    }
    
    public Cursor cursor() {
        return this.cursor(0);
    }
    
    public Cursor cursor(final int fromIndex) {
        final Cursor cursor = new Cursor(this, fromIndex);
        this.registerCursor(cursor);
        return cursor;
    }
    
    protected void updateNode(final Node node, final Object value) {
        super.updateNode(node, value);
        this.broadcastNodeChanged(node);
    }
    
    protected void addNode(final Node nodeToInsert, final Node insertBeforeNode) {
        super.addNode(nodeToInsert, insertBeforeNode);
        this.broadcastNodeInserted(nodeToInsert);
    }
    
    protected void removeNode(final Node node) {
        super.removeNode(node);
        this.broadcastNodeRemoved(node);
    }
    
    protected void removeAllNodes() {
        if (this.size() > 0) {
            final Iterator it = this.iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
    }
    
    protected void registerCursor(final Cursor cursor) {
        final Iterator it = this.cursors.iterator();
        while (it.hasNext()) {
            final WeakReference ref = it.next();
            if (ref.get() == null) {
                it.remove();
            }
        }
        this.cursors.add(new WeakReference<Cursor>(cursor));
    }
    
    protected void unregisterCursor(final Cursor cursor) {
        final Iterator it = this.cursors.iterator();
        while (it.hasNext()) {
            final WeakReference ref = it.next();
            final Cursor cur = (Cursor)ref.get();
            if (cur == null) {
                it.remove();
            }
            else {
                if (cur == cursor) {
                    ref.clear();
                    it.remove();
                    break;
                }
                continue;
            }
        }
    }
    
    protected void broadcastNodeChanged(final Node node) {
        final Iterator it = this.cursors.iterator();
        while (it.hasNext()) {
            final WeakReference ref = it.next();
            final Cursor cursor = (Cursor)ref.get();
            if (cursor == null) {
                it.remove();
            }
            else {
                cursor.nodeChanged(node);
            }
        }
    }
    
    protected void broadcastNodeRemoved(final Node node) {
        final Iterator it = this.cursors.iterator();
        while (it.hasNext()) {
            final WeakReference ref = it.next();
            final Cursor cursor = (Cursor)ref.get();
            if (cursor == null) {
                it.remove();
            }
            else {
                cursor.nodeRemoved(node);
            }
        }
    }
    
    protected void broadcastNodeInserted(final Node node) {
        final Iterator it = this.cursors.iterator();
        while (it.hasNext()) {
            final WeakReference ref = it.next();
            final Cursor cursor = (Cursor)ref.get();
            if (cursor == null) {
                it.remove();
            }
            else {
                cursor.nodeInserted(node);
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        this.doWriteObject(out);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.doReadObject(in);
    }
    
    protected ListIterator createSubListListIterator(final LinkedSubList subList, final int fromIndex) {
        final SubCursor cursor = new SubCursor(subList, fromIndex);
        this.registerCursor(cursor);
        return cursor;
    }
    
    public static class Cursor extends LinkedListIterator
    {
        boolean valid;
        boolean nextIndexValid;
        boolean currentRemovedByAnother;
        
        protected Cursor(final CursorableLinkedList parent, final int index) {
            super(parent, index);
            this.valid = true;
            this.nextIndexValid = true;
            this.currentRemovedByAnother = false;
            this.valid = true;
        }
        
        public void remove() {
            if (super.current != null || !this.currentRemovedByAnother) {
                this.checkModCount();
                super.parent.removeNode(this.getLastNodeReturned());
            }
            this.currentRemovedByAnother = false;
        }
        
        public void add(final Object obj) {
            super.add(obj);
            super.next = super.next.next;
        }
        
        public int nextIndex() {
            if (!this.nextIndexValid) {
                if (super.next == super.parent.header) {
                    super.nextIndex = super.parent.size();
                }
                else {
                    int pos = 0;
                    for (Node temp = super.parent.header.next; temp != super.next; temp = temp.next) {
                        ++pos;
                    }
                    super.nextIndex = pos;
                }
                this.nextIndexValid = true;
            }
            return super.nextIndex;
        }
        
        protected void nodeChanged(final Node node) {
        }
        
        protected void nodeRemoved(final Node node) {
            if (node == super.next && node == super.current) {
                super.next = node.next;
                super.current = null;
                this.currentRemovedByAnother = true;
            }
            else if (node == super.next) {
                super.next = node.next;
                this.currentRemovedByAnother = false;
            }
            else if (node == super.current) {
                super.current = null;
                this.currentRemovedByAnother = true;
                --super.nextIndex;
            }
            else {
                this.nextIndexValid = false;
                this.currentRemovedByAnother = false;
            }
        }
        
        protected void nodeInserted(final Node node) {
            if (node.previous == super.current) {
                super.next = node;
            }
            else if (super.next.previous == node) {
                super.next = node;
            }
            else {
                this.nextIndexValid = false;
            }
        }
        
        protected void checkModCount() {
            if (!this.valid) {
                throw new ConcurrentModificationException("Cursor closed");
            }
        }
        
        public void close() {
            if (this.valid) {
                ((CursorableLinkedList)super.parent).unregisterCursor(this);
                this.valid = false;
            }
        }
    }
    
    protected static class SubCursor extends Cursor
    {
        protected final LinkedSubList sub;
        
        protected SubCursor(final LinkedSubList sub, final int index) {
            super((CursorableLinkedList)sub.parent, index + sub.offset);
            this.sub = sub;
        }
        
        public boolean hasNext() {
            return this.nextIndex() < this.sub.size;
        }
        
        public boolean hasPrevious() {
            return this.previousIndex() >= 0;
        }
        
        public int nextIndex() {
            return super.nextIndex() - this.sub.offset;
        }
        
        public void add(final Object obj) {
            super.add(obj);
            this.sub.expectedModCount = super.parent.modCount;
            final LinkedSubList sub = this.sub;
            ++sub.size;
        }
        
        public void remove() {
            super.remove();
            this.sub.expectedModCount = super.parent.modCount;
            final LinkedSubList sub = this.sub;
            --sub.size;
        }
    }
}
