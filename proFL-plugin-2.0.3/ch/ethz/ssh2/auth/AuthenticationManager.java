// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.auth;

import ch.ethz.ssh2.packets.PacketUserauthInfoResponse;
import ch.ethz.ssh2.packets.PacketUserauthInfoRequest;
import ch.ethz.ssh2.packets.PacketUserauthRequestInteractive;
import ch.ethz.ssh2.InteractiveCallback;
import ch.ethz.ssh2.packets.PacketUserauthRequestPassword;
import ch.ethz.ssh2.signature.RSASignature;
import ch.ethz.ssh2.signature.DSASignature;
import ch.ethz.ssh2.signature.RSASHA1Verify;
import ch.ethz.ssh2.signature.RSAPrivateKey;
import ch.ethz.ssh2.packets.PacketUserauthRequestPublicKey;
import ch.ethz.ssh2.packets.TypesWriter;
import ch.ethz.ssh2.signature.DSASHA1Verify;
import ch.ethz.ssh2.signature.DSAPrivateKey;
import ch.ethz.ssh2.crypto.PEMDecoder;
import java.security.SecureRandom;
import ch.ethz.ssh2.packets.PacketUserauthFailure;
import ch.ethz.ssh2.packets.PacketServiceAccept;
import ch.ethz.ssh2.packets.PacketUserauthRequestNone;
import ch.ethz.ssh2.packets.PacketServiceRequest;
import ch.ethz.ssh2.packets.PacketUserauthBanner;
import java.io.IOException;
import java.util.Vector;
import ch.ethz.ssh2.transport.TransportManager;
import ch.ethz.ssh2.transport.MessageHandler;

public class AuthenticationManager implements MessageHandler
{
    TransportManager tm;
    Vector packets;
    boolean connectionClosed;
    String banner;
    String[] remainingMethods;
    boolean isPartialSuccess;
    boolean authenticated;
    boolean initDone;
    
    public AuthenticationManager(final TransportManager tm) {
        this.packets = new Vector();
        this.connectionClosed = false;
        this.remainingMethods = null;
        this.isPartialSuccess = false;
        this.authenticated = false;
        this.initDone = false;
        this.tm = tm;
    }
    
    boolean methodPossible(final String methName) {
        if (this.remainingMethods == null) {
            return false;
        }
        for (int i = 0; i < this.remainingMethods.length; ++i) {
            if (this.remainingMethods[i].compareTo(methName) == 0) {
                return true;
            }
        }
        return false;
    }
    
    byte[] deQueue() throws IOException {
        synchronized (this.packets) {
            while (this.packets.size() == 0) {
                if (this.connectionClosed) {
                    throw (IOException)new IOException("The connection is closed.").initCause(this.tm.getReasonClosedCause());
                }
                try {
                    this.packets.wait();
                }
                catch (InterruptedException ex) {}
            }
            final byte[] res = this.packets.firstElement();
            this.packets.removeElementAt(0);
            // monitorexit(this.packets)
            return res;
        }
    }
    
    byte[] getNextMessage() throws IOException {
        byte[] msg;
        while (true) {
            msg = this.deQueue();
            if (msg[0] != 53) {
                break;
            }
            final PacketUserauthBanner sb = new PacketUserauthBanner(msg, 0, msg.length);
            this.banner = sb.getBanner();
        }
        return msg;
    }
    
    public String[] getRemainingMethods(final String user) throws IOException {
        this.initialize(user);
        return this.remainingMethods;
    }
    
    public boolean getPartialSuccess() {
        return this.isPartialSuccess;
    }
    
