// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.context.LogLevel;

public interface ExceptionHandlingStrategyFactory
{
    ExceptionHandlingStrategy makeExceptionHandlingStrategy(final LogLevel p0, final PrintStream p1);
}
