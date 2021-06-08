// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;
import java.net.InetAddress;
import java.net.Socket;
import org.mudebug.prapr.reloc.commons.httpclient.URIException;
import java.util.TimeZone;
import com.mks.api.response.APIConnectionException;
import java.net.ConnectException;
import com.mks.api.response.APIException;
import com.mks.api.util.Base64;
import com.mks.api.CmdRunner;
import java.text.MessageFormat;
import org.mudebug.prapr.reloc.commons.httpclient.methods.HeadMethod;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpRecoverableException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethod;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.APIInternalError;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnectionManager;
import org.mudebug.prapr.reloc.commons.httpclient.MultiThreadedHttpConnectionManager;
import com.mks.api.VersionNumber;
import org.mudebug.prapr.reloc.commons.httpclient.HttpClient;
import org.mudebug.prapr.reloc.commons.httpclient.HostConfiguration;
import com.mks.api.IntegrationPoint;
import com.mks.api.util.MKSLogger;
import org.mudebug.prapr.reloc.commons.httpclient.Header;
import org.mudebug.prapr.reloc.commons.httpclient.URI;
import com.mks.api.Session;

final class UserApplicationSessionImpl extends CmdRunnerCreatorImpl implements Session
{
    private static URI API_URI;
    private int timeout;
    private boolean autoReconnect;
    private static final String INVALID_API_URI_MSG = "API URI not initialized.";
    private static final String SET_TIMEOUT_MSG = "Setting connection timeout to: {0,number,#}";
    private static final String REDIRECT_FAILED_MSG = "Failed to establish a session: {0}";
    private static final String HEADER_NOT_FOUND_MSG = "Cannot get session ID.";
    private static final String BAD_STATUS_LINE_MSG = "Bad status line: {0}";
    private static final String AUTHENTICATION_FAILED_MSG = "Session not authenticated/authorized.";
    static final String API_COMMUNICATION_LOCALE = "UTF-8";
    private static final int DEFAULT_RETRIES = 3;
    private static final int DEFAULT_RETRY_SLEEP = 3000;
    private static final int RETRIES;
    private static final int RETRY_SLEEP;
    protected static final Header OUT_OF_BAND_MESSAGE;
    private static final Header PRE_9_6_PROTOCOL_VERSION;
    private static final Header PROTOCOL_VERSION;
    private static final Header SESSION_RELEASE;
    private static final Header CODEPAGE;
    private static final Header NEW_SESSION;
    private static final Header TIMEZONE;
    private MKSLogger apiLogger;
    private URI url;
    private boolean anonymous;
    private IntegrationPoint ip;
    private HostConfiguration hostconfig;
    private HttpClient httpClient;
    private String sessionUser;
    private String sessionPass;
    private Header sadCookie;
    private boolean supportsChunking;
    private VersionNumber apiVersion;
    
    UserApplicationSessionImpl(final IntegrationPoint ip, final VersionNumber apiRequestVersion, final String username, final String password, final boolean anonymous) {
        this.timeout = 300000;
        this.hostconfig = new HostConfiguration();
        this.httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        this.sadCookie = null;
        if (UserApplicationSessionImpl.API_URI == null) {
            throw new APIInternalError("API URI not initialized.");
        }
        this.ip = ip;
        this.apiVersion = apiRequestVersion;
        this.anonymous = anonymous;
        this.sessionUser = username;
        this.sessionPass = password;
        this.apiLogger = IntegrationPointFactory.getLogger();
        configureHttpClient(this.httpClient, this.hostconfig, ip);
        if (!ip.isClientIntegrationPoint()) {
            final String ipHostname = ip.getHostname();
            if (!"localhost".equalsIgnoreCase(ipHostname) && !"127.0.0.1".equalsIgnoreCase(ipHostname)) {
                this.setDefaultHostname(ipHostname);
                this.setDefaultPort(ip.getPort());
            }
        }
        if (this.sessionUser != null) {
            this.setDefaultUsername(this.sessionUser);
        }
        if (this.sessionPass != null) {
            this.setDefaultPassword(this.sessionPass);
        }
    }
    
    protected HttpClient createHttpClient() {
        final HttpClient retval = new HttpClient(new MultiThreadedHttpConnectionManager());
        configureHttpClient(retval, this.hostconfig);
        return retval;
    }
    
    protected static void releaseHttpClient(final HttpClient client) {
        final HttpConnectionManager hcm = client.getHttpConnectionManager();
        if (hcm instanceof MultiThreadedHttpConnectionManager) {
            ((MultiThreadedHttpConnectionManager)hcm).shutdown();
        }
    }
    
    protected static void configureHttpClient(final HttpClient httpClient, final HostConfiguration hostConfig) {
        configureHttpClient(httpClient, hostConfig, null);
    }
    
    protected static void configureHttpClient(final HttpClient httpClient, final HostConfiguration hostConfig, final IntegrationPoint ip) {
        if (ip != null) {
            final Protocol protocol = ip.isSecure() ? new Protocol("https", new SSLSocketFactory(), 443) : Protocol.getProtocol("http");
            hostConfig.setHost(ip.getHostname(), ip.getPort(), protocol);
        }
        httpClient.setHostConfiguration(hostConfig);
    }
    
