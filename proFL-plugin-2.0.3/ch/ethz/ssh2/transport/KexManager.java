// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.transport;

import ch.ethz.ssh2.packets.PacketKexDHReply;
import ch.ethz.ssh2.packets.PacketKexDhGexReply;
import ch.ethz.ssh2.packets.PacketKexDhGexInit;
import ch.ethz.ssh2.crypto.dh.DhGroupExchange;
import ch.ethz.ssh2.packets.PacketKexDhGexGroup;
import ch.ethz.ssh2.packets.PacketKexDHInit;
import ch.ethz.ssh2.crypto.dh.DhExchange;
import ch.ethz.ssh2.packets.PacketKexDhGexRequest;
import ch.ethz.ssh2.packets.PacketKexDhGexRequestOld;
import ch.ethz.ssh2.signature.DSAPublicKey;
import ch.ethz.ssh2.signature.DSASignature;
import ch.ethz.ssh2.signature.RSAPublicKey;
import ch.ethz.ssh2.signature.RSASignature;
import ch.ethz.ssh2.signature.DSASHA1Verify;
import ch.ethz.ssh2.signature.RSASHA1Verify;
import ch.ethz.ssh2.crypto.cipher.BlockCipher;
import ch.ethz.ssh2.packets.PacketNewKeys;
import ch.ethz.ssh2.crypto.cipher.BlockCipherFactory;
import ch.ethz.ssh2.crypto.digest.MAC;
import ch.ethz.ssh2.packets.PacketKexInit;
import java.io.IOException;
import java.security.SecureRandom;
import ch.ethz.ssh2.ServerHostKeyVerifier;
import ch.ethz.ssh2.DHGexParameters;
import ch.ethz.ssh2.crypto.CryptoWishList;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.crypto.KeyMaterial;
import ch.ethz.ssh2.log.Logger;

