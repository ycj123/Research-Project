// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import uk.org.lidalia.sysoutslf4j.system.SLF4JPrintStreamImpl;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategyFactory;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.LogPerLineExceptionHandlingStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SysOutOverSLF4J
{
    private static final LoggingSystemRegister LOGGING_SYSTEM_REGISTER;
    private static final SLF4JPrintStreamManager SLF4J_PRINT_STREAM_MANAGER;
    
    static {
        LOGGING_SYSTEM_REGISTER = new LoggingSystemRegister();
        SLF4J_PRINT_STREAM_MANAGER = new SLF4JPrintStreamManager();
        final SysOutOverSLF4JInitialiser sysOutOverSLF4JInitialiser = new SysOutOverSLF4JInitialiser(SysOutOverSLF4J.LOGGING_SYSTEM_REGISTER);
        final Logger loggerImplementation = LoggerFactory.getLogger("ROOT");
        sysOutOverSLF4JInitialiser.initialise(loggerImplementation);
    }
    
    public static void sendSystemOutAndErrToSLF4J() {
        sendSystemOutAndErrToSLF4J(LogLevel.INFO, LogLevel.ERROR);
    }
    
    public static void sendSystemOutAndErrToSLF4J(final LogLevel outLevel, final LogLevel errLevel) {
        final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory = LogPerLineExceptionHandlingStrategyFactory.getInstance();
        sendSystemOutAndErrToSLF4J(outLevel, errLevel, exceptionHandlingStrategyFactory);
    }
    
    public static void sendSystemOutAndErrToSLF4J(final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        sendSystemOutAndErrToSLF4J(LogLevel.INFO, LogLevel.ERROR, exceptionHandlingStrategyFactory);
    }
    
    public static void sendSystemOutAndErrToSLF4J(final LogLevel outLevel, final LogLevel errLevel, final ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        synchronized (System.class) {
            SysOutOverSLF4J.SLF4J_PRINT_STREAM_MANAGER.sendSystemOutAndErrToSLF4J(outLevel, errLevel, exceptionHandlingStrategyFactory);
        }
        // monitorexit(System.class)
    }
    
    public static void stopSendingSystemOutAndErrToSLF4J() {
        synchronized (System.class) {
            SysOutOverSLF4J.SLF4J_PRINT_STREAM_MANAGER.stopSendingSystemOutAndErrToSLF4J();
        }
        // monitorexit(System.class)
    }
    
    public static void restoreOriginalSystemOutputs() {
        synchronized (System.class) {
            SysOutOverSLF4J.SLF4J_PRINT_STREAM_MANAGER.restoreOriginalSystemOutputsIfNecessary();
        }
        // monitorexit(System.class)
    }
    
    public static void registerLoggingSystem(final String packageName) {
        SysOutOverSLF4J.LOGGING_SYSTEM_REGISTER.registerLoggingSystem(packageName);
    }
    
    public static void unregisterLoggingSystem(final String packageName) {
        SysOutOverSLF4J.LOGGING_SYSTEM_REGISTER.unregisterLoggingSystem(packageName);
    }
    
    public static boolean isInLoggingSystem(final String className) {
        return SysOutOverSLF4J.LOGGING_SYSTEM_REGISTER.isInLoggingSystem(className);
    }
    
    private SysOutOverSLF4J() {
        throw new UnsupportedOperationException("Not instantiable");
    }
    
    public static boolean systemOutputsAreSLF4JPrintStreams() {
        return System.out.getClass().getName().equals(SLF4JPrintStreamImpl.class.getName());
    }
}
