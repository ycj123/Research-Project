// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;

public class ForkNumberBucket
{
    private static final ForkNumberBucket INSTANCE;
    private Queue<Integer> qFree;
    private AtomicInteger highWaterMark;
    
    protected ForkNumberBucket() {
        this.qFree = new ConcurrentLinkedQueue<Integer>();
        this.highWaterMark = new AtomicInteger(1);
    }
    
    public static int drawNumber() {
        return getInstance()._drawNumber();
    }
    
    public static void returnNumber(final int number) {
        getInstance()._returnNumber(number);
    }
    
    private static ForkNumberBucket getInstance() {
        return ForkNumberBucket.INSTANCE;
    }
    
    protected int _drawNumber() {
        final Integer nextFree = this.qFree.poll();
        if (null == nextFree) {
            return this.highWaterMark.getAndIncrement();
        }
        return nextFree;
    }
    
    protected int getHighestDrawnNumber() {
        return this.highWaterMark.get() - 1;
    }
    
    protected void _returnNumber(final int number) {
        this.qFree.add(number);
    }
    
    static {
        INSTANCE = new ForkNumberBucket();
    }
}