public class KexManager
{
    private static final Logger log;
    KexState kxs;
    int kexCount;
    KeyMaterial km;
    byte[] sessionId;
    ClientServerHello csh;
    final Object accessLock;
    ConnectionInfo lastConnInfo;
    boolean connectionClosed;
    boolean ignore_next_kex_packet;
    final TransportManager tm;
    CryptoWishList nextKEXcryptoWishList;
    DHGexParameters nextKEXdhgexParameters;
    ServerHostKeyVerifier verifier;
    final String hostname;
    final int port;
    final SecureRandom rnd;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = KexManager.class$0) == null) {
            try {
                class$0 = (KexManager.class$0 = Class.forName("ch.ethz.ssh2.transport.KexManager"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public KexManager(final TransportManager tm, final ClientServerHello csh, final CryptoWishList initialCwl, final String hostname, final int port, final ServerHostKeyVerifier keyVerifier, final SecureRandom rnd) {
        this.kexCount = 0;
        this.accessLock = new Object();
        this.lastConnInfo = null;
        this.connectionClosed = false;
        this.ignore_next_kex_packet = false;
        this.tm = tm;
        this.csh = csh;
        this.nextKEXcryptoWishList = initialCwl;
        this.nextKEXdhgexParameters = new DHGexParameters();
        this.hostname = hostname;
        this.port = port;
        this.verifier = keyVerifier;
        this.rnd = rnd;
    }
    
    public ConnectionInfo getOrWaitForConnectionInfo(final int minKexCount) throws IOException {
        synchronized (this.accessLock) {
            while (this.lastConnInfo == null || this.lastConnInfo.keyExchangeCounter < minKexCount) {
                if (this.connectionClosed) {
                    throw (IOException)new IOException("Key exchange was not finished, connection is closed.").initCause(this.tm.getReasonClosedCause());
                }
                try {
                    this.accessLock.wait();
                }
                catch (InterruptedException ex) {}
            }
            // monitorexit(this.accessLock)
            return this.lastConnInfo;
        }
    }
    
    private String getFirstMatch(final String[] client, final String[] server) throws NegotiateException {
        if (client == null || server == null) {
            throw new IllegalArgumentException();
        }
        if (client.length == 0) {
            return null;
        }
        for (int i = 0; i < client.length; ++i) {
            for (int j = 0; j < server.length; ++j) {
                if (client[i].equals(server[j])) {
                    return client[i];
                }
            }
        }
        throw new NegotiateException();
    }
    
    private boolean compareFirstOfNameList(final String[] a, final String[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        return (a.length == 0 && b.length == 0) || (a.length != 0 && b.length != 0 && a[0].equals(b[0]));
    }
    
    private boolean isGuessOK(final KexParameters cpar, final KexParameters spar) {
        if (cpar == null || spar == null) {
            throw new IllegalArgumentException();
        }
        return this.compareFirstOfNameList(cpar.kex_algorithms, spar.kex_algorithms) && this.compareFirstOfNameList(cpar.server_host_key_algorithms, spar.server_host_key_algorithms);
    }
    
    private NegotiatedParameters mergeKexParameters(final KexParameters client, final KexParameters server) {
        final NegotiatedParameters np = new NegotiatedParameters();
        try {
            np.kex_algo = this.getFirstMatch(client.kex_algorithms, server.kex_algorithms);
            KexManager.log.log(20, "kex_algo=" + np.kex_algo);
            np.server_host_key_algo = this.getFirstMatch(client.server_host_key_algorithms, server.server_host_key_algorithms);
            KexManager.log.log(20, "server_host_key_algo=" + np.server_host_key_algo);
            np.enc_algo_client_to_server = this.getFirstMatch(client.encryption_algorithms_client_to_server, server.encryption_algorithms_client_to_server);
            np.enc_algo_server_to_client = this.getFirstMatch(client.encryption_algorithms_server_to_client, server.encryption_algorithms_server_to_client);
            KexManager.log.log(20, "enc_algo_client_to_server=" + np.enc_algo_client_to_server);
            KexManager.log.log(20, "enc_algo_server_to_client=" + np.enc_algo_server_to_client);
            np.mac_algo_client_to_server = this.getFirstMatch(client.mac_algorithms_client_to_server, server.mac_algorithms_client_to_server);
            np.mac_algo_server_to_client = this.getFirstMatch(client.mac_algorithms_server_to_client, server.mac_algorithms_server_to_client);
            KexManager.log.log(20, "mac_algo_client_to_server=" + np.mac_algo_client_to_server);
            KexManager.log.log(20, "mac_algo_server_to_client=" + np.mac_algo_server_to_client);
            np.comp_algo_client_to_server = this.getFirstMatch(client.compression_algorithms_client_to_server, server.compression_algorithms_client_to_server);
            np.comp_algo_server_to_client = this.getFirstMatch(client.compression_algorithms_server_to_client, server.compression_algorithms_server_to_client);
            KexManager.log.log(20, "comp_algo_client_to_server=" + np.comp_algo_client_to_server);
            KexManager.log.log(20, "comp_algo_server_to_client=" + np.comp_algo_server_to_client);
        }
        catch (NegotiateException e) {
            return null;
        }
        try {
            np.lang_client_to_server = this.getFirstMatch(client.languages_client_to_server, server.languages_client_to_server);
        }
        catch (NegotiateException e2) {
            np.lang_client_to_server = null;
        }
        try {
            np.lang_server_to_client = this.getFirstMatch(client.languages_server_to_client, server.languages_server_to_client);
        }
        catch (NegotiateException e3) {
            np.lang_server_to_client = null;
        }
        if (this.isGuessOK(client, server)) {
            np.guessOK = true;
        }
        return np;
    }
    
    public synchronized void initiateKEX(final CryptoWishList cwl, final DHGexParameters dhgex) throws IOException {
        this.nextKEXcryptoWishList = cwl;
        this.nextKEXdhgexParameters = dhgex;
        if (this.kxs == null) {
            this.kxs = new KexState();
            this.kxs.dhgexParameters = this.nextKEXdhgexParameters;
            final PacketKexInit kp = new PacketKexInit(this.nextKEXcryptoWishList, this.rnd);
            this.kxs.localKEX = kp;
            this.tm.sendKexMessage(kp.getPayload());
        }
    }
    
    private boolean establishKeyMaterial() {
        try {
            final int mac_cs_key_len = MAC.getKeyLen(this.kxs.np.mac_algo_client_to_server);
            final int enc_cs_key_len = BlockCipherFactory.getKeySize(this.kxs.np.enc_algo_client_to_server);
            final int enc_cs_block_len = BlockCipherFactory.getBlockSize(this.kxs.np.enc_algo_client_to_server);
            final int mac_sc_key_len = MAC.getKeyLen(this.kxs.np.mac_algo_server_to_client);
            final int enc_sc_key_len = BlockCipherFactory.getKeySize(this.kxs.np.enc_algo_server_to_client);
            final int enc_sc_block_len = BlockCipherFactory.getBlockSize(this.kxs.np.enc_algo_server_to_client);
            this.km = KeyMaterial.create("SHA1", this.kxs.H, this.kxs.K, this.sessionId, enc_cs_key_len, enc_cs_block_len, mac_cs_key_len, enc_sc_key_len, enc_sc_block_len, mac_sc_key_len);
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    private void finishKex() throws IOException {
        if (this.sessionId == null) {
            this.sessionId = this.kxs.H;
        }
        this.establishKeyMaterial();
        final PacketNewKeys ign = new PacketNewKeys();
        this.tm.sendKexMessage(ign.getPayload());
        BlockCipher cbc;
        MAC mac;
        try {
            cbc = BlockCipherFactory.createCipher(this.kxs.np.enc_algo_client_to_server, true, this.km.enc_key_client_to_server, this.km.initial_iv_client_to_server);
            mac = new MAC(this.kxs.np.mac_algo_client_to_server, this.km.integrity_key_client_to_server);
        }
        catch (IllegalArgumentException e1) {
            throw new IOException("Fatal error during MAC startup!");
        }
        this.tm.changeSendCipher(cbc, mac);
        this.tm.kexFinished();
    }
    
    public static final String[] getDefaultServerHostkeyAlgorithmList() {
        return new String[] { "ssh-rsa", "ssh-dss" };
    }
    
    public static final void checkServerHostkeyAlgorithmsList(final String[] algos) {
        for (int i = 0; i < algos.length; ++i) {
            if (!"ssh-rsa".equals(algos[i]) && !"ssh-dss".equals(algos[i])) {
                throw new IllegalArgumentException("Unknown server host key algorithm '" + algos[i] + "'");
            }
        }
    }
    
    public static final String[] getDefaultKexAlgorithmList() {
        return new String[] { "diffie-hellman-group-exchange-sha1", "diffie-hellman-group14-sha1", "diffie-hellman-group1-sha1" };
    }
    
    public static final void checkKexAlgorithmList(final String[] algos) {
        for (int i = 0; i < algos.length; ++i) {
            if (!"diffie-hellman-group-exchange-sha1".equals(algos[i])) {
                if (!"diffie-hellman-group14-sha1".equals(algos[i])) {
                    if (!"diffie-hellman-group1-sha1".equals(algos[i])) {
                        throw new IllegalArgumentException("Unknown kex algorithm '" + algos[i] + "'");
                    }
                }
            }
        }
    }
    
    private boolean verifySignature(final byte[] sig, final byte[] hostkey) throws IOException {
        if (this.kxs.np.server_host_key_algo.equals("ssh-rsa")) {
            final RSASignature rs = RSASHA1Verify.decodeSSHRSASignature(sig);
            final RSAPublicKey rpk = RSASHA1Verify.decodeSSHRSAPublicKey(hostkey);
            KexManager.log.log(50, "Verifying ssh-rsa signature");
            return RSASHA1Verify.verifySignature(this.kxs.H, rs, rpk);
        }
        if (this.kxs.np.server_host_key_algo.equals("ssh-dss")) {
            final DSASignature ds = DSASHA1Verify.decodeSSHDSASignature(sig);
            final DSAPublicKey dpk = DSASHA1Verify.decodeSSHDSAPublicKey(hostkey);
            KexManager.log.log(50, "Verifying ssh-dss signature");
            return DSASHA1Verify.verifySignature(this.kxs.H, ds, dpk);
        }
        throw new IOException("Unknown server host key algorithm '" + this.kxs.np.server_host_key_algo + "'");
    }
    
    public synchronized void handleMessage(final byte[] msg, final int msglen) throws IOException {
        if (msg == null) {
            synchronized (this.accessLock) {
                this.connectionClosed = true;
                this.accessLock.notifyAll();
                // monitorexit(this.accessLock)
                return;
            }
        }
        if (this.kxs == null && msg[0] != 20) {
            throw new IOException("Unexpected KEX message (type " + msg[0] + ")");
        }
        if (this.ignore_next_kex_packet) {
            this.ignore_next_kex_packet = false;
            return;
        }
        if (msg[0] == 20) {
            if (this.kxs != null && this.kxs.state != 0) {
                throw new IOException("Unexpected SSH_MSG_KEXINIT message during on-going kex exchange!");
            }
            if (this.kxs == null) {
                this.kxs = new KexState();
                this.kxs.dhgexParameters = this.nextKEXdhgexParameters;
                final PacketKexInit kip = new PacketKexInit(this.nextKEXcryptoWishList, this.rnd);
                this.kxs.localKEX = kip;
                this.tm.sendKexMessage(kip.getPayload());
            }
            final PacketKexInit kip = new PacketKexInit(msg, 0, msglen);
            this.kxs.remoteKEX = kip;
            this.kxs.np = this.mergeKexParameters(this.kxs.localKEX.getKexParameters(), this.kxs.remoteKEX.getKexParameters());
            if (this.kxs.np == null) {
                throw new IOException("Cannot negotiate, proposals do not match.");
            }
            if (this.kxs.remoteKEX.isFirst_kex_packet_follows() && !this.kxs.np.guessOK) {
                this.ignore_next_kex_packet = true;
            }
            if (this.kxs.np.kex_algo.equals("diffie-hellman-group-exchange-sha1")) {
                if (this.kxs.dhgexParameters.getMin_group_len() == 0) {
                    final PacketKexDhGexRequestOld dhgexreq = new PacketKexDhGexRequestOld(this.kxs.dhgexParameters);
                    this.tm.sendKexMessage(dhgexreq.getPayload());
                }
                else {
                    final PacketKexDhGexRequest dhgexreq2 = new PacketKexDhGexRequest(this.kxs.dhgexParameters);
                    this.tm.sendKexMessage(dhgexreq2.getPayload());
                }
                this.kxs.state = 1;
                return;
            }
            if (this.kxs.np.kex_algo.equals("diffie-hellman-group1-sha1") || this.kxs.np.kex_algo.equals("diffie-hellman-group14-sha1")) {
                this.kxs.dhx = new DhExchange();
                if (this.kxs.np.kex_algo.equals("diffie-hellman-group1-sha1")) {
                    this.kxs.dhx.init(1, this.rnd);
                }
                else {
                    this.kxs.dhx.init(14, this.rnd);
                }
                final PacketKexDHInit kp = new PacketKexDHInit(this.kxs.dhx.getE());
                this.tm.sendKexMessage(kp.getPayload());
                this.kxs.state = 1;
                return;
            }
            throw new IllegalStateException("Unkown KEX method!");
        }
        else if (msg[0] == 21) {
            if (this.km == null) {
                throw new IOException("Peer sent SSH_MSG_NEWKEYS, but I have no key material ready!");
            }
            BlockCipher cbc;
            MAC mac;
            try {
                cbc = BlockCipherFactory.createCipher(this.kxs.np.enc_algo_server_to_client, false, this.km.enc_key_server_to_client, this.km.initial_iv_server_to_client);
                mac = new MAC(this.kxs.np.mac_algo_server_to_client, this.km.integrity_key_server_to_client);
            }
            catch (IllegalArgumentException e3) {
                throw new IOException("Fatal error during MAC startup!");
            }
            this.tm.changeRecvCipher(cbc, mac);
            final ConnectionInfo sci = new ConnectionInfo();
            ++this.kexCount;
            sci.keyExchangeAlgorithm = this.kxs.np.kex_algo;
            sci.keyExchangeCounter = this.kexCount;
            sci.clientToServerCryptoAlgorithm = this.kxs.np.enc_algo_client_to_server;
            sci.serverToClientCryptoAlgorithm = this.kxs.np.enc_algo_server_to_client;
            sci.clientToServerMACAlgorithm = this.kxs.np.mac_algo_client_to_server;
            sci.serverToClientMACAlgorithm = this.kxs.np.mac_algo_server_to_client;
            sci.serverHostKeyAlgorithm = this.kxs.np.server_host_key_algo;
            sci.serverHostKey = this.kxs.hostkey;
            synchronized (this.accessLock) {
                this.lastConnInfo = sci;
                this.accessLock.notifyAll();
            }
            // monitorexit(this.accessLock)
            this.kxs = null;
        }
        else {
            if (this.kxs == null || this.kxs.state == 0) {
                throw new IOException("Unexpected Kex submessage!");
            }
            if (this.kxs.np.kex_algo.equals("diffie-hellman-group-exchange-sha1")) {
                if (this.kxs.state == 1) {
                    final PacketKexDhGexGroup dhgexgrp = new PacketKexDhGexGroup(msg, 0, msglen);
                    (this.kxs.dhgx = new DhGroupExchange(dhgexgrp.getP(), dhgexgrp.getG())).init(this.rnd);
                    final PacketKexDhGexInit dhgexinit = new PacketKexDhGexInit(this.kxs.dhgx.getE());
                    this.tm.sendKexMessage(dhgexinit.getPayload());
                    this.kxs.state = 2;
                    return;
                }
                if (this.kxs.state != 2) {
                    throw new IllegalStateException("Illegal State in KEX Exchange!");
                }
                final PacketKexDhGexReply dhgexrpl = new PacketKexDhGexReply(msg, 0, msglen);
                this.kxs.hostkey = dhgexrpl.getHostKey();
                if (this.verifier != null) {
                    boolean vres = false;
                    try {
                        vres = this.verifier.verifyServerHostKey(this.hostname, this.port, this.kxs.np.server_host_key_algo, this.kxs.hostkey);
                    }
                    catch (Exception e) {
                        throw (IOException)new IOException("The server hostkey was not accepted by the verifier callback.").initCause(e);
                    }
                    if (!vres) {
                        throw new IOException("The server hostkey was not accepted by the verifier callback");
                    }
                }
                this.kxs.dhgx.setF(dhgexrpl.getF());
                try {
                    this.kxs.H = this.kxs.dhgx.calculateH(this.csh.getClientString(), this.csh.getServerString(), this.kxs.localKEX.getPayload(), this.kxs.remoteKEX.getPayload(), dhgexrpl.getHostKey(), this.kxs.dhgexParameters);
                }
                catch (IllegalArgumentException e2) {
                    throw (IOException)new IOException("KEX error.").initCause(e2);
                }
                final boolean res = this.verifySignature(dhgexrpl.getSignature(), this.kxs.hostkey);
                if (!res) {
                    throw new IOException("Hostkey signature sent by remote is wrong!");
                }
                this.kxs.K = this.kxs.dhgx.getK();
                this.finishKex();
                this.kxs.state = -1;
            }
            else {
                if ((!this.kxs.np.kex_algo.equals("diffie-hellman-group1-sha1") && !this.kxs.np.kex_algo.equals("diffie-hellman-group14-sha1")) || this.kxs.state != 1) {
                    throw new IllegalStateException("Unkown KEX method! (" + this.kxs.np.kex_algo + ")");
                }
                final PacketKexDHReply dhr = new PacketKexDHReply(msg, 0, msglen);
                this.kxs.hostkey = dhr.getHostKey();
                if (this.verifier != null) {
                    boolean vres = false;
                    try {
                        vres = this.verifier.verifyServerHostKey(this.hostname, this.port, this.kxs.np.server_host_key_algo, this.kxs.hostkey);
                    }
                    catch (Exception e) {
                        throw (IOException)new IOException("The server hostkey was not accepted by the verifier callback.").initCause(e);
                    }
                    if (!vres) {
                        throw new IOException("The server hostkey was not accepted by the verifier callback");
                    }
                }
                this.kxs.dhx.setF(dhr.getF());
                try {
                    this.kxs.H = this.kxs.dhx.calculateH(this.csh.getClientString(), this.csh.getServerString(), this.kxs.localKEX.getPayload(), this.kxs.remoteKEX.getPayload(), dhr.getHostKey());
                }
                catch (IllegalArgumentException e2) {
                    throw (IOException)new IOException("KEX error.").initCause(e2);
                }
                final boolean res = this.verifySignature(dhr.getSignature(), this.kxs.hostkey);
                if (!res) {
                    throw new IOException("Hostkey signature sent by remote is wrong!");
                }
                this.kxs.K = this.kxs.dhx.getK();
                this.finishKex();
                this.kxs.state = -1;
            }
        }
    }
}
