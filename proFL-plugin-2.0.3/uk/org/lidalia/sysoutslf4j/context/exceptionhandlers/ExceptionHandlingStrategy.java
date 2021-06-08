// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import org.slf4j.Logger;

public interface ExceptionHandlingStrategy
{
    void handleExceptionLine(final String p0, final Logger p1);
    
    void notifyNotStackTrace();
}
