// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.IOException;
import java.io.OutputStream;

public final class ChannelOutputStream extends OutputStream
{
    Channel c;
    boolean isClosed;
    
    ChannelOutputStream(final Channel c) {
        this.isClosed = false;
        this.c = c;
    }
    
    public void write(final int b) throws IOException {
        final byte[] buff = { (byte)b };
        this.write(buff, 0, 1);
    }
    
    public void close() throws IOException {
        if (!this.isClosed) {
            this.isClosed = true;
            this.c.cm.sendEOF(this.c);
        }
    }
    
    public void flush() throws IOException {
        if (this.isClosed) {
            throw new IOException("This OutputStream is closed.");
        }
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (this.isClosed) {
            throw new IOException("This OutputStream is closed.");
        }
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0 || off > b.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }
        this.c.cm.sendData(this.c, b, off, len);
    }
    
    public void write(final byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }
}
