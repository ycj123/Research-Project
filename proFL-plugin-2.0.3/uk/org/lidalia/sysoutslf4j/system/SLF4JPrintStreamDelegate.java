// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

import uk.org.lidalia.sysoutslf4j.common.StringUtils;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import java.io.PrintStream;

class SLF4JPrintStreamDelegate
{
    private final PrintStream originalPrintStream;
    private final LoggerAppenderStore loggerAppenderStore;
    
    SLF4JPrintStreamDelegate(final PrintStream originalPrintStream, final LoggerAppenderStore loggerAppenderStore) {
        this.originalPrintStream = originalPrintStream;
        this.loggerAppenderStore = loggerAppenderStore;
    }
    
    void registerLoggerAppender(final LoggerAppender loggerAppender) {
        this.loggerAppenderStore.put(loggerAppender);
    }
    
    void deregisterLoggerAppender() {
        this.loggerAppenderStore.remove();
    }
    
    void delegatePrintln(final String message) {
        final LoggerAppender loggerAppender = this.loggerAppenderStore.get();
        if (loggerAppender == null) {
            this.originalPrintStream.println(message);
        }
        else {
            appendAndLog(message, loggerAppender);
        }
    }
    
    void delegatePrint(final String message) {
        final LoggerAppender loggerAppender = this.loggerAppenderStore.get();
        if (loggerAppender == null) {
            this.originalPrintStream.print(message);
        }
        else if (message.endsWith("\n")) {
            final String messageWithoutLineBreak = StringUtils.stripEnd(message, "\r\n");
            appendAndLog(messageWithoutLineBreak, loggerAppender);
        }
        else {
            loggerAppender.append(message);
        }
    }
    
    private static void appendAndLog(final String message, final LoggerAppender loggerAppender) {
        final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        final String libraryPackageName = "uk.org.lidalia.sysoutslf4j";
        final CallOrigin callOrigin = CallOrigin.getCallOrigin(stackTraceElements, "uk.org.lidalia.sysoutslf4j");
        loggerAppender.appendAndLog(message, callOrigin.getClassName(), callOrigin.isPrintingStackTrace());
    }
}
