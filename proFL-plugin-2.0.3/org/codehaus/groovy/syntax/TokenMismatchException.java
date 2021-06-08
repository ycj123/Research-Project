// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

public class TokenMismatchException extends TokenException
{
    private final Token unexpectedToken;
    private final int expectedType;
    
    public TokenMismatchException(final Token token, final int expectedType) {
        super("Expected token: " + expectedType + " but found: " + token, token);
        this.unexpectedToken = token;
        this.expectedType = expectedType;
    }
    
    public Token getUnexpectedToken() {
        return this.unexpectedToken;
    }
    
    public int getExpectedType() {
        return this.expectedType;
    }
}
