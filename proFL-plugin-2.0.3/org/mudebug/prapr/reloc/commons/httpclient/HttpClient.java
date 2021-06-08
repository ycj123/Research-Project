// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.security.Provider;
import java.security.Security;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import java.net.URL;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class HttpClient
{
    private static final Log LOG;
    private HttpConnectionManager httpConnectionManager;
    private HttpState state;
    private long httpConnectionTimeout;
    private int timeoutInMilliseconds;
    private int connectionTimeout;
    private HostConfiguration hostConfiguration;
    private boolean strictMode;
    
    public HttpClient() {
        this(new SimpleHttpConnectionManager());
    }
    
    public HttpClient(final HttpConnectionManager httpConnectionManager) {
        this.httpConnectionTimeout = 0L;
        this.timeoutInMilliseconds = 0;
        this.connectionTimeout = 0;
        this.strictMode = false;
        if (httpConnectionManager == null) {
            throw new IllegalArgumentException("httpConnectionManager cannot be null");
        }
        this.state = new HttpState();
        this.httpConnectionManager = httpConnectionManager;
        this.hostConfiguration = new HostConfiguration();
    }
    
    public synchronized HttpState getState() {
        return this.state;
    }
    
    public synchronized void setState(final HttpState state) {
        this.state = state;
    }
    
    public synchronized void setStrictMode(final boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    public synchronized boolean isStrictMode() {
        return this.strictMode;
    }
    
    public synchronized void setTimeout(final int newTimeoutInMilliseconds) {
        this.timeoutInMilliseconds = newTimeoutInMilliseconds;
    }
    
    public synchronized void setHttpConnectionFactoryTimeout(final long timeout) {
        this.httpConnectionTimeout = timeout;
    }
    
    public synchronized void setConnectionTimeout(final int newTimeoutInMilliseconds) {
        this.connectionTimeout = newTimeoutInMilliseconds;
    }
    
    public void startSession(final String host, final int port) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int)");
        this.startSession(host, port, false);
    }
    
    public void startSession(final String host, final int port, final boolean https) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, boolean)");
        if (HttpClient.LOG.isDebugEnabled()) {
            HttpClient.LOG.debug("HttpClient.startSession(String,int,boolean): Host:" + host + " Port:" + port + " HTTPS:" + https);
        }
        this.hostConfiguration.setHost(host, port, https ? "https" : "http");
    }
    
    public void startSession(final String host, final int port, final Credentials creds) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, Credentials)");
        this.startSession(host, port, creds, false);
    }
    
    public void startSession(final String host, final int port, final Credentials creds, final boolean https) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, Credentials, boolean)");
        if (HttpClient.LOG.isDebugEnabled()) {
            HttpClient.LOG.debug("Starting HttpClient session Host:" + host + " Port:" + port + " Credentials:" + creds + " HTTPS:" + https);
        }
        this.getState().setCredentials(null, creds);
        this.hostConfiguration.setHost(host, port, https ? "https" : "http");
    }
    
    public void startSession(final URI uri) throws URIException, IllegalStateException {
        HttpClient.LOG.trace("enter HttpClient.startSession(URI)");
        final String scheme = uri.getScheme();
        if (scheme == null) {
            HttpClient.LOG.error("no scheme to start a session");
            throw new IllegalStateException("no scheme to start a session");
        }
        final Protocol protocol = Protocol.getProtocol(scheme);
        final String userinfo = uri.getUserinfo();
        if (userinfo != null) {
            this.getState().setCredentials(null, new UsernamePasswordCredentials(userinfo));
        }
        final String host = uri.getHost();
        if (host == null || host.length() == 0) {
            HttpClient.LOG.error("no host to start a session");
            throw new IllegalStateException("no host to start a session");
        }
        final int port = uri.getPort();
        if (port == -1) {
            HttpClient.LOG.error("HttpURL or HttpsURL instance required");
            throw new IllegalStateException("HttpURL or HttpsURL instance required");
        }
        this.hostConfiguration.setHost(host, null, port, protocol);
    }
    
    public void startSession(final URL url) throws IllegalArgumentException {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, Credentials, boolean)");
        final int port = url.getPort();
        final Protocol protocol = Protocol.getProtocol(url.getProtocol());
        this.hostConfiguration.setHost(url.getHost(), null, port, protocol);
    }
    
    public void startSession(final URL url, final Credentials creds) throws IllegalArgumentException {
        HttpClient.LOG.trace("enter HttpClient.startSession(URL, Credentials)");
        this.getState().setCredentials(null, creds);
        this.startSession(url);
    }
    
    public void startSession(final String host, final int port, final String proxyhost, final int proxyport) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, String, int)");
        this.startSession(host, port, proxyhost, proxyport, false);
    }
    
    public void startSession(final String host, final int port, final String proxyhost, final int proxyport, final boolean secure) {
        HttpClient.LOG.trace("enter HttpClient.startSession(String, int, String, int, boolean)");
        this.hostConfiguration.setHost(host, port, secure ? "https" : "http");
        this.hostConfiguration.setProxy(proxyhost, proxyport);
    }
    
    public int executeMethod(final HttpMethod method) throws IOException, HttpException {
        HttpClient.LOG.trace("enter HttpClient.executeMethod(HttpMethod)");
        return this.executeMethod((method.getHostConfiguration() != null) ? method.getHostConfiguration() : this.getHostConfiguration(), method, null);
    }
    
    public int executeMethod(final HostConfiguration hostConfiguration, final HttpMethod method) throws IOException, HttpException {
        HttpClient.LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod)");
        return this.executeMethod(hostConfiguration, method, null);
    }
    
    public int executeMethod(final HostConfiguration hostConfiguration, HttpMethod method, HttpState state) throws IOException, HttpException {
        HttpClient.LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod,HttpState)");
        if (method == null) {
            throw new IllegalArgumentException("HttpMethod parameter may not be null");
        }
        int soTimeout = 0;
        boolean strictMode = false;
        int connectionTimeout = 0;
        long httpConnectionTimeout = 0L;
        HostConfiguration defaultHostConfiguration = null;
        synchronized (this) {
            soTimeout = this.timeoutInMilliseconds;
            strictMode = this.strictMode;
            connectionTimeout = this.connectionTimeout;
            httpConnectionTimeout = this.httpConnectionTimeout;
            if (state == null) {
                state = this.getState();
            }
            defaultHostConfiguration = this.getHostConfiguration();
        }
        final HostConfiguration methodConfiguration = new HostConfiguration(hostConfiguration);
        if (hostConfiguration != defaultHostConfiguration) {
            if (!methodConfiguration.isHostSet()) {
                methodConfiguration.setHost(defaultHostConfiguration.getHost(), defaultHostConfiguration.getVirtualHost(), defaultHostConfiguration.getPort(), defaultHostConfiguration.getProtocol());
            }
            if (!methodConfiguration.isProxySet() && defaultHostConfiguration.isProxySet()) {
                methodConfiguration.setProxy(defaultHostConfiguration.getProxyHost(), defaultHostConfiguration.getProxyPort());
            }
            if (methodConfiguration.getLocalAddress() == null && defaultHostConfiguration.getLocalAddress() != null) {
                methodConfiguration.setLocalAddress(defaultHostConfiguration.getLocalAddress());
            }
        }
        HttpConnectionManager connmanager = this.httpConnectionManager;
        if (state.getHttpConnectionManager() != null) {
            connmanager = state.getHttpConnectionManager();
        }
        final HttpConnection connection = connmanager.getConnection(methodConfiguration, httpConnectionTimeout);
        try {
            method.setStrictMode(strictMode);
            if (!connection.isOpen()) {
                connection.setConnectionTimeout(connectionTimeout);
                connection.open();
                if (connection.isProxied() && connection.isSecure()) {
                    method = new ConnectMethod(method);
                }
            }
            connection.setSoTimeout(soTimeout);
        }
        catch (IOException e) {
            connection.releaseConnection();
            throw e;
        }
        catch (RuntimeException e2) {
            connection.releaseConnection();
            throw e2;
        }
        return method.execute(state, connection);
    }
    
    public void endSession() throws IOException {
    }
    
    public String getHost() {
        return this.hostConfiguration.getHost();
    }
    
    public int getPort() {
        return this.hostConfiguration.getPort();
    }
    
    public synchronized HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }
    
    public synchronized void setHostConfiguration(final HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }
    
    public synchronized HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }
    
    public synchronized void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
    }
    
    static {
        LOG = LogFactory.getLog(HttpClient.class);
        if (HttpClient.LOG.isDebugEnabled()) {
            try {
                HttpClient.LOG.debug("Java version: " + System.getProperty("java.version"));
                HttpClient.LOG.debug("Java vendor: " + System.getProperty("java.vendor"));
                HttpClient.LOG.debug("Java class path: " + System.getProperty("java.class.path"));
                HttpClient.LOG.debug("Operating system name: " + System.getProperty("os.name"));
                HttpClient.LOG.debug("Operating system architecture: " + System.getProperty("os.arch"));
                HttpClient.LOG.debug("Operating system version: " + System.getProperty("os.version"));
                final Provider[] providers = Security.getProviders();
                for (int i = 0; i < providers.length; ++i) {
                    final Provider provider = providers[i];
                    HttpClient.LOG.debug(provider.getName() + " " + provider.getVersion() + ": " + provider.getInfo());
                }
            }
            catch (SecurityException ex) {}
        }
    }
}
