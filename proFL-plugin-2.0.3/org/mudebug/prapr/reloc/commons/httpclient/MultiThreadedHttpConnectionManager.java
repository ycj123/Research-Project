// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.net.SocketException;
import java.io.IOException;
import java.io.OutputStream;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;
import java.io.InputStream;
import java.net.InetAddress;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.HashMap;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class MultiThreadedHttpConnectionManager implements HttpConnectionManager
{
    private static final Log LOG;
    public static final int DEFAULT_MAX_HOST_CONNECTIONS = 2;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
    private static final Map REFERENCE_TO_CONNECTION_SOURCE;
    private static final ReferenceQueue REFERENCE_QUEUE;
    private static ReferenceQueueThread REFERENCE_QUEUE_THREAD;
    private static WeakHashMap ALL_CONNECTION_MANAGERS;
    private int maxHostConnections;
    private int maxTotalConnections;
    private boolean connectionStaleCheckingEnabled;
    private boolean shutdown;
    private ConnectionPool connectionPool;
    
    public static void shutdownAll() {
        synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE) {
            synchronized (MultiThreadedHttpConnectionManager.ALL_CONNECTION_MANAGERS) {
                final Iterator connIter = MultiThreadedHttpConnectionManager.ALL_CONNECTION_MANAGERS.keySet().iterator();
                while (connIter.hasNext()) {
                    final MultiThreadedHttpConnectionManager connManager = connIter.next();
                    connIter.remove();
                    connManager.shutdown();
                }
            }
            if (MultiThreadedHttpConnectionManager.REFERENCE_QUEUE_THREAD != null) {
                MultiThreadedHttpConnectionManager.REFERENCE_QUEUE_THREAD.shutdown();
                MultiThreadedHttpConnectionManager.REFERENCE_QUEUE_THREAD = null;
            }
            MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.clear();
        }
    }
    
    private static void storeReferenceToConnection(final HttpConnectionWithReference connection, final HostConfiguration hostConfiguration, final ConnectionPool connectionPool) {
        final ConnectionSource source = new ConnectionSource();
        source.connectionPool = connectionPool;
        source.hostConfiguration = hostConfiguration;
        synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE) {
            if (MultiThreadedHttpConnectionManager.REFERENCE_QUEUE_THREAD == null) {
                (MultiThreadedHttpConnectionManager.REFERENCE_QUEUE_THREAD = new ReferenceQueueThread()).start();
            }
            MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.put(connection.reference, source);
        }
    }
    
    private static void shutdownCheckedOutConnections(final ConnectionPool connectionPool) {
        final ArrayList connectionsToClose = new ArrayList();
        synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE) {
            final Iterator referenceIter = MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.keySet().iterator();
            while (referenceIter.hasNext()) {
                final Reference ref = referenceIter.next();
                final ConnectionSource source = MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.get(ref);
                if (source.connectionPool == connectionPool) {
                    referenceIter.remove();
                    final HttpConnection connection = ref.get();
                    if (connection == null) {
                        continue;
                    }
                    connectionsToClose.add(connection);
                }
            }
        }
        for (final HttpConnection connection2 : connectionsToClose) {
            connection2.close();
            connection2.setHttpConnectionManager(null);
            connection2.releaseConnection();
        }
    }
    
    private static void removeReferenceToConnection(final HttpConnectionWithReference connection) {
        synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE) {
            MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.remove(connection.reference);
        }
    }
    
    public MultiThreadedHttpConnectionManager() {
        this.maxHostConnections = 2;
        this.maxTotalConnections = 20;
        this.connectionStaleCheckingEnabled = true;
        this.shutdown = false;
        this.connectionPool = new ConnectionPool();
        synchronized (MultiThreadedHttpConnectionManager.ALL_CONNECTION_MANAGERS) {
            MultiThreadedHttpConnectionManager.ALL_CONNECTION_MANAGERS.put(this, null);
        }
    }
    
    public synchronized void shutdown() {
        synchronized (this.connectionPool) {
            if (!this.shutdown) {
                this.shutdown = true;
                this.connectionPool.shutdown();
            }
        }
    }
    
    public boolean isConnectionStaleCheckingEnabled() {
        return this.connectionStaleCheckingEnabled;
    }
    
    public void setConnectionStaleCheckingEnabled(final boolean connectionStaleCheckingEnabled) {
        this.connectionStaleCheckingEnabled = connectionStaleCheckingEnabled;
    }
    
    public void setMaxConnectionsPerHost(final int maxHostConnections) {
        this.maxHostConnections = maxHostConnections;
    }
    
    public int getMaxConnectionsPerHost() {
        return this.maxHostConnections;
    }
    
    public void setMaxTotalConnections(final int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }
    
    public int getMaxTotalConnections() {
        return this.maxTotalConnections;
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration) {
        try {
            return this.getConnection(hostConfiguration, 0L);
        }
        catch (HttpException e) {
            MultiThreadedHttpConnectionManager.LOG.debug("Unexpected exception while waiting for connection", e);
            return this.getConnection(hostConfiguration, 0L);
        }
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration, final long timeout) throws HttpException {
        MultiThreadedHttpConnectionManager.LOG.trace("enter HttpConnectionManager.getConnection(HostConfiguration, long)");
        if (hostConfiguration == null) {
            throw new IllegalArgumentException("hostConfiguration is null");
        }
        if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
            MultiThreadedHttpConnectionManager.LOG.debug("HttpConnectionManager.getConnection:  config = " + hostConfiguration + ", timeout = " + timeout);
        }
        final HttpConnection conn = this.doGetConnection(hostConfiguration, timeout);
        return new HttpConnectionAdapter(conn);
    }
    
    private HttpConnection doGetConnection(HostConfiguration hostConfiguration, final long timeout) throws HttpException {
        HttpConnection connection = null;
        synchronized (this.connectionPool) {
            hostConfiguration = new HostConfiguration(hostConfiguration);
            final HostConnectionPool hostPool = this.connectionPool.getHostPool(hostConfiguration);
            WaitingThread waitingThread = null;
            final boolean useTimeout = timeout > 0L;
            long timeToWait = timeout;
            long startWait = 0L;
            long endWait = 0L;
            while (connection == null) {
                if (this.shutdown) {
                    throw new IllegalStateException("Connection factory has been shutdown.");
                }
                if (hostPool.freeConnections.size() > 0) {
                    connection = this.connectionPool.getFreeConnection(hostConfiguration);
                }
                else if (hostPool.numConnections < this.maxHostConnections && this.connectionPool.numConnections < this.maxTotalConnections) {
                    connection = this.connectionPool.createConnection(hostConfiguration);
                }
                else if (hostPool.numConnections < this.maxHostConnections && this.connectionPool.freeConnections.size() > 0) {
                    this.connectionPool.deleteLeastUsedConnection();
                    connection = this.connectionPool.createConnection(hostConfiguration);
                }
                else {
                    try {
                        if (useTimeout && timeToWait <= 0L) {
                            throw new HttpException("Timeout waiting for connection");
                        }
                        if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                            MultiThreadedHttpConnectionManager.LOG.debug("Unable to get a connection, waiting..., hostConfig=" + hostConfiguration);
                        }
                        if (waitingThread == null) {
                            waitingThread = new WaitingThread();
                            waitingThread.hostConnectionPool = hostPool;
                            waitingThread.thread = Thread.currentThread();
                        }
                        if (useTimeout) {
                            startWait = System.currentTimeMillis();
                        }
                        hostPool.waitingThreads.addLast(waitingThread);
                        this.connectionPool.waitingThreads.addLast(waitingThread);
                        this.connectionPool.wait(timeToWait);
                        hostPool.waitingThreads.remove(waitingThread);
                        this.connectionPool.waitingThreads.remove(waitingThread);
                    }
                    catch (InterruptedException e) {}
                    finally {
                        if (useTimeout) {
                            endWait = System.currentTimeMillis();
                            timeToWait -= endWait - startWait;
                        }
                    }
                }
            }
        }
        return connection;
    }
    
    public int getConnectionsInUse(final HostConfiguration hostConfiguration) {
        synchronized (this.connectionPool) {
            final HostConnectionPool hostPool = this.connectionPool.getHostPool(hostConfiguration);
            return hostPool.numConnections;
        }
    }
    
    public int getConnectionsInUse() {
        synchronized (this.connectionPool) {
            return this.connectionPool.numConnections;
        }
    }
    
    public void releaseConnection(HttpConnection conn) {
        MultiThreadedHttpConnectionManager.LOG.trace("enter HttpConnectionManager.releaseConnection(HttpConnection)");
        if (conn instanceof HttpConnectionAdapter) {
            conn = ((HttpConnectionAdapter)conn).getWrappedConnection();
        }
        SimpleHttpConnectionManager.finishLastResponse(conn);
        this.connectionPool.freeConnection(conn);
    }
    
    private HostConfiguration configurationForConnection(final HttpConnection conn) {
        final HostConfiguration connectionConfiguration = new HostConfiguration();
        connectionConfiguration.setHost(conn.getHost(), conn.getVirtualHost(), conn.getPort(), conn.getProtocol());
        if (conn.getLocalAddress() != null) {
            connectionConfiguration.setLocalAddress(conn.getLocalAddress());
        }
        if (conn.getProxyHost() != null) {
            connectionConfiguration.setProxy(conn.getProxyHost(), conn.getProxyPort());
        }
        return connectionConfiguration;
    }
    
    static {
        LOG = LogFactory.getLog(MultiThreadedHttpConnectionManager.class);
        REFERENCE_TO_CONNECTION_SOURCE = new HashMap();
        REFERENCE_QUEUE = new ReferenceQueue();
        MultiThreadedHttpConnectionManager.ALL_CONNECTION_MANAGERS = new WeakHashMap();
    }
    
    private class ConnectionPool
    {
        private LinkedList freeConnections;
        private LinkedList waitingThreads;
        private final Map mapHosts;
        private int numConnections;
        
        private ConnectionPool() {
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.mapHosts = new HashMap();
            this.numConnections = 0;
        }
        
        public synchronized void shutdown() {
            Iterator iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                final HttpConnection conn = iter.next();
                iter.remove();
                conn.close();
            }
            shutdownCheckedOutConnections(this);
            iter = this.waitingThreads.iterator();
            while (iter.hasNext()) {
                final WaitingThread waiter = iter.next();
                iter.remove();
                waiter.thread.interrupt();
            }
            this.mapHosts.clear();
        }
        
        public synchronized HttpConnection createConnection(final HostConfiguration hostConfiguration) {
            HttpConnectionWithReference connection = null;
            final HostConnectionPool hostPool = this.getHostPool(hostConfiguration);
            if (hostPool.numConnections < MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost() && this.numConnections < MultiThreadedHttpConnectionManager.this.getMaxTotalConnections()) {
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("Allocating new connection, hostConfig=" + hostConfiguration);
                }
                connection = new HttpConnectionWithReference(hostConfiguration);
                connection.setStaleCheckingEnabled(MultiThreadedHttpConnectionManager.this.connectionStaleCheckingEnabled);
                connection.setHttpConnectionManager(MultiThreadedHttpConnectionManager.this);
                ++this.numConnections;
                final HostConnectionPool hostConnectionPool = hostPool;
                ++hostConnectionPool.numConnections;
                storeReferenceToConnection(connection, hostConfiguration, this);
            }
            else if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                if (hostPool.numConnections >= MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("No connection allocated, host pool has already reached maxConnectionsPerHost, hostConfig=" + hostConfiguration + ", maxConnectionsPerhost=" + MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost());
                }
                else {
                    MultiThreadedHttpConnectionManager.LOG.debug("No connection allocated, maxTotalConnections reached, maxTotalConnections=" + MultiThreadedHttpConnectionManager.this.getMaxTotalConnections());
                }
            }
            return connection;
        }
        
        public synchronized void handleLostConnection(final HostConfiguration config) {
            final HostConnectionPool hostPool2;
            final HostConnectionPool hostPool = hostPool2 = this.getHostPool(config);
            --hostPool2.numConnections;
            --this.numConnections;
            this.notifyWaitingThread(config);
        }
        
        public synchronized HostConnectionPool getHostPool(final HostConfiguration hostConfiguration) {
            MultiThreadedHttpConnectionManager.LOG.trace("enter HttpConnectionManager.ConnectionPool.getHostPool(HostConfiguration)");
            HostConnectionPool listConnections = this.mapHosts.get(hostConfiguration);
            if (listConnections == null) {
                listConnections = new HostConnectionPool();
                listConnections.hostConfiguration = hostConfiguration;
                this.mapHosts.put(hostConfiguration, listConnections);
            }
            return listConnections;
        }
        
        public synchronized HttpConnection getFreeConnection(final HostConfiguration hostConfiguration) {
            HttpConnectionWithReference connection = null;
            final HostConnectionPool hostPool = this.getHostPool(hostConfiguration);
            if (hostPool.freeConnections.size() > 0) {
                connection = hostPool.freeConnections.removeFirst();
                this.freeConnections.remove(connection);
                storeReferenceToConnection(connection, hostConfiguration, this);
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("Getting free connection, hostConfig=" + hostConfiguration);
                }
            }
            else if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.LOG.debug("There were no free connections to get, hostConfig=" + hostConfiguration);
            }
            return connection;
        }
        
        public synchronized void deleteLeastUsedConnection() {
            final HttpConnection connection = this.freeConnections.removeFirst();
            if (connection != null) {
                final HostConfiguration connectionConfiguration = MultiThreadedHttpConnectionManager.this.configurationForConnection(connection);
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("Reclaiming unused connection, hostConfig=" + connectionConfiguration);
                }
                connection.close();
                final HostConnectionPool hostPool = this.getHostPool(connectionConfiguration);
                hostPool.freeConnections.remove(connection);
                final HostConnectionPool hostConnectionPool = hostPool;
                --hostConnectionPool.numConnections;
                --this.numConnections;
            }
            else if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.LOG.debug("Attempted to reclaim an unused connection but there were none.");
            }
        }
        
        public synchronized void notifyWaitingThread(final HostConfiguration configuration) {
            this.notifyWaitingThread(this.getHostPool(configuration));
        }
        
        public synchronized void notifyWaitingThread(final HostConnectionPool hostPool) {
            WaitingThread waitingThread = null;
            if (hostPool.waitingThreads.size() > 0) {
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("Notifying thread waiting on host pool, hostConfig=" + hostPool.hostConfiguration);
                }
                waitingThread = hostPool.waitingThreads.removeFirst();
                this.waitingThreads.remove(waitingThread);
            }
            else if (this.waitingThreads.size() > 0) {
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("No-one waiting on host pool, notifying next waiting thread.");
                }
                waitingThread = this.waitingThreads.removeFirst();
                waitingThread.hostConnectionPool.waitingThreads.remove(waitingThread);
            }
            else if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.LOG.debug("Notifying no-one, there are no waiting threads");
            }
            if (waitingThread != null) {
                waitingThread.thread.interrupt();
            }
        }
        
        public void freeConnection(final HttpConnection conn) {
            final HostConfiguration connectionConfiguration = MultiThreadedHttpConnectionManager.this.configurationForConnection(conn);
            if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.LOG.debug("Freeing connection, hostConfig=" + connectionConfiguration);
            }
            synchronized (this) {
                if (MultiThreadedHttpConnectionManager.this.shutdown) {
                    conn.close();
                    return;
                }
                final HostConnectionPool hostPool = this.getHostPool(connectionConfiguration);
                hostPool.freeConnections.add(conn);
                if (hostPool.numConnections == 0) {
                    MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + connectionConfiguration);
                    hostPool.numConnections = 1;
                }
                this.freeConnections.add(conn);
                removeReferenceToConnection((HttpConnectionWithReference)conn);
                if (this.numConnections == 0) {
                    MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + connectionConfiguration);
                    this.numConnections = 1;
                }
                this.notifyWaitingThread(hostPool);
            }
        }
    }
    
    private static class ConnectionSource
    {
        public ConnectionPool connectionPool;
        public HostConfiguration hostConfiguration;
    }
    
    private static class HostConnectionPool
    {
        public HostConfiguration hostConfiguration;
        public LinkedList freeConnections;
        public LinkedList waitingThreads;
        public int numConnections;
        
        private HostConnectionPool() {
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.numConnections = 0;
        }
    }
    
    private static class WaitingThread
    {
        public Thread thread;
        public HostConnectionPool hostConnectionPool;
    }
    
    private static class ReferenceQueueThread extends Thread
    {
        private boolean shutdown;
        
        public ReferenceQueueThread() {
            this.shutdown = false;
            this.setDaemon(true);
            this.setName("MultiThreadedHttpConnectionManager cleanup");
        }
        
        public void shutdown() {
            this.shutdown = true;
        }
        
        private void handleReference(final Reference ref) {
            ConnectionSource source = null;
            synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE) {
                source = MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.remove(ref);
            }
            if (source != null) {
                if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.LOG.debug("Connection reclaimed by garbage collector, hostConfig=" + source.hostConfiguration);
                }
                source.connectionPool.handleLostConnection(source.hostConfiguration);
            }
        }
        
        public void run() {
            while (!this.shutdown) {
                try {
                    final Reference ref = MultiThreadedHttpConnectionManager.REFERENCE_QUEUE.remove(1000L);
                    if (ref == null) {
                        continue;
                    }
                    this.handleReference(ref);
                }
                catch (InterruptedException e) {
                    MultiThreadedHttpConnectionManager.LOG.debug("ReferenceQueueThread interrupted", e);
                }
            }
        }
    }
    
    private static class HttpConnectionWithReference extends HttpConnection
    {
        public WeakReference reference;
        
        public HttpConnectionWithReference(final HostConfiguration hostConfiguration) {
            super(hostConfiguration);
            this.reference = new WeakReference((T)this, MultiThreadedHttpConnectionManager.REFERENCE_QUEUE);
        }
    }
    
    private static class HttpConnectionAdapter extends HttpConnection
    {
        private HttpConnection wrappedConnection;
        
        public HttpConnectionAdapter(final HttpConnection connection) {
            super(connection.getHost(), connection.getPort(), connection.getProtocol());
            this.wrappedConnection = connection;
        }
        
        protected boolean hasConnection() {
            return this.wrappedConnection != null;
        }
        
        HttpConnection getWrappedConnection() {
            return this.wrappedConnection;
        }
        
        public void close() {
            if (this.hasConnection()) {
                this.wrappedConnection.close();
            }
        }
        
        public InetAddress getLocalAddress() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getLocalAddress();
            }
            return null;
        }
        
        public boolean isStaleCheckingEnabled() {
            return this.hasConnection() && this.wrappedConnection.isStaleCheckingEnabled();
        }
        
        public void setLocalAddress(final InetAddress localAddress) {
            if (this.hasConnection()) {
                this.wrappedConnection.setLocalAddress(localAddress);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void setStaleCheckingEnabled(final boolean staleCheckEnabled) {
            if (this.hasConnection()) {
                this.wrappedConnection.setStaleCheckingEnabled(staleCheckEnabled);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public String getHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getHost();
            }
            return null;
        }
        
        public HttpConnectionManager getHttpConnectionManager() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getHttpConnectionManager();
            }
            return null;
        }
        
        public InputStream getLastResponseInputStream() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getLastResponseInputStream();
            }
            return null;
        }
        
        public int getPort() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getPort();
            }
            return -1;
        }
        
        public Protocol getProtocol() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProtocol();
            }
            return null;
        }
        
        public String getProxyHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProxyHost();
            }
            return null;
        }
        
        public int getProxyPort() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProxyPort();
            }
            return -1;
        }
        
        public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getRequestOutputStream();
            }
            return null;
        }
        
        public OutputStream getRequestOutputStream(final boolean useChunking) throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getRequestOutputStream(useChunking);
            }
            return null;
        }
        
        public InputStream getResponseInputStream() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getResponseInputStream();
            }
            return null;
        }
        
        public InputStream getResponseInputStream(final HttpMethod method) throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getResponseInputStream(method);
            }
            return null;
        }
        
        public boolean isOpen() {
            return this.hasConnection() && this.wrappedConnection.isOpen();
        }
        
        public boolean isProxied() {
            return this.hasConnection() && this.wrappedConnection.isProxied();
        }
        
        public boolean isResponseAvailable() throws IOException {
            return this.hasConnection() && this.wrappedConnection.isResponseAvailable();
        }
        
        public boolean isResponseAvailable(final int timeout) throws IOException {
            return this.hasConnection() && this.wrappedConnection.isResponseAvailable(timeout);
        }
        
        public boolean isSecure() {
            return this.hasConnection() && this.wrappedConnection.isSecure();
        }
        
        public boolean isTransparent() {
            return this.hasConnection() && this.wrappedConnection.isTransparent();
        }
        
        public void open() throws IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.open();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void print(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.print(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void printLine() throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.printLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void printLine(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.printLine(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public String readLine() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.readLine();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void releaseConnection() {
            if (this.hasConnection()) {
                final HttpConnection wrappedConnection = this.wrappedConnection;
                this.wrappedConnection = null;
                wrappedConnection.releaseConnection();
            }
        }
        
        public void setConnectionTimeout(final int timeout) {
            if (this.hasConnection()) {
                this.wrappedConnection.setConnectionTimeout(timeout);
            }
        }
        
        public void setHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setHost(host);
            }
        }
        
        public void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
            if (this.hasConnection()) {
                this.wrappedConnection.setHttpConnectionManager(httpConnectionManager);
            }
        }
        
        public void setLastResponseInputStream(final InputStream inStream) {
            if (this.hasConnection()) {
                this.wrappedConnection.setLastResponseInputStream(inStream);
            }
        }
        
        public void setPort(final int port) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setPort(port);
            }
        }
        
        public void setProtocol(final Protocol protocol) {
            if (this.hasConnection()) {
                this.wrappedConnection.setProtocol(protocol);
            }
        }
        
        public void setProxyHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setProxyHost(host);
            }
        }
        
        public void setProxyPort(final int port) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setProxyPort(port);
            }
        }
        
        public void setSecure(final boolean secure) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setSecure(secure);
            }
        }
        
        public void setSoTimeout(final int timeout) throws SocketException, IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setSoTimeout(timeout);
            }
        }
        
        public void shutdownOutput() {
            if (this.hasConnection()) {
                this.wrappedConnection.shutdownOutput();
            }
        }
        
        public void tunnelCreated() throws IllegalStateException, IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.tunnelCreated();
            }
        }
        
        public void write(final byte[] data, final int offset, final int length) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.write(data, offset, length);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void write(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.write(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void writeLine() throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.writeLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void writeLine(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.writeLine(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void flushRequestOutputStream() throws IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.flushRequestOutputStream();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public int getSoTimeout() throws SocketException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getSoTimeout();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public String getVirtualHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getVirtualHost();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void setVirtualHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setVirtualHost(host);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public int getSendBufferSize() throws SocketException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getSendBufferSize();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void setSendBufferSize(final int sendBufferSize) throws SocketException {
            if (this.hasConnection()) {
                this.wrappedConnection.setSendBufferSize(sendBufferSize);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
    }
}
