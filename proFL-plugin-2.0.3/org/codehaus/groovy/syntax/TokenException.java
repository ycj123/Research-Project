// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

public class TokenException extends SyntaxException
{
    public TokenException(final String message, final Token token) {
        super((token == null) ? (message + ". No token") : message, getLine(token), getColumn(token));
    }
    
    public TokenException(final String message, final Throwable cause, final int line, final int column) {
        super(message, cause, line, column);
    }
    
    @Override
    public int getEndColumn() {
        final int length = 1;
        return this.getStartColumn() + length;
    }
    
    private static int getColumn(final Token token) {
        return (token != null) ? token.getStartColumn() : -1;
    }
    
    private static int getLine(final Token token) {
        return (token != null) ? token.getStartLine() : -1;
    }
}
