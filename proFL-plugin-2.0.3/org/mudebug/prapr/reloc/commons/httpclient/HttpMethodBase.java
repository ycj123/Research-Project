// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.auth.MalformedChallengeException;
import java.io.InterruptedIOException;
import org.mudebug.prapr.reloc.commons.httpclient.cookie.MalformedCookieException;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;
import org.mudebug.prapr.reloc.commons.httpclient.cookie.CookieSpec;
import org.mudebug.prapr.reloc.commons.httpclient.cookie.CookiePolicy;
import org.mudebug.prapr.reloc.commons.httpclient.auth.NTLMScheme;
import java.util.HashSet;
import org.mudebug.prapr.reloc.commons.httpclient.auth.AuthenticationException;
import org.mudebug.prapr.reloc.commons.httpclient.auth.HttpAuthenticator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import org.mudebug.prapr.reloc.commons.httpclient.util.EncodingUtil;
import java.io.InputStream;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.httpclient.auth.AuthScheme;
import org.mudebug.prapr.reloc.commons.logging.Log;

public abstract class HttpMethodBase implements HttpMethod
{
    private static final int MAX_FORWARDS = 100;
    private static final Log LOG;
    protected static final Header USER_AGENT;
    private HeaderGroup requestHeaders;
    private StatusLine statusLine;
    private HeaderGroup responseHeaders;
    private HeaderGroup responseTrailerHeaders;
    private AuthScheme authScheme;
    private Set realms;
    private String realm;
    private AuthScheme proxyAuthScheme;
    private Set proxyRealms;
    private String proxyRealm;
    private String path;
    private String queryString;
    private InputStream responseStream;
    private HttpConnection responseConnection;
    private byte[] responseBody;
    private boolean followRedirects;
    private boolean doAuthentication;
    private boolean http11;
    private boolean strictMode;
    private boolean used;
    private int recoverableExceptionCount;
    private HostConfiguration hostConfiguration;
    private MethodRetryHandler methodRetryHandler;
    private boolean inExecute;
    private boolean doneWithConnection;
    private boolean connectionCloseForced;
    private static final int RESPONSE_WAIT_TIME_MS = 3000;
    private static final int BUFFER_WARN_TRIGGER_LIMIT = 1048576;
    private static final int DEFAULT_INITIAL_BUFFER_SIZE = 4096;
    
    public HttpMethodBase() {
        this.requestHeaders = new HeaderGroup();
        this.statusLine = null;
        this.responseHeaders = new HeaderGroup();
        this.responseTrailerHeaders = new HeaderGroup();
        this.authScheme = null;
        this.realms = null;
        this.realm = null;
        this.proxyAuthScheme = null;
        this.proxyRealms = null;
        this.proxyRealm = null;
        this.path = null;
        this.queryString = null;
        this.responseStream = null;
        this.responseConnection = null;
        this.responseBody = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.http11 = true;
        this.strictMode = false;
        this.used = false;
        this.recoverableExceptionCount = 0;
        this.inExecute = false;
        this.doneWithConnection = false;
        this.connectionCloseForced = false;
    }
    
    public HttpMethodBase(String uri) throws IllegalArgumentException, IllegalStateException {
        this.requestHeaders = new HeaderGroup();
        this.statusLine = null;
        this.responseHeaders = new HeaderGroup();
        this.responseTrailerHeaders = new HeaderGroup();
        this.authScheme = null;
        this.realms = null;
        this.realm = null;
        this.proxyAuthScheme = null;
        this.proxyRealms = null;
        this.proxyRealm = null;
        this.path = null;
        this.queryString = null;
        this.responseStream = null;
        this.responseConnection = null;
        this.responseBody = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.http11 = true;
        this.strictMode = false;
        this.used = false;
        this.recoverableExceptionCount = 0;
        this.inExecute = false;
        this.doneWithConnection = false;
        this.connectionCloseForced = false;
        try {
            if (uri == null || uri.equals("")) {
                uri = "/";
            }
            final URI parsedURI = new URI(uri.toCharArray());
            if (parsedURI.isAbsoluteURI()) {
                (this.hostConfiguration = new HostConfiguration()).setHost(parsedURI.getHost(), parsedURI.getPort(), parsedURI.getScheme());
            }
            this.setPath((parsedURI.getPath() == null) ? "/" : parsedURI.getEscapedPath());
            this.setQueryString(parsedURI.getEscapedQuery());
        }
        catch (URIException e) {
            throw new IllegalArgumentException("Invalid uri '" + uri + "': " + e.getMessage());
        }
    }
    
    public abstract String getName();
    
    public URI getURI() throws URIException {
        if (this.hostConfiguration == null) {
            final URI tmpUri = new URI(null, null, this.path, null, null);
            tmpUri.setEscapedQuery(this.queryString);
            return tmpUri;
        }
        int port = this.hostConfiguration.getPort();
        if (port == this.hostConfiguration.getProtocol().getDefaultPort()) {
            port = -1;
        }
        final URI tmpUri2 = new URI(this.hostConfiguration.getProtocol().getScheme(), null, this.hostConfiguration.getHost(), port, this.path, null);
        tmpUri2.setEscapedQuery(this.queryString);
        return tmpUri2;
    }
    
    public void setFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
    
    public boolean getFollowRedirects() {
        return this.followRedirects;
    }
    
    public void setHttp11(final boolean http11) {
        this.http11 = http11;
    }
    
    public boolean getDoAuthentication() {
        return this.doAuthentication;
    }
    
    public void setDoAuthentication(final boolean doAuthentication) {
        this.doAuthentication = doAuthentication;
    }
    
