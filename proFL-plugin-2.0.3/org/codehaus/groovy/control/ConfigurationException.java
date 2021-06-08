// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.GroovyExceptionInterface;

public class ConfigurationException extends RuntimeException implements GroovyExceptionInterface
{
    protected Exception cause;
    
    public ConfigurationException(final Exception cause) {
        super(cause.getMessage());
        this.cause = cause;
    }
    
    public ConfigurationException(final String message) {
        super(message);
    }
    
    @Override
    public Throwable getCause() {
        return this.cause;
    }
    
    public boolean isFatal() {
        return true;
    }
    
    public void setFatal(final boolean fatal) {
    }
}