    protected static void handleHTTPResponse(final HttpClient httpClient, final HttpMethod method) throws IOException {
        int retry = 0;
        while (true) {
            try {
                httpClient.executeMethod(method);
            }
            catch (HttpRecoverableException hre) {
                IntegrationPointFactory.getLogger().message("API", 5, "Got recoverable exception: " + hre.getLocalizedMessage() + ", sleeping for: " + UserApplicationSessionImpl.RETRY_SLEEP + "ms, then retrying...");
                if (++retry >= UserApplicationSessionImpl.RETRIES) {
                    throw hre;
                }
                try {
                    Thread.sleep(UserApplicationSessionImpl.RETRY_SLEEP);
                }
                catch (InterruptedException ex) {}
                continue;
            }
            break;
        }
    }
    
    protected synchronized URI getSession(final URI apiURL) throws IOException {
        final HttpMethod method = new HeadMethod(apiURL.getPath());
        try {
            method.setFollowRedirects(false);
            this.setupRequest(method);
            method.setQueryString(apiURL.getQuery());
            if (!this.anonymous) {
                method.setRequestHeader(UserApplicationSessionImpl.NEW_SESSION);
            }
            final String msg = MessageFormat.format("Setting connection timeout to: {0,number,#}", new Integer(this.timeout));
            this.apiLogger.message(this, "API", 10, msg);
            this.httpClient.setTimeout(this.timeout);
            handleHTTPResponse(this.httpClient, method);
            final Header server = method.getResponseHeader("Server");
            this.supportsChunking = (server == null || !server.getValue().startsWith("WebLogic 5.1.0"));
            final int code = method.getStatusCode();
            if (code == 410) {
                this.invalidateURI();
                throw new InvalidSessionException();
            }
            if (code != 302) {
                String errDetails = method.getStatusText();
                if (code == 403) {
                    errDetails = "Session not authenticated/authorized.";
                }
                final String errMsg = MessageFormat.format("Failed to establish a session: {0}", errDetails);
                throw new BlimpException(errMsg);
            }
            final Header red = method.getResponseHeader("Location");
            if (red == null) {
                final String errMsg = "Cannot get session ID.";
                throw new IOException(errMsg);
            }
            return new URI(red.getValue());
        }
        finally {
            method.releaseConnection();
        }
    }
    
    public int getTimeout() {
        return this.timeout / 1000;
    }
    
    public void setTimeout(final int timeout) {
        this.timeout = timeout * 1000;
    }
    
    protected void removeConnection(final CmdRunner c) {
        this.removeCmdRunner(c);
    }
    
    protected synchronized URI getSessionURI() throws IOException {
        if (this.url == null) {
            this.url = this.getSession(UserApplicationSessionImpl.API_URI);
        }
        return this.url;
    }
    
    protected synchronized void invalidateURI() {
        this.url = null;
    }
    
    public IntegrationPoint getIntegrationPoint() {
        return this.ip;
    }
    
    public VersionNumber getAPIRequestVersion() {
        return (this.apiVersion == null) ? this.ip.getAPIRequestVersion() : this.apiVersion;
    }
    
    protected void setupRequest(final HttpMethod method) {
        method.setRequestHeader(UserApplicationSessionImpl.PRE_9_6_PROTOCOL_VERSION);
        method.setRequestHeader(UserApplicationSessionImpl.CODEPAGE);
        method.setRequestHeader(UserApplicationSessionImpl.TIMEZONE);
        if (this.sadCookie != null) {
            method.setRequestHeader(this.sadCookie);
        }
        if (this.sessionUser != null) {
            method.setRequestHeader("Authorization", "Basic " + Base64.encode(this.sessionUser + ":" + this.sessionPass));
        }
        final String apiVersion = this.getAPIRequestVersion().toVersionString();
        if (apiVersion != null) {
            method.setRequestHeader(new Header("APIVersion", apiVersion));
        }
        else {
            this.apiLogger.message(this, "ERROR", 0, "API version not available!");
        }
    }
    
    public final void release(final boolean force) throws IOException, APIException {
        this.release(force, true);
    }
    
    protected void release(final boolean force, final boolean removeParent) throws IOException, APIException {
        synchronized (this) {
            super.release(force);
            try {
                final URI session = this.url;
                if (session != null) {
                    final HttpMethod method = new HeadMethod(session.getPath());
                    try {
                        method.setQueryString(session.getQuery());
                        method.setFollowRedirects(false);
                        this.setupRequest(method);
                        method.setRequestHeader(UserApplicationSessionImpl.SESSION_RELEASE);
                        handleHTTPResponse(this.httpClient, method);
                        final int code = method.getStatusCode();
                        if (code != 200 && code != 302 && code != 410) {
                            final String msg = MessageFormat.format("Bad status line: {0}", method.getStatusLine());
                            throw new BlimpException(msg);
                        }
                    }
                    finally {
                        method.releaseConnection();
                    }
                }
            }
            catch (ConnectException ce) {}
            finally {
                releaseHttpClient(this.httpClient);
            }
            this.invalidateURI();
        }
        if (removeParent) {
            ((IntegrationPointImpl)this.ip).removeSession(this);
        }
    }
    
