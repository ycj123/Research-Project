// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.transport;

import ch.ethz.ssh2.packets.Packets;
import java.io.IOException;
import ch.ethz.ssh2.crypto.cipher.BlockCipher;
import ch.ethz.ssh2.crypto.cipher.NullCipher;
import java.io.OutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import ch.ethz.ssh2.crypto.digest.MAC;
import ch.ethz.ssh2.crypto.cipher.CipherOutputStream;
import ch.ethz.ssh2.crypto.cipher.CipherInputStream;
import ch.ethz.ssh2.log.Logger;

public class TransportConnection
{
    private static final Logger log;
    int send_seq_number;
    int recv_seq_number;
    CipherInputStream cis;
    CipherOutputStream cos;
    boolean useRandomPadding;
    MAC send_mac;
    byte[] send_mac_buffer;
    int send_padd_blocksize;
    MAC recv_mac;
    byte[] recv_mac_buffer;
    byte[] recv_mac_buffer_cmp;
    int recv_padd_blocksize;
    final byte[] send_padding_buffer;
    final byte[] send_packet_header_buffer;
    final byte[] recv_padding_buffer;
    final byte[] recv_packet_header_buffer;
    boolean recv_packet_header_present;
    ClientServerHello csh;
    final SecureRandom rnd;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = TransportConnection.class$0) == null) {
            try {
                class$0 = (TransportConnection.class$0 = Class.forName("ch.ethz.ssh2.transport.TransportConnection"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public TransportConnection(final InputStream is, final OutputStream os, final SecureRandom rnd) {
        this.send_seq_number = 0;
        this.recv_seq_number = 0;
        this.useRandomPadding = false;
        this.send_padd_blocksize = 8;
        this.recv_padd_blocksize = 8;
        this.send_padding_buffer = new byte[256];
        this.send_packet_header_buffer = new byte[5];
        this.recv_padding_buffer = new byte[256];
        this.recv_packet_header_buffer = new byte[5];
        this.recv_packet_header_present = false;
        this.cis = new CipherInputStream(new NullCipher(), is);
        this.cos = new CipherOutputStream(new NullCipher(), os);
        this.rnd = rnd;
    }
    
    public void changeRecvCipher(final BlockCipher bc, final MAC mac) {
        this.cis.changeCipher(bc);
        this.recv_mac = mac;
        this.recv_mac_buffer = (byte[])((mac != null) ? new byte[mac.size()] : null);
        this.recv_mac_buffer_cmp = (byte[])((mac != null) ? new byte[mac.size()] : null);
        this.recv_padd_blocksize = bc.getBlockSize();
        if (this.recv_padd_blocksize < 8) {
            this.recv_padd_blocksize = 8;
        }
    }
    
    public void changeSendCipher(final BlockCipher bc, final MAC mac) {
        if (!(bc instanceof NullCipher)) {
            this.useRandomPadding = true;
        }
        this.cos.changeCipher(bc);
        this.send_mac = mac;
        this.send_mac_buffer = (byte[])((mac != null) ? new byte[mac.size()] : null);
        this.send_padd_blocksize = bc.getBlockSize();
        if (this.send_padd_blocksize < 8) {
            this.send_padd_blocksize = 8;
        }
    }
    
    public void sendMessage(final byte[] message) throws IOException {
        this.sendMessage(message, 0, message.length, 0);
    }
    
    public void sendMessage(final byte[] message, final int off, final int len) throws IOException {
        this.sendMessage(message, off, len, 0);
    }
    
    public int getPacketOverheadEstimate() {
        return 9 + (this.send_padd_blocksize - 1) + this.send_mac_buffer.length;
    }
    
    public void sendMessage(final byte[] message, final int off, final int len, int padd) throws IOException {
        if (padd < 4) {
            padd = 4;
        }
        else if (padd > 64) {
            padd = 64;
        }
        int packet_len = 5 + len + padd;
        final int slack = packet_len % this.send_padd_blocksize;
        if (slack != 0) {
            packet_len += this.send_padd_blocksize - slack;
        }
        if (packet_len < 16) {
            packet_len = 16;
        }
        final int padd_len = packet_len - (5 + len);
        if (this.useRandomPadding) {
            for (int i = 0; i < padd_len; i += 4) {
                final int r = this.rnd.nextInt();
                this.send_padding_buffer[i] = (byte)r;
                this.send_padding_buffer[i + 1] = (byte)(r >> 8);
                this.send_padding_buffer[i + 2] = (byte)(r >> 16);
                this.send_padding_buffer[i + 3] = (byte)(r >> 24);
            }
        }
        else {
            for (int i = 0; i < padd_len; ++i) {
                this.send_padding_buffer[i] = 0;
            }
        }
        this.send_packet_header_buffer[0] = (byte)(packet_len - 4 >> 24);
        this.send_packet_header_buffer[1] = (byte)(packet_len - 4 >> 16);
        this.send_packet_header_buffer[2] = (byte)(packet_len - 4 >> 8);
        this.send_packet_header_buffer[3] = (byte)(packet_len - 4);
        this.send_packet_header_buffer[4] = (byte)padd_len;
        this.cos.write(this.send_packet_header_buffer, 0, 5);
        this.cos.write(message, off, len);
        this.cos.write(this.send_padding_buffer, 0, padd_len);
        if (this.send_mac != null) {
            this.send_mac.initMac(this.send_seq_number);
            this.send_mac.update(this.send_packet_header_buffer, 0, 5);
            this.send_mac.update(message, off, len);
            this.send_mac.update(this.send_padding_buffer, 0, padd_len);
            this.send_mac.getMac(this.send_mac_buffer, 0);
            this.cos.writePlain(this.send_mac_buffer, 0, this.send_mac_buffer.length);
        }
        this.cos.flush();
        if (TransportConnection.log.isEnabled()) {
            TransportConnection.log.log(90, "Sent " + Packets.getMessageName(message[off] & 0xFF) + " " + len + " bytes payload");
        }
        ++this.send_seq_number;
    }
    
    public int peekNextMessageLength() throws IOException {
        if (!this.recv_packet_header_present) {
            this.cis.read(this.recv_packet_header_buffer, 0, 5);
            this.recv_packet_header_present = true;
        }
        final int packet_length = (this.recv_packet_header_buffer[0] & 0xFF) << 24 | (this.recv_packet_header_buffer[1] & 0xFF) << 16 | (this.recv_packet_header_buffer[2] & 0xFF) << 8 | (this.recv_packet_header_buffer[3] & 0xFF);
        final int padding_length = this.recv_packet_header_buffer[4] & 0xFF;
        if (packet_length > 35000 || packet_length < 12) {
            throw new IOException("Illegal packet size! (" + packet_length + ")");
        }
        final int payload_length = packet_length - padding_length - 1;
        if (payload_length < 0) {
            throw new IOException("Illegal padding_length in packet from remote (" + padding_length + ")");
        }
        return payload_length;
    }
    
    public int receiveMessage(final byte[] buffer, final int off, final int len) throws IOException {
        if (!this.recv_packet_header_present) {
            this.cis.read(this.recv_packet_header_buffer, 0, 5);
        }
        else {
            this.recv_packet_header_present = false;
        }
        final int packet_length = (this.recv_packet_header_buffer[0] & 0xFF) << 24 | (this.recv_packet_header_buffer[1] & 0xFF) << 16 | (this.recv_packet_header_buffer[2] & 0xFF) << 8 | (this.recv_packet_header_buffer[3] & 0xFF);
        final int padding_length = this.recv_packet_header_buffer[4] & 0xFF;
        if (packet_length > 35000 || packet_length < 12) {
            throw new IOException("Illegal packet size! (" + packet_length + ")");
        }
        final int payload_length = packet_length - padding_length - 1;
        if (payload_length < 0) {
            throw new IOException("Illegal padding_length in packet from remote (" + padding_length + ")");
        }
        if (payload_length >= len) {
            throw new IOException("Receive buffer too small (" + len + ", need " + payload_length + ")");
        }
        this.cis.read(buffer, off, payload_length);
        this.cis.read(this.recv_padding_buffer, 0, padding_length);
        if (this.recv_mac != null) {
            this.cis.readPlain(this.recv_mac_buffer, 0, this.recv_mac_buffer.length);
            this.recv_mac.initMac(this.recv_seq_number);
            this.recv_mac.update(this.recv_packet_header_buffer, 0, 5);
            this.recv_mac.update(buffer, off, payload_length);
            this.recv_mac.update(this.recv_padding_buffer, 0, padding_length);
            this.recv_mac.getMac(this.recv_mac_buffer_cmp, 0);
            for (int i = 0; i < this.recv_mac_buffer.length; ++i) {
                if (this.recv_mac_buffer[i] != this.recv_mac_buffer_cmp[i]) {
                    throw new IOException("Remote sent corrupt MAC.");
                }
            }
        }
        ++this.recv_seq_number;
        if (TransportConnection.log.isEnabled()) {
            TransportConnection.log.log(90, "Received " + Packets.getMessageName(buffer[off] & 0xFF) + " " + payload_length + " bytes payload");
        }
        return payload_length;
    }
}