    private boolean initialize(final String user) throws IOException {
        if (this.initDone) {
            return this.authenticated;
        }
        this.tm.registerMessageHandler(this, 0, 255);
        final PacketServiceRequest sr = new PacketServiceRequest("ssh-userauth");
        this.tm.sendMessage(sr.getPayload());
        final PacketUserauthRequestNone urn = new PacketUserauthRequestNone("ssh-connection", user);
        this.tm.sendMessage(urn.getPayload());
        byte[] msg = this.getNextMessage();
        new PacketServiceAccept(msg, 0, msg.length);
        msg = this.getNextMessage();
        this.initDone = true;
        if (msg[0] == 52) {
            return this.authenticated = true;
        }
        if (msg[0] == 51) {
            final PacketUserauthFailure puf = new PacketUserauthFailure(msg, 0, msg.length);
            this.remainingMethods = puf.getAuthThatCanContinue();
            this.isPartialSuccess = puf.isPartialSuccess();
            return false;
        }
        throw new IOException("Unexpected SSH message (type " + msg[0] + ")");
    }
    
    public boolean authenticatePublicKey(final String user, final char[] PEMPrivateKey, final String password, final SecureRandom rnd) throws IOException {
        try {
            this.initialize(user);
            if (!this.methodPossible("publickey")) {
                throw new IOException("Authentication method publickey not supported by the server at this stage.");
            }
            final Object key = PEMDecoder.decode(PEMPrivateKey, password);
            if (key instanceof DSAPrivateKey) {
                final DSAPrivateKey pk = (DSAPrivateKey)key;
                final byte[] pk_enc = DSASHA1Verify.encodeSSHDSAPublicKey(pk.getPublicKey());
                final TypesWriter tw = new TypesWriter();
                final byte[] H = this.tm.getSessionIdentifier();
                tw.writeString(H, 0, H.length);
                tw.writeByte(50);
                tw.writeString(user);
                tw.writeString("ssh-connection");
                tw.writeString("publickey");
                tw.writeBoolean(true);
                tw.writeString("ssh-dss");
                tw.writeString(pk_enc, 0, pk_enc.length);
                final byte[] msg = tw.getBytes();
                final DSASignature ds = DSASHA1Verify.generateSignature(msg, pk, rnd);
                final byte[] ds_enc = DSASHA1Verify.encodeSSHDSASignature(ds);
                final PacketUserauthRequestPublicKey ua = new PacketUserauthRequestPublicKey("ssh-connection", user, "ssh-dss", pk_enc, ds_enc);
                this.tm.sendMessage(ua.getPayload());
            }
            else {
                if (!(key instanceof RSAPrivateKey)) {
                    throw new IOException("Unknown private key type returned by the PEM decoder.");
                }
                final RSAPrivateKey pk2 = (RSAPrivateKey)key;
                final byte[] pk_enc = RSASHA1Verify.encodeSSHRSAPublicKey(pk2.getPublicKey());
                final TypesWriter tw = new TypesWriter();
                final byte[] H = this.tm.getSessionIdentifier();
                tw.writeString(H, 0, H.length);
                tw.writeByte(50);
                tw.writeString(user);
                tw.writeString("ssh-connection");
                tw.writeString("publickey");
                tw.writeBoolean(true);
                tw.writeString("ssh-rsa");
                tw.writeString(pk_enc, 0, pk_enc.length);
                final byte[] msg2 = tw.getBytes();
                final RSASignature ds2 = RSASHA1Verify.generateSignature(msg2, pk2);
                final byte[] rsa_sig_enc = RSASHA1Verify.encodeSSHRSASignature(ds2);
                final PacketUserauthRequestPublicKey ua2 = new PacketUserauthRequestPublicKey("ssh-connection", user, "ssh-rsa", pk_enc, rsa_sig_enc);
                this.tm.sendMessage(ua2.getPayload());
            }
            final byte[] ar = this.getNextMessage();
            if (ar[0] == 52) {
                this.authenticated = true;
                this.tm.removeMessageHandler(this, 0, 255);
                return true;
            }
            if (ar[0] == 51) {
                final PacketUserauthFailure puf = new PacketUserauthFailure(ar, 0, ar.length);
                this.remainingMethods = puf.getAuthThatCanContinue();
                this.isPartialSuccess = puf.isPartialSuccess();
                return false;
            }
            throw new IOException("Unexpected SSH message (type " + ar[0] + ")");
        }
        catch (IOException e) {
            this.tm.close(e, false);
            throw (IOException)new IOException("Publickey authentication failed.").initCause(e);
        }
    }
    
