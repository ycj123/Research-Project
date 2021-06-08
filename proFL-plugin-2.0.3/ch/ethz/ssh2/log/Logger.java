// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.log;

public class Logger
{
    private static final boolean enabled = false;
    private static final int logLevel = 99;
    private String className;
    
    public static final Logger getLogger(final Class x) {
        return new Logger(x);
    }
    
    public Logger(final Class x) {
        this.className = x.getName();
    }
    
    public final boolean isEnabled() {
        return false;
    }
    
    public final void log(final int level, final String message) {
    }
}