    protected CmdRunner _createCmdRunner() throws APIException {
        CmdRunner cr = null;
        try {
            if (this.ip.isClientIntegrationPoint()) {
                cr = new ClientCmdRunnerImpl(this, this.createHttpClient());
                configureHttpClient(this.httpClient, this.hostconfig, this.ip);
            }
            else {
                cr = new HttpCmdRunnerImpl(this, this.createHttpClient());
            }
            cr.setDefaultUsername(this.sessionUser);
            cr.setDefaultPassword(this.sessionPass);
        }
        catch (UnsatisfiedLinkError err) {
            this.apiLogger.exception(this, "API", 0, err);
            if (!System.getProperty("os.name").startsWith("Windows")) {
                throw new APIConnectionException(err);
            }
            ((IntegrationPointImpl)this.ip).setPort(31000);
            configureHttpClient(this.httpClient, this.hostconfig, this.ip);
            cr = new HttpCmdRunnerImpl(this, this.createHttpClient());
        }
        return cr;
    }
    
    public boolean isCommon() {
        return this.anonymous;
    }
    
    protected void setAuthenticationCookie(final String cookie) {
        this.sadCookie = null;
        if (cookie != null) {
            this.sadCookie = new Header("SadCookie", cookie);
        }
    }
    
    public void setAutoReconnect(final boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
    }
    
    public boolean getAutoReconnect() {
        return this.autoReconnect;
    }
    
    protected boolean supportsChunking() {
        return this.supportsChunking;
    }
    
    static {
        UserApplicationSessionImpl.API_URI = null;
        RETRIES = Integer.getInteger("IntegrityAPI.retries", 3);
        RETRY_SLEEP = Integer.getInteger("IntegrityAPI.retryPeriod", 3000);
        OUT_OF_BAND_MESSAGE = new Header("OutOfBandMessage", "1");
        PRE_9_6_PROTOCOL_VERSION = new Header("Protocol-version", "1.1");
        PROTOCOL_VERSION = new Header("Protocol-version", "1.2");
        SESSION_RELEASE = new Header("AppConnection", "close");
        CODEPAGE = new Header("CodePage", "UTF-8");
        NEW_SESSION = new Header("AppSession", "new");
        TIMEZONE = new Header("TimeZone", TimeZone.getDefault().getID());
        try {
            UserApplicationSessionImpl.API_URI = new URI("/icapi");
        }
        catch (URIException ue) {
            IntegrationPointFactory.getLogger().exception(UserApplicationSessionImpl.class, "API", 0, ue);
            ue.printStackTrace();
        }
        System.setProperty("org.mudebug.prapr.reloc.commons.logging.Log", "org.mudebug.prapr.reloc.commons.logging.impl.SimpleLog");
        System.setProperty("org.mudebug.prapr.reloc.commons.logging.simplelog.showdatetime", "true");
        final boolean debugHTTP = Boolean.getBoolean("IntegrityAPI.log.HTTP");
        if (debugHTTP) {
            IntegrationPointFactory.getLogger().message("API", "Logging http from HTTPClient");
            System.setProperty("org.mudebug.prapr.reloc.commons.logging.simplelog.log.httpclient.wire", "debug");
            System.setProperty("org.mudebug.prapr.reloc.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
        }
        else {
            System.setProperty("org.mudebug.prapr.reloc.commons.logging.simplelog.log.httpclient.wire", "off");
            System.setProperty("org.mudebug.prapr.reloc.commons.logging.simplelog.log.org.apache.commons.httpclient", "off");
        }
    }
    
    private static class SSLSocketFactory implements SecureProtocolSocketFactory
    {
        private final SecureProtocolSocketFactory delegate;
        
        public SSLSocketFactory() {
            this.delegate = (SecureProtocolSocketFactory)Protocol.getProtocol("https").getSocketFactory();
        }
        
        public Socket createSocket(final String host, final int port) throws IOException {
            final Socket socket = this.delegate.createSocket(host, port);
            this.configureSocket(socket);
            return socket;
        }
        
        public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort) throws IOException {
            final Socket socket = this.delegate.createSocket(host, port, localAddress, localPort);
            this.configureSocket(socket);
            return socket;
        }
        
        public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException {
            final Socket newSocket = this.delegate.createSocket(socket, host, port, autoClose);
            this.configureSocket(newSocket);
            return newSocket;
        }
        
        private void configureSocket(final Socket socket) {
            final SSLSocket sslSocket = (SSLSocket)socket;
            final List protocols = new ArrayList(Arrays.asList(sslSocket.getEnabledProtocols()));
            protocols.remove("SSLv2Hello");
            sslSocket.setEnabledProtocols(protocols.toArray(new String[protocols.size()]));
        }
    }
}
