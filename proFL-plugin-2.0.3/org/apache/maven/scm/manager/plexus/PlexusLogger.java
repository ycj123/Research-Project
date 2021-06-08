// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager.plexus;

import org.codehaus.plexus.logging.Logger;
import org.apache.maven.scm.log.ScmLogger;

public class PlexusLogger implements ScmLogger
{
    private Logger logger;
    
    public PlexusLogger(final Logger logger) {
        this.logger = logger;
    }
    
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }
    
    public void debug(final String content) {
        this.logger.debug(content);
    }
    
    public void debug(final String content, final Throwable error) {
        this.logger.debug(content, error);
    }
    
    public void debug(final Throwable error) {
        this.logger.debug("", error);
    }
    
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }
    
    public void info(final String content) {
        this.logger.info(content);
    }
    
    public void info(final String content, final Throwable error) {
        this.logger.info(content, error);
    }
    
    public void info(final Throwable error) {
        this.logger.info("", error);
    }
    
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }
    
    public void warn(final String content) {
        this.logger.warn(content);
    }
    
    public void warn(final String content, final Throwable error) {
        this.logger.warn(content, error);
    }
    
    public void warn(final Throwable error) {
        this.logger.warn("", error);
    }
    
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }
    
    public void error(final String content) {
        this.logger.error(content);
    }
    
    public void error(final String content, final Throwable error) {
        this.logger.error(content, error);
    }
    
    public void error(final Throwable error) {
        this.logger.error("", error);
    }
}
