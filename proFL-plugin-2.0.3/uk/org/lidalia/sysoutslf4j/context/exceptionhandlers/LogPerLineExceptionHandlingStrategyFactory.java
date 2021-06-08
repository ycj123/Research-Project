// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.MarkerFactory;
import org.slf4j.Marker;
import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.context.LogLevel;

public final class LogPerLineExceptionHandlingStrategyFactory implements ExceptionHandlingStrategyFactory
{
    private static final ExceptionHandlingStrategyFactory INSTANCE;
    
    static {
        INSTANCE = new LogPerLineExceptionHandlingStrategyFactory();
    }
    
    public static ExceptionHandlingStrategyFactory getInstance() {
        return LogPerLineExceptionHandlingStrategyFactory.INSTANCE;
    }
    
    private LogPerLineExceptionHandlingStrategyFactory() {
    }
    
    @Override
    public ExceptionHandlingStrategy makeExceptionHandlingStrategy(final LogLevel logLevel, final PrintStream originalPrintStream) {
        return new LogPerLineExceptionHandlingStrategy(logLevel);
    }
    
    private static final class LogPerLineExceptionHandlingStrategy implements ExceptionHandlingStrategy
    {
        private static final Marker MARKER;
        private final LogLevel logLevel;
        
        static {
            MARKER = MarkerFactory.getMarker("stacktrace");
        }
        
        LogPerLineExceptionHandlingStrategy(final LogLevel logLevel) {
            this.logLevel = logLevel;
        }
        
        @Override
        public void notifyNotStackTrace() {
        }
        
        @Override
        public void handleExceptionLine(final String line, final Logger log) {
            this.logLevel.log(log, LogPerLineExceptionHandlingStrategy.MARKER, line);
        }
    }
}
