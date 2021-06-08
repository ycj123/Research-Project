// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

public class LogDisplayWrapper extends Log
{
    private final String prefix;
    private final boolean outputMessages;
    private final Log log;
    
    public LogDisplayWrapper(final Log log, final String prefix, final boolean outputMessages) {
        super(log.getLogChute());
        this.log = log;
        this.prefix = prefix;
        this.outputMessages = outputMessages;
    }
    
    protected LogChute getLogChute() {
        return this.log.getLogChute();
    }
    
    protected void log(final int level, final Object message) {
        this.log(this.outputMessages, level, message);
    }
    
    protected void log(final boolean doLogging, final int level, final Object message) {
        if (doLogging) {
            this.getLogChute().log(level, this.prefix + String.valueOf(message));
        }
    }
    
    protected void log(final int level, final Object message, final Throwable t) {
        this.log(this.outputMessages, level, message);
    }
    
    protected void log(final boolean doLogging, final int level, final Object message, final Throwable t) {
        if (doLogging) {
            this.getLogChute().log(level, this.prefix + String.valueOf(message), t);
        }
    }
    
    public void trace(final boolean doLogging, final Object message) {
        this.log(doLogging, -1, message);
    }
    
    public void trace(final boolean doLogging, final Object message, final Throwable t) {
        this.log(doLogging, -1, message, t);
    }
    
    public void debug(final boolean doLogging, final Object message) {
        this.log(doLogging, 0, message);
    }
    
    public void debug(final boolean doLogging, final Object message, final Throwable t) {
        this.log(doLogging, 0, message, t);
    }
    
    public void info(final boolean doLogging, final Object message) {
        this.log(doLogging, 1, message);
    }
    
    public void info(final boolean doLogging, final Object message, final Throwable t) {
        this.log(doLogging, 1, message, t);
    }
    
    public void warn(final boolean doLogging, final Object message) {
        this.log(doLogging, 2, message);
    }
    
    public void warn(final boolean doLogging, final Object message, final Throwable t) {
        this.log(doLogging, 2, message, t);
    }
    
    public void error(final boolean doLogging, final Object message) {
        this.log(doLogging, 3, message);
    }
    
    public void error(final boolean doLogging, final Object message, final Throwable t) {
        this.log(doLogging, 3, message, t);
    }
}
