// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

public class Log4JLogSystem extends Log4JLogChute implements LogSystem
{
    public void logVelocityMessage(final int level, final String message) {
        this.log(level, message);
    }
}
