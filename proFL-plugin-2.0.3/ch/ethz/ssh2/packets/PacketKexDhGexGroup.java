// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;
import java.math.BigInteger;

public class PacketKexDhGexGroup
{
    byte[] payload;
    BigInteger p;
    BigInteger g;
    
    public PacketKexDhGexGroup(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 31) {
            throw new IllegalArgumentException("This is not a SSH_MSG_KEX_DH_GEX_GROUP! (" + packet_type + ")");
        }
        this.p = tr.readMPINT();
        this.g = tr.readMPINT();
        if (tr.remain() != 0) {
            throw new IOException("PADDING IN SSH_MSG_KEX_DH_GEX_GROUP!");
        }
    }
    
    public BigInteger getG() {
        return this.g;
    }
    
    public BigInteger getP() {
        return this.p;
    }
}