    public boolean isHttp11() {
        return this.http11;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public void addRequestHeader(final Header header) {
        HttpMethodBase.LOG.trace("HttpMethodBase.addRequestHeader(Header)");
        if (header == null) {
            HttpMethodBase.LOG.debug("null header value ignored");
        }
        else {
            this.getRequestHeaderGroup().addHeader(header);
        }
    }
    
    public void addResponseFooter(final Header footer) {
        this.getResponseTrailerHeaderGroup().addHeader(footer);
    }
    
    public String getPath() {
        return (this.path == null || this.path.equals("")) ? "/" : this.path;
    }
    
    public void setQueryString(final String queryString) {
        this.queryString = queryString;
    }
    
    public void setQueryString(final NameValuePair[] params) {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.setQueryString(NameValuePair[])");
        this.queryString = EncodingUtil.formUrlEncode(params, "UTF-8");
    }
    
    public String getQueryString() {
        return this.queryString;
    }
    
    public void setRequestHeader(final String headerName, final String headerValue) {
        final Header header = new Header(headerName, headerValue);
        this.setRequestHeader(header);
    }
    
    public void setRequestHeader(final Header header) {
        final Header[] headers = this.getRequestHeaderGroup().getHeaders(header.getName());
        for (int i = 0; i < headers.length; ++i) {
            this.getRequestHeaderGroup().removeHeader(headers[i]);
        }
        this.getRequestHeaderGroup().addHeader(header);
    }
    
    public Header getRequestHeader(final String headerName) {
        if (headerName == null) {
            return null;
        }
        return this.getRequestHeaderGroup().getCondensedHeader(headerName);
    }
    
    public Header[] getRequestHeaders() {
        return this.getRequestHeaderGroup().getAllHeaders();
    }
    
    protected HeaderGroup getRequestHeaderGroup() {
        return this.requestHeaders;
    }
    
    protected HeaderGroup getResponseTrailerHeaderGroup() {
        return this.responseTrailerHeaders;
    }
    
    protected HeaderGroup getResponseHeaderGroup() {
        return this.responseHeaders;
    }
    
    public int getStatusCode() {
        return this.statusLine.getStatusCode();
    }
    
    public StatusLine getStatusLine() {
        return this.statusLine;
    }
    
    private boolean responseAvailable() {
        return this.responseBody != null || this.responseStream != null;
    }
    
    public Header[] getResponseHeaders() {
        return this.getResponseHeaderGroup().getAllHeaders();
    }
    
    public Header getResponseHeader(final String headerName) {
        if (headerName == null) {
            return null;
        }
        return this.getResponseHeaderGroup().getCondensedHeader(headerName);
    }
    
    protected int getResponseContentLength() {
        final Header[] headers = this.getResponseHeaderGroup().getHeaders("Content-Length");
        if (headers.length == 0) {
            return -1;
        }
        if (headers.length > 1) {
            HttpMethodBase.LOG.warn("Multiple content-length headers detected");
        }
        int i = headers.length - 1;
        while (i >= 0) {
            final Header header = headers[i];
            try {
                return Integer.parseInt(header.getValue());
            }
            catch (NumberFormatException e) {
                if (HttpMethodBase.LOG.isWarnEnabled()) {
                    HttpMethodBase.LOG.warn("Invalid content-length value: " + e.getMessage());
                }
                --i;
                continue;
            }
            break;
        }
        return -1;
    }
    
    public byte[] getResponseBody() {
        if (this.responseBody == null) {
            try {
                final InputStream instream = this.getResponseBodyAsStream();
                if (instream != null) {
                    final int contentLength = this.getResponseContentLength();
                    if (contentLength == -1 || contentLength > 1048576) {
                        HttpMethodBase.LOG.warn("Going to buffer response body of large or unknown size. Using getResponseAsStream instead is recommended.");
                    }
                    HttpMethodBase.LOG.debug("Buffering response body");
                    final ByteArrayOutputStream outstream = new ByteArrayOutputStream((contentLength > 0) ? contentLength : 4096);
                    final byte[] buffer = new byte[4096];
                    int len;
                    while ((len = instream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, len);
                    }
                    outstream.close();
                    this.setResponseStream(null);
                    this.responseBody = outstream.toByteArray();
                }
            }
            catch (IOException e) {
                HttpMethodBase.LOG.error("I/O failure reading response body", e);
                this.responseBody = null;
            }
        }
        return this.responseBody;
    }
    
    public InputStream getResponseBodyAsStream() throws IOException {
        if (this.responseStream != null) {
            return this.responseStream;
        }
        if (this.responseBody != null) {
            final InputStream byteResponseStream = new ByteArrayInputStream(this.responseBody);
            HttpMethodBase.LOG.debug("re-creating response stream from byte array");
            return byteResponseStream;
        }
        return null;
    }
    
    public String getResponseBodyAsString() {
        byte[] rawdata = null;
        if (this.responseAvailable()) {
            rawdata = this.getResponseBody();
        }
        if (rawdata != null) {
            return HttpConstants.getContentString(rawdata, this.getResponseCharSet());
        }
        return null;
    }
    
    public Header[] getResponseFooters() {
        return this.getResponseTrailerHeaderGroup().getAllHeaders();
    }
    
    public Header getResponseFooter(final String footerName) {
        if (footerName == null) {
            return null;
        }
        return this.getResponseTrailerHeaderGroup().getCondensedHeader(footerName);
    }
    
    protected void setResponseStream(final InputStream responseStream) {
        this.responseStream = responseStream;
    }
    
    protected InputStream getResponseStream() {
        return this.responseStream;
    }
    
    public String getStatusText() {
        return this.statusLine.getReasonPhrase();
    }
    
    public void setStrictMode(final boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    public boolean isStrictMode() {
        return this.strictMode;
    }
    
    public void addRequestHeader(final String headerName, final String headerValue) {
        this.addRequestHeader(new Header(headerName, headerValue));
    }
    
    protected boolean isConnectionCloseForced() {
        return this.connectionCloseForced;
    }
    
    protected void setConnectionCloseForced(final boolean b) {
        if (HttpMethodBase.LOG.isDebugEnabled()) {
            HttpMethodBase.LOG.debug("Force-close connection: " + b);
        }
        this.connectionCloseForced = b;
    }
    
    protected boolean shouldCloseConnection(final HttpConnection conn) {
        if (this.isConnectionCloseForced()) {
            HttpMethodBase.LOG.debug("Should force-close connection.");
            return true;
        }
        Header connectionHeader = null;
        if (!conn.isTransparent()) {
            connectionHeader = this.responseHeaders.getFirstHeader("proxy-connection");
        }
        if (connectionHeader == null) {
            connectionHeader = this.responseHeaders.getFirstHeader("connection");
        }
        if (connectionHeader != null) {
            if (connectionHeader.getValue().equalsIgnoreCase("close")) {
                if (HttpMethodBase.LOG.isDebugEnabled()) {
                    HttpMethodBase.LOG.debug("Should close connection in response to " + connectionHeader.toExternalForm());
                }
                return true;
            }
            if (connectionHeader.getValue().equalsIgnoreCase("keep-alive")) {
                if (HttpMethodBase.LOG.isDebugEnabled()) {
                    HttpMethodBase.LOG.debug("Should NOT close connection in response to " + connectionHeader.toExternalForm());
                }
                return false;
            }
            if (HttpMethodBase.LOG.isDebugEnabled()) {
                HttpMethodBase.LOG.debug("Unknown directive: " + connectionHeader.toExternalForm());
            }
        }
        HttpMethodBase.LOG.debug("Resorting to protocol version default close connection policy");
        if (this.http11) {
            HttpMethodBase.LOG.debug("Should NOT close connection, using HTTP/1.1.");
        }
        else {
            HttpMethodBase.LOG.debug("Should close connection, using HTTP/1.0.");
        }
        return !this.http11;
    }
    
    private boolean isRetryNeeded(final int statusCode, final HttpState state, final HttpConnection conn) {
        switch (statusCode) {
            case 401:
            case 407: {
                HttpMethodBase.LOG.debug("Authorization required");
                if (!this.doAuthentication) {
                    return false;
                }
                if (this.processAuthenticationResponse(state, conn)) {
                    return false;
                }
                break;
            }
            case 301:
            case 302:
            case 303:
            case 307: {
                HttpMethodBase.LOG.debug("Redirect required");
                if (!this.processRedirectResponse(conn)) {
                    return false;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    private void checkExecuteConditions(final HttpState state, final HttpConnection conn) throws HttpException {
        if (state == null) {
            throw new IllegalArgumentException("HttpState parameter may not be null");
        }
        if (conn == null) {
            throw new IllegalArgumentException("HttpConnection parameter may not be null");
        }
        if (this.hasBeenUsed()) {
            throw new HttpException("Already used, but not recycled.");
        }
        if (!this.validate()) {
            throw new HttpException("Not valid");
        }
        if (this.inExecute) {
            throw new IllegalStateException("Execute invoked recursively, or exited abnormally.");
        }
    }
    
    public int execute(final HttpState state, final HttpConnection conn) throws HttpException, HttpRecoverableException, IOException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.execute(HttpState, HttpConnection)");
        this.checkExecuteConditions(state, this.responseConnection = conn);
        this.inExecute = true;
        try {
            if (state.isAuthenticationPreemptive()) {
                HttpMethodBase.LOG.debug("Preemptively sending default basic credentials");
                try {
                    if (HttpAuthenticator.authenticateDefault(this, conn, state)) {
                        HttpMethodBase.LOG.debug("Default basic credentials applied");
                    }
                    else {
                        HttpMethodBase.LOG.warn("Preemptive authentication failed");
                    }
                    if (conn.isProxied()) {
                        if (HttpAuthenticator.authenticateProxyDefault(this, conn, state)) {
                            HttpMethodBase.LOG.debug("Default basic proxy credentials applied");
                        }
                        else {
                            HttpMethodBase.LOG.warn("Preemptive proxy authentication failed");
                        }
                    }
                }
                catch (AuthenticationException e) {
                    HttpMethodBase.LOG.error(e.getMessage(), e);
                }
            }
            this.realms = new HashSet();
            this.proxyRealms = new HashSet();
            int forwardCount = 0;
            while (forwardCount++ < 100) {
                conn.setLastResponseInputStream(null);
                if (HttpMethodBase.LOG.isDebugEnabled()) {
                    HttpMethodBase.LOG.debug("Execute loop try " + forwardCount);
                }
                this.statusLine = null;
                this.connectionCloseForced = false;
                this.processRequest(state, conn);
                if (!this.isRetryNeeded(this.statusLine.getStatusCode(), state, conn)) {
                    break;
                }
                if (this.responseStream == null) {
                    continue;
                }
                this.responseStream.close();
            }
            if (forwardCount >= 100) {
                HttpMethodBase.LOG.error("Narrowly avoided an infinite loop in execute");
                throw new HttpRecoverableException("Maximum redirects (100) exceeded");
            }
        }
        finally {
            this.inExecute = false;
            if (this.doneWithConnection) {
                this.ensureConnectionRelease();
            }
        }
        return this.statusLine.getStatusCode();
    }
    
    private boolean processRedirectResponse(final HttpConnection conn) {
        if (!this.getFollowRedirects()) {
            HttpMethodBase.LOG.info("Redirect requested but followRedirects is disabled");
            return false;
        }
        final Header locationHeader = this.getResponseHeader("location");
        if (locationHeader == null) {
            HttpMethodBase.LOG.error("Received redirect response " + this.getStatusCode() + " but no location header");
            return false;
        }
        final String location = locationHeader.getValue();
        if (HttpMethodBase.LOG.isDebugEnabled()) {
            HttpMethodBase.LOG.debug("Redirect requested to location '" + location + "'");
        }
        URI redirectUri = null;
        URI currentUri = null;
        try {
            currentUri = new URI(conn.getProtocol().getScheme(), null, conn.getHost(), conn.getPort(), this.getPath());
            redirectUri = new URI(location.toCharArray());
            if (redirectUri.isRelativeURI()) {
                if (this.isStrictMode()) {
                    HttpMethodBase.LOG.warn("Redirected location '" + location + "' is not acceptable in strict mode");
                    return false;
                }
                HttpMethodBase.LOG.debug("Redirect URI is not absolute - parsing as relative");
                redirectUri = new URI(currentUri, redirectUri);
            }
        }
        catch (URIException e) {
            HttpMethodBase.LOG.warn("Redirected location '" + location + "' is malformed");
            return false;
        }
        try {
            checkValidRedirect(currentUri, redirectUri);
        }
        catch (HttpException ex) {
            HttpMethodBase.LOG.warn(ex.getMessage());
            return false;
        }
        this.realms.clear();
        if (this.proxyAuthScheme instanceof NTLMScheme) {
            this.removeRequestHeader("Proxy-Authorization");
        }
        this.removeRequestHeader("Authorization");
        this.setPath(redirectUri.getEscapedPath());
        this.setQueryString(redirectUri.getEscapedQuery());
        if (HttpMethodBase.LOG.isDebugEnabled()) {
            HttpMethodBase.LOG.debug("Redirecting from '" + currentUri.getEscapedURI() + "' to '" + redirectUri.getEscapedURI());
        }
        return true;
    }
    
    private static void checkValidRedirect(final URI currentUri, final URI redirectUri) throws HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.checkValidRedirect(HttpConnection, URL)");
        final String oldProtocol = currentUri.getScheme();
        final String newProtocol = redirectUri.getScheme();
        if (!oldProtocol.equals(newProtocol)) {
            throw new HttpException("Redirect from protocol " + oldProtocol + " to " + newProtocol + " is not supported");
        }
        try {
            final String oldHost = currentUri.getHost();
            final String newHost = redirectUri.getHost();
            if (!oldHost.equalsIgnoreCase(newHost)) {
                throw new HttpException("Redirect from host " + oldHost + " to " + newHost + " is not supported");
            }
        }
        catch (URIException ex) {
            HttpMethodBase.LOG.warn("Error getting URI host", ex);
            throw new HttpException("Invalid Redirect URI from: " + currentUri.getEscapedURI() + " to: " + redirectUri.getEscapedURI());
        }
        int oldPort = currentUri.getPort();
        if (oldPort < 0) {
            oldPort = getDefaultPort(oldProtocol);
        }
        int newPort = redirectUri.getPort();
        if (newPort < 0) {
            newPort = getDefaultPort(newProtocol);
        }
        if (oldPort != newPort) {
            throw new HttpException("Redirect from port " + oldPort + " to " + newPort + " is not supported");
        }
    }
    
    private static int getDefaultPort(final String protocol) {
        final String proto = protocol.toLowerCase().trim();
        if (proto.equals("http")) {
            return 80;
        }
        if (proto.equals("https")) {
            return 443;
        }
        return -1;
    }
    
    public boolean hasBeenUsed() {
        return this.used;
    }
    
    public void recycle() {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.recycle()");
        this.releaseConnection();
        this.path = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.authScheme = null;
        this.realm = null;
        this.proxyAuthScheme = null;
        this.proxyRealm = null;
        this.queryString = null;
        this.getRequestHeaderGroup().clear();
        this.getResponseHeaderGroup().clear();
        this.getResponseTrailerHeaderGroup().clear();
        this.statusLine = null;
        this.used = false;
        this.http11 = true;
        this.responseBody = null;
        this.recoverableExceptionCount = 0;
        this.inExecute = false;
        this.doneWithConnection = false;
        this.connectionCloseForced = false;
    }
    
    public void releaseConnection() {
        if (this.responseStream != null) {
            try {
                this.responseStream.close();
            }
            catch (IOException e) {
                this.ensureConnectionRelease();
            }
        }
        else {
            this.ensureConnectionRelease();
        }
    }
    
    public void removeRequestHeader(final String headerName) {
        final Header[] headers = this.getRequestHeaderGroup().getHeaders(headerName);
        for (int i = 0; i < headers.length; ++i) {
            this.getRequestHeaderGroup().removeHeader(headers[i]);
        }
    }
    
    public boolean validate() {
        return true;
    }
    
    protected int getRequestContentLength() {
        return 0;
    }
    
    protected void addAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addAuthorizationRequestHeader(HttpState, HttpConnection)");
        if (this.getRequestHeader("Authorization") == null) {
            final Header[] challenges = this.getResponseHeaderGroup().getHeaders("WWW-Authenticate");
            if (challenges.length > 0) {
                try {
                    HttpAuthenticator.authenticate(this.authScheme = HttpAuthenticator.selectAuthScheme(challenges), this, conn, state);
                }
                catch (HttpException e) {
                    if (HttpMethodBase.LOG.isErrorEnabled()) {
                        HttpMethodBase.LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
    
    protected void addContentLengthRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addContentLengthRequestHeader(HttpState, HttpConnection)");
        final int len = this.getRequestContentLength();
        if (this.getRequestHeader("content-length") == null) {
            if (0 < len) {
                this.setRequestHeader("Content-Length", String.valueOf(len));
            }
            else if (this.http11 && len < 0) {
                this.setRequestHeader("Transfer-Encoding", "chunked");
            }
        }
    }
    
    protected void addCookieRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addCookieRequestHeader(HttpState, HttpConnection)");
        this.removeRequestHeader("cookie");
        final CookieSpec matcher = CookiePolicy.getSpecByPolicy(state.getCookiePolicy());
        final Cookie[] cookies = matcher.match(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), state.getCookies());
        if (cookies != null && cookies.length > 0) {
            if (this.isStrictMode()) {
                this.getRequestHeaderGroup().addHeader(matcher.formatCookieHeader(cookies));
            }
            else {
                for (int i = 0; i < cookies.length; ++i) {
                    this.getRequestHeaderGroup().addHeader(matcher.formatCookieHeader(cookies[i]));
                }
            }
        }
    }
    
    protected void addHostRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addHostRequestHeader(HttpState, HttpConnection)");
        String host = conn.getVirtualHost();
        if (host != null) {
            HttpMethodBase.LOG.debug("Using virtual host name: " + host);
        }
        else {
            host = conn.getHost();
        }
        final int port = conn.getPort();
        if (this.getRequestHeader("host") != null) {
            HttpMethodBase.LOG.debug("Request to add Host header ignored: header already added");
            return;
        }
        if (HttpMethodBase.LOG.isDebugEnabled()) {
            HttpMethodBase.LOG.debug("Adding Host request header");
        }
        if (conn.getProtocol().getDefaultPort() != port) {
            host = host + ":" + port;
        }
        this.setRequestHeader("Host", host);
    }
    
    protected void addProxyAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addProxyAuthorizationRequestHeader(HttpState, HttpConnection)");
        if (this.getRequestHeader("Proxy-Authorization") == null) {
            final Header[] challenges = this.getResponseHeaderGroup().getHeaders("Proxy-Authenticate");
            if (challenges.length > 0) {
                try {
                    HttpAuthenticator.authenticateProxy(this.proxyAuthScheme = HttpAuthenticator.selectAuthScheme(challenges), this, conn, state);
                }
                catch (HttpException e) {
                    if (HttpMethodBase.LOG.isErrorEnabled()) {
                        HttpMethodBase.LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
    
    protected void addProxyConnectionHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addProxyConnectionHeader(HttpState, HttpConnection)");
        if (!conn.isTransparent()) {
            this.setRequestHeader("Proxy-Connection", "Keep-Alive");
        }
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addRequestHeaders(HttpState, HttpConnection)");
        this.addUserAgentRequestHeader(state, conn);
        this.addHostRequestHeader(state, conn);
        this.addCookieRequestHeader(state, conn);
        this.addAuthorizationRequestHeader(state, conn);
        this.addProxyAuthorizationRequestHeader(state, conn);
        this.addProxyConnectionHeader(state, conn);
        this.addContentLengthRequestHeader(state, conn);
    }
    
    protected void addUserAgentRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.addUserAgentRequestHeaders(HttpState, HttpConnection)");
        if (this.getRequestHeader("user-agent") == null) {
            this.setRequestHeader(HttpMethodBase.USER_AGENT);
        }
    }
    
    protected void checkNotUsed() throws IllegalStateException {
        if (this.used) {
            throw new IllegalStateException("Already used.");
        }
    }
    
    protected void checkUsed() throws IllegalStateException {
        if (!this.used) {
            throw new IllegalStateException("Not Used.");
        }
    }
    
    protected static String generateRequestLine(final HttpConnection connection, final String name, final String requestPath, final String query, final String version) {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.generateRequestLine(HttpConnection, String, String, String, String)");
        final StringBuffer buf = new StringBuffer();
        buf.append(name);
        buf.append(" ");
        if (!connection.isTransparent()) {
            final Protocol protocol = connection.getProtocol();
            buf.append(protocol.getScheme().toLowerCase());
            buf.append("://");
            buf.append(connection.getHost());
            if (connection.getPort() != -1 && connection.getPort() != protocol.getDefaultPort()) {
                buf.append(":");
                buf.append(connection.getPort());
            }
        }
        if (requestPath == null) {
            buf.append("/");
        }
        else {
            if (!connection.isTransparent() && !requestPath.startsWith("/")) {
                buf.append("/");
            }
            buf.append(requestPath);
        }
        if (query != null) {
            if (query.indexOf("?") != 0) {
                buf.append("?");
            }
            buf.append(query);
        }
        buf.append(" ");
        buf.append(version);
        buf.append("\r\n");
        return buf.toString();
    }
    
    protected void processResponseBody(final HttpState state, final HttpConnection conn) {
    }
    
    protected void processResponseHeaders(final HttpState state, final HttpConnection conn) {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.processResponseHeaders(HttpState, HttpConnection)");
        Header[] headers = this.getResponseHeaderGroup().getHeaders("set-cookie2");
        if (headers.length == 0) {
            headers = this.getResponseHeaderGroup().getHeaders("set-cookie");
        }
        final CookieSpec parser = CookiePolicy.getSpecByPolicy(state.getCookiePolicy());
        for (int i = 0; i < headers.length; ++i) {
            final Header header = headers[i];
            Cookie[] cookies = null;
            try {
                cookies = parser.parse(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), header);
            }
            catch (MalformedCookieException e) {
                if (HttpMethodBase.LOG.isWarnEnabled()) {
                    HttpMethodBase.LOG.warn("Invalid cookie header: \"" + header.getValue() + "\". " + e.getMessage());
                }
            }
            if (cookies != null) {
                for (int j = 0; j < cookies.length; ++j) {
                    final Cookie cookie = cookies[j];
                    try {
                        parser.validate(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), cookie);
                        state.addCookie(cookie);
                        if (HttpMethodBase.LOG.isDebugEnabled()) {
                            HttpMethodBase.LOG.debug("Cookie accepted: \"" + parser.formatCookie(cookie) + "\"");
                        }
                    }
                    catch (MalformedCookieException e2) {
                        if (HttpMethodBase.LOG.isWarnEnabled()) {
                            HttpMethodBase.LOG.warn("Cookie rejected: \"" + parser.formatCookie(cookie) + "\". " + e2.getMessage());
                        }
                    }
                }
            }
        }
    }
    
    protected void processStatusLine(final HttpState state, final HttpConnection conn) {
    }
    
    protected void readResponse(final HttpState state, final HttpConnection conn) throws HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.readResponse(HttpState, HttpConnection)");
        try {
            while (this.statusLine == null) {
                this.readStatusLine(state, conn);
                this.processStatusLine(state, conn);
                this.readResponseHeaders(state, conn);
                this.processResponseHeaders(state, conn);
                final int status = this.statusLine.getStatusCode();
                if (status >= 100 && status < 200) {
                    if (HttpMethodBase.LOG.isInfoEnabled()) {
                        HttpMethodBase.LOG.info("Discarding unexpected response: " + this.statusLine.toString());
                    }
                    this.statusLine = null;
                }
            }
            this.readResponseBody(state, conn);
            this.processResponseBody(state, conn);
        }
        catch (IOException e) {
            throw new HttpRecoverableException(e.toString());
        }
    }
    
    protected void readResponseBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.readResponseBody(HttpState, HttpConnection)");
        this.doneWithConnection = false;
        final InputStream stream = this.readResponseBody(conn);
        if (stream == null) {
            this.responseBodyConsumed();
        }
        else {
            conn.setLastResponseInputStream(stream);
            this.setResponseStream(stream);
        }
    }
    
    private InputStream readResponseBody(final HttpConnection conn) throws IOException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.readResponseBody(HttpConnection)");
        this.responseBody = null;
        InputStream is = conn.getResponseInputStream();
        if (Wire.CONTENT_WIRE.enabled()) {
            is = new WireLogInputStream(is, Wire.CONTENT_WIRE);
        }
        InputStream result = null;
        final Header transferEncodingHeader = this.responseHeaders.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            final String transferEncoding = transferEncodingHeader.getValue();
            if (!"chunked".equalsIgnoreCase(transferEncoding) && !"identity".equalsIgnoreCase(transferEncoding) && HttpMethodBase.LOG.isWarnEnabled()) {
                HttpMethodBase.LOG.warn("Unsupported transfer encoding: " + transferEncoding);
            }
            final HeaderElement[] encodings = transferEncodingHeader.getValues();
            final int len = encodings.length;
            if (len > 0 && "chunked".equalsIgnoreCase(encodings[len - 1].getName())) {
                if (conn.isResponseAvailable(conn.getSoTimeout())) {
                    result = new ChunkedInputStream(is, this);
                }
                else {
                    if (this.isStrictMode()) {
                        throw new HttpException("Chunk-encoded body declared but not sent");
                    }
                    HttpMethodBase.LOG.warn("Chunk-encoded body missing");
                }
            }
            else {
                HttpMethodBase.LOG.info("Response content is not chunk-encoded");
                this.setConnectionCloseForced(true);
                result = is;
            }
        }
        else {
            final int expectedLength = this.getResponseContentLength();
            if (expectedLength == -1) {
                if (canResponseHaveBody(this.statusLine.getStatusCode())) {
                    final Header connectionHeader = this.responseHeaders.getFirstHeader("Connection");
                    String connectionDirective = null;
                    if (connectionHeader != null) {
                        connectionDirective = connectionHeader.getValue();
                    }
                    if (this.isHttp11() && !"close".equalsIgnoreCase(connectionDirective)) {
                        HttpMethodBase.LOG.info("Response content length is not known");
                        this.setConnectionCloseForced(true);
                    }
                    result = is;
                }
            }
            else {
                result = new ContentLengthInputStream(is, expectedLength);
            }
        }
        if (result != null) {
            result = new AutoCloseInputStream(result, new ResponseConsumedWatcher() {
                public void responseConsumed() {
                    HttpMethodBase.this.responseBodyConsumed();
                }
            });
        }
        return result;
    }
    
