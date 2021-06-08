// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy;

public class GroovyBugError extends AssertionError
{
    private String message;
    private final Exception exception;
    
    public GroovyBugError(final String message) {
        this(message, null);
    }
    
    public GroovyBugError(final Exception exception) {
        this(null, exception);
    }
    
    public GroovyBugError(final String msg, final Exception exception) {
        this.exception = exception;
        this.message = msg;
    }
    
    @Override
    public String toString() {
        return this.getMessage();
    }
    
    @Override
    public String getMessage() {
        if (this.message != null) {
            return "BUG! " + this.message;
        }
        return "BUG! UNCAUGHT EXCEPTION: " + this.exception.getMessage();
    }
    
    @Override
    public Throwable getCause() {
        return this.exception;
    }
    
    public String getBugText() {
        if (this.message != null) {
            return this.message;
        }
        return this.exception.getMessage();
    }
    
    public void setBugText(final String msg) {
        this.message = msg;
    }
}
