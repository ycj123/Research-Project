// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Collection;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableBuffer extends AbstractBufferDecorator implements Unmodifiable, Serializable
{
    private static final long serialVersionUID = 1832948656215393357L;
    
    public static Buffer decorate(final Buffer buffer) {
        if (buffer instanceof Unmodifiable) {
            return buffer;
        }
        return new UnmodifiableBuffer(buffer);
    }
    
    private UnmodifiableBuffer(final Buffer buffer) {
        super(buffer);
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(super.collection);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.collection = (Collection)in.readObject();
    }
    
    public Iterator iterator() {
        return UnmodifiableIterator.decorate(this.getCollection().iterator());
    }
    
    public boolean add(final Object object) {
        throw new UnsupportedOperationException();
    }
    
    public boolean addAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public Object remove() {
        throw new UnsupportedOperationException();
    }
}
