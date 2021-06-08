// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy;
import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;
import uk.org.lidalia.sysoutslf4j.common.SystemOutput;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategyFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

class SLF4JPrintStreamManager
{
    private static final Logger LOG;
    
    static {
        LOG = LoggerFactory.getLogger((Class)SysOutOverSLF4J.class);
    }
    
    void sendSystemOutAndErrToSLF4J(final LogLevel outLevel, final LogLevel errLevel, final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        this.makeSystemOutputsSLF4JPrintStreamsIfNecessary();
        this.sendSystemOutAndErrToSLF4JForThisContext(outLevel, errLevel, exceptionHandlingStrategyFactory);
        SLF4JPrintStreamManager.LOG.info("Redirected System.out and System.err to SLF4J for this context");
    }
    
    private void makeSystemOutputsSLF4JPrintStreamsIfNecessary() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            SLF4JPrintStreamManager.LOG.debug("System.out and System.err are already SLF4JPrintStreams");
        }
        else {
            PrintStreamCoordinatorFactory.createPrintStreamCoordinator().replaceSystemOutputsWithSLF4JPrintStreams();
            SLF4JPrintStreamManager.LOG.info("Replaced standard System.out and System.err PrintStreams with SLF4JPrintStreams");
        }
    }
    
    private void sendSystemOutAndErrToSLF4JForThisContext(final LogLevel outLevel, final LogLevel errLevel, final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        this.registerNewLoggerAppender(exceptionHandlingStrategyFactory, this.wrap(SystemOutput.OUT.get()), outLevel);
        this.registerNewLoggerAppender(exceptionHandlingStrategyFactory, this.wrap(SystemOutput.ERR.get()), errLevel);
    }
    
    private void registerNewLoggerAppender(final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory, final SLF4JPrintStream slf4jPrintStream, final LogLevel logLevel) {
        final PrintStream originalPrintStream = slf4jPrintStream.getOriginalPrintStream();
        final ExceptionHandlingStrategy exceptionHandlingStrategy = exceptionHandlingStrategyFactory.makeExceptionHandlingStrategy(logLevel, originalPrintStream);
        final Object loggerAppender = new LoggerAppenderImpl(logLevel, exceptionHandlingStrategy, originalPrintStream);
        ReferenceHolder.preventGarbageCollectionForLifeOfClassLoader(loggerAppender);
        slf4jPrintStream.registerLoggerAppender(loggerAppender);
    }
    
    void stopSendingSystemOutAndErrToSLF4J() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            SystemOutput[] values;
            for (int length = (values = SystemOutput.values()).length, i = 0; i < length; ++i) {
                final SystemOutput systemOutput = values[i];
                final SLF4JPrintStream slf4jPrintStream = this.wrap(systemOutput.get());
                slf4jPrintStream.deregisterLoggerAppender();
            }
        }
        else {
            SLF4JPrintStreamManager.LOG.warn("Cannot stop sending System.out and System.err to SLF4J - they are not being sent there at the moment");
        }
    }
    
    private SLF4JPrintStream wrap(final PrintStream target) {
        return ReflectionUtils.wrap(target, SLF4JPrintStream.class);
    }
    
    void restoreOriginalSystemOutputsIfNecessary() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            PrintStreamCoordinatorFactory.createPrintStreamCoordinator().restoreOriginalSystemOutputs();
            SLF4JPrintStreamManager.LOG.info("Restored original System.out and System.err");
        }
        else {
            SLF4JPrintStreamManager.LOG.warn("System.out and System.err are not SLF4JPrintStreams - cannot restore");
        }
    }
}
