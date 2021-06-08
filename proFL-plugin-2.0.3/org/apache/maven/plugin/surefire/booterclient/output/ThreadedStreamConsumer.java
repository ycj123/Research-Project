// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient.output;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.StreamConsumer;

public class ThreadedStreamConsumer implements StreamConsumer
{
    private final BlockingQueue<String> items;
    private static final String poison = "Pioson";
    private final Thread thread;
    private final Pumper pumper;
    
    public ThreadedStreamConsumer(final StreamConsumer target) {
        this.items = new LinkedBlockingQueue<String>();
        this.pumper = new Pumper(this.items, target);
        (this.thread = new Thread(this.pumper, "ThreadedStreamConsumer")).start();
    }
    
    public void consumeLine(final String s) {
        this.items.add(s);
        if (this.items.size() > 10000) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void close() {
        try {
            this.items.add("Pioson");
            this.thread.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (this.pumper.getThrowable() != null) {
            throw new RuntimeException(this.pumper.getThrowable());
        }
    }
    
    static class Pumper implements Runnable
    {
        private final BlockingQueue<String> queue;
        private final StreamConsumer target;
        private volatile Throwable throwable;
        
        Pumper(final BlockingQueue<String> queue, final StreamConsumer target) {
            this.queue = queue;
            this.target = target;
        }
        
        public void run() {
            try {
                for (String item = this.queue.take(); item != "Pioson"; item = this.queue.take()) {
                    this.target.consumeLine(item);
                }
            }
            catch (Throwable t) {
                this.throwable = t;
            }
        }
        
        public Throwable getThrowable() {
            return this.throwable;
        }
    }
}