    protected void readResponseHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.readResponseHeaders(HttpState,HttpConnection)");
        this.getResponseHeaderGroup().clear();
        final Header[] headers = HttpParser.parseHeaders(conn.getResponseInputStream());
        if (Wire.HEADER_WIRE.enabled()) {
            for (int i = 0; i < headers.length; ++i) {
                Wire.HEADER_WIRE.input(headers[i].toExternalForm());
            }
        }
        this.getResponseHeaderGroup().setHeaders(headers);
    }
    
    protected void readStatusLine(final HttpState state, final HttpConnection conn) throws IOException, HttpRecoverableException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.readStatusLine(HttpState, HttpConnection)");
        String s;
        for (s = conn.readLine(); s != null && !StatusLine.startsWithHTTP(s); s = conn.readLine()) {
            if (Wire.HEADER_WIRE.enabled()) {
                Wire.HEADER_WIRE.input(s + "\r\n");
            }
        }
        if (s == null) {
            throw new HttpRecoverableException("Error in parsing the status  line from the response: unable to find line starting with \"HTTP\"");
        }
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.input(s + "\r\n");
        }
        this.statusLine = new StatusLine(s);
        final String httpVersion = this.statusLine.getHttpVersion();
        if (httpVersion.equals("HTTP/1.0")) {
            this.http11 = false;
        }
        else if (httpVersion.equals("HTTP/1.1")) {
            this.http11 = true;
        }
        else {
            if (!httpVersion.equals("HTTP")) {
                throw new HttpException("Unrecognized server protocol: '" + httpVersion + "'");
            }
            this.http11 = false;
        }
    }
    
    protected void writeRequest(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.writeRequest(HttpState, HttpConnection)");
        this.writeRequestLine(state, conn);
        this.writeRequestHeaders(state, conn);
        conn.writeLine();
        conn.flushRequestOutputStream();
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output("\r\n");
        }
        final Header expectheader = this.getRequestHeader("Expect");
        String expectvalue = null;
        if (expectheader != null) {
            expectvalue = expectheader.getValue();
        }
        if (expectvalue != null && expectvalue.compareToIgnoreCase("100-continue") == 0) {
            if (this.isHttp11()) {
                final int readTimeout = conn.getSoTimeout();
                try {
                    conn.setSoTimeout(3000);
                    this.readStatusLine(state, conn);
                    this.processStatusLine(state, conn);
                    this.readResponseHeaders(state, conn);
                    this.processResponseHeaders(state, conn);
                    if (this.statusLine.getStatusCode() != 100) {
                        return;
                    }
                    this.statusLine = null;
                    HttpMethodBase.LOG.debug("OK to continue received");
                }
                catch (InterruptedIOException e) {
                    this.removeRequestHeader("Expect");
                    HttpMethodBase.LOG.info("100 (continue) read timeout. Resume sending the request");
                }
                finally {
                    conn.setSoTimeout(readTimeout);
                }
            }
            else {
                this.removeRequestHeader("Expect");
                HttpMethodBase.LOG.info("'Expect: 100-continue' handshake is only supported by HTTP/1.1 or higher");
            }
        }
        this.writeRequestBody(state, conn);
        conn.flushRequestOutputStream();
    }
    
    protected boolean writeRequestBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        return true;
    }
    
    protected void writeRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.writeRequestHeaders(HttpState,HttpConnection)");
        this.addRequestHeaders(state, conn);
        final Header[] headers = this.getRequestHeaders();
        for (int i = 0; i < headers.length; ++i) {
            final String s = headers[i].toExternalForm();
            if (Wire.HEADER_WIRE.enabled()) {
                Wire.HEADER_WIRE.output(s);
            }
            conn.print(s);
        }
    }
    
    protected void writeRequestLine(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.writeRequestLine(HttpState, HttpConnection)");
        final String requestLine = this.getRequestLine(conn);
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output(requestLine);
        }
        conn.print(requestLine);
    }
    
    private String getRequestLine(final HttpConnection conn) {
        return generateRequestLine(conn, this.getName(), this.getPath(), this.getQueryString(), this.getHttpVersion());
    }
    
    private String getHttpVersion() {
        return this.http11 ? "HTTP/1.1" : "HTTP/1.0";
    }
    
    private static boolean canResponseHaveBody(final int status) {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.canResponseHaveBody(int)");
        boolean result = true;
        if ((status >= 100 && status <= 199) || status == 204 || status == 304) {
            result = false;
        }
        return result;
    }
    
    private boolean processAuthenticationResponse(final HttpState state, final HttpConnection conn) {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
        if (this.proxyAuthScheme instanceof NTLMScheme) {
            this.removeRequestHeader("Proxy-Authorization");
        }
        if (this.authScheme instanceof NTLMScheme) {
            this.removeRequestHeader("Authorization");
        }
        final int statusCode = this.statusLine.getStatusCode();
        Header[] challenges = null;
        Set realmsUsed = null;
        String host = null;
        switch (statusCode) {
            case 401: {
                challenges = this.getResponseHeaderGroup().getHeaders("WWW-Authenticate");
                realmsUsed = this.realms;
                host = conn.getVirtualHost();
                if (host == null) {
                    host = conn.getHost();
                    break;
                }
                break;
            }
            case 407: {
                challenges = this.getResponseHeaderGroup().getHeaders("Proxy-Authenticate");
                realmsUsed = this.proxyRealms;
                host = conn.getProxyHost();
                break;
            }
        }
        boolean authenticated = false;
        if (challenges.length > 0) {
            AuthScheme authscheme = null;
            try {
                authscheme = HttpAuthenticator.selectAuthScheme(challenges);
            }
            catch (MalformedChallengeException e) {
                if (HttpMethodBase.LOG.isErrorEnabled()) {
                    HttpMethodBase.LOG.error(e.getMessage(), e);
                }
                return true;
            }
            catch (UnsupportedOperationException e2) {
                if (HttpMethodBase.LOG.isErrorEnabled()) {
                    HttpMethodBase.LOG.error(e2.getMessage(), e2);
                }
                return true;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(host);
            buffer.append('#');
            buffer.append(authscheme.getID());
            final String realm = buffer.toString();
            if (realmsUsed.contains(realm)) {
                if (HttpMethodBase.LOG.isInfoEnabled()) {
                    buffer = new StringBuffer();
                    buffer.append("Already tried to authenticate with '");
                    buffer.append(authscheme.getRealm());
                    buffer.append("' authentication realm at ");
                    buffer.append(host);
                    buffer.append(", but still receiving: ");
                    buffer.append(this.statusLine.toString());
                    HttpMethodBase.LOG.info(buffer.toString());
                }
                return true;
            }
            realmsUsed.add(realm);
            try {
                switch (statusCode) {
                    case 401: {
                        this.removeRequestHeader("Authorization");
                        authenticated = HttpAuthenticator.authenticate(authscheme, this, conn, state);
                        this.realm = authscheme.getRealm();
                        this.authScheme = authscheme;
                        break;
                    }
                    case 407: {
                        this.removeRequestHeader("Proxy-Authorization");
                        authenticated = HttpAuthenticator.authenticateProxy(authscheme, this, conn, state);
                        this.proxyRealm = authscheme.getRealm();
                        this.proxyAuthScheme = authscheme;
                        break;
                    }
                }
            }
            catch (AuthenticationException e3) {
                HttpMethodBase.LOG.warn(e3.getMessage());
                return true;
            }
            if (!authenticated) {
                HttpMethodBase.LOG.debug("HttpMethodBase.execute(): Server demands authentication credentials, but none are available, so aborting.");
            }
            else {
                HttpMethodBase.LOG.debug("HttpMethodBase.execute(): Server demanded authentication credentials, will try again.");
            }
        }
        return !authenticated;
    }
    
    public String getProxyAuthenticationRealm() {
        return this.proxyRealm;
    }
    
    public String getAuthenticationRealm() {
        return this.realm;
    }
    
    private void processRequest(final HttpState state, final HttpConnection connection) throws HttpException, IOException {
        HttpMethodBase.LOG.trace("enter HttpMethodBase.processRequest(HttpState, HttpConnection)");
        int execCount = 0;
        boolean requestSent = false;
        while (true) {
            ++execCount;
            requestSent = false;
            if (HttpMethodBase.LOG.isTraceEnabled()) {
                HttpMethodBase.LOG.trace("Attempt number " + execCount + " to process request");
            }
            try {
                if (!connection.isOpen()) {
                    HttpMethodBase.LOG.debug("Opening the connection.");
                    connection.open();
                }
                this.writeRequest(state, connection);
                requestSent = true;
                this.readResponse(state, connection);
                this.used = true;
            }
            catch (HttpRecoverableException httpre) {
                if (HttpMethodBase.LOG.isDebugEnabled()) {
                    HttpMethodBase.LOG.debug("Closing the connection.");
                }
                connection.close();
                HttpMethodBase.LOG.info("Recoverable exception caught when processing request");
                ++this.recoverableExceptionCount;
                if (!this.getMethodRetryHandler().retryMethod(this, connection, httpre, execCount, requestSent)) {
                    HttpMethodBase.LOG.warn("Recoverable exception caught but MethodRetryHandler.retryMethod() returned false, rethrowing exception");
                    this.doneWithConnection = true;
                    throw httpre;
                }
                continue;
            }
            catch (IOException e) {
                connection.close();
                this.doneWithConnection = true;
                throw e;
            }
            catch (RuntimeException e2) {
                connection.close();
                this.doneWithConnection = true;
                throw e2;
            }
            break;
        }
    }
    
    protected static String getContentCharSet(final Header contentheader) {
        HttpMethodBase.LOG.trace("enter getContentCharSet( Header contentheader )");
        String charset = null;
        if (contentheader != null) {
            try {
                final HeaderElement[] values = contentheader.getValues();
                if (values.length == 1) {
                    final NameValuePair param = values[0].getParameterByName("charset");
                    if (param != null) {
                        charset = param.getValue();
                    }
                }
            }
            catch (HttpException e) {
                HttpMethodBase.LOG.error(e);
            }
        }
        if (charset == null) {
            if (HttpMethodBase.LOG.isDebugEnabled()) {
                HttpMethodBase.LOG.debug("Default charset used: ISO-8859-1");
            }
            charset = "ISO-8859-1";
        }
        return charset;
    }
    
    public String getRequestCharSet() {
        return getContentCharSet(this.getRequestHeader("Content-Type"));
    }
    
    public String getResponseCharSet() {
        return getContentCharSet(this.getResponseHeader("Content-Type"));
    }
    
    public int getRecoverableExceptionCount() {
        return this.recoverableExceptionCount;
    }
    
    protected void responseBodyConsumed() {
        this.responseStream = null;
        if (this.responseConnection != null) {
            this.responseConnection.setLastResponseInputStream(null);
            if (this.shouldCloseConnection(this.responseConnection)) {
                this.responseConnection.close();
            }
        }
        this.connectionCloseForced = false;
        this.doneWithConnection = true;
        if (!this.inExecute) {
            this.ensureConnectionRelease();
        }
    }
    
    private void ensureConnectionRelease() {
        if (this.responseConnection != null) {
            this.responseConnection.releaseConnection();
            this.responseConnection = null;
        }
    }
    
    public HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }
    
    public void setHostConfiguration(final HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }
    
    public MethodRetryHandler getMethodRetryHandler() {
        if (this.methodRetryHandler == null) {
            this.methodRetryHandler = new DefaultMethodRetryHandler();
        }
        return this.methodRetryHandler;
    }
    
    public void setMethodRetryHandler(final MethodRetryHandler handler) {
        this.methodRetryHandler = handler;
    }
    
    protected void fakeResponse(final StatusLine statusline, final HeaderGroup responseheaders, final InputStream responseStream) {
        this.used = true;
        this.statusLine = statusline;
        this.responseHeaders = responseheaders;
        this.responseBody = null;
        this.responseStream = responseStream;
    }
    
    static {
        LOG = LogFactory.getLog(HttpMethodBase.class);
        String agent = null;
        try {
            agent = System.getProperty("httpclient.useragent");
        }
        catch (SecurityException ex) {}
        if (agent == null) {
            agent = "Jakarta Commons-HttpClient/2.0.2";
        }
        USER_AGENT = new Header("User-Agent", agent);
    }
}