    public boolean authenticatePassword(final String user, final String pass) throws IOException {
        try {
            this.initialize(user);
            if (!this.methodPossible("password")) {
                throw new IOException("Authentication method password not supported by the server at this stage.");
            }
            final PacketUserauthRequestPassword ua = new PacketUserauthRequestPassword("ssh-connection", user, pass);
            this.tm.sendMessage(ua.getPayload());
            final byte[] ar = this.getNextMessage();
            if (ar[0] == 52) {
                this.authenticated = true;
                this.tm.removeMessageHandler(this, 0, 255);
                return true;
            }
            if (ar[0] == 51) {
                final PacketUserauthFailure puf = new PacketUserauthFailure(ar, 0, ar.length);
                this.remainingMethods = puf.getAuthThatCanContinue();
                this.isPartialSuccess = puf.isPartialSuccess();
                return false;
            }
            throw new IOException("Unexpected SSH message (type " + ar[0] + ")");
        }
        catch (IOException e) {
            this.tm.close(e, false);
            throw (IOException)new IOException("Password authentication failed.").initCause(e);
        }
    }
    
    public boolean authenticateInteractive(final String user, String[] submethods, final InteractiveCallback cb) throws IOException {
        try {
            this.initialize(user);
            if (!this.methodPossible("keyboard-interactive")) {
                throw new IOException("Authentication method keyboard-interactive not supported by the server at this stage.");
            }
            if (submethods == null) {
                submethods = new String[0];
            }
            final PacketUserauthRequestInteractive ua = new PacketUserauthRequestInteractive("ssh-connection", user, submethods);
            this.tm.sendMessage(ua.getPayload());
            while (true) {
                final byte[] ar = this.getNextMessage();
                if (ar[0] == 52) {
                    this.authenticated = true;
                    this.tm.removeMessageHandler(this, 0, 255);
                    return true;
                }
                if (ar[0] == 51) {
                    final PacketUserauthFailure puf = new PacketUserauthFailure(ar, 0, ar.length);
                    this.remainingMethods = puf.getAuthThatCanContinue();
                    this.isPartialSuccess = puf.isPartialSuccess();
                    return false;
                }
                if (ar[0] != 60) {
                    throw new IOException("Unexpected SSH message (type " + ar[0] + ")");
                }
                final PacketUserauthInfoRequest pui = new PacketUserauthInfoRequest(ar, 0, ar.length);
                String[] responses;
                try {
                    responses = cb.replyToChallenge(pui.getName(), pui.getInstruction(), pui.getNumPrompts(), pui.getPrompt(), pui.getEcho());
                }
                catch (Exception e) {
                    throw (IOException)new IOException("Exception in callback.").initCause(e);
                }
                if (responses == null) {
                    throw new IOException("Your callback may not return NULL!");
                }
                final PacketUserauthInfoResponse puir = new PacketUserauthInfoResponse(responses);
                this.tm.sendMessage(puir.getPayload());
            }
        }
        catch (IOException e2) {
            this.tm.close(e2, false);
            throw (IOException)new IOException("Keyboard-interactive authentication failed.").initCause(e2);
        }
    }
    
    public void handleMessage(final byte[] msg, final int msglen) throws IOException {
        synchronized (this.packets) {
            if (msg == null) {
                this.connectionClosed = true;
            }
            else {
                final byte[] tmp = new byte[msglen];
                System.arraycopy(msg, 0, tmp, 0, msglen);
                this.packets.addElement(tmp);
            }
            this.packets.notifyAll();
            if (this.packets.size() > 5) {
                this.connectionClosed = true;
                throw new IOException("Error, peer is flooding us with authentication packets.");
            }
        }
        // monitorexit(this.packets)
    }
}
