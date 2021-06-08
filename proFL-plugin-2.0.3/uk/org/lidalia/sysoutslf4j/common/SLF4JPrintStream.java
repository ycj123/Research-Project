// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

import java.io.PrintStream;

public interface SLF4JPrintStream
{
    void registerLoggerAppender(final Object p0);
    
    void deregisterLoggerAppender();
    
    PrintStream getOriginalPrintStream();
}
