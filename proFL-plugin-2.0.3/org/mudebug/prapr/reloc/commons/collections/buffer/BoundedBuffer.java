// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractIteratorDecorator;
import org.mudebug.prapr.reloc.commons.collections.BufferUnderflowException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.mudebug.prapr.reloc.commons.collections.BufferOverflowException;
import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import org.mudebug.prapr.reloc.commons.collections.BoundedCollection;

public class BoundedBuffer extends SynchronizedBuffer implements BoundedCollection
{
    private static final long serialVersionUID = 1536432911093974264L;
    private final int maximumSize;
    private final long timeout;
    
    public static BoundedBuffer decorate(final Buffer buffer, final int maximumSize) {
        return new BoundedBuffer(buffer, maximumSize, 0L);
    }
    
    public static BoundedBuffer decorate(final Buffer buffer, final int maximumSize, final long timeout) {
        return new BoundedBuffer(buffer, maximumSize, timeout);
    }
    
    protected BoundedBuffer(final Buffer buffer, final int maximumSize, final long timeout) {
        super(buffer);
        if (maximumSize < 1) {
            throw new IllegalArgumentException();
        }
        this.maximumSize = maximumSize;
        this.timeout = timeout;
    }
    
    public Object remove() {
        synchronized (super.lock) {
            final Object returnValue = this.getBuffer().remove();
            super.lock.notifyAll();
            return returnValue;
        }
    }
    
    public boolean add(final Object o) {
        synchronized (super.lock) {
            this.timeoutWait(1);
            return this.getBuffer().add(o);
        }
    }
    
    public boolean addAll(final Collection c) {
        synchronized (super.lock) {
            this.timeoutWait(c.size());
            return this.getBuffer().addAll(c);
        }
    }
    
    public Iterator iterator() {
        return new NotifyingIterator(super.collection.iterator());
    }
    
    private void timeoutWait(final int nAdditions) {
        if (nAdditions > this.maximumSize) {
            throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize);
        }
        if (this.timeout <= 0L) {
            if (this.getBuffer().size() + nAdditions > this.maximumSize) {
                throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize);
            }
        }
        else {
            final long expiration = System.currentTimeMillis() + this.timeout;
            long timeLeft = expiration - System.currentTimeMillis();
            while (timeLeft > 0L && this.getBuffer().size() + nAdditions > this.maximumSize) {
                try {
                    super.lock.wait(timeLeft);
                    timeLeft = expiration - System.currentTimeMillis();
                }
                catch (InterruptedException ex) {
                    final PrintWriter out = new PrintWriter(new StringWriter());
                    ex.printStackTrace(out);
                    throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
                }
            }
            if (this.getBuffer().size() + nAdditions > this.maximumSize) {
                throw new BufferOverflowException("Timeout expired");
            }
        }
    }
    
    public boolean isFull() {
        return this.size() == this.maxSize();
    }
    
    public int maxSize() {
        return this.maximumSize;
    }
    
    private class NotifyingIterator extends AbstractIteratorDecorator
    {
        public NotifyingIterator(final Iterator it) {
            super(it);
        }
        
        public void remove() {
            synchronized (BoundedBuffer.this.lock) {
                super.iterator.remove();
                BoundedBuffer.this.lock.notifyAll();
            }
        }
    }
}
