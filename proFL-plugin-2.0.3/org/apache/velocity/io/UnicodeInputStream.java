// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.io;

import org.apache.velocity.util.ExceptionUtils;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.InputStream;

public class UnicodeInputStream extends InputStream
{
    public static final UnicodeBOM UTF8_BOM;
    public static final UnicodeBOM UTF16LE_BOM;
    public static final UnicodeBOM UTF16BE_BOM;
    public static final UnicodeBOM UTF32LE_BOM;
    public static final UnicodeBOM UTF32BE_BOM;
    private static final int MAX_BOM_SIZE = 4;
    private byte[] buf;
    private int pos;
    private final String encoding;
    private final boolean skipBOM;
    private final PushbackInputStream inputStream;
    
    public UnicodeInputStream(final InputStream inputStream) throws IllegalStateException, IOException {
        this(inputStream, true);
    }
    
    public UnicodeInputStream(final InputStream inputStream, final boolean skipBOM) throws IllegalStateException, IOException {
        this.buf = new byte[4];
        this.pos = 0;
        this.skipBOM = skipBOM;
        this.inputStream = new PushbackInputStream(inputStream, 4);
        try {
            this.encoding = this.readEncoding();
        }
        catch (IOException ioe) {
            final IllegalStateException ex = new IllegalStateException("Could not read BOM from Stream");
            ExceptionUtils.setCause(ex, ioe);
            throw ex;
        }
    }
    
    public boolean isSkipBOM() {
        return this.skipBOM;
    }
    
    public String getEncodingFromStream() {
        return this.encoding;
    }
    
    protected String readEncoding() throws IOException {
        this.pos = 0;
        UnicodeBOM encoding = null;
        if (this.readByte()) {
            switch (this.buf[0]) {
                case 0: {
                    encoding = this.match(UnicodeInputStream.UTF32BE_BOM, null);
                    break;
                }
                case -17: {
                    encoding = this.match(UnicodeInputStream.UTF8_BOM, null);
                    break;
                }
                case -2: {
                    encoding = this.match(UnicodeInputStream.UTF16BE_BOM, null);
                    break;
                }
                case -1: {
                    encoding = this.match(UnicodeInputStream.UTF16LE_BOM, null);
                    if (encoding != null) {
                        encoding = this.match(UnicodeInputStream.UTF32LE_BOM, encoding);
                        break;
                    }
                    break;
                }
                default: {
                    encoding = null;
                    break;
                }
            }
        }
        this.pushback(encoding);
        return (encoding != null) ? encoding.getEncoding() : null;
    }
    
    private final UnicodeBOM match(final UnicodeBOM matchEncoding, final UnicodeBOM noMatchEncoding) throws IOException {
        final byte[] bom = matchEncoding.getBytes();
        for (int i = 0; i < bom.length; ++i) {
            if (this.pos <= i && !this.readByte()) {
                return noMatchEncoding;
            }
            if (bom[i] != this.buf[i]) {
                return noMatchEncoding;
            }
        }
        return matchEncoding;
    }
    
    private final boolean readByte() throws IOException {
        final int res = this.inputStream.read();
        if (res == -1) {
            return false;
        }
        if (this.pos >= this.buf.length) {
            throw new IOException("BOM read error");
        }
        this.buf[this.pos++] = (byte)res;
        return true;
    }
    
    private final void pushback(final UnicodeBOM matchBOM) throws IOException {
        int count = this.pos;
        int start = 0;
        if (matchBOM != null && this.skipBOM) {
            start = matchBOM.getBytes().length;
            count = this.pos - start;
            if (count < 0) {
                throw new IllegalStateException("Match has more bytes than available!");
            }
        }
        this.inputStream.unread(this.buf, start, count);
    }
    
    public void close() throws IOException {
        this.inputStream.close();
    }
    
    public int available() throws IOException {
        return this.inputStream.available();
    }
    
    public void mark(final int readlimit) {
        this.inputStream.mark(readlimit);
    }
    
    public boolean markSupported() {
        return this.inputStream.markSupported();
    }
    
    public int read() throws IOException {
        return this.inputStream.read();
    }
    
    public int read(final byte[] b) throws IOException {
        return this.inputStream.read(b);
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return this.inputStream.read(b, off, len);
    }
    
    public void reset() throws IOException {
        this.inputStream.reset();
    }
    
    public long skip(final long n) throws IOException {
        return this.inputStream.skip(n);
    }
    
    static {
        UTF8_BOM = new UnicodeBOM("UTF-8", new byte[] { -17, -69, -65 });
        UTF16LE_BOM = new UnicodeBOM("UTF-16LE", new byte[] { -1, -2 });
        UTF16BE_BOM = new UnicodeBOM("UTF-16BE", new byte[] { -2, -1 });
        UTF32LE_BOM = new UnicodeBOM("UTF-32LE", new byte[] { -1, -2, 0, 0 });
        UTF32BE_BOM = new UnicodeBOM("UTF-32BE", new byte[] { 0, 0, -2, -1 });
    }
    
    static final class UnicodeBOM
    {
        private final String encoding;
        private final byte[] bytes;
        
        private UnicodeBOM(final String encoding, final byte[] bytes) {
            this.encoding = encoding;
            this.bytes = bytes;
        }
        
        String getEncoding() {
            return this.encoding;
        }
        
        byte[] getBytes() {
            return this.bytes;
        }
    }
}
