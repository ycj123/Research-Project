// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import java.io.IOException;
import org.codehaus.groovy.GroovyException;

public class ReadException extends GroovyException
{
    private final IOException cause;
    
    public ReadException(final IOException cause) {
        this.cause = cause;
    }
    
    public ReadException(final String message, final IOException cause) {
        super(message);
        this.cause = cause;
    }
    
    public IOException getIOCause() {
        return this.cause;
    }
    
    @Override
    public String toString() {
        String message = super.getMessage();
        if (message == null || message.trim().equals("")) {
            message = this.cause.getMessage();
        }
        return message;
    }
    
    @Override
    public String getMessage() {
        return this.toString();
    }
}
