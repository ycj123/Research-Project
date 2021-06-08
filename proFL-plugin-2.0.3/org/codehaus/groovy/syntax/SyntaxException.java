// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import org.codehaus.groovy.GroovyException;

public class SyntaxException extends GroovyException
{
    private final int line;
    private final int column;
    private String sourceLocator;
    
    public SyntaxException(final String message, final int line, final int column) {
        super(message, false);
        this.line = line;
        this.column = column;
    }
    
    public SyntaxException(final String message, final Throwable cause, final int line, final int column) {
        super(message, cause);
        this.line = line;
        this.column = column;
    }
    
    public void setSourceLocator(final String sourceLocator) {
        this.sourceLocator = sourceLocator;
    }
    
    public String getSourceLocator() {
        return this.sourceLocator;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public int getStartColumn() {
        return this.column;
    }
    
    public int getStartLine() {
        return this.getLine();
    }
    
    public int getEndColumn() {
        return this.getStartColumn() + 1;
    }
    
    public String getOriginalMessage() {
        return super.getMessage();
    }
    
    @Override
    public String getMessage() {
        return super.getMessage() + " @ line " + this.line + ", column " + this.column + ".";
    }
}
