// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketDisconnect
{
    byte[] payload;
    int reason;
    String desc;
    String lang;
    
    public PacketDisconnect(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 1) {
            throw new IOException("This is not a Disconnect Packet! (" + packet_type + ")");
        }
        this.reason = tr.readUINT32();
        this.desc = tr.readString();
        this.lang = tr.readString();
    }
    
    public PacketDisconnect(final int reason, final String desc, final String lang) {
        this.reason = reason;
        this.desc = desc;
        this.lang = lang;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(1);
            tw.writeUINT32(this.reason);
            tw.writeString(this.desc);
            tw.writeString(this.lang);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
