// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.mudebug.prapr.reloc.commons.httpclient.ContentLengthInputStream;
import org.mudebug.prapr.reloc.commons.httpclient.ChunkedOutputStream;
import java.io.ByteArrayInputStream;
import org.mudebug.prapr.reloc.commons.httpclient.HttpException;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.logging.Log;

public abstract class EntityEnclosingMethod extends ExpectContinueMethod
{
    public static final int CONTENT_LENGTH_AUTO = -2;
    public static final int CONTENT_LENGTH_CHUNKED = -1;
    private static final Log LOG;
    private byte[] buffer;
    private InputStream requestStream;
    private String requestString;
    private byte[] contentCache;
    private int repeatCount;
    private int requestContentLength;
    
    public EntityEnclosingMethod() {
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2;
        this.setFollowRedirects(false);
    }
    
    public EntityEnclosingMethod(final String uri) {
        super(uri);
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2;
        this.setFollowRedirects(false);
    }
    
    public EntityEnclosingMethod(final String uri, final String tempDir) {
        super(uri, tempDir);
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2;
        this.setFollowRedirects(false);
    }
    
    public EntityEnclosingMethod(final String uri, final String tempDir, final String tempFile) {
        super(uri, tempDir, tempFile);
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2;
        this.setFollowRedirects(false);
    }
    
    protected boolean hasRequestContent() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
        return this.buffer != null || this.requestStream != null || this.requestString != null;
    }
    
    protected void clearRequestBody() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
        this.requestStream = null;
        this.requestString = null;
        this.buffer = null;
        this.contentCache = null;
    }
    
    protected byte[] generateRequestBody() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
        if (this.requestStream != null) {
            this.bufferContent();
        }
        if (this.buffer != null) {
            return this.buffer;
        }
        if (this.requestString != null) {
            return HttpConstants.getContentBytes(this.requestString, this.getRequestCharSet());
        }
        return null;
    }
    
    public boolean getFollowRedirects() {
        return false;
    }
    
    public void setFollowRedirects(final boolean followRedirects) {
        if (followRedirects) {}
        super.setFollowRedirects(false);
    }
    
    public void setRequestContentLength(final int length) {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        this.requestContentLength = length;
    }
    
    protected int getRequestContentLength() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
        if (!this.hasRequestContent()) {
            return 0;
        }
        if (this.requestContentLength != -2) {
            return this.requestContentLength;
        }
        if (this.contentCache == null) {
            this.contentCache = this.generateRequestBody();
        }
        return (this.contentCache == null) ? 0 : this.contentCache.length;
    }
    
    protected void addContentLengthRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        EntityEnclosingMethod.LOG.trace("enter HttpMethodBase.addContentLengthRequestHeader(HttpState, HttpConnection)");
        if (this.getRequestHeader("content-length") == null && this.getRequestHeader("Transfer-Encoding") == null) {
            final int len = this.getRequestContentLength();
            if (len >= 0) {
                this.addRequestHeader("Content-Length", String.valueOf(len));
            }
            else if (len == -1 && this.isHttp11()) {
                this.addRequestHeader("Transfer-Encoding", "chunked");
            }
        }
    }
    
    public void setRequestBody(final InputStream body) {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
        this.clearRequestBody();
        this.requestStream = body;
    }
    
    public InputStream getRequestBody() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.getRequestBody()");
        final byte[] content = this.generateRequestBody();
        if (content != null) {
            return new ByteArrayInputStream(content);
        }
        return new ByteArrayInputStream(new byte[0]);
    }
    
    public void setRequestBody(final String body) {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
        this.clearRequestBody();
        this.requestString = body;
    }
    
    public String getRequestBodyAsString() throws IOException {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.getRequestBodyAsString()");
        final byte[] content = this.generateRequestBody();
        if (content != null) {
            return HttpConstants.getContentString(content, this.getRequestCharSet());
        }
        return null;
    }
    
    protected boolean writeRequestBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
        if (!this.hasRequestContent()) {
            EntityEnclosingMethod.LOG.debug("Request body has not been specified");
            return true;
        }
        final int contentLength = this.getRequestContentLength();
        if (contentLength == -1 && !this.isHttp11()) {
            throw new HttpException("Chunked transfer encoding not allowed for HTTP/1.0");
        }
        InputStream instream = null;
        if (this.requestStream != null) {
            EntityEnclosingMethod.LOG.debug("Using unbuffered request body");
            instream = this.requestStream;
        }
        else {
            if (this.contentCache == null) {
                this.contentCache = this.generateRequestBody();
            }
            if (this.contentCache != null) {
                EntityEnclosingMethod.LOG.debug("Using buffered request body");
                instream = new ByteArrayInputStream(this.contentCache);
            }
        }
        if (instream == null) {
            EntityEnclosingMethod.LOG.debug("Request body is empty");
            return true;
        }
        if (this.repeatCount > 0 && this.contentCache == null) {
            throw new HttpException("Unbuffered entity enclosing request can not be repeated.");
        }
        ++this.repeatCount;
        OutputStream outstream = conn.getRequestOutputStream();
        if (contentLength == -1) {
            outstream = new ChunkedOutputStream(outstream);
        }
        if (contentLength >= 0) {
            instream = new ContentLengthInputStream(instream, contentLength);
        }
        final byte[] tmp = new byte[4096];
        int total = 0;
        int i = 0;
        while ((i = instream.read(tmp)) >= 0) {
            outstream.write(tmp, 0, i);
            total += i;
        }
        if (outstream instanceof ChunkedOutputStream) {
            ((ChunkedOutputStream)outstream).writeClosingChunk();
        }
        if (contentLength > 0 && total < contentLength) {
            throw new IOException("Unexpected end of input stream after " + total + " bytes (expected " + contentLength + " bytes)");
        }
        EntityEnclosingMethod.LOG.debug("Request body sent");
        return true;
    }
    
    public void recycle() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.recycle()");
        this.clearRequestBody();
        this.requestContentLength = -2;
        this.repeatCount = 0;
        super.recycle();
    }
    
    private void bufferContent() {
        EntityEnclosingMethod.LOG.trace("enter EntityEnclosingMethod.bufferContent()");
        if (this.buffer != null) {
            return;
        }
        if (this.requestStream != null) {
            try {
                final ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                final byte[] data = new byte[4096];
                int l = 0;
                while ((l = this.requestStream.read(data)) >= 0) {
                    tmp.write(data, 0, l);
                }
                this.buffer = tmp.toByteArray();
                this.requestStream = null;
            }
            catch (IOException e) {
                EntityEnclosingMethod.LOG.error(e.getMessage(), e);
                this.buffer = null;
                this.requestStream = null;
            }
        }
    }
    
    static {
        LOG = LogFactory.getLog(EntityEnclosingMethod.class);
    }
}
