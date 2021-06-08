// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.HttpException;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import org.mudebug.prapr.reloc.commons.logging.Log;

public abstract class ExpectContinueMethod extends GetMethod
{
    private boolean useExpectHeader;
    private static final Log LOG;
    
    public ExpectContinueMethod() {
        this.useExpectHeader = false;
    }
    
    public ExpectContinueMethod(final String uri) {
        super(uri);
        this.useExpectHeader = false;
    }
    
    public ExpectContinueMethod(final String uri, final String tempDir) {
        super(uri, tempDir);
        this.useExpectHeader = false;
    }
    
    public ExpectContinueMethod(final String uri, final String tempDir, final String tempFile) {
        super(uri, tempDir, tempFile);
        this.useExpectHeader = false;
    }
    
    public boolean getUseExpectHeader() {
        return this.useExpectHeader;
    }
    
    public void setUseExpectHeader(final boolean value) {
        this.useExpectHeader = value;
    }
    
    protected abstract boolean hasRequestContent();
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        ExpectContinueMethod.LOG.trace("enter ExpectContinueMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(state, conn);
        final boolean headerPresent = this.getRequestHeader("Expect") != null;
        if (this.getUseExpectHeader() && this.isHttp11() && this.hasRequestContent()) {
            if (!headerPresent) {
                this.setRequestHeader("Expect", "100-continue");
            }
        }
        else if (headerPresent) {
            this.removeRequestHeader("Expect");
        }
    }
    
    static {
        LOG = LogFactory.getLog(ExpectContinueMethod.class);
    }
}
