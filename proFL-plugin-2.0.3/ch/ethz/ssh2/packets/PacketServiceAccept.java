// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketServiceAccept
{
    byte[] payload;
    String serviceName;
    
    public PacketServiceAccept(final String serviceName) {
        this.serviceName = serviceName;
    }
    
    public PacketServiceAccept(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 6) {
            throw new IOException("This is not a SSH_MSG_SERVICE_ACCEPT! (" + packet_type + ")");
        }
        this.serviceName = tr.readString();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_SERVICE_ACCEPT packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(6);
            tw.writeString(this.serviceName);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
