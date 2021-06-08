// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketUserauthRequestNone
{
    byte[] payload;
    String userName;
    String serviceName;
    
    public PacketUserauthRequestNone(final String serviceName, final String user) {
        this.serviceName = serviceName;
        this.userName = user;
    }
    
    public PacketUserauthRequestNone(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 50) {
            throw new IOException("This is not a SSH_MSG_USERAUTH_REQUEST! (" + packet_type + ")");
        }
        this.userName = tr.readString();
        this.serviceName = tr.readString();
        final String method = tr.readString();
        if (!method.equals("none")) {
            throw new IOException("This is not a SSH_MSG_USERAUTH_REQUEST with type none!");
        }
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_USERAUTH_REQUEST packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(50);
            tw.writeString(this.userName);
            tw.writeString(this.serviceName);
            tw.writeString("none");
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
