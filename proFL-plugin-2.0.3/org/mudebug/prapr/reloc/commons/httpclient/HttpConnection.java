// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.lang.reflect.Method;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.SecureProtocolSocketFactory;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import org.mudebug.prapr.reloc.commons.httpclient.util.TimeoutController;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.ProtocolSocketFactory;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.DefaultProtocolSocketFactory;
import java.net.SocketException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class HttpConnection
{
    private static final byte[] CRLF;
    private static final Log LOG;
    private boolean used;
    private String hostName;
    private String virtualName;
    private int portNumber;
    private String proxyHostName;
    private int proxyPortNumber;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int sendBufferSize;
    private InputStream lastResponseInputStream;
    protected boolean isOpen;
    private Protocol protocolInUse;
    private int soTimeout;
    private boolean soNodelay;
    private boolean usingSecureSocket;
    private boolean tunnelEstablished;
    private boolean staleCheckingEnabled;
    private int connectTimeout;
    private HttpConnectionManager httpConnectionManager;
    private InetAddress localAddress;
    
    public HttpConnection(final String host, final int port) {
        this(null, -1, host, port, false);
    }
    
    public HttpConnection(final String host, final int port, final boolean secure) {
        this(null, -1, host, port, secure);
    }
    
    public HttpConnection(final String host, final int port, final Protocol protocol) {
        this(null, -1, host, null, port, protocol);
    }
    
    public HttpConnection(final String host, final String virtualHost, final int port, final Protocol protocol) {
        this(null, -1, host, virtualHost, port, protocol);
    }
    
    public HttpConnection(final String proxyHost, final int proxyPort, final String host, final int port) {
        this(proxyHost, proxyPort, host, port, false);
    }
    
    public HttpConnection(final String proxyHost, final int proxyPort, final String host, final int port, final boolean secure) {
        this(proxyHost, proxyPort, host, null, port, Protocol.getProtocol(secure ? "https" : "http"));
    }
    
    public HttpConnection(final HostConfiguration hostConfiguration) {
        this(hostConfiguration.getProxyHost(), hostConfiguration.getProxyPort(), hostConfiguration.getHost(), hostConfiguration.getVirtualHost(), hostConfiguration.getPort(), hostConfiguration.getProtocol());
        this.localAddress = hostConfiguration.getLocalAddress();
    }
    
    public HttpConnection(final String proxyHost, final int proxyPort, final String host, final String virtualHost, final int port, final Protocol protocol) {
        this.used = false;
        this.hostName = null;
        this.virtualName = null;
        this.portNumber = -1;
        this.proxyHostName = null;
        this.proxyPortNumber = -1;
        this.socket = null;
        this.inputStream = null;
        this.outputStream = null;
        this.sendBufferSize = -1;
        this.lastResponseInputStream = null;
        this.isOpen = false;
        this.soTimeout = 0;
        this.soNodelay = true;
        this.usingSecureSocket = false;
        this.tunnelEstablished = false;
        this.staleCheckingEnabled = true;
        this.connectTimeout = 0;
        if (host == null) {
            throw new IllegalArgumentException("host parameter is null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        this.proxyHostName = proxyHost;
        this.proxyPortNumber = proxyPort;
        this.hostName = host;
        this.virtualName = virtualHost;
        this.portNumber = protocol.resolvePort(port);
        this.protocolInUse = protocol;
    }
    
    public String getHost() {
        return this.hostName;
    }
    
    public void setHost(final String host) throws IllegalStateException {
        if (host == null) {
            throw new IllegalArgumentException("host parameter is null");
        }
        this.assertNotOpen();
        this.hostName = host;
    }
    
    public String getVirtualHost() {
        return this.virtualName;
    }
    
    public void setVirtualHost(final String host) throws IllegalStateException {
        this.assertNotOpen();
        this.virtualName = host;
    }
    
    public int getPort() {
        if (this.portNumber < 0) {
            return this.isSecure() ? 443 : 80;
        }
        return this.portNumber;
    }
    
    public void setPort(final int port) throws IllegalStateException {
        this.assertNotOpen();
        this.portNumber = port;
    }
    
    public String getProxyHost() {
        return this.proxyHostName;
    }
    
    public void setProxyHost(final String host) throws IllegalStateException {
        this.assertNotOpen();
        this.proxyHostName = host;
    }
    
    public int getProxyPort() {
        return this.proxyPortNumber;
    }
    
    public void setProxyPort(final int port) throws IllegalStateException {
        this.assertNotOpen();
        this.proxyPortNumber = port;
    }
    
    public boolean isSecure() {
        return this.protocolInUse.isSecure();
    }
    
    public Protocol getProtocol() {
        return this.protocolInUse;
    }
    
    public void setSecure(final boolean secure) throws IllegalStateException {
        this.assertNotOpen();
        this.protocolInUse = (secure ? Protocol.getProtocol("https") : Protocol.getProtocol("http"));
    }
    
    public void setProtocol(final Protocol protocol) {
        this.assertNotOpen();
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        this.protocolInUse = protocol;
    }
    
    public InetAddress getLocalAddress() {
        return this.localAddress;
    }
    
    public void setLocalAddress(final InetAddress localAddress) {
        this.assertNotOpen();
        this.localAddress = localAddress;
    }
    
    public boolean isOpen() {
        if (this.used && this.isOpen && this.isStaleCheckingEnabled() && this.isStale()) {
            HttpConnection.LOG.debug("Connection is stale, closing...");
            this.close();
        }
        return this.isOpen;
    }
    
    public boolean isStaleCheckingEnabled() {
        return this.staleCheckingEnabled;
    }
    
    public void setStaleCheckingEnabled(final boolean staleCheckEnabled) {
        this.staleCheckingEnabled = staleCheckEnabled;
    }
    
    protected boolean isStale() {
        boolean isStale = true;
        if (this.isOpen) {
            isStale = false;
            try {
                if (this.inputStream.available() == 0) {
                    try {
                        this.socket.setSoTimeout(1);
                        this.inputStream.mark(1);
                        final int byteRead = this.inputStream.read();
                        if (byteRead == -1) {
                            isStale = true;
                        }
                        else {
                            this.inputStream.reset();
                        }
                    }
                    finally {
                        this.socket.setSoTimeout(this.soTimeout);
                    }
                }
            }
            catch (InterruptedIOException e2) {}
            catch (IOException e) {
                HttpConnection.LOG.debug("An error occurred while reading from the socket, is appears to be stale", e);
                isStale = true;
            }
        }
        return isStale;
    }
    
    public boolean isProxied() {
        return null != this.proxyHostName && 0 < this.proxyPortNumber;
    }
    
    public void setLastResponseInputStream(final InputStream inStream) {
        this.lastResponseInputStream = inStream;
    }
    
    public InputStream getLastResponseInputStream() {
        return this.lastResponseInputStream;
    }
    
    public void setSoTimeout(final int timeout) throws SocketException, IllegalStateException {
        HttpConnection.LOG.debug("HttpConnection.setSoTimeout(" + timeout + ")");
        this.soTimeout = timeout;
        if (this.socket != null) {
            this.socket.setSoTimeout(timeout);
        }
    }
    
    public int getSoTimeout() throws SocketException {
        HttpConnection.LOG.debug("HttpConnection.getSoTimeout()");
        if (this.socket != null) {
            return this.socket.getSoTimeout();
        }
        return this.soTimeout;
    }
    
    public void setConnectionTimeout(final int timeout) {
        this.connectTimeout = timeout;
    }
    
    public void open() throws IOException {
        HttpConnection.LOG.trace("enter HttpConnection.open()");
        this.assertNotOpen();
        try {
            if (null == this.socket) {
                final String host = (null == this.proxyHostName) ? this.hostName : this.proxyHostName;
                final int port = (null == this.proxyHostName) ? this.portNumber : this.proxyPortNumber;
                this.usingSecureSocket = (this.isSecure() && !this.isProxied());
                final ProtocolSocketFactory socketFactory = (this.isSecure() && this.isProxied()) ? new DefaultProtocolSocketFactory() : this.protocolInUse.getSocketFactory();
                if (this.connectTimeout == 0) {
                    if (this.localAddress != null) {
                        this.socket = socketFactory.createSocket(host, port, this.localAddress, 0);
                    }
                    else {
                        this.socket = socketFactory.createSocket(host, port);
                    }
                }
                else {
                    final SocketTask task = new SocketTask() {
                        public void doit() throws IOException {
                            if (HttpConnection.this.localAddress != null) {
                                this.setSocket(socketFactory.createSocket(host, port, HttpConnection.this.localAddress, 0));
                            }
                            else {
                                this.setSocket(socketFactory.createSocket(host, port));
                            }
                        }
                    };
                    TimeoutController.execute(task, this.connectTimeout);
                    this.socket = task.getSocket();
                    if (task.exception != null) {
                        throw task.exception;
                    }
                }
            }
            this.socket.setTcpNoDelay(this.soNodelay);
            this.socket.setSoTimeout(this.soTimeout);
            if (this.sendBufferSize != -1) {
                this.socket.setSendBufferSize(this.sendBufferSize);
            }
            int outbuffersize = this.socket.getSendBufferSize();
            if (outbuffersize > 2048) {
                outbuffersize = 2048;
            }
            int inbuffersize = this.socket.getReceiveBufferSize();
            if (inbuffersize > 2048) {
                inbuffersize = 2048;
            }
            this.inputStream = new BufferedInputStream(this.socket.getInputStream(), inbuffersize);
            this.outputStream = new BufferedOutputStream(new WrappedOutputStream(this.socket.getOutputStream()), outbuffersize);
            this.isOpen = true;
            this.used = false;
        }
        catch (IOException e) {
            this.closeSocketAndStreams();
            throw e;
        }
        catch (TimeoutController.TimeoutException e2) {
            if (HttpConnection.LOG.isWarnEnabled()) {
                HttpConnection.LOG.warn("The host " + this.hostName + ":" + this.portNumber + " (or proxy " + this.proxyHostName + ":" + this.proxyPortNumber + ") did not accept the connection within timeout of " + this.connectTimeout + " milliseconds");
            }
            throw new ConnectionTimeoutException();
        }
    }
    
    public void tunnelCreated() throws IllegalStateException, IOException {
        HttpConnection.LOG.trace("enter HttpConnection.tunnelCreated()");
        if (!this.isSecure() || !this.isProxied()) {
            throw new IllegalStateException("Connection must be secure and proxied to use this feature");
        }
        if (this.usingSecureSocket) {
            throw new IllegalStateException("Already using a secure socket");
        }
        final SecureProtocolSocketFactory socketFactory = (SecureProtocolSocketFactory)this.protocolInUse.getSocketFactory();
        this.socket = socketFactory.createSocket(this.socket, this.hostName, this.portNumber, true);
        if (this.sendBufferSize != -1) {
            this.socket.setSendBufferSize(this.sendBufferSize);
        }
        int outbuffersize = this.socket.getSendBufferSize();
        if (outbuffersize > 2048) {
            outbuffersize = 2048;
        }
        int inbuffersize = this.socket.getReceiveBufferSize();
        if (inbuffersize > 2048) {
            inbuffersize = 2048;
        }
        this.inputStream = new BufferedInputStream(this.socket.getInputStream(), inbuffersize);
        this.outputStream = new BufferedOutputStream(new WrappedOutputStream(this.socket.getOutputStream()), outbuffersize);
        this.usingSecureSocket = true;
        this.tunnelEstablished = true;
        HttpConnection.LOG.debug("Secure tunnel created");
    }
    
    public boolean isTransparent() {
        return !this.isProxied() || this.tunnelEstablished;
    }
    
    public void flushRequestOutputStream() throws IOException {
        HttpConnection.LOG.trace("enter HttpConnection.flushRequestOutputStream()");
        this.assertOpen();
        this.outputStream.flush();
    }
    
    public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
        HttpConnection.LOG.trace("enter HttpConnection.getRequestOutputStream()");
        this.assertOpen();
        OutputStream out = this.outputStream;
        if (Wire.CONTENT_WIRE.enabled()) {
            out = new WireLogOutputStream(out, Wire.CONTENT_WIRE);
        }
        return out;
    }
    
    public OutputStream getRequestOutputStream(final boolean useChunking) throws IOException, IllegalStateException {
        HttpConnection.LOG.trace("enter HttpConnection.getRequestOutputStream(boolean)");
        OutputStream out = this.getRequestOutputStream();
        if (useChunking) {
            out = new ChunkedOutputStream(out);
        }
        return out;
    }
    
    public InputStream getResponseInputStream(final HttpMethod method) throws IOException, IllegalStateException {
        HttpConnection.LOG.trace("enter HttpConnection.getResponseInputStream(HttpMethod)");
        return this.getResponseInputStream();
    }
    
    public InputStream getResponseInputStream() throws IOException, IllegalStateException {
        HttpConnection.LOG.trace("enter HttpConnection.getResponseInputStream()");
        this.assertOpen();
        return this.inputStream;
    }
    
    public boolean isResponseAvailable() throws IOException {
        HttpConnection.LOG.trace("enter HttpConnection.isResponseAvailable()");
        this.assertOpen();
        return this.inputStream.available() > 0;
    }
    
    public boolean isResponseAvailable(final int timeout) throws IOException {
        HttpConnection.LOG.trace("enter HttpConnection.isResponseAvailable(int)");
        this.assertOpen();
        boolean result = false;
        if (this.inputStream.available() > 0) {
            result = true;
        }
        else {
            try {
                this.socket.setSoTimeout(timeout);
                this.inputStream.mark(1);
                final int byteRead = this.inputStream.read();
                if (byteRead != -1) {
                    this.inputStream.reset();
                    HttpConnection.LOG.debug("Input data available");
                    result = true;
                }
                else {
                    HttpConnection.LOG.debug("Input data not available");
                }
            }
            catch (InterruptedIOException e) {
                if (HttpConnection.LOG.isDebugEnabled()) {
                    HttpConnection.LOG.debug("Input data not available after " + timeout + " ms");
                }
            }
            finally {
                try {
                    this.socket.setSoTimeout(this.soTimeout);
                }
                catch (IOException ioe) {
                    HttpConnection.LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", ioe);
                    result = false;
                }
            }
        }
        return result;
    }
    
    public void write(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.write(byte[])");
        this.write(data, 0, data.length);
    }
    
    public void write(final byte[] data, final int offset, final int length) throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.write(byte[], int, int)");
        if (offset + length > data.length) {
            throw new HttpRecoverableException("Unable to write: offset=" + offset + " length=" + length + " data.length=" + data.length);
        }
        if (data.length <= 0) {
            throw new HttpRecoverableException("Unable to write: data.length=" + data.length);
        }
        this.assertOpen();
        try {
            this.outputStream.write(data, offset, length);
        }
        catch (HttpRecoverableException hre) {
            throw hre;
        }
        catch (SocketException se) {
            HttpConnection.LOG.debug("HttpConnection: Socket exception while writing data", se);
            throw new HttpRecoverableException(se.toString());
        }
        catch (IOException ioe) {
            HttpConnection.LOG.debug("HttpConnection: Exception while writing data", ioe);
            throw ioe;
        }
    }
    
    public void writeLine(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.writeLine(byte[])");
        this.write(data);
        this.writeLine();
    }
    
    public void writeLine() throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.writeLine()");
        this.write(HttpConnection.CRLF);
    }
    
    public void print(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.print(String)");
        this.write(HttpConstants.getBytes(data));
    }
    
    public void printLine(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.printLine(String)");
        this.writeLine(HttpConstants.getBytes(data));
    }
    
    public void printLine() throws IOException, IllegalStateException, HttpRecoverableException {
        HttpConnection.LOG.trace("enter HttpConnection.printLine()");
        this.writeLine();
    }
    
    public String readLine() throws IOException, IllegalStateException {
        HttpConnection.LOG.trace("enter HttpConnection.readLine()");
        this.assertOpen();
        return HttpParser.readLine(this.inputStream);
    }
    
    public void shutdownOutput() {
        HttpConnection.LOG.trace("enter HttpConnection.shutdownOutput()");
        try {
            final Class[] paramsClasses = new Class[0];
            final Method shutdownOutput = this.socket.getClass().getMethod("shutdownOutput", (Class<?>[])paramsClasses);
            final Object[] params = new Object[0];
            shutdownOutput.invoke(this.socket, params);
        }
        catch (Exception ex) {
            HttpConnection.LOG.debug("Unexpected Exception caught", ex);
        }
    }
    
    public void close() {
        HttpConnection.LOG.trace("enter HttpConnection.close()");
        this.closeSocketAndStreams();
    }
    
    public HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }
    
    public void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
    }
    
    public void releaseConnection() {
        HttpConnection.LOG.trace("enter HttpConnection.releaseConnection()");
        this.used = true;
        if (this.httpConnectionManager != null) {
            this.httpConnectionManager.releaseConnection(this);
        }
    }
    
    protected void closeSocketAndStreams() {
        HttpConnection.LOG.trace("enter HttpConnection.closeSockedAndStreams()");
        this.lastResponseInputStream = null;
        if (null != this.outputStream) {
            final OutputStream temp = this.outputStream;
            this.outputStream = null;
            try {
                temp.close();
            }
            catch (Exception ex) {
                HttpConnection.LOG.debug("Exception caught when closing output", ex);
            }
        }
        if (null != this.inputStream) {
            final InputStream temp2 = this.inputStream;
            this.inputStream = null;
            try {
                temp2.close();
            }
            catch (Exception ex) {
                HttpConnection.LOG.debug("Exception caught when closing input", ex);
            }
        }
        if (null != this.socket) {
            final Socket temp3 = this.socket;
            this.socket = null;
            try {
                temp3.close();
            }
            catch (Exception ex) {
                HttpConnection.LOG.debug("Exception caught when closing socket", ex);
            }
        }
        this.isOpen = false;
        this.used = false;
        this.tunnelEstablished = false;
        this.usingSecureSocket = false;
    }
    
    protected void assertNotOpen() throws IllegalStateException {
        if (this.isOpen) {
            throw new IllegalStateException("Connection is open");
        }
    }
    
    protected void assertOpen() throws IllegalStateException {
        if (!this.isOpen) {
            throw new IllegalStateException("Connection is not open");
        }
    }
    
    public int getSendBufferSize() throws SocketException {
        if (this.socket == null) {
            return -1;
        }
        return this.socket.getSendBufferSize();
    }
    
    public void setSendBufferSize(final int sendBufferSize) throws SocketException {
        this.sendBufferSize = sendBufferSize;
        if (this.socket != null) {
            this.socket.setSendBufferSize(sendBufferSize);
        }
    }
    
    static {
        CRLF = new byte[] { 13, 10 };
        LOG = LogFactory.getLog(HttpConnection.class);
    }
    
    public static class ConnectionTimeoutException extends IOException
    {
    }
    
    private abstract class SocketTask implements Runnable
    {
        private Socket socket;
        private IOException exception;
        
        protected void setSocket(final Socket newSocket) {
            this.socket = newSocket;
        }
        
        protected Socket getSocket() {
            return this.socket;
        }
        
        public abstract void doit() throws IOException;
        
        public void run() {
            try {
                this.doit();
            }
            catch (IOException e) {
                this.exception = e;
            }
        }
    }
    
    private class WrappedOutputStream extends OutputStream
    {
        private OutputStream out;
        
        public WrappedOutputStream(final OutputStream out) {
            this.out = out;
        }
        
        private IOException handleException(final IOException ioe) {
            final boolean tempUsed = HttpConnection.this.used;
            HttpConnection.this.close();
            if (tempUsed) {
                HttpConnection.LOG.debug("Output exception occurred on a used connection.  Will treat as recoverable.", ioe);
                return new HttpRecoverableException(ioe.toString());
            }
            return ioe;
        }
        
        public void write(final int b) throws IOException {
            try {
                this.out.write(b);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void flush() throws IOException {
            try {
                this.out.flush();
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void close() throws IOException {
            try {
                this.out.close();
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void write(final byte[] b, final int off, final int len) throws IOException {
            try {
                this.out.write(b, off, len);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void write(final byte[] b) throws IOException {
            try {
                this.out.write(b);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
    }
}
