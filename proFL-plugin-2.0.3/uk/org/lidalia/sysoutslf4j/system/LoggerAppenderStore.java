// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import java.lang.ref.WeakReference;
import java.util.Map;

class LoggerAppenderStore
{
    private final Map<ClassLoader, WeakReference<LoggerAppender>> loggerAppenderMap;
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;
    
    LoggerAppenderStore() {
        this.loggerAppenderMap = new WeakHashMap<ClassLoader, WeakReference<LoggerAppender>>();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
    }
    
    LoggerAppender get() {
        this.readLock.lock();
        try {
            return this.get(this.contextClassLoader());
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    private LoggerAppender get(final ClassLoader classLoader) {
        final WeakReference<LoggerAppender> loggerAppenderReference = this.loggerAppenderMap.get(classLoader);
        LoggerAppender result;
        if (loggerAppenderReference == null) {
            if (classLoader == null) {
                result = null;
            }
            else {
                result = this.get(classLoader.getParent());
            }
        }
        else {
            result = loggerAppenderReference.get();
        }
        return result;
    }
    
    void put(final LoggerAppender loggerAppender) {
        this.writeLock.lock();
        try {
            this.loggerAppenderMap.put(this.contextClassLoader(), new WeakReference<LoggerAppender>(loggerAppender));
        }
        finally {
            this.writeLock.unlock();
        }
        this.writeLock.unlock();
    }
    
    void remove() {
        this.writeLock.lock();
        try {
            this.loggerAppenderMap.remove(this.contextClassLoader());
        }
        finally {
            this.writeLock.unlock();
        }
        this.writeLock.unlock();
    }
    
    private ClassLoader contextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
