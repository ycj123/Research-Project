// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketServiceRequest
{
    byte[] payload;
    String serviceName;
    
    public PacketServiceRequest(final String serviceName) {
        this.serviceName = serviceName;
    }
    
    public PacketServiceRequest(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 5) {
            throw new IOException("This is not a SSH_MSG_SERVICE_REQUEST! (" + packet_type + ")");
        }
        this.serviceName = tr.readString();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_SERVICE_REQUEST packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(5);
            tw.writeString(this.serviceName);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
