// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.apt;

import org.apache.maven.doxia.parser.ParseException;

public class AptParseException extends ParseException
{
    public AptParseException(final String message, final AptSource source) {
        super(null, message, source.getName(), source.getLineNumber());
    }
    
    public AptParseException(final String message, final AptSource source, final Exception e) {
        super(e, message, source.getName(), source.getLineNumber());
    }
    
    public AptParseException(final String message, final String name, final int lineNumber, final Exception e) {
        super(e, message, name, lineNumber);
    }
    
    public AptParseException(final String message, final Exception e) {
        super(message, e);
    }
    
    public AptParseException(final String message) {
        super(message);
    }
    
    public AptParseException(final Exception e) {
        super(e);
    }
}
