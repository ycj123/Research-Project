// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.IOException;
import java.io.InputStream;

public final class ChannelInputStream extends InputStream
{
    Channel c;
    boolean isClosed;
    boolean isEOF;
    boolean extendedFlag;
    
    ChannelInputStream(final Channel c, final boolean isExtended) {
        this.isClosed = false;
        this.isEOF = false;
        this.extendedFlag = false;
        this.c = c;
        this.extendedFlag = isExtended;
    }
    
    public int available() throws IOException {
        if (this.isEOF) {
            return 0;
        }
        final int avail = this.c.cm.getAvailable(this.c, this.extendedFlag);
        return (avail > 0) ? avail : 0;
    }
    
    public void close() throws IOException {
        this.isClosed = true;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0 || off > b.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.isEOF) {
            return -1;
        }
        final int ret = this.c.cm.getChannelData(this.c, this.extendedFlag, b, off, len);
        if (ret == -1) {
            this.isEOF = true;
        }
        return ret;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
    
    public int read() throws IOException {
        final byte[] b = { 0 };
        final int ret = this.read(b, 0, 1);
        if (ret != 1) {
            return -1;
        }
        return b[0] & 0xFF;
    }
}
