// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.logging;

import org.codehaus.plexus.logging.Logger;
import org.apache.maven.plugin.logging.Log;

public class DefaultLog implements Log
{
    private final Logger logger;
    
    public DefaultLog(final Logger logger) {
        this.logger = logger;
    }
    
    public void debug(final CharSequence content) {
        this.logger.debug(this.toString(content));
    }
    
    private String toString(final CharSequence content) {
        if (content == null) {
            return "";
        }
        return content.toString();
    }
    
    public void debug(final CharSequence content, final Throwable error) {
        this.logger.debug(this.toString(content), error);
    }
    
    public void debug(final Throwable error) {
        this.logger.debug("", error);
    }
    
    public void info(final CharSequence content) {
        this.logger.info(this.toString(content));
    }
    
    public void info(final CharSequence content, final Throwable error) {
        this.logger.info(this.toString(content), error);
    }
    
    public void info(final Throwable error) {
        this.logger.info("", error);
    }
    
    public void warn(final CharSequence content) {
        this.logger.warn(this.toString(content));
    }
    
    public void warn(final CharSequence content, final Throwable error) {
        this.logger.warn(this.toString(content), error);
    }
    
    public void warn(final Throwable error) {
        this.logger.warn("", error);
    }
    
    public void error(final CharSequence content) {
        this.logger.error(this.toString(content));
    }
    
    public void error(final CharSequence content, final Throwable error) {
        this.logger.error(this.toString(content), error);
    }
    
    public void error(final Throwable error) {
        this.logger.error("", error);
    }
    
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }
    
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }
    
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }
    
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }
}
