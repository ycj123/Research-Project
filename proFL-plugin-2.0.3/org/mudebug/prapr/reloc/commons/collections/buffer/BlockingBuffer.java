// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import org.mudebug.prapr.reloc.commons.collections.BufferUnderflowException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Buffer;

public class BlockingBuffer extends SynchronizedBuffer
{
    private static final long serialVersionUID = 1719328905017860541L;
    private final long timeout;
    
    public static Buffer decorate(final Buffer buffer) {
        return new BlockingBuffer(buffer);
    }
    
    public static Buffer decorate(final Buffer buffer, final long timeoutMillis) {
        return new BlockingBuffer(buffer, timeoutMillis);
    }
    
    protected BlockingBuffer(final Buffer buffer) {
        super(buffer);
        this.timeout = 0L;
    }
    
    protected BlockingBuffer(final Buffer buffer, final long timeoutMillis) {
        super(buffer);
        this.timeout = ((timeoutMillis < 0L) ? 0L : timeoutMillis);
    }
    
    public boolean add(final Object o) {
        synchronized (super.lock) {
            final boolean result = super.collection.add(o);
            super.lock.notifyAll();
            return result;
        }
    }
    
    public boolean addAll(final Collection c) {
        synchronized (super.lock) {
            final boolean result = super.collection.addAll(c);
            super.lock.notifyAll();
            return result;
        }
    }
    
    public Object get() {
        synchronized (super.lock) {
            while (super.collection.isEmpty()) {
                try {
                    if (this.timeout > 0L) {
                        return this.get(this.timeout);
                    }
                    super.lock.wait();
                }
                catch (InterruptedException e) {
                    final PrintWriter out = new PrintWriter(new StringWriter());
                    e.printStackTrace(out);
                    throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
                }
            }
            return this.getBuffer().get();
        }
    }
    
    public Object get(final long timeout) {
        synchronized (super.lock) {
            final long expiration = System.currentTimeMillis() + timeout;
            long timeLeft = expiration - System.currentTimeMillis();
            while (timeLeft > 0L && super.collection.isEmpty()) {
                try {
                    super.lock.wait(timeLeft);
                    timeLeft = expiration - System.currentTimeMillis();
                }
                catch (InterruptedException e) {
                    final PrintWriter out = new PrintWriter(new StringWriter());
                    e.printStackTrace(out);
                    throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
                }
            }
            if (super.collection.isEmpty()) {
                throw new BufferUnderflowException("Timeout expired");
            }
            return this.getBuffer().get();
        }
    }
    
    public Object remove() {
        synchronized (super.lock) {
            while (super.collection.isEmpty()) {
                try {
                    if (this.timeout > 0L) {
                        return this.remove(this.timeout);
                    }
                    super.lock.wait();
                }
                catch (InterruptedException e) {
                    final PrintWriter out = new PrintWriter(new StringWriter());
                    e.printStackTrace(out);
                    throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
                }
            }
            return this.getBuffer().remove();
        }
    }
    
    public Object remove(final long timeout) {
        synchronized (super.lock) {
            final long expiration = System.currentTimeMillis() + timeout;
            long timeLeft = expiration - System.currentTimeMillis();
            while (timeLeft > 0L && super.collection.isEmpty()) {
                try {
                    super.lock.wait(timeLeft);
                    timeLeft = expiration - System.currentTimeMillis();
                }
                catch (InterruptedException e) {
                    final PrintWriter out = new PrintWriter(new StringWriter());
                    e.printStackTrace(out);
                    throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
                }
            }
            if (super.collection.isEmpty()) {
                throw new BufferUnderflowException("Timeout expired");
            }
            return this.getBuffer().remove();
        }
    }
}
