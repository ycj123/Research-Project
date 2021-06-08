// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.OutputStream;
import org.mudebug.prapr.reloc.commons.httpclient.HttpException;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import java.io.FileNotFoundException;
import org.mudebug.prapr.reloc.commons.httpclient.methods.multipart.FilePart;
import java.io.File;
import org.mudebug.prapr.reloc.commons.httpclient.methods.multipart.Part;
import org.mudebug.prapr.reloc.commons.httpclient.methods.multipart.StringPart;
import java.util.ArrayList;
import java.util.List;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class MultipartPostMethod extends ExpectContinueMethod
{
    public static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    private static final Log LOG;
    private final List parameters;
    
    public MultipartPostMethod() {
        this.parameters = new ArrayList();
    }
    
    public MultipartPostMethod(final String uri) {
        super(uri);
        this.parameters = new ArrayList();
    }
    
    public MultipartPostMethod(final String uri, final String tempDir) {
        super(uri, tempDir);
        this.parameters = new ArrayList();
    }
    
    public MultipartPostMethod(final String uri, final String tempDir, final String tempFile) {
        super(uri, tempDir, tempFile);
        this.parameters = new ArrayList();
    }
    
    protected boolean hasRequestContent() {
        return true;
    }
    
    public String getName() {
        return "POST";
    }
    
    public void addParameter(final String parameterName, final String parameterValue) {
        MultipartPostMethod.LOG.trace("enter addParameter(String parameterName, String parameterValue)");
        final Part param = new StringPart(parameterName, parameterValue);
        this.parameters.add(param);
    }
    
    public void addParameter(final String parameterName, final File parameterFile) throws FileNotFoundException {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, File parameterFile)");
        final Part param = new FilePart(parameterName, parameterFile);
        this.parameters.add(param);
    }
    
    public void addParameter(final String parameterName, final String fileName, final File parameterFile) throws FileNotFoundException {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, String fileName, File parameterFile)");
        final Part param = new FilePart(parameterName, fileName, parameterFile);
        this.parameters.add(param);
    }
    
    public void addPart(final Part part) {
        MultipartPostMethod.LOG.trace("enter addPart(Part part)");
        this.parameters.add(part);
    }
    
    public Part[] getParts() {
        return this.parameters.toArray(new Part[this.parameters.size()]);
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.addRequestHeaders(HttpState state, HttpConnection conn)");
        super.addRequestHeaders(state, conn);
        if (!this.parameters.isEmpty()) {
            final StringBuffer buffer = new StringBuffer("multipart/form-data");
            if (Part.getBoundary() != null) {
                buffer.append("; boundary=");
                buffer.append(Part.getBoundary());
            }
            this.setRequestHeader("Content-Type", buffer.toString());
        }
    }
    
    protected boolean writeRequestBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.writeRequestBody(HttpState state, HttpConnection conn)");
        final OutputStream out = conn.getRequestOutputStream();
        Part.sendParts(out, this.getParts());
        return true;
    }
    
    protected int getRequestContentLength() {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.getRequestContentLength()");
        try {
            final long len = Part.getLengthOfParts(this.getParts());
            if (len <= 2147483647L) {
                return (int)len;
            }
            return Integer.MAX_VALUE;
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public void recycle() {
        MultipartPostMethod.LOG.trace("enter MultipartPostMethod.recycle()");
        super.recycle();
        this.parameters.clear();
    }
    
    static {
        LOG = LogFactory.getLog(MultipartPostMethod.class);
    }
}
