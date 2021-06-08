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
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;

public class HeadMethod extends HttpMethodBase
{
    private static final Log LOG;
    private int bodyCheckTimeout;
    
    public HeadMethod() {
        this.bodyCheckTimeout = -1;
        this.setFollowRedirects(true);
    }
    
    public HeadMethod(final String uri) {
        super(uri);
        this.bodyCheckTimeout = -1;
        this.setFollowRedirects(true);
    }
    
    public String getName() {
        return "HEAD";
    }
    
    public void recycle() {
        super.recycle();
        this.setFollowRedirects(true);
    }
    
    protected void readResponseBody(final HttpState state, final HttpConnection conn) throws IOException {
        HeadMethod.LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
        if (this.bodyCheckTimeout < 0) {
            this.responseBodyConsumed();
        }
        else {
            if (HeadMethod.LOG.isDebugEnabled()) {
                HeadMethod.LOG.debug("Check for non-compliant response body. Timeout in " + this.bodyCheckTimeout + " ms");
            }
            boolean responseAvailable = false;
            try {
                responseAvailable = conn.isResponseAvailable(this.bodyCheckTimeout);
            }
            catch (IOException e) {
                HeadMethod.LOG.debug("An IOException occurred while testing if a response was available, we will assume one is not.", e);
                responseAvailable = false;
            }
            if (responseAvailable) {
                if (this.isStrictMode()) {
                    throw new HttpException("Body content may not be sent in response to HTTP HEAD request");
                }
                HeadMethod.LOG.warn("Body content returned in response to HTTP HEAD");
                super.readResponseBody(state, conn);
            }
        }
    }
    
    public int getBodyCheckTimeout() {
        return this.bodyCheckTimeout;
    }
    
    public void setBodyCheckTimeout(final int timeout) {
        this.bodyCheckTimeout = timeout;
    }
    
    static {
        LOG = LogFactory.getLog(HeadMethod.class);
    }
}
