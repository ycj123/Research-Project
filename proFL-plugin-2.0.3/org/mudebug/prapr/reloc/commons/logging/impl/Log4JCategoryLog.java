// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Category;
import org.mudebug.prapr.reloc.commons.logging.Log;

public final class Log4JCategoryLog implements Log
{
    private static final String FQCN;
    private Category category;
    
    public Log4JCategoryLog() {
        this.category = null;
    }
    
    public Log4JCategoryLog(final String name) {
        this.category = null;
        this.category = Category.getInstance(name);
    }
    
    public Log4JCategoryLog(final Category category) {
        this.category = null;
        this.category = category;
    }
    
    public void trace(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.DEBUG, message, (Throwable)null);
    }
    
    public void trace(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.DEBUG, message, t);
    }
    
    public void debug(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.DEBUG, message, (Throwable)null);
    }
    
    public void debug(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.DEBUG, message, t);
    }
    
    public void info(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.INFO, message, (Throwable)null);
    }
    
    public void info(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.INFO, message, t);
    }
    
    public void warn(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.WARN, message, (Throwable)null);
    }
    
    public void warn(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.WARN, message, t);
    }
    
    public void error(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.ERROR, message, (Throwable)null);
    }
    
    public void error(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.ERROR, message, t);
    }
    
    public void fatal(final Object message) {
        this.category.log(Log4JCategoryLog.FQCN, Level.FATAL, message, (Throwable)null);
    }
    
    public void fatal(final Object message, final Throwable t) {
        this.category.log(Log4JCategoryLog.FQCN, Level.FATAL, message, t);
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public boolean isDebugEnabled() {
        return this.category.isDebugEnabled();
    }
    
    public boolean isErrorEnabled() {
        return this.category.isEnabledFor(Level.ERROR);
    }
    
    public boolean isFatalEnabled() {
        return this.category.isEnabledFor(Level.FATAL);
    }
    
    public boolean isInfoEnabled() {
        return this.category.isInfoEnabled();
    }
    
    public boolean isTraceEnabled() {
        return this.category.isDebugEnabled();
    }
    
    public boolean isWarnEnabled() {
        return this.category.isEnabledFor(Level.WARN);
    }
    
    static {
        FQCN = Log4JCategoryLog.class.getName();
    }
}
