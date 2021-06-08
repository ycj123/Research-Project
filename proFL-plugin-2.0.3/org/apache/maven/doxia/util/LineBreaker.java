// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.util;

import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.Writer;

public class LineBreaker
{
    public static final int DEFAULT_MAX_LINE_LENGTH = 78;
    private static final String EOL;
    private Writer destination;
    private BufferedWriter writer;
    private int maxLineLength;
    private int lineLength;
    private StringBuffer word;
    
    public LineBreaker(final Writer out) {
        this(out, 78);
    }
    
    public LineBreaker(final Writer out, final int max) {
        this.lineLength = 0;
        this.word = new StringBuffer(1024);
        if (max <= 0) {
            throw new IllegalArgumentException("maxLineLength <= 0");
        }
        this.destination = out;
        this.maxLineLength = max;
        this.writer = new BufferedWriter(out);
    }
    
    public Writer getDestination() {
        return this.destination;
    }
    
    public void write(final String text) throws IOException {
        this.write(text, false);
    }
    
    public void write(final String text, final boolean preserveSpace) {
        final int length = text.length();
        try {
            for (int i = 0; i < length; ++i) {
                final char c = text.charAt(i);
                final String os = System.getProperty("os.name").toLowerCase();
                switch (c) {
                    case ' ': {
                        if (preserveSpace) {
                            this.word.append(c);
                            break;
                        }
                        this.writeWord();
                        break;
                    }
                    case '\r': {
                        if (os.indexOf("windows") != -1) {
                            break;
                        }
                    }
                    case '\n': {
                        this.writeWord();
                        this.writer.write(LineBreaker.EOL);
                        this.lineLength = 0;
                        break;
                    }
                    default: {
                        this.word.append(c);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void flush() {
        try {
            this.writeWord();
            this.writer.flush();
        }
        catch (IOException ex) {}
    }
    
    private void writeWord() throws IOException {
        final int length = this.word.length();
        if (length > 0) {
            if (this.lineLength > 0) {
                if (this.lineLength + 1 + length > this.maxLineLength) {
                    this.writer.write(LineBreaker.EOL);
                    this.lineLength = 0;
                }
                else {
                    this.writer.write(32);
                    ++this.lineLength;
                }
            }
            this.writer.write(this.word.toString());
            this.word.setLength(0);
            this.lineLength += length;
        }
    }
    
    public void close() {
        IOUtil.close(this.writer);
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
