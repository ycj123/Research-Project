// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class LoggedDataOutputStream extends FilterOutputStream
{
    private long counter;
    
    public LoggedDataOutputStream(final OutputStream out) {
        super(out);
    }
    
    public void writeChars(final String s) throws IOException {
        this.writeBytes(s);
    }
    
    public void writeBytes(final String s) throws IOException {
        final byte[] bytes = s.getBytes();
        this.out.write(bytes);
        Logger.logOutput(bytes);
        this.counter += bytes.length;
    }
    
    public void writeBytes(final String s, final String charsetName) throws IOException {
        final byte[] bytes = s.getBytes(charsetName);
        this.out.write(bytes);
        Logger.logOutput(bytes);
        this.counter += bytes.length;
    }
    
    public void write(final int b) throws IOException {
        super.write(b);
        ++this.counter;
    }
    
    public void write(final byte[] b) throws IOException {
        super.write(b);
        this.counter += b.length;
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        super.write(b, off, len);
        this.counter += len;
    }
    
    public void close() throws IOException {
        this.out.close();
    }
    
    public OutputStream getUnderlyingStream() {
        return this.out;
    }
    
    public void setUnderlyingStream(final OutputStream out) {
        this.out = out;
    }
    
    public long getCounter() {
        return this.counter;
    }
}
