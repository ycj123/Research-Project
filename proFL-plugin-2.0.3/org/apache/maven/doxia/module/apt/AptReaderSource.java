// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.apt;

import java.io.IOException;
import java.io.Reader;
import java.io.LineNumberReader;

public class AptReaderSource implements AptSource
{
    private LineNumberReader reader;
    private int lineNumber;
    
    public AptReaderSource(final Reader in) {
        this.reader = new LineNumberReader(in);
        this.lineNumber = -1;
    }
    
    public String getNextLine() throws AptParseException {
        if (this.reader == null) {
            return null;
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
            throw new AptParseException(e);
        }
        return line;
    }
    
    public String getName() {
        return "";
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            }
            catch (IOException ex) {}
        }
        this.reader = null;
    }
}
