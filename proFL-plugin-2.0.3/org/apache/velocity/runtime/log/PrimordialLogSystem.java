// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

public class PrimordialLogSystem extends HoldingLogChute implements LogSystem
{
    public void logVelocityMessage(final int level, final String message) {
        this.log(level, message);
    }
    
    public void dumpLogMessages(final LogSystem newLogger) {
        this.transferTo(new LogChuteSystem(newLogger));
    }
}
