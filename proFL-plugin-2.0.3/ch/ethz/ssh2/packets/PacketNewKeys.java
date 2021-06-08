// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketNewKeys
{
    byte[] payload;
    
    public PacketNewKeys() {
    }
    
    public PacketNewKeys(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 21) {
            throw new IOException("This is not a SSH_MSG_NEWKEYS! (" + packet_type + ")");
        }
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_NEWKEYS packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(21);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
