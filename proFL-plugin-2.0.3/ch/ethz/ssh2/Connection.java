// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import ch.ethz.ssh2.util.TimeoutService;
import java.net.SocketTimeoutException;
import java.io.FileReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import ch.ethz.ssh2.transport.KexManager;
import ch.ethz.ssh2.crypto.digest.MAC;
import ch.ethz.ssh2.crypto.cipher.BlockCipherFactory;
import java.util.Vector;
import ch.ethz.ssh2.transport.TransportManager;
import ch.ethz.ssh2.crypto.CryptoWishList;
import ch.ethz.ssh2.channel.ChannelManager;
import ch.ethz.ssh2.auth.AuthenticationManager;
import java.security.SecureRandom;

public class Connection
{
    public static final String identification = "Ganymed Build_210";
    private SecureRandom generator;
    private AuthenticationManager am;
    private boolean authenticated;
    private ChannelManager cm;
    private CryptoWishList cryptoWishList;
    private DHGexParameters dhgexpara;
    private final String hostname;
    private final int port;
    private TransportManager tm;
    private boolean tcpNoDelay;
    private ProxyData proxyData;
    private Vector connectionMonitors;
    
    public static synchronized String[] getAvailableCiphers() {
        return BlockCipherFactory.getDefaultCipherList();
    }
    
    public static synchronized String[] getAvailableMACs() {
        return MAC.getMacList();
    }
    
    public static synchronized String[] getAvailableServerHostKeyAlgorithms() {
        return KexManager.getDefaultServerHostkeyAlgorithmList();
    }
    
    public Connection(final String hostname) {
        this(hostname, 22);
    }
    
    public Connection(final String hostname, final int port) {
        this.authenticated = false;
        this.cryptoWishList = new CryptoWishList();
        this.dhgexpara = new DHGexParameters();
        this.tcpNoDelay = false;
        this.proxyData = null;
        this.connectionMonitors = new Vector();
        this.hostname = hostname;
        this.port = port;
    }
    
