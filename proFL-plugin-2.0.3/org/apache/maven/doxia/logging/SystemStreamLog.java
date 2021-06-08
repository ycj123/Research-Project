// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.logging;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SystemStreamLog implements Log
{
    private static final String EOL;
    private int currentLevel;
    
    public SystemStreamLog() {
        this.currentLevel = 1;
    }
    
    public void setLogLevel(final int level) {
        if (level <= 0) {
            this.currentLevel = 0;
        }
        else if (level <= 1) {
            this.currentLevel = 1;
        }
        else if (level <= 2) {
            this.currentLevel = 2;
        }
        else if (level <= 3) {
            this.currentLevel = 3;
        }
        else {
            this.currentLevel = 5;
        }
    }
    
    public void debug(final CharSequence content) {
        if (this.isDebugEnabled()) {
            this.print("debug", content);
        }
    }
    
    public void debug(final CharSequence content, final Throwable error) {
        if (this.isDebugEnabled()) {
            this.print("debug", content, error);
        }
    }
    
    public void debug(final Throwable error) {
        if (this.isDebugEnabled()) {
            this.print("debug", error);
        }
    }
    
    public void info(final CharSequence content) {
        if (this.isInfoEnabled()) {
            this.print("info", content);
        }
    }
    
    public void info(final CharSequence content, final Throwable error) {
        if (this.isInfoEnabled()) {
            this.print("info", content, error);
        }
    }
    
    public void info(final Throwable error) {
        if (this.isInfoEnabled()) {
            this.print("info", error);
        }
    }
    
    public void warn(final CharSequence content) {
        if (this.isWarnEnabled()) {
            this.print("warn", content);
        }
    }
    
    public void warn(final CharSequence content, final Throwable error) {
        if (this.isWarnEnabled()) {
            this.print("warn", content, error);
        }
    }
    
    public void warn(final Throwable error) {
        if (this.isWarnEnabled()) {
            this.print("warn", error);
        }
    }
    
    public void error(final CharSequence content) {
        if (this.isErrorEnabled()) {
            System.err.println("[error] " + content.toString());
        }
    }
    
    public void error(final CharSequence content, final Throwable error) {
        if (this.isErrorEnabled()) {
            final StringWriter sWriter = new StringWriter();
            final PrintWriter pWriter = new PrintWriter(sWriter);
            error.printStackTrace(pWriter);
            System.err.println("[error] " + content.toString() + SystemStreamLog.EOL + SystemStreamLog.EOL + sWriter.toString());
        }
    }
    
    public void error(final Throwable error) {
        if (this.isErrorEnabled()) {
            final StringWriter sWriter = new StringWriter();
            final PrintWriter pWriter = new PrintWriter(sWriter);
            error.printStackTrace(pWriter);
            System.err.println("[error] " + sWriter.toString());
        }
    }
    
    public boolean isDebugEnabled() {
        return this.currentLevel <= 0;
    }
    
    public boolean isInfoEnabled() {
        return this.currentLevel <= 1;
    }
    
    public boolean isWarnEnabled() {
        return this.currentLevel <= 2;
    }
    
    public boolean isErrorEnabled() {
        return this.currentLevel <= 3;
    }
    
    private void print(final String prefix, final CharSequence content) {
        System.out.println("[" + prefix + "] " + content.toString());
    }
    
    private void print(final String prefix, final Throwable error) {
        final StringWriter sWriter = new StringWriter();
        final PrintWriter pWriter = new PrintWriter(sWriter);
        error.printStackTrace(pWriter);
        System.out.println("[" + prefix + "] " + sWriter.toString());
    }
    
    private void print(final String prefix, final CharSequence content, final Throwable error) {
        final StringWriter sWriter = new StringWriter();
        final PrintWriter pWriter = new PrintWriter(sWriter);
        error.printStackTrace(pWriter);
        System.out.println("[" + prefix + "] " + content.toString() + SystemStreamLog.EOL + SystemStreamLog.EOL + sWriter.toString());
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
