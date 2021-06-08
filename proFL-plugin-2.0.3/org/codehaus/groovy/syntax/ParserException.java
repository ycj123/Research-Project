// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

public class ParserException extends TokenException
{
    public ParserException(final String message, final Token token) {
        super(message, token);
    }
    
    public ParserException(final String message, final Throwable cause, final int lineNumber, final int columnNumber) {
        super(message, cause, lineNumber, columnNumber);
    }
}
