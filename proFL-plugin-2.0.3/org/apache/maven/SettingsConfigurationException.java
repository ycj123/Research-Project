// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven;

public class SettingsConfigurationException extends Exception
{
    private int lineNumber;
    private int columnNumber;
    
    public SettingsConfigurationException(final String message) {
        super(message);
    }
    
    public SettingsConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public SettingsConfigurationException(final String message, final Throwable cause, final int lineNumber, final int columnNumber) {
        super(message + ((lineNumber > 0) ? ("\n  Line:   " + lineNumber) : "") + ((columnNumber > 0) ? ("\n  Column: " + columnNumber) : ""), cause);
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
}
