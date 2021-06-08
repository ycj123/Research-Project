// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.io.InputStream;

public class ResponseInputStream extends InputStream
{
    public static final Log LOG;
    private boolean closed;
    private boolean chunk;
    private boolean endChunk;
    private byte[] buffer;
    private int length;
    private int pos;
    private int count;
    private int contentLength;
    private InputStream stream;
    
    public ResponseInputStream(final InputStream stream, final boolean chunked, final int contentLength) {
        this.closed = false;
        this.chunk = false;
        this.endChunk = false;
        this.buffer = null;
        this.length = 0;
        this.pos = 0;
        this.count = 0;
        this.contentLength = -1;
        this.stream = null;
        ResponseInputStream.LOG.trace("enter ResponseInputStream(InputStream, boolean, int)");
        if (null == stream) {
            throw new NullPointerException("InputStream parameter is null");
        }
        this.closed = false;
        this.count = 0;
        this.chunk = chunked;
        this.contentLength = contentLength;
        this.stream = stream;
    }
    
    public ResponseInputStream(final InputStream stream, final HttpMethod method) {
        this.closed = false;
        this.chunk = false;
        this.endChunk = false;
        this.buffer = null;
        this.length = 0;
        this.pos = 0;
        this.count = 0;
        this.contentLength = -1;
        this.stream = null;
        ResponseInputStream.LOG.trace("enter ResponseInputStream(InputStream, HttpMethod)");
        if (null == stream) {
            throw new NullPointerException("InputStream parameter is null");
        }
        if (null == method) {
            throw new NullPointerException("HttpMethod parameter is null");
        }
        this.closed = false;
        this.count = 0;
        final Header transferEncoding = method.getResponseHeader("transfer-encoding");
        if (null != transferEncoding && transferEncoding.getValue().toLowerCase().indexOf("chunked") != -1) {
            this.chunk = true;
        }
        final Header contentLengthHeader = method.getResponseHeader("content-length");
        if (null != contentLengthHeader) {
            try {
                this.contentLength = Integer.parseInt(contentLengthHeader.getValue());
            }
            catch (NumberFormatException ex) {}
        }
        this.stream = stream;
    }
    
    public void close() throws IOException {
        ResponseInputStream.LOG.trace("enter ResponseInputStream.close()");
        if (!this.closed) {
            try {
                if (this.chunk) {
                    while (!this.endChunk) {
                        final int b = this.read();
                        if (b < 0) {
                            break;
                        }
                    }
                }
                else if (this.length > 0) {
                    while (this.count < this.length) {
                        final int b = this.read();
                        if (b < 0) {
                            break;
                        }
                    }
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            finally {
                this.closed = true;
            }
        }
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        ResponseInputStream.LOG.trace("enter ResponseInputStream.read(byte, int, int)");
        int avail = this.length - this.pos;
        if (avail == 0 && !this.fillBuffer()) {
            return -1;
        }
        avail = this.length - this.pos;
        if (avail == 0) {
            return -1;
        }
        int toCopy = avail;
        if (toCopy < 0) {
            return -1;
        }
        if (avail > len) {
            toCopy = len;
        }
        System.arraycopy(this.buffer, this.pos, b, off, toCopy);
        this.pos += toCopy;
        return toCopy;
    }
    
    public int read() throws IOException {
        ResponseInputStream.LOG.trace("enter ResponseInputStream.read()");
        if (this.pos == this.length && !this.fillBuffer()) {
            return -1;
        }
        return this.buffer[this.pos++] & 0xFF;
    }
    
    private boolean fillBuffer() throws IOException {
        ResponseInputStream.LOG.trace("enter ResponseInputStream.fillBuffer()");
        if (this.closed) {
            return false;
        }
        if (this.endChunk) {
            return false;
        }
        if (this.contentLength >= 0 && this.count >= this.contentLength) {
            return false;
        }
        this.pos = 0;
        if (this.chunk) {
            try {
                final String numberValue = this.readLineFromStream();
                if (numberValue == null) {
                    throw new NumberFormatException("unable to find chunk length");
                }
                this.length = Integer.parseInt(numberValue.trim(), 16);
            }
            catch (NumberFormatException e) {
                this.length = -1;
                this.chunk = false;
                this.endChunk = true;
                this.closed = true;
                return false;
            }
            if (this.length == 0) {
                for (String trailingLine = this.readLineFromStream(); !trailingLine.equals(""); trailingLine = this.readLineFromStream()) {}
                this.endChunk = true;
                return false;
            }
            if (this.buffer == null || this.length > this.buffer.length) {
                this.buffer = new byte[this.length];
            }
            for (int nbRead = 0, currentRead = 0; nbRead < this.length; nbRead += currentRead) {
                try {
                    currentRead = this.stream.read(this.buffer, nbRead, this.length - nbRead);
                }
                catch (Throwable t) {
                    ResponseInputStream.LOG.debug("Exception thrown reading chunk from response", t);
                    throw new IOException();
                }
                if (currentRead < 0) {
                    throw new IOException("Not enough bytes read");
                }
            }
            this.readLineFromStream();
        }
        else {
            try {
                if (this.buffer == null) {
                    this.buffer = new byte[4096];
                }
                this.length = this.stream.read(this.buffer);
                this.count += this.length;
            }
            catch (Throwable t2) {
                ResponseInputStream.LOG.debug("Exception thrown reading from response", t2);
                throw new IOException(t2.getMessage());
            }
        }
        return true;
    }
    
    private String readLineFromStream() throws IOException {
        ResponseInputStream.LOG.trace("enter ResponseInputStream.ReadLineFromStream()");
        final StringBuffer sb = new StringBuffer();
        while (true) {
            final int ch = this.stream.read();
            if (ch < 0) {
                if (sb.length() == 0) {
                    return null;
                }
                break;
            }
            else {
                if (ch == 13) {
                    continue;
                }
                if (ch == 10) {
                    break;
                }
                sb.append((char)ch);
            }
        }
        return sb.toString();
    }
    
    static {
        LOG = LogFactory.getLog(ResponseInputStream.class);
    }
}
