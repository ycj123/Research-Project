// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser;

public class ParseException extends Exception
{
    private String fileName;
    private int lineNumber;
    
    public ParseException(final String message) {
        this(null, message, null, -1);
    }
    
    public ParseException(final String message, final Exception e) {
        this(e, message, null, -1);
    }
    
    public ParseException(final Exception e) {
        this(e, null, null, -1);
    }
    
    public ParseException(final Exception e, final String file, final int line) {
        this(e, null, file, line);
    }
    
    public ParseException(final Exception e, final String message, final String file, final int line) {
        super((message == null) ? ((e == null) ? null : e.getMessage()) : message, e);
        this.fileName = file;
        this.lineNumber = line;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
}
