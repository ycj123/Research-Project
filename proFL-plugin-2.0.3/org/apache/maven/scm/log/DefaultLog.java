// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.log;

public class DefaultLog implements ScmLogger
{
    private boolean debug;
    
    public DefaultLog() {
        this.debug = false;
    }
    
    public DefaultLog(final boolean debug) {
        this.debug = false;
        this.debug = debug;
    }
    
    public boolean isDebugEnabled() {
        return this.debug;
    }
    
    public void debug(final String content) {
        if (this.debug) {
            System.out.println(content);
        }
    }
    
    public void debug(final String content, final Throwable error) {
        if (this.debug) {
            System.out.println(content);
            error.printStackTrace();
        }
    }
    
    public void debug(final Throwable error) {
        if (this.debug) {
            error.printStackTrace();
        }
    }
    
    public boolean isInfoEnabled() {
        return true;
    }
    
    public void info(final String content) {
        System.out.println(content);
    }
    
    public void info(final String content, final Throwable error) {
        System.out.println(content);
        error.printStackTrace();
    }
    
    public void info(final Throwable error) {
        error.printStackTrace();
    }
    
    public boolean isWarnEnabled() {
        return true;
    }
    
    public void warn(final String content) {
        System.out.println(content);
    }
    
    public void warn(final String content, final Throwable error) {
        System.out.println(content);
        error.printStackTrace();
    }
    
    public void warn(final Throwable error) {
        error.printStackTrace();
    }
    
    public boolean isErrorEnabled() {
        return true;
    }
    
    public void error(final String content) {
        System.out.print("[ERROR] " + content);
    }
    
    public void error(final String content, final Throwable error) {
        System.out.println("[ERROR] " + content);
        error.printStackTrace();
    }
    
    public void error(final Throwable error) {
        error.printStackTrace();
    }
}
