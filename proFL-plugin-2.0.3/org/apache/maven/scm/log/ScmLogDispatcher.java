// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.log;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ScmLogDispatcher implements ScmLogger
{
    private List<ScmLogger> listeners;
    
    public ScmLogDispatcher() {
        this.listeners = new ArrayList<ScmLogger>();
    }
    
    public void addListener(final ScmLogger logger) {
        this.listeners.add(logger);
    }
    
    public void debug(final String content, final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.debug(content, error);
        }
    }
    
    public void debug(final String content) {
        for (final ScmLogger logger : this.listeners) {
            logger.debug(content);
        }
    }
    
    public void debug(final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.debug(error);
        }
    }
    
    public void error(final String content, final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.error(content, error);
        }
    }
    
    public void error(final String content) {
        for (final ScmLogger logger : this.listeners) {
            logger.error(content);
        }
    }
    
    public void error(final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.error(error);
        }
    }
    
    public void info(final String content, final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isInfoEnabled()) {
                logger.info(content, error);
            }
        }
    }
    
    public void info(final String content) {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isInfoEnabled()) {
                logger.info(content);
            }
        }
    }
    
    public void info(final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isInfoEnabled()) {
                logger.info(error);
            }
        }
    }
    
    public boolean isDebugEnabled() {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isDebugEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isErrorEnabled() {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isErrorEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isInfoEnabled() {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isInfoEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isWarnEnabled() {
        for (final ScmLogger logger : this.listeners) {
            if (logger.isWarnEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public void warn(final String content, final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.warn(content, error);
        }
    }
    
    public void warn(final String content) {
        for (final ScmLogger logger : this.listeners) {
            logger.warn(content);
        }
    }
    
    public void warn(final Throwable error) {
        for (final ScmLogger logger : this.listeners) {
            logger.warn(error);
        }
    }
}
