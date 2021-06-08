// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;

public class LoggerAppenderImpl implements LoggerAppender
{
    private final LogLevel level;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    private final PrintStream originalPrintStream;
    private StringBuilder buffer;
    
    LoggerAppenderImpl(final LogLevel level, final ExceptionHandlingStrategy exceptionHandlingStrategy, final PrintStream originalPrintStream) {
        this.buffer = new StringBuilder();
        this.level = level;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy;
        this.originalPrintStream = originalPrintStream;
    }
    
    @Override
    public void append(final String message) {
        this.exceptionHandlingStrategy.notifyNotStackTrace();
        this.buffer.append(message);
    }
    
    @Override
    public void appendAndLog(final String message, final String className, final boolean isStackTrace) {
        this.buffer.append(message);
        final String logStatement = this.flushBuffer();
        this.logOrPrint(logStatement, className, isStackTrace);
    }
    
    private String flushBuffer() {
        final String logStatement = this.buffer.toString();
        this.buffer = new StringBuilder();
        return logStatement;
    }
    
    private void logOrPrint(final String logStatement, final String className, final boolean isStackTrace) {
        if (SysOutOverSLF4J.isInLoggingSystem(className)) {
            this.originalPrintStream.println(logStatement);
        }
        else {
            this.log(logStatement, className, isStackTrace);
        }
    }
    
    private void log(final String logStatement, final String className, final boolean isStackTrace) {
        final Logger log = LoggerFactory.getLogger(className);
        if (isStackTrace) {
            this.exceptionHandlingStrategy.handleExceptionLine(logStatement, log);
        }
        else {
            this.exceptionHandlingStrategy.notifyNotStackTrace();
            this.level.log(log, logStatement);
        }
    }
}
