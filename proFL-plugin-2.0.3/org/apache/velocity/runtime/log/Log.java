// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

public class Log
{
    private LogChute chute;
    
    public Log() {
        this.setLogChute(new HoldingLogChute());
    }
    
    public Log(final LogChute chute) {
        this.setLogChute(chute);
    }
    
    protected void setLogChute(final LogChute chute) {
        if (chute == null) {
            throw new NullPointerException("The LogChute cannot be set to null!");
        }
        this.chute = chute;
    }
    
    protected LogChute getLogChute() {
        return this.chute;
    }
    
    protected void log(final int level, final Object message) {
        this.getLogChute().log(level, String.valueOf(message));
    }
    
    protected void log(final int level, final Object message, final Throwable t) {
        this.getLogChute().log(level, String.valueOf(message), t);
    }
    
    public boolean isTraceEnabled() {
        return this.getLogChute().isLevelEnabled(-1);
    }
    
    public void trace(final Object message) {
        this.log(-1, message);
    }
    
    public void trace(final Object message, final Throwable t) {
        this.log(-1, message, t);
    }
    
    public boolean isDebugEnabled() {
        return this.getLogChute().isLevelEnabled(0);
    }
    
    public void debug(final Object message) {
        this.log(0, message);
    }
    
    public void debug(final Object message, final Throwable t) {
        this.log(0, message, t);
    }
    
    public boolean isInfoEnabled() {
        return this.getLogChute().isLevelEnabled(1);
    }
    
    public void info(final Object message) {
        this.log(1, message);
    }
    
    public void info(final Object message, final Throwable t) {
        this.log(1, message, t);
    }
    
    public boolean isWarnEnabled() {
        return this.getLogChute().isLevelEnabled(2);
    }
    
    public void warn(final Object message) {
        this.log(2, message);
    }
    
    public void warn(final Object message, final Throwable t) {
        this.log(2, message, t);
    }
    
    public boolean isErrorEnabled() {
        return this.getLogChute().isLevelEnabled(3);
    }
    
    public void error(final Object message) {
        this.log(3, message);
    }
    
    public void error(final Object message, final Throwable t) {
        this.log(3, message, t);
    }
}
