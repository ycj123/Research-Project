// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

public interface EventHandlerMethodExecutor
{
    void execute(final EventHandler p0) throws Exception;
    
    boolean isDone();
    
    Object getReturnValue();
}
