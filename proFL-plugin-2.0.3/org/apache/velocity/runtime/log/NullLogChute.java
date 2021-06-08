// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import org.apache.velocity.runtime.RuntimeServices;

public class NullLogChute implements LogChute
{
    public void init(final RuntimeServices rs) throws Exception {
    }
    
    public void log(final int level, final String message) {
    }
    
    public void log(final int level, final String message, final Throwable t) {
    }
    
    public boolean isLevelEnabled(final int level) {
        return false;
    }
}
