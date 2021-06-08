// 
// Decompiled by Procyon v0.5.36
// 

package groovy.io;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.Writer;

public class PlatformLineWriter extends Writer
{
    private BufferedWriter writer;
    
    public PlatformLineWriter(final Writer out) {
        this.writer = new BufferedWriter(out);
    }
    
    public PlatformLineWriter(final Writer out, final int sz) {
        this.writer = new BufferedWriter(out, sz);
    }
    
    @Override
    public void write(final char[] cbuf, int off, int len) throws IOException {
        while (len > 0) {
            final char c = cbuf[off++];
            if (c == '\n') {
                this.writer.newLine();
            }
            else if (c != '\r') {
                this.writer.write(c);
            }
            --len;
        }
    }
    
    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }
    
    @Override
    public void close() throws IOException {
        this.writer.close();
    }
}
