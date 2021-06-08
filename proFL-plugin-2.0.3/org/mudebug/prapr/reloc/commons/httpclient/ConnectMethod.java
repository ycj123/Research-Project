// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class ConnectMethod extends HttpMethodBase
{
    public static final String NAME = "CONNECT";
    private static final Log LOG;
    private HttpMethod method;
    
    public ConnectMethod(final HttpMethod method) {
        ConnectMethod.LOG.trace("enter ConnectMethod(HttpMethod)");
        this.method = method;
    }
    
    public String getName() {
        return "CONNECT";
    }
    
    protected void addAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
    }
    
    protected void addContentLengthRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
    }
    
    protected void addCookieRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        ConnectMethod.LOG.trace("enter ConnectMethod.addRequestHeaders(HttpState, HttpConnection)");
        this.addUserAgentRequestHeader(state, conn);
        this.addHostRequestHeader(state, conn);
        this.addProxyAuthorizationRequestHeader(state, conn);
        this.addProxyConnectionHeader(state, conn);
    }
    
    public int execute(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        ConnectMethod.LOG.trace("enter ConnectMethod.execute(HttpState, HttpConnection)");
        int code = super.execute(state, conn);
        ConnectMethod.LOG.debug("CONNECT status code " + code);
        if (code >= 200 && code < 300) {
            conn.tunnelCreated();
            code = this.method.execute(state, conn);
        }
        else {
            ConnectMethod.LOG.debug("CONNECT failed, fake the response for the original method");
            if (this.method instanceof HttpMethodBase) {
                ((HttpMethodBase)this.method).fakeResponse(this.getStatusLine(), this.getResponseHeaderGroup(), this.getResponseStream());
            }
            else {
                this.releaseConnection();
            }
        }
        return code;
    }
    
    protected void writeRequestLine(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        int port = conn.getPort();
        if (port == -1) {
            port = conn.getProtocol().getDefaultPort();
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append(this.getName());
        buffer.append(' ');
        buffer.append(conn.getHost());
        if (port > -1) {
            buffer.append(':');
            buffer.append(port);
        }
        buffer.append(" HTTP/1.1");
        final String line = buffer.toString();
        conn.printLine(line);
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output(line);
        }
    }
    
    protected boolean shouldCloseConnection(final HttpConnection conn) {
        if (this.getStatusCode() == 200) {
            Header connectionHeader = null;
            if (!conn.isTransparent()) {
                connectionHeader = this.getResponseHeader("proxy-connection");
            }
            if (connectionHeader == null) {
                connectionHeader = this.getResponseHeader("connection");
            }
            if (connectionHeader != null && connectionHeader.getValue().equalsIgnoreCase("close") && ConnectMethod.LOG.isWarnEnabled()) {
                ConnectMethod.LOG.warn("Invalid header encountered '" + connectionHeader.toExternalForm() + "' in response " + this.getStatusLine().toString());
            }
            return false;
        }
        return super.shouldCloseConnection(conn);
    }
    
    static {
        LOG = LogFactory.getLog(ConnectMethod.class);
    }
}
