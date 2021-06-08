// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.io.OutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.io.File;
import java.nio.charset.Charset;
import org.apache.maven.surefire.shade.org.apache.commons.io.output.DeferredFileOutputStream;

class Utf8RecodingDeferredFileOutputStream
{
    private DeferredFileOutputStream deferredFileOutputStream;
    private static final Charset UTF8;
    
    public Utf8RecodingDeferredFileOutputStream(final String channel) {
        this.deferredFileOutputStream = new DeferredFileOutputStream(1000000, channel, "deferred", null);
    }
    
    public void write(final byte[] buf, final int off, final int len) throws IOException {
        if (!Charset.defaultCharset().equals(Utf8RecodingDeferredFileOutputStream.UTF8)) {
            final CharBuffer decodedFromDefaultCharset = Charset.defaultCharset().decode(ByteBuffer.wrap(buf, off, len));
            final ByteBuffer utf8Encoded = Utf8RecodingDeferredFileOutputStream.UTF8.encode(decodedFromDefaultCharset);
            if (utf8Encoded.hasArray()) {
                final byte[] convertedBytes = utf8Encoded.array();
                this.deferredFileOutputStream.write(convertedBytes, utf8Encoded.position(), utf8Encoded.remaining());
            }
            else {
                final byte[] convertedBytes = new byte[utf8Encoded.remaining()];
                utf8Encoded.get(convertedBytes, 0, utf8Encoded.remaining());
                this.deferredFileOutputStream.write(convertedBytes, 0, convertedBytes.length);
            }
        }
        else {
            this.deferredFileOutputStream.write(buf, off, len);
        }
    }
    
    public long getByteCount() {
        return this.deferredFileOutputStream.getByteCount();
    }
    
    public void close() throws IOException {
        this.deferredFileOutputStream.close();
    }
    
    public void writeTo(final OutputStream out) throws IOException {
        this.deferredFileOutputStream.writeTo(out);
    }
    
    public void free() {
        if (null != this.deferredFileOutputStream && null != this.deferredFileOutputStream.getFile()) {
            try {
                this.deferredFileOutputStream.close();
                if (!this.deferredFileOutputStream.getFile().delete()) {
                    this.deferredFileOutputStream.getFile().deleteOnExit();
                }
            }
            catch (IOException ioe) {
                this.deferredFileOutputStream.getFile().deleteOnExit();
            }
        }
    }
    
    static {
        UTF8 = Charset.forName("UTF-8");
    }
}
