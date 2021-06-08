// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.util;

import java.io.IOException;
import org.apache.maven.doxia.parser.ParseException;
import java.io.Reader;
import java.io.LineNumberReader;

public class ByLineReaderSource implements ByLineSource
{
    private LineNumberReader reader;
    private int lineNumber;
    private String lastLine;
    private boolean ungetted;
    
    public ByLineReaderSource(final Reader in) {
        this.ungetted = false;
        this.reader = new LineNumberReader(in);
        this.lineNumber = -1;
    }
    
    public final String getNextLine() throws ParseException {
        if (this.reader == null) {
            return null;
        }
        if (this.ungetted) {
            this.ungetted = false;
            return this.lastLine;
        }
        String line;
        try {
            line = this.reader.readLine();
            if (line == null) {
                this.reader.close();
                this.reader = null;
            }
            else {
                this.lineNumber = this.reader.getLineNumber();
            }
        }
        catch (IOException e) {
            throw new ParseException(e);
        }
        return this.lastLine = line;
    }
    
    public final String getName() {
        return "";
    }
    
    public final int getLineNumber() {
        return this.lineNumber;
    }
    
    public final void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            }
            catch (IOException ex) {}
        }
        this.reader = null;
    }
    
    public final void ungetLine() throws IllegalStateException {
        if (this.ungetted) {
            throw new IllegalStateException("we support only one level of ungetLine()");
        }
        this.ungetted = true;
    }
    
    public final void unget(final String s) throws IllegalStateException {
        if (s == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (s.length() != 0) {
            this.ungetLine();
            this.lastLine = s;
        }
    }
}
