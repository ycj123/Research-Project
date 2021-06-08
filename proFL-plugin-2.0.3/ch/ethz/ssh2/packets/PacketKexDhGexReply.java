// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;
import java.math.BigInteger;

public class PacketKexDhGexReply
{
    byte[] payload;
    byte[] hostKey;
    BigInteger f;
    byte[] signature;
    
    public PacketKexDhGexReply(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 33) {
            throw new IOException("This is not a SSH_MSG_KEX_DH_GEX_REPLY! (" + packet_type + ")");
        }
        this.hostKey = tr.readByteString();
        this.f = tr.readMPINT();
        this.signature = tr.readByteString();
        if (tr.remain() != 0) {
            throw new IOException("PADDING IN SSH_MSG_KEX_DH_GEX_REPLY!");
        }
    }
    
    public BigInteger getF() {
        return this.f;
    }
    
    public byte[] getHostKey() {
        return this.hostKey;
    }
    
    public byte[] getSignature() {
        return this.signature;
    }
}
