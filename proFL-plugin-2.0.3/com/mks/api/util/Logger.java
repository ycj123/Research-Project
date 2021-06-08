// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.security.Principal;
import javax.security.auth.Subject;
import java.security.AccessController;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

final class Logger implements Runnable
{
    public static final String DEBUG = "DEBUG";
    public static final String FATAL = "FATAL";
    public static final String ERROR = "ERROR";
    public static final String GENERAL = "GENERAL";
    public static final String IGNORE = "IGNORE";
    public static final String WARNING = "WARNING";
    public static final String LOCKFILE = "LOCKFILE";
    public static final int LOWEST = 20;
    public static final int LOW = 10;
    public static final int MEDIUM = 5;
    public static final int HIGH = 0;
    public static final int OFF = -1;
    private static Logger defaultLogger;
    private List logListeners;
    private static ThreadLocal threadData;
    private Queue messageQueue;
    private int maxQueueSize;
    private Thread loggerThread;
    private boolean runLogger;
    
    public Logger(final boolean makeAppDefault) {
        this(makeAppDefault, "Logger");
    }
    
    public Logger(final boolean makeAppDefault, final String threadName) {
        this.runLogger = true;
        this.logListeners = new ArrayList();
        this.messageQueue = new Queue();
        this.maxQueueSize = 10000;
        (this.loggerThread = new Thread(this, threadName)).setDaemon(true);
        this.loggerThread.start();
        if (makeAppDefault) {
            setApplicationLogger(this);
        }
    }
    
    public void stop() {
        if (this == Logger.defaultLogger) {
            new Exception("Trying to stop the default logger!!").printStackTrace(System.out);
        }
        else {
            this.runLogger = false;
            this.loggerThread.interrupt();
        }
    }
    
    public static Logger getApplicationLogger() {
        return Logger.defaultLogger;
    }
    
    public static synchronized void setApplicationLogger(final Logger logger) {
        if (Logger.defaultLogger != null && logger == null) {
            new Exception("Someone is trying to set the default logger to null!!!").printStackTrace(System.out);
        }
        else {
            Logger.defaultLogger = logger;
        }
    }
    
    public void setPriority(final int priority) {
        this.loggerThread.setPriority(priority);
    }
    
    public void setMessageQueueSize(final int maxsize) {
        this.maxQueueSize = maxsize;
    }
    
    public static void setApplicationMessageQueueSize(final int maxsize) {
        if (Logger.defaultLogger != null) {
            Logger.defaultLogger.setMessageQueueSize(maxsize);
        }
    }
    
    public void addLogListener(final InternalAPILogListener listener) {
        synchronized (this.logListeners) {
            if (!this.logListeners.contains(listener)) {
                this.logListeners.add(listener);
            }
        }
    }
    
    public void removeLogListener(final InternalAPILogListener listener) {
        synchronized (this.logListeners) {
            this.logListeners.remove(listener);
        }
    }
    
    public List getLogListeners() {
        synchronized (this.logListeners) {
            return new ArrayList(this.logListeners);
        }
    }
    
    public void logMessage(final Class klass, final Object obj, String category, final int level, final String message) {
        if (message == null) {
            this.logMessage(null, null, "ERROR", 0, "Logger.logMessage called with null error message from: " + StackTrace.getStackTrace());
            return;
        }
        if (this.logListeners.isEmpty()) {
            return;
        }
        if (category == null) {
            category = "GENERAL";
        }
        final Object threadData = this.defaultThreadData();
        synchronized (this.messageQueue) {
            if (this.maxQueueSize != 0 && this.messageQueue.size() >= this.maxQueueSize) {
                this.processNextMessage(false);
            }
            this.messageQueue.enqueue(new LogMessage(klass, obj, category, level, threadData, message));
            this.messageQueue.notify();
        }
    }
    
    public Object defaultThreadData() {
        final Object data = Logger.threadData.get();
        if (data != null) {
            return data;
        }
        final Thread t = Thread.currentThread();
        final Subject subject = Subject.getSubject(AccessController.getContext());
        if (subject == null) {
            return t.getName();
        }
        return this.getName(subject) + "[" + t.getName() + "]";
    }
    
    private String getName(final Subject s) {
        if (s != null) {
            final Iterator i = s.getPrincipals().iterator();
            if (i.hasNext()) {
                final Principal p = i.next();
                return p.getName();
            }
        }
        return "unknown";
    }
    