    public synchronized boolean authenticateWithDSA(final String user, final String pem, final String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        }
        if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        }
        if (this.am == null) {
            this.am = new AuthenticationManager(this.tm);
        }
        if (this.cm == null) {
            this.cm = new ChannelManager(this.tm);
        }
        if (user == null) {
            throw new IllegalArgumentException("user argument is null");
        }
        if (pem == null) {
            throw new IllegalArgumentException("pem argument is null");
        }
        return this.authenticated = this.am.authenticatePublicKey(user, pem.toCharArray(), password, this.getOrCreateSecureRND());
    }
    
    public synchronized boolean authenticateWithKeyboardInteractive(final String user, final InteractiveCallback cb) throws IOException {
        return this.authenticateWithKeyboardInteractive(user, null, cb);
    }
    
    public synchronized boolean authenticateWithKeyboardInteractive(final String user, final String[] submethods, final InteractiveCallback cb) throws IOException {
        if (cb == null) {
            throw new IllegalArgumentException("Callback may not ne NULL!");
        }
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        }
        if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        }
        if (this.am == null) {
            this.am = new AuthenticationManager(this.tm);
        }
        if (this.cm == null) {
            this.cm = new ChannelManager(this.tm);
        }
        if (user == null) {
            throw new IllegalArgumentException("user argument is null");
        }
        return this.authenticated = this.am.authenticateInteractive(user, submethods, cb);
    }
    
    public synchronized boolean authenticateWithPassword(final String user, final String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        }
        if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        }
        if (this.am == null) {
            this.am = new AuthenticationManager(this.tm);
        }
        if (this.cm == null) {
            this.cm = new ChannelManager(this.tm);
        }
        if (user == null) {
            throw new IllegalArgumentException("user argument is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password argument is null");
        }
        return this.authenticated = this.am.authenticatePassword(user, password);
    }
    
    public synchronized boolean authenticateWithPublicKey(final String user, final char[] pemPrivateKey, final String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        }
        if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        }
        if (this.am == null) {
            this.am = new AuthenticationManager(this.tm);
        }
        if (this.cm == null) {
            this.cm = new ChannelManager(this.tm);
        }
        if (user == null) {
            throw new IllegalArgumentException("user argument is null");
        }
        if (pemPrivateKey == null) {
            throw new IllegalArgumentException("pemPrivateKey argument is null");
        }
        return this.authenticated = this.am.authenticatePublicKey(user, pemPrivateKey, password, this.getOrCreateSecureRND());
    }
    
    public synchronized boolean authenticateWithPublicKey(final String user, final File pemFile, final String password) throws IOException {
        if (pemFile == null) {
            throw new IllegalArgumentException("pemFile argument is null");
        }
        final char[] buff = new char[256];
        final CharArrayWriter cw = new CharArrayWriter();
        final FileReader fr = new FileReader(pemFile);
        while (true) {
            final int len = fr.read(buff);
            if (len < 0) {
                break;
            }
            cw.write(buff, 0, len);
        }
        fr.close();
        return this.authenticateWithPublicKey(user, cw.toCharArray(), password);
    }
    
    public synchronized void addConnectionMonitor(final ConnectionMonitor cmon) {
        if (cmon == null) {
            throw new IllegalArgumentException("cmon argument is null");
        }
        this.connectionMonitors.addElement(cmon);
        if (this.tm != null) {
            this.tm.setConnectionMonitors(this.connectionMonitors);
        }
    }
    
    public synchronized void close() {
        final Throwable t = new Throwable("Closed due to user request.");
        this.close(t, false);
    }
    
    private void close(final Throwable t, final boolean hard) {
        if (this.cm != null) {
            this.cm.closeAllChannels();
        }
        if (this.tm != null) {
            this.tm.close(t, !hard);
            this.tm = null;
        }
        this.am = null;
        this.cm = null;
        this.authenticated = false;
    }
    
    public synchronized ConnectionInfo connect() throws IOException {
        return this.connect(null, 0, 0);
    }
    
    public synchronized ConnectionInfo connect(final ServerHostKeyVerifier verifier) throws IOException {
        return this.connect(verifier, 0, 0);
    }
    
    public synchronized ConnectionInfo connect(final ServerHostKeyVerifier verifier, final int connectTimeout, final int kexTimeout) throws IOException {
        if (this.tm != null) {
            throw new IOException("Connection to " + this.hostname + " is already in connected state!");
        }
        if (connectTimeout < 0) {
            throw new IllegalArgumentException("connectTimeout must be non-negative!");
        }
        if (kexTimeout < 0) {
            throw new IllegalArgumentException("kexTimeout must be non-negative!");
        }
        final Connection$1.TimeoutState state = new Connection$1.TimeoutState();
        (this.tm = new TransportManager(this.hostname, this.port)).setConnectionMonitors(this.connectionMonitors);
        final TransportManager tm = this.tm;
        // monitorenter(tm)
        // monitorexit(tm)
        try {
            TimeoutService.TimeoutToken token = null;
            if (kexTimeout > 0) {
                final Runnable timeoutHandler = new Runnable() {
                    public void run() {
                        synchronized (state) {
                            if (state.isCancelled) {
                                // monitorexit(this.val$state)
                                return;
                            }
                            state.timeoutSocketClosed = true;
                            Connection.this.tm.close(new SocketTimeoutException("The connect timeout expired"), false);
                        }
                        // monitorexit(this.val$state)
                    }
                    
                    private final class TimeoutState
                    {
                        boolean isCancelled;
                        boolean timeoutSocketClosed;
                        
                        TimeoutState() {
                            this.isCancelled = false;
                            this.timeoutSocketClosed = false;
                        }
                    }
                };
                final long timeoutHorizont = System.currentTimeMillis() + kexTimeout;
                token = TimeoutService.addTimeoutHandler(timeoutHorizont, timeoutHandler);
            }
            try {
                this.tm.initialize(this.cryptoWishList, verifier, this.dhgexpara, connectTimeout, this.getOrCreateSecureRND(), this.proxyData);
            }
            catch (SocketTimeoutException se) {
                throw (SocketTimeoutException)new SocketTimeoutException("The connect() operation on the socket timed out.").initCause(se);
            }
            this.tm.setTcpNoDelay(this.tcpNoDelay);
            final ConnectionInfo ci = this.tm.getConnectionInfo(1);
            if (token != null) {
                TimeoutService.cancelTimeoutHandler(token);
                synchronized (state) {
                    if (state.timeoutSocketClosed) {
                        throw new IOException("This exception will be replaced by the one below =)");
                    }
                    state.isCancelled = true;
                }
                // monitorexit(state)
            }
            return ci;
        }
        catch (SocketTimeoutException ste) {
            throw ste;
        }
        catch (IOException e1) {
            this.close(new Throwable("There was a problem during connect."), false);
            synchronized (state) {
                if (state.timeoutSocketClosed) {
                    throw new SocketTimeoutException("The kexTimeout (" + kexTimeout + " ms) expired.");
                }
            }
            // monitorexit(state)
            if (e1 instanceof HTTPProxyException) {
                throw e1;
            }
            throw (IOException)new IOException("There was a problem while connecting to " + this.hostname + ":" + this.port).initCause(e1);
        }
    }
    
    public synchronized LocalPortForwarder createLocalPortForwarder(final int local_port, final String host_to_connect, final int port_to_connect) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot forward ports, you need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("Cannot forward ports, connection is not authenticated.");
        }
        return new LocalPortForwarder(this.cm, local_port, host_to_connect, port_to_connect);
    }
    
    public synchronized LocalStreamForwarder createLocalStreamForwarder(final String host_to_connect, final int port_to_connect) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot forward, you need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("Cannot forward, connection is not authenticated.");
        }
        return new LocalStreamForwarder(this.cm, host_to_connect, port_to_connect);
    }
    
    public synchronized SCPClient createSCPClient() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot create SCP client, you need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("Cannot create SCP client, connection is not authenticated.");
        }
        return new SCPClient(this);
    }
    
    public synchronized void forceKeyExchange() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        }
        this.tm.forceKeyExchange(this.cryptoWishList, this.dhgexpara);
    }
    
    public synchronized String getHostname() {
        return this.hostname;
    }
    
    public synchronized int getPort() {
        return this.port;
    }
    
    public synchronized ConnectionInfo getConnectionInfo() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot get details of connection, you need to establish a connection first.");
        }
        return this.tm.getConnectionInfo(1);
    }
    
    public synchronized String[] getRemainingAuthMethods(final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("user argument may not be NULL!");
        }
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        }
        if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        }
        if (this.am == null) {
            this.am = new AuthenticationManager(this.tm);
        }
        if (this.cm == null) {
            this.cm = new ChannelManager(this.tm);
        }
        return this.am.getRemainingMethods(user);
    }
    
    public synchronized boolean isAuthenticationComplete() {
        return this.authenticated;
    }
    
    public synchronized boolean isAuthenticationPartialSuccess() {
        return this.am != null && this.am.getPartialSuccess();
    }
    
    public synchronized boolean isAuthMethodAvailable(final String user, final String method) throws IOException {
        if (method == null) {
            throw new IllegalArgumentException("method argument may not be NULL!");
        }
        final String[] methods = this.getRemainingAuthMethods(user);
        for (int i = 0; i < methods.length; ++i) {
            if (methods[i].compareTo(method) == 0) {
                return true;
            }
        }
        return false;
    }
    
    private final SecureRandom getOrCreateSecureRND() {
        if (this.generator == null) {
            this.generator = new SecureRandom();
        }
        return this.generator;
    }
    
    public synchronized Session openSession() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot open session, you need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("Cannot open session, connection is not authenticated.");
        }
        return new Session(this.cm, this.getOrCreateSecureRND());
    }
    
    private String[] removeDuplicates(final String[] list) {
        if (list == null || list.length < 2) {
            return list;
        }
        final String[] list2 = new String[list.length];
        int count = 0;
        for (int i = 0; i < list.length; ++i) {
            boolean duplicate = false;
            final String element = list[i];
            for (int j = 0; j < count; ++j) {
                if ((element == null && list2[j] == null) || (element != null && element.equals(list2[j]))) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                list2[count++] = list[i];
            }
        }
        if (count == list2.length) {
            return list2;
        }
        final String[] tmp = new String[count];
        System.arraycopy(list2, 0, tmp, 0, count);
        return tmp;
    }
    
    public synchronized void setClient2ServerCiphers(String[] ciphers) {
        if (ciphers == null || ciphers.length == 0) {
            throw new IllegalArgumentException();
        }
        ciphers = this.removeDuplicates(ciphers);
        BlockCipherFactory.checkCipherList(ciphers);
        this.cryptoWishList.c2s_enc_algos = ciphers;
    }
    
    public synchronized void setClient2ServerMACs(String[] macs) {
        if (macs == null || macs.length == 0) {
            throw new IllegalArgumentException();
        }
        macs = this.removeDuplicates(macs);
        MAC.checkMacList(macs);
        this.cryptoWishList.c2s_mac_algos = macs;
    }
    
    public synchronized void setDHGexParameters(final DHGexParameters dgp) {
        if (dgp == null) {
            throw new IllegalArgumentException();
        }
        this.dhgexpara = dgp;
    }
    
    public synchronized void setServer2ClientCiphers(String[] ciphers) {
        if (ciphers == null || ciphers.length == 0) {
            throw new IllegalArgumentException();
        }
        ciphers = this.removeDuplicates(ciphers);
        BlockCipherFactory.checkCipherList(ciphers);
        this.cryptoWishList.s2c_enc_algos = ciphers;
    }
    
    public synchronized void setServer2ClientMACs(String[] macs) {
        if (macs == null || macs.length == 0) {
            throw new IllegalArgumentException();
        }
        macs = this.removeDuplicates(macs);
        MAC.checkMacList(macs);
        this.cryptoWishList.s2c_mac_algos = macs;
    }
    
    public synchronized void setServerHostKeyAlgorithms(String[] algos) {
        if (algos == null || algos.length == 0) {
            throw new IllegalArgumentException();
        }
        algos = this.removeDuplicates(algos);
        KexManager.checkServerHostkeyAlgorithmsList(algos);
        this.cryptoWishList.serverHostKeyAlgorithms = algos;
    }
    
    public synchronized void setTCPNoDelay(final boolean enable) throws IOException {
        this.tcpNoDelay = enable;
        if (this.tm != null) {
            this.tm.setTcpNoDelay(enable);
        }
    }
    
    public synchronized void setProxyData(final ProxyData proxyData) {
        this.proxyData = proxyData;
    }
    
    public synchronized void requestRemotePortForwarding(final String bindAddress, final int bindPort, final String targetAddress, final int targetPort) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("The connection is not authenticated.");
        }
        if (bindAddress == null || targetAddress == null || bindPort <= 0 || targetPort <= 0) {
            throw new IllegalArgumentException();
        }
        this.cm.requestGlobalForward(bindAddress, bindPort, targetAddress, targetPort);
    }
    
    public synchronized void cancelRemotePortForwarding(final int bindPort) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        }
        if (!this.authenticated) {
            throw new IllegalStateException("The connection is not authenticated.");
        }
        this.cm.requestCancelGlobalForward(bindPort);
    }
    
    public synchronized void setSecureRandom(final SecureRandom rnd) {
        if (rnd == null) {
            throw new IllegalArgumentException();
        }
        this.generator = rnd;
    }
}
