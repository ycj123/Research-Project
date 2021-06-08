// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import org.mudebug.prapr.reloc.commons.collections.buffer.TransformedBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.TypedBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.PredicatedBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.UnmodifiableBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.BoundedBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.BlockingBuffer;
import org.mudebug.prapr.reloc.commons.collections.buffer.SynchronizedBuffer;

public class BufferUtils
{
    public static final Buffer EMPTY_BUFFER;
    
    public static Buffer synchronizedBuffer(final Buffer buffer) {
        return SynchronizedBuffer.decorate(buffer);
    }
    
    public static Buffer blockingBuffer(final Buffer buffer) {
        return BlockingBuffer.decorate(buffer);
    }
    
    public static Buffer blockingBuffer(final Buffer buffer, final long timeoutMillis) {
        return BlockingBuffer.decorate(buffer, timeoutMillis);
    }
    
    public static Buffer boundedBuffer(final Buffer buffer, final int maximumSize) {
        return BoundedBuffer.decorate(buffer, maximumSize);
    }
    
    public static Buffer boundedBuffer(final Buffer buffer, final int maximumSize, final long timeoutMillis) {
        return BoundedBuffer.decorate(buffer, maximumSize, timeoutMillis);
    }
    
    public static Buffer unmodifiableBuffer(final Buffer buffer) {
        return UnmodifiableBuffer.decorate(buffer);
    }
    
    public static Buffer predicatedBuffer(final Buffer buffer, final Predicate predicate) {
        return PredicatedBuffer.decorate(buffer, predicate);
    }
    
    public static Buffer typedBuffer(final Buffer buffer, final Class type) {
        return TypedBuffer.decorate(buffer, type);
    }
    
    public static Buffer transformedBuffer(final Buffer buffer, final Transformer transformer) {
        return TransformedBuffer.decorate(buffer, transformer);
    }
    
    static {
        EMPTY_BUFFER = UnmodifiableBuffer.decorate(new ArrayStack(1));
    }
}
