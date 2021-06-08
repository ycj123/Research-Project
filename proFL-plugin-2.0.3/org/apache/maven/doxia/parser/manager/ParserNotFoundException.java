// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser.manager;

public class ParserNotFoundException extends Exception
{
    public ParserNotFoundException(final String message) {
        super(message);
    }
    
    public ParserNotFoundException(final Throwable cause) {
        super(cause);
    }
    
    public ParserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
