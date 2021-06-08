// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;
import java.lang.reflect.Method;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;

final class LoggerAppenderProxy implements LoggerAppender
{
    private final Object targetLoggerAppender;
    private final Method appendMethod;
    private final Method appendAndLogMethod;
    
    private LoggerAppenderProxy(final Object targetLoggerAppender) {
        try {
            final Class<?> loggerAppenderClass = targetLoggerAppender.getClass();
            this.targetLoggerAppender = targetLoggerAppender;
            this.appendMethod = loggerAppenderClass.getDeclaredMethod("append", String.class);
            this.appendAndLogMethod = loggerAppenderClass.getDeclaredMethod("appendAndLog", String.class, String.class, Boolean.TYPE);
        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Must only be instantiated with a LoggerAppender instance, got a " + targetLoggerAppender.getClass(), e);
        }
    }
    
    @Override
    public void append(final String message) {
        ReflectionUtils.invokeMethod(this.appendMethod, this.targetLoggerAppender, message);
    }
    
    @Override
    public void appendAndLog(final String message, final String className, final boolean isStackTrace) {
        ReflectionUtils.invokeMethod(this.appendAndLogMethod, this.targetLoggerAppender, message, className, isStackTrace);
    }
    
    static LoggerAppender wrap(final Object targetLoggerAppender) {
        LoggerAppender result;
        if (targetLoggerAppender instanceof LoggerAppender) {
            result = (LoggerAppender)targetLoggerAppender;
        }
        else {
            result = new LoggerAppenderProxy(targetLoggerAppender);
        }
        return result;
    }
}
