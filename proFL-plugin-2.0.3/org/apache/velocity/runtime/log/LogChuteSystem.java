// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import org.apache.velocity.util.StringUtils;
import org.apache.velocity.runtime.RuntimeServices;

public class LogChuteSystem implements LogChute
{
    private LogSystem logSystem;
    
    protected LogChuteSystem(final LogSystem wrapMe) {
        this.logSystem = wrapMe;
    }
    
    public void init(final RuntimeServices rs) throws Exception {
        this.logSystem.init(rs);
    }
    
    public void log(final int level, final String message) {
        this.logSystem.logVelocityMessage(level, message);
    }
    
    public void log(final int level, final String message, final Throwable t) {
        this.logSystem.logVelocityMessage(level, message);
        this.logSystem.logVelocityMessage(level, StringUtils.stackTrace(t));
    }
    
    public boolean isLevelEnabled(final int level) {
        return true;
    }
}
