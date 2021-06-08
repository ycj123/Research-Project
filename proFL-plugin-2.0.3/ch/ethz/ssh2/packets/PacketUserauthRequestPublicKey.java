// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketUserauthRequestPublicKey
{
    byte[] payload;
    String userName;
    String serviceName;
    String password;
    String pkAlgoName;
    byte[] pk;
    byte[] sig;
    
    public PacketUserauthRequestPublicKey(final String serviceName, final String user, final String pkAlgorithmName, final byte[] pk, final byte[] sig) {
        this.serviceName = serviceName;
        this.userName = user;
        this.pkAlgoName = pkAlgorithmName;
        this.pk = pk;
        this.sig = sig;
    }
    
    public PacketUserauthRequestPublicKey(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 50) {
            throw new IOException("This is not a SSH_MSG_USERAUTH_REQUEST! (" + packet_type + ")");
        }
        throw new IOException("Not implemented!");
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(50);
            tw.writeString(this.userName);
            tw.writeString(this.serviceName);
            tw.writeString("publickey");
            tw.writeBoolean(true);
            tw.writeString(this.pkAlgoName);
            tw.writeString(this.pk, 0, this.pk.length);
            tw.writeString(this.sig, 0, this.sig.length);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
