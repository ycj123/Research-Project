// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.io.IOException;
import java.util.Iterator;
import org.apache.maven.surefire.util.ReflectionUtils;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.util.List;
import org.apache.maven.surefire.util.TestsToRun;

class LazyTestsToRun extends TestsToRun
{
    private final List<Class> workQueue;
    private BufferedReader inputReader;
    private boolean streamClosed;
    private PrintStream originalOutStream;
    
    public LazyTestsToRun(final InputStream testSource, final PrintStream originalOutStream) {
        super((List<Class>)Collections.emptyList());
        this.workQueue = new ArrayList<Class>();
        this.streamClosed = false;
        this.originalOutStream = originalOutStream;
        this.inputReader = new BufferedReader(new InputStreamReader(testSource));
    }
    
    protected void addWorkItem(final String className) {
        synchronized (this.workQueue) {
            this.workQueue.add(ReflectionUtils.loadClass(Thread.currentThread().getContextClassLoader(), className));
        }
    }
    
    protected void requestNextTest() {
        final StringBuilder sb = new StringBuilder();
        sb.append('N').append(",0,want more!\n");
        this.originalOutStream.print(sb.toString());
    }
    
    @Override
    public Iterator<Class> iterator() {
        return new BlockingIterator();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LazyTestsToRun ");
        synchronized (this.workQueue) {
            sb.append("(more items expected: ").append(!this.streamClosed).append("): ");
            sb.append(this.workQueue);
        }
        return sb.toString();
    }
    
    @Override
    public boolean allowEagerReading() {
        return false;
    }
    
    private class BlockingIterator implements Iterator<Class>
    {
        private int lastPos;
        
        private BlockingIterator() {
            this.lastPos = -1;
        }
        
        public boolean hasNext() {
            final int nextPos = this.lastPos + 1;
            synchronized (LazyTestsToRun.this.workQueue) {
                if (LazyTestsToRun.this.workQueue.size() > nextPos) {
                    return true;
                }
                if (this.needsToWaitForInput(nextPos)) {
                    LazyTestsToRun.this.requestNextTest();
                    String nextClassName;
                    try {
                        nextClassName = LazyTestsToRun.this.inputReader.readLine();
                    }
                    catch (IOException e) {
                        LazyTestsToRun.this.streamClosed = true;
                        return false;
                    }
                    if (null == nextClassName) {
                        LazyTestsToRun.this.streamClosed = true;
                    }
                    else {
                        LazyTestsToRun.this.addWorkItem(nextClassName);
                    }
                }
                return LazyTestsToRun.this.workQueue.size() > nextPos;
            }
        }
        
        private boolean needsToWaitForInput(final int nextPos) {
            return LazyTestsToRun.this.workQueue.size() == nextPos && !LazyTestsToRun.this.streamClosed;
        }
        
        public Class next() {
            synchronized (LazyTestsToRun.this.workQueue) {
                return LazyTestsToRun.this.workQueue.get(++this.lastPos);
            }
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
