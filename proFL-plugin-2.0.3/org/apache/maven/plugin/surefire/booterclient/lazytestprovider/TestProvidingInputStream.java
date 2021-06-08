// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient.lazytestprovider;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.Queue;
import java.io.InputStream;

public class TestProvidingInputStream extends InputStream
{
    private final Queue<String> testItemQueue;
    private byte[] currentBuffer;
    private int currentPos;
    private Semaphore semaphore;
    private FlushReceiverProvider flushReceiverProvider;
    private boolean closed;
    
    public TestProvidingInputStream(final Queue<String> testItemQueue) {
        this.semaphore = new Semaphore(0);
        this.closed = false;
        this.testItemQueue = testItemQueue;
    }
    
    public void setFlushReceiverProvider(final FlushReceiverProvider flushReceiverProvider) {
        this.flushReceiverProvider = flushReceiverProvider;
    }
    
    @Override
    public synchronized int read() throws IOException {
        if (null == this.currentBuffer) {
            if (null != this.flushReceiverProvider && null != this.flushReceiverProvider.getFlushReceiver()) {
                this.flushReceiverProvider.getFlushReceiver().flush();
            }
            this.semaphore.acquireUninterruptibly();
            if (this.closed) {
                return -1;
            }
            final String currentElement = this.testItemQueue.poll();
            if (null == currentElement) {
                return -1;
            }
            this.currentBuffer = currentElement.getBytes();
            this.currentPos = 0;
        }
        if (this.currentPos < this.currentBuffer.length) {
            return this.currentBuffer[this.currentPos++] & 0xFF;
        }
        this.currentBuffer = null;
        return 10;
    }
    
    public void provideNewTest() {
        this.semaphore.release();
    }
    
    @Override
    public void close() {
        this.closed = true;
        this.semaphore.release();
    }
}
