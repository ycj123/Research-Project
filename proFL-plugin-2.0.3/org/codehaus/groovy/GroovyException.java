// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy;

public class GroovyException extends Exception implements GroovyExceptionInterface
{
    private boolean fatal;
    
    public GroovyException() {
        this.fatal = true;
    }
    
    public GroovyException(final String message) {
        super(message);
        this.fatal = true;
    }
    
    public GroovyException(final String message, final Throwable cause) {
        super(message, cause);
        this.fatal = true;
    }
    
    public GroovyException(final boolean fatal) {
        this.fatal = true;
        this.fatal = fatal;
    }
    
    public GroovyException(final String message, final boolean fatal) {
        super(message);
        this.fatal = true;
        this.fatal = fatal;
    }
    
    public boolean isFatal() {
        return this.fatal;
    }
    
    public void setFatal(final boolean fatal) {
        this.fatal = fatal;
    }
}