    public boolean logMessageCheck(final String category, final int level) {
        try {
            for (final InternalAPILogListener listener : this.logListeners) {
                if (listener.willLogMessage(category, level)) {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException e) {
            return this.logMessageCheck(category, level);
        }
        return false;
    }
    
    public static void message(final Class klass, final Object obj, final String category, final int level, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(klass, obj, category, level, message);
    }
    
    public void logMessage(final Class klass, final String category, final int level, final String message) {
        this.logMessage(klass, null, category, level, message);
    }
    
    public static void message(final Class klass, final String category, final int level, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(klass, category, level, message);
    }
    
    public void logMessage(final Object obj, final String category, final int level, final String message) {
        this.logMessage((obj == null) ? null : obj.getClass(), obj, category, level, message);
    }
    
    public static void message(final Object obj, final String category, final int level, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(obj, category, level, message);
    }
    
    public static boolean messageCheck(final String category, final int level) {
        return Logger.defaultLogger != null && Logger.defaultLogger.logMessageCheck(category, level);
    }
    
    public void logMessage(final String category, final int level, final String message) {
        this.logMessage(null, null, category, level, message);
    }
    
    public static void message(final String category, final int level, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(category, level, message);
    }
    
    public static void multiLineMessage(final String category, final int level, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        final StringTokenizer st = new StringTokenizer(message, "\n");
        while (st.hasMoreTokens()) {
            Logger.defaultLogger.logMessage(category, level, st.nextToken());
        }
    }
    
    public void logMessage(final String category, final String message) {
        this.logMessage(null, null, category, 0, message);
    }
    
    public boolean logMessageCheck(final String category) {
        return this.logMessageCheck(category, 0);
    }
    
    public static void message(final String category, final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(category, message);
    }
    
    public static boolean messageCheck(final String category) {
        return Logger.defaultLogger != null && Logger.defaultLogger.logMessageCheck(category);
    }
    
    public void logMessage(final String message) {
        this.logMessage(null, null, null, 0, message);
    }
    
    public boolean logMessageCheck() {
        return this.logMessageCheck(null, 0);
    }
    
    public static void message(final String message) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logMessage(message);
    }
    
    public static boolean messageCheck() {
        return Logger.defaultLogger != null && Logger.defaultLogger.logMessageCheck();
    }
    
    public void logException(final Class klass, final Object obj, String category, final int level, final Throwable exception) {
        if (exception == null) {
            this.logMessage(null, null, "ERROR", 0, "Logger.logException called with null exception from: " + StackTrace.getStackTrace());
            return;
        }
        if (this.logListeners.isEmpty()) {
            return;
        }
        if (category == null) {
            category = "GENERAL";
        }
        final Object threadData = this.defaultThreadData();
        synchronized (this.messageQueue) {
            if (this.maxQueueSize != 0 && this.messageQueue.size() >= this.maxQueueSize) {
                this.processNextMessage(false);
            }
            this.messageQueue.enqueue(new LogException(klass, obj, category, level, threadData, exception));
            this.messageQueue.notify();
        }
    }
    
    public boolean logExceptionCheck(final String category, final int level) {
        try {
            for (final InternalAPILogListener listener : this.logListeners) {
                if (listener.willLogException(category, level)) {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException e) {
            return this.logMessageCheck(category, level);
        }
        return false;
    }
    
    public static void exception(final Class klass, final Object obj, final String category, final int level, final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        try {
            Logger.defaultLogger.logException(klass, obj, category, level, exception);
        }
        catch (OutOfMemoryError oom) {
            try {
                System.err.println(exception.getMessage());
                exception.printStackTrace();
            }
            catch (Throwable t) {}
        }
    }
    
    public static boolean exceptionCheck(final String category, final int level) {
        return Logger.defaultLogger != null && Logger.defaultLogger.logExceptionCheck(category, level);
    }
    
    public void logException(final Class klass, final String category, final int level, final Throwable exception) {
        this.logException(klass, null, category, level, exception);
    }
    
    public static void exception(final Class klass, final String category, final int level, final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logException(klass, category, level, exception);
    }
    
    public void logException(final Object obj, final String category, final int level, final Throwable exception) {
        this.logException((obj == null) ? null : obj.getClass(), obj, category, level, exception);
    }
    
    public static void exception(final Object obj, final String category, final int level, final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logException(obj, category, level, exception);
    }
    
    public void logException(final String category, final int level, final Throwable exception) {
        this.logException(null, null, category, level, exception);
    }
    
    public static void exception(final String category, final int level, final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logException(category, level, exception);
    }
    
    public void logException(final String category, final Throwable exception) {
        this.logException(null, null, category, 0, exception);
    }
    
    public boolean logExceptionCheck(final String category) {
        return this.logExceptionCheck(category, 0);
    }
    
    public static void exception(final String category, final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logException(category, exception);
    }
    
    public boolean exceptionCheck(final String category) {
        return Logger.defaultLogger != null && Logger.defaultLogger.logExceptionCheck(category);
    }
    
    public void logException(final Throwable exception) {
        this.logException(null, null, null, 0, exception);
    }
    
    public boolean logExceptionCheck() {
        return this.logExceptionCheck(null, 0);
    }
    
    public static void exception(final Throwable exception) {
        if (Logger.defaultLogger == null) {
            return;
        }
        Logger.defaultLogger.logException(exception);
    }
    
    public boolean exceptionCheck() {
        return Logger.defaultLogger != null && Logger.defaultLogger.logExceptionCheck();
    }
    
    public int updateCategoryLevel(final String category, final int level) {
        final List logList = this.getLogListeners();
        int n = 0;
        for (final InternalAPILogListener ll : logList) {
            ll.removeCategoryIncludeFilter(0, category);
            ll.removeCategoryIncludeFilter(1, category);
            ll.removeCategoryExcludeFilter(0, category);
            ll.removeCategoryExcludeFilter(1, category);
            ll.addCategoryIncludeFilter(0, category, level);
            ll.addCategoryIncludeFilter(1, category, level);
            ++n;
        }
        return n;
    }
    
    public int getCategoryLevel(final String category) {
        try {
            final Iterator i = this.logListeners.iterator();
            int max = -1;
            while (i.hasNext()) {
                final InternalAPILogListener ll = i.next();
                for (int level = 20; level >= 0; --level) {
                    if (ll.willLogMessage(category, level) || ll.willLogException(category, level)) {
                        if (level == 20) {
                            return 20;
                        }
                        if (level > max) {
                            max = level;
                            break;
                        }
                    }
                }
            }
            return max;
        }
        catch (ConcurrentModificationException e) {
            return this.getCategoryLevel(category);
        }
    }
    
    public static int getDefaultCategoryLevel(final String category) {
        final Logger logger = Logger.defaultLogger;
        if (logger == null) {
            return -1;
        }
        return logger.getCategoryLevel(category);
    }
    
    public void logAddThreadData(final Object obj) {
        Logger.threadData.set(obj);
    }
    
    public static void addThreadData(final Object obj) {
        if (Logger.defaultLogger != null) {
            Logger.defaultLogger.logAddThreadData(obj);
        }
    }
    
    public Object logGetThreadData() {
        return Logger.threadData.get();
    }
    
    public static Object getThreadData() {
        return (Logger.defaultLogger == null) ? null : Logger.defaultLogger.logGetThreadData();
    }
    
    public void run() {
        try {
            while (this.runLogger) {
                try {
                    this.processNextMessage(true);
                }
                catch (Throwable th) {
                    th.printStackTrace(System.out);
                }
            }
        }
        finally {
            if (this == Logger.defaultLogger) {
                System.out.println(new Date() + " - Cleaning up default logger!!!, runLogger is: " + this.runLogger);
                Logger.defaultLogger = null;
            }
        }
    }
    
    private void processNextMessage(final boolean wait) {
        final LogObject obj;
        synchronized (this.messageQueue) {
            if (this.messageQueue.isEmpty()) {
                if (wait) {
                    try {
                        this.messageQueue.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                return;
            }
            obj = (LogObject)this.messageQueue.dequeue();
        }
        boolean done = false;
        while (!done) {
            try {
                final Iterator i = this.logListeners.iterator();
                while (i.hasNext()) {
                    obj.log(i.next());
                }
                done = true;
            }
            catch (ConcurrentModificationException e) {}
        }
    }
    
    public void flush() {
        final LogFlush semaphore = new LogFlush();
        synchronized (semaphore) {
            synchronized (this.messageQueue) {
                this.messageQueue.enqueue(semaphore);
                this.messageQueue.notify();
            }
            try {
                semaphore.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    static {
        Logger.threadData = new ThreadLocal();
    }
    
    private abstract static class LogObject
    {
        protected Class klass;
        protected Object obj;
        protected String category;
        protected int level;
        protected Object threadData;
        
        public abstract void log(final InternalAPILogListener p0);
    }
    
    private static class LogMessage extends LogObject
    {
        private String message;
        
        public LogMessage(final Class klass, final Object obj, final String category, final int level, final Object threadData, final String message) {
            this.klass = klass;
            this.obj = obj;
            this.category = category;
            this.level = level;
            this.threadData = threadData;
            this.message = message;
        }
        
        public void log(final InternalAPILogListener listener) {
            listener.logMessage(this.klass, this.obj, this.category, this.level, this.threadData, this.message);
        }
    }
    
    private static class LogException extends LogObject
    {
        private Throwable exception;
        
        public LogException(final Class klass, final Object obj, final String category, final int level, final Object threadData, final Throwable exception) {
            this.klass = klass;
            this.obj = obj;
            this.category = category;
            this.level = level;
            this.threadData = threadData;
            this.exception = exception;
        }
        
        public void log(final InternalAPILogListener listener) {
            listener.logException(this.klass, this.obj, this.category, this.level, this.threadData, this.exception);
        }
    }
    
    private static class LogFlush extends LogObject
    {
        private boolean notified;
        
        private LogFlush() {
            this.notified = false;
        }
        
        public void log(final InternalAPILogListener listener) {
            if (!this.notified) {
                synchronized (this) {
                    this.notify();
                }
                this.notified = true;
            }
        }
    }
}
