// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketIgnore
{
    byte[] payload;
    byte[] body;
    
    public void setBody(final byte[] body) {
        this.body = body;
        this.payload = null;
    }
    
    public PacketIgnore(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 2) {
            throw new IOException("This is not a SSH_MSG_IGNORE packet! (" + packet_type + ")");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(2);
            tw.writeString(this.body, 0, this.body.length);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
