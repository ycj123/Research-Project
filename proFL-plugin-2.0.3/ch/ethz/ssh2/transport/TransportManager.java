// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.transport;

import ch.ethz.ssh2.packets.TypesReader;
import ch.ethz.ssh2.crypto.digest.MAC;
import ch.ethz.ssh2.crypto.cipher.BlockCipher;
import java.security.SecureRandom;
import ch.ethz.ssh2.DHGexParameters;
import ch.ethz.ssh2.ServerHostKeyVerifier;
import ch.ethz.ssh2.crypto.CryptoWishList;
import java.io.InputStream;
import java.io.OutputStream;
import ch.ethz.ssh2.HTTPProxyException;
import ch.ethz.ssh2.crypto.Base64;
import ch.ethz.ssh2.HTTPProxyData;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import ch.ethz.ssh2.ProxyData;
import ch.ethz.ssh2.ConnectionMonitor;
import ch.ethz.ssh2.packets.PacketDisconnect;
import ch.ethz.ssh2.ConnectionInfo;
import java.io.IOException;
import ch.ethz.ssh2.util.Tokenizer;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;
import ch.ethz.ssh2.log.Logger;

public class TransportManager
{
    private static final Logger log;
    private final Vector asynchronousQueue;
    private Thread asynchronousThread;
    String hostname;
    int port;
    final Socket sock;
    Object connectionSemaphore;
    boolean flagKexOngoing;
    boolean connectionClosed;
    Throwable reasonClosedCause;
    TransportConnection tc;
    KexManager km;
    Vector messageHandlers;
    Thread receiveThread;
    Vector connectionMonitors;
    boolean monitorsWereInformed;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = TransportManager.class$0) == null) {
            try {
                class$0 = (TransportManager.class$0 = Class.forName("ch.ethz.ssh2.transport.TransportManager"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    private InetAddress createInetAddress(final String host) throws UnknownHostException {
        final InetAddress addr = this.parseIPv4Address(host);
        if (addr != null) {
            return addr;
        }
        return InetAddress.getByName(host);
    }
    
    private InetAddress parseIPv4Address(final String host) throws UnknownHostException {
        if (host == null) {
            return null;
        }
        final String[] quad = Tokenizer.parseTokens(host, '.');
        if (quad == null || quad.length != 4) {
            return null;
        }
        final byte[] addr = new byte[4];
        for (int i = 0; i < 4; ++i) {
            int part = 0;
            if (quad[i].length() == 0 || quad[i].length() > 3) {
                return null;
            }
            for (int k = 0; k < quad[i].length(); ++k) {
                final char c = quad[i].charAt(k);
                if (c < '0' || c > '9') {
                    return null;
                }
                part = part * 10 + (c - '0');
            }
            if (part > 255) {
                return null;
            }
            addr[i] = (byte)part;
        }
        return InetAddress.getByAddress(host, addr);
    }
    
    public TransportManager(final String host, final int port) throws IOException {
        this.asynchronousQueue = new Vector();
        this.asynchronousThread = null;
        this.sock = new Socket();
        this.connectionSemaphore = new Object();
        this.flagKexOngoing = false;
        this.connectionClosed = false;
        this.reasonClosedCause = null;
        this.messageHandlers = new Vector();
        this.connectionMonitors = new Vector();
        this.monitorsWereInformed = false;
        this.hostname = host;
        this.port = port;
    }
    
    public int getPacketOverheadEstimate() {
        return this.tc.getPacketOverheadEstimate();
    }
    
    public void setTcpNoDelay(final boolean state) throws IOException {
        this.sock.setTcpNoDelay(state);
    }
    
    public void setSoTimeout(final int timeout) throws IOException {
        this.sock.setSoTimeout(timeout);
    }
    
    public ConnectionInfo getConnectionInfo(final int kexNumber) throws IOException {
        return this.km.getOrWaitForConnectionInfo(kexNumber);
    }
    
    public Throwable getReasonClosedCause() {
        synchronized (this.connectionSemaphore) {
            // monitorexit(this.connectionSemaphore)
            return this.reasonClosedCause;
        }
    }
    
    public byte[] getSessionIdentifier() {
        return this.km.sessionId;
    }
    
    public void close(final Throwable cause, final boolean useDisconnectPacket) {
        if (!useDisconnectPacket) {
            try {
                this.sock.close();
            }
            catch (IOException ex) {}
        }
        synchronized (this.connectionSemaphore) {
            if (!this.connectionClosed) {
                if (useDisconnectPacket) {
                    try {
                        final byte[] msg = new PacketDisconnect(11, cause.getMessage(), "").getPayload();
                        if (this.tc != null) {
                            this.tc.sendMessage(msg);
                        }
                    }
                    catch (IOException ex2) {}
                    try {
                        this.sock.close();
                    }
                    catch (IOException ex3) {}
                }
                this.connectionClosed = true;
                this.reasonClosedCause = cause;
            }
            this.connectionSemaphore.notifyAll();
        }
        // monitorexit(this.connectionSemaphore)
        Vector monitors = null;
        synchronized (this) {
            if (!this.monitorsWereInformed) {
                this.monitorsWereInformed = true;
                monitors = (Vector)this.connectionMonitors.clone();
            }
        }
        if (monitors != null) {
            for (int i = 0; i < monitors.size(); ++i) {
                try {
                    final ConnectionMonitor cmon = monitors.elementAt(i);
                    cmon.connectionLost(this.reasonClosedCause);
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    private void establishConnection(final ProxyData proxyData, final int connectTimeout) throws IOException {
        if (proxyData == null) {
            final InetAddress addr = this.createInetAddress(this.hostname);
            this.sock.connect(new InetSocketAddress(addr, this.port), connectTimeout);
            this.sock.setSoTimeout(0);
            return;
        }
        if (!(proxyData instanceof HTTPProxyData)) {
            throw new IOException("Unsupported ProxyData");
        }
        final HTTPProxyData pd = (HTTPProxyData)proxyData;
        final InetAddress addr2 = this.createInetAddress(pd.proxyHost);
        this.sock.connect(new InetSocketAddress(addr2, pd.proxyPort), connectTimeout);
        this.sock.setSoTimeout(0);
        final StringBuffer sb = new StringBuffer();
        sb.append("CONNECT ");
        sb.append(this.hostname);
        sb.append(':');
        sb.append(this.port);
        sb.append(" HTTP/1.0\r\n");
        if (pd.proxyUser != null && pd.proxyPass != null) {
            final String credentials = String.valueOf(pd.proxyUser) + ":" + pd.proxyPass;
            final char[] encoded = Base64.encode(credentials.getBytes());
            sb.append("Proxy-Authorization: Basic ");
            sb.append(encoded);
            sb.append("\r\n");
        }
        if (pd.requestHeaderLines != null) {
            for (int i = 0; i < pd.requestHeaderLines.length; ++i) {
                if (pd.requestHeaderLines[i] != null) {
                    sb.append(pd.requestHeaderLines[i]);
                    sb.append("\r\n");
                }
            }
        }
        sb.append("\r\n");
        final OutputStream out = this.sock.getOutputStream();
        out.write(sb.toString().getBytes());
        out.flush();
        final byte[] buffer = new byte[1024];
        final InputStream in = this.sock.getInputStream();
        int len = ClientServerHello.readLineRN(in, buffer);
        final String httpReponse = new String(buffer, 0, len);
        if (!httpReponse.startsWith("HTTP/")) {
            throw new IOException("The proxy did not send back a valid HTTP response.");
        }
        if (httpReponse.length() < 14 || httpReponse.charAt(8) != ' ' || httpReponse.charAt(12) != ' ') {
            throw new IOException("The proxy did not send back a valid HTTP response.");
        }
        int errorCode = 0;
        try {
            errorCode = Integer.parseInt(httpReponse.substring(9, 12));
        }
        catch (NumberFormatException ignore) {
            throw new IOException("The proxy did not send back a valid HTTP response.");
        }
        if (errorCode < 0 || errorCode > 999) {
            throw new IOException("The proxy did not send back a valid HTTP response.");
        }
        if (errorCode != 200) {
            throw new HTTPProxyException(httpReponse.substring(13), errorCode);
        }
        do {
            len = ClientServerHello.readLineRN(in, buffer);
        } while (len != 0);
    }
    
    public void initialize(final CryptoWishList cwl, final ServerHostKeyVerifier verifier, final DHGexParameters dhgex, final int connectTimeout, final SecureRandom rnd, final ProxyData proxyData) throws IOException {
        this.establishConnection(proxyData, connectTimeout);
        final ClientServerHello csh = new ClientServerHello(this.sock.getInputStream(), this.sock.getOutputStream());
        this.tc = new TransportConnection(this.sock.getInputStream(), this.sock.getOutputStream(), rnd);
        (this.km = new KexManager(this, csh, cwl, this.hostname, this.port, verifier, rnd)).initiateKEX(cwl, dhgex);
        (this.receiveThread = new Thread(new Runnable() {
            public void run() {
                try {
                    TransportManager.this.receiveLoop();
                }
                catch (IOException e) {
                    TransportManager.this.close(e, false);
                    if (TransportManager.log.isEnabled()) {
                        TransportManager.log.log(10, "Receive thread: error in receiveLoop: " + e.getMessage());
                    }
                }
                if (TransportManager.log.isEnabled()) {
                    TransportManager.log.log(50, "Receive thread: back from receiveLoop");
                }
                if (TransportManager.this.km != null) {
                    try {
                        TransportManager.this.km.handleMessage(null, 0);
                    }
                    catch (IOException ex) {}
                }
                for (int i = 0; i < TransportManager.this.messageHandlers.size(); ++i) {
                    final HandlerEntry he = TransportManager.this.messageHandlers.elementAt(i);
                    try {
                        he.mh.handleMessage(null, 0);
                    }
                    catch (Exception ex2) {}
                }
            }
        })).setDaemon(true);
        this.receiveThread.start();
    }
    
    public void registerMessageHandler(final MessageHandler mh, final int low, final int high) {
        final HandlerEntry he = new HandlerEntry();
        he.mh = mh;
        he.low = low;
        he.high = high;
        synchronized (this.messageHandlers) {
            this.messageHandlers.addElement(he);
        }
        // monitorexit(this.messageHandlers)
    }
    
    public void removeMessageHandler(final MessageHandler mh, final int low, final int high) {
        synchronized (this.messageHandlers) {
            for (int i = 0; i < this.messageHandlers.size(); ++i) {
                final HandlerEntry he = this.messageHandlers.elementAt(i);
                if (he.mh == mh && he.low == low && he.high == high) {
                    this.messageHandlers.removeElementAt(i);
                    break;
                }
            }
        }
        // monitorexit(this.messageHandlers)
    }
    
    public void sendKexMessage(final byte[] msg) throws IOException {
        synchronized (this.connectionSemaphore) {
            if (this.connectionClosed) {
                throw (IOException)new IOException("Sorry, this connection is closed.").initCause(this.reasonClosedCause);
            }
            this.flagKexOngoing = true;
            try {
                this.tc.sendMessage(msg);
            }
            catch (IOException e) {
                this.close(e, false);
                throw e;
            }
        }
        // monitorexit(this.connectionSemaphore)
    }
    
    public void kexFinished() throws IOException {
        synchronized (this.connectionSemaphore) {
            this.flagKexOngoing = false;
            this.connectionSemaphore.notifyAll();
        }
        // monitorexit(this.connectionSemaphore)
    }
    
    public void forceKeyExchange(final CryptoWishList cwl, final DHGexParameters dhgex) throws IOException {
        this.km.initiateKEX(cwl, dhgex);
    }
    
    public void changeRecvCipher(final BlockCipher bc, final MAC mac) {
        this.tc.changeRecvCipher(bc, mac);
    }
    
    public void changeSendCipher(final BlockCipher bc, final MAC mac) {
        this.tc.changeSendCipher(bc, mac);
    }
    
    public void sendAsynchronousMessage(final byte[] msg) throws IOException {
        synchronized (this.asynchronousQueue) {
            this.asynchronousQueue.addElement(msg);
            if (this.asynchronousQueue.size() > 100) {
                throw new IOException("Error: the peer is not consuming our asynchronous replies.");
            }
            if (this.asynchronousThread == null) {
                (this.asynchronousThread = new AsynchronousWorker()).setDaemon(true);
                this.asynchronousThread.start();
            }
        }
        // monitorexit(this.asynchronousQueue)
    }
    
    public void setConnectionMonitors(final Vector monitors) {
        synchronized (this) {
            this.connectionMonitors = (Vector)monitors.clone();
        }
    }
    
    public void sendMessage(final byte[] msg) throws IOException {
        if (Thread.currentThread() == this.receiveThread) {
            throw new IOException("Assertion error: sendMessage may never be invoked by the receiver thread!");
        }
        synchronized (this.connectionSemaphore) {
            while (!this.connectionClosed) {
                if (!this.flagKexOngoing) {
                    try {
                        this.tc.sendMessage(msg);
                    }
                    catch (IOException e) {
                        this.close(e, false);
                        throw e;
                    }
                    // monitorexit(this.connectionSemaphore)
                    return;
                }
                try {
                    this.connectionSemaphore.wait();
                }
                catch (InterruptedException ex) {}
            }
            throw (IOException)new IOException("Sorry, this connection is closed.").initCause(this.reasonClosedCause);
        }
    }
    
    public void receiveLoop() throws IOException {
        final byte[] msg = new byte[35000];
        while (true) {
            final int msglen = this.tc.receiveMessage(msg, 0, msg.length);
            final int type = msg[0] & 0xFF;
            if (type == 2) {
                continue;
            }
            if (type == 4) {
                if (!TransportManager.log.isEnabled()) {
                    continue;
                }
                final TypesReader tr = new TypesReader(msg, 0, msglen);
                tr.readByte();
                tr.readBoolean();
                final StringBuffer debugMessageBuffer = new StringBuffer();
                debugMessageBuffer.append(tr.readString("UTF-8"));
                for (int i = 0; i < debugMessageBuffer.length(); ++i) {
                    final char c = debugMessageBuffer.charAt(i);
                    if (c < ' ' || c > '~') {
                        debugMessageBuffer.setCharAt(i, '\ufffd');
                    }
                }
                TransportManager.log.log(50, "DEBUG Message from remote: '" + debugMessageBuffer.toString() + "'");
            }
            else {
                if (type == 3) {
                    throw new IOException("Peer sent UNIMPLEMENTED message, that should not happen.");
                }
                if (type == 1) {
                    final TypesReader tr = new TypesReader(msg, 0, msglen);
                    tr.readByte();
                    final int reason_code = tr.readUINT32();
                    final StringBuffer reasonBuffer = new StringBuffer();
                    reasonBuffer.append(tr.readString("UTF-8"));
                    if (reasonBuffer.length() > 255) {
                        reasonBuffer.setLength(255);
                        reasonBuffer.setCharAt(254, '.');
                        reasonBuffer.setCharAt(253, '.');
                        reasonBuffer.setCharAt(252, '.');
                    }
                    for (int j = 0; j < reasonBuffer.length(); ++j) {
                        final char c2 = reasonBuffer.charAt(j);
                        if (c2 < ' ' || c2 > '~') {
                            reasonBuffer.setCharAt(j, '\ufffd');
                        }
                    }
                    throw new IOException("Peer sent DISCONNECT message (reason code " + reason_code + "): " + reasonBuffer.toString());
                }
                if (type == 20 || type == 21 || (type >= 30 && type <= 49)) {
                    this.km.handleMessage(msg, msglen);
                }
                else {
                    MessageHandler mh = null;
                    for (int k = 0; k < this.messageHandlers.size(); ++k) {
                        final HandlerEntry he = this.messageHandlers.elementAt(k);
                        if (he.low <= type && type <= he.high) {
                            mh = he.mh;
                            break;
                        }
                    }
                    if (mh == null) {
                        throw new IOException("Unexpected SSH message (type " + type + ")");
                    }
                    mh.handleMessage(msg, msglen);
                }
            }
        }
    }
    
    static /* synthetic */ void access$1(final TransportManager transportManager, final Thread asynchronousThread) {
        transportManager.asynchronousThread = asynchronousThread;
    }
    
    class HandlerEntry
    {
        MessageHandler mh;
        int low;
        int high;
    }
    
    class AsynchronousWorker extends Thread
    {
        public void run() {
            while (true) {
                byte[] msg = null;
                synchronized (TransportManager.this.asynchronousQueue) {
                    if (TransportManager.this.asynchronousQueue.size() == 0) {
                        try {
                            TransportManager.this.asynchronousQueue.wait(2000L);
                        }
                        catch (InterruptedException ex) {}
                        if (TransportManager.this.asynchronousQueue.size() == 0) {
                            TransportManager.access$1(TransportManager.this, null);
                            // monitorexit(TransportManager.access$0(this.this$0))
                            return;
                        }
                    }
                    msg = TransportManager.this.asynchronousQueue.remove(0);
                }
                // monitorexit(TransportManager.access$0(this.this$0))
                try {
                    TransportManager.this.sendMessage(msg);
                }
                catch (IOException e) {}
            }
        }
    }
}
