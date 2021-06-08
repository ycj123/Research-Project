// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.io.OutputStream;

public class RequestOutputStream extends OutputStream
{
    private static final Log LOG;
    private boolean closed;
    private OutputStream stream;
    private boolean useChunking;
    private static final byte[] CRLF;
    private static final byte[] ENDCHUNK;
    private static final byte[] ZERO;
    private static final byte[] ONE;
    
    public RequestOutputStream(final OutputStream stream) {
        this(stream, false);
    }
    
    public RequestOutputStream(final OutputStream stream, final boolean useChunking) {
        this.closed = false;
        this.stream = null;
        this.useChunking = false;
        if (stream == null) {
            throw new NullPointerException("stream parameter is null");
        }
        this.stream = stream;
        this.useChunking = useChunking;
    }
    
    public void setUseChunking(final boolean useChunking) {
        this.useChunking = useChunking;
    }
    
    public boolean isUseChunking() {
        return this.useChunking;
    }
    
    public void print(String s) throws IOException {
        RequestOutputStream.LOG.trace("enter RequestOutputStream.print(String)");
        if (s == null) {
            s = "null";
        }
        for (int len = s.length(), i = 0; i < len; ++i) {
            this.write(s.charAt(i));
        }
    }
    
    public void println() throws IOException {
        this.print("\r\n");
    }
    
    public void println(final String s) throws IOException {
        this.print(s);
        this.println();
    }
    
    public void write(final int b) throws IOException {
        if (this.useChunking) {
            this.stream.write(RequestOutputStream.ONE, 0, RequestOutputStream.ONE.length);
            this.stream.write(RequestOutputStream.CRLF, 0, RequestOutputStream.CRLF.length);
            this.stream.write(b);
            this.stream.write(RequestOutputStream.ENDCHUNK, 0, RequestOutputStream.ENDCHUNK.length);
        }
        else {
            this.stream.write(b);
        }
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        RequestOutputStream.LOG.trace("enter RequestOutputStream.write(byte[], int, int)");
        if (this.useChunking) {
            final byte[] chunkHeader = HttpConstants.getBytes(Integer.toHexString(len) + "\r\n");
            this.stream.write(chunkHeader, 0, chunkHeader.length);
            this.stream.write(b, off, len);
            this.stream.write(RequestOutputStream.ENDCHUNK, 0, RequestOutputStream.ENDCHUNK.length);
        }
        else {
            this.stream.write(b, off, len);
        }
    }
    
    public void close() throws IOException {
        RequestOutputStream.LOG.trace("enter RequestOutputStream.close()");
        if (!this.closed) {
            try {
                if (this.useChunking) {
                    this.stream.write(RequestOutputStream.ZERO, 0, RequestOutputStream.ZERO.length);
                    this.stream.write(RequestOutputStream.CRLF, 0, RequestOutputStream.CRLF.length);
                    this.stream.write(RequestOutputStream.ENDCHUNK, 0, RequestOutputStream.ENDCHUNK.length);
                }
            }
            catch (IOException ioe) {
                RequestOutputStream.LOG.debug("Unexpected exception caught when closing output  stream", ioe);
                throw ioe;
            }
            finally {
                this.closed = true;
                super.close();
            }
        }
    }
    
    static {
        LOG = LogFactory.getLog(RequestOutputStream.class);
        CRLF = new byte[] { 13, 10 };
        ENDCHUNK = RequestOutputStream.CRLF;
        ZERO = new byte[] { 48 };
        ONE = new byte[] { 49 };
    }
}
