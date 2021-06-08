// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ref.ReferenceQueue;

public class ReferenceManager
{
    private ReferenceQueue queue;
    private static final ReferenceBundle SOFT_BUNDLE;
    private static final ReferenceBundle WEAK_BUNDLE;
    
    public static ReferenceManager createThreadedManager(final ReferenceQueue queue) {
        return new ThreadedReferenceManager(queue);
    }
    
    public static ReferenceManager createIdlingManager(final ReferenceQueue queue) {
        return new ReferenceManager(queue);
    }
    
    public static ReferenceManager createCallBackedManager(final ReferenceQueue queue) {
        return new ReferenceManager(queue) {
            @Override
            public void removeStallEntries() {
                final ReferenceQueue queue = this.getReferenceQueue();
                while (true) {
                    java.lang.ref.Reference r = queue.poll();
                    if (r == null) {
                        break;
                    }
                    if (r instanceof Reference) {
                        final Reference ref = (Reference)r;
                        final Finalizable holder = ref.getHandler();
                        if (holder != null) {
                            holder.finalizeReference();
                        }
                    }
                    r.clear();
                    r = null;
                }
            }
            
            @Override
            public void afterReferenceCreation(final Reference r) {
                this.removeStallEntries();
            }
            
            @Override
            public String toString() {
                return "ReferenceManager(callback)";
            }
        };
    }
    
    public static ReferenceManager createThresholdedIdlingManager(final ReferenceQueue queue, final ReferenceManager callback, final int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("threshold must not be below 0.");
        }
        return new ReferenceManager(queue) {
            private AtomicInteger refCnt = new AtomicInteger();
            private volatile ReferenceManager manager = ReferenceManager.createIdlingManager(queue);
            
            @Override
            public void afterReferenceCreation(final Reference r) {
                if (this.manager == callback) {
                    callback.afterReferenceCreation(r);
                    return;
                }
                final int count = this.refCnt.incrementAndGet();
                if (count > threshold || count < 0) {
                    this.manager = callback;
                }
            }
            
            @Override
            public String toString() {
                return "ReferenceManager(thresholded, current manager=" + this.manager + ", threshold=" + this.refCnt.get() + "/" + threshold + ")";
            }
        };
    }
    
    public ReferenceManager(final ReferenceQueue queue) {
        this.queue = queue;
    }
    
    protected ReferenceQueue getReferenceQueue() {
        return this.queue;
    }
    
    public void afterReferenceCreation(final Reference r) {
    }
    
    public void removeStallEntries() {
    }
    
    public void stopThread() {
    }
    
    @Override
    public String toString() {
        return "ReferenceManager(idling)";
    }
    
    public static ReferenceBundle getDefaultSoftBundle() {
        return ReferenceManager.SOFT_BUNDLE;
    }
    
    public static ReferenceBundle getDefaultWeakBundle() {
        return ReferenceManager.WEAK_BUNDLE;
    }
    
    static {
        final ReferenceQueue queue = new ReferenceQueue();
        final ReferenceManager callBack = createCallBackedManager(queue);
        final ReferenceManager manager = createThresholdedIdlingManager(queue, callBack, 500);
        SOFT_BUNDLE = new ReferenceBundle(manager, ReferenceType.SOFT);
        WEAK_BUNDLE = new ReferenceBundle(manager, ReferenceType.WEAK);
    }
    
    private static class ThreadedReferenceManager extends ReferenceManager
    {
        private final Thread thread;
        private volatile boolean shouldRun;
        
        public ThreadedReferenceManager(final ReferenceQueue queue) {
            super(queue);
            this.shouldRun = true;
            (this.thread = new Thread() {
                @Override
                public void run() {
                    final ReferenceQueue queue = ThreadedReferenceManager.this.getReferenceQueue();
                    java.lang.ref.Reference r = null;
                    while (ThreadedReferenceManager.this.shouldRun) {
                        try {
                            r = queue.remove(1000L);
                        }
                        catch (InterruptedException e) {
                            break;
                        }
                        if (r == null) {
                            continue;
                        }
                        if (r instanceof Reference) {
                            final Reference ref = (Reference)r;
                            final Finalizable holder = ref.getHandler();
                            if (holder != null) {
                                holder.finalizeReference();
                            }
                        }
                        r.clear();
                        r = null;
                    }
                }
            }).setContextClassLoader(null);
            this.thread.setDaemon(true);
            this.thread.setName(ThreadedReferenceManager.class.getName());
            this.thread.start();
        }
        
        @Override
        public void stopThread() {
            this.shouldRun = false;
            this.thread.interrupt();
        }
        
        @Override
        public String toString() {
            return "ReferenceManager(threaded, thread=" + this.thread + ")";
        }
    }
}
