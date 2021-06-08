// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketChannelOpenFailure
{
    byte[] payload;
    public int recipientChannelID;
    public int reasonCode;
    public String description;
    public String languageTag;
    
    public PacketChannelOpenFailure(final int recipientChannelID, final int reasonCode, final String description, final String languageTag) {
        this.recipientChannelID = recipientChannelID;
        this.reasonCode = reasonCode;
        this.description = description;
        this.languageTag = languageTag;
    }
    
    public PacketChannelOpenFailure(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 92) {
            throw new IOException("This is not a SSH_MSG_CHANNEL_OPEN_FAILURE! (" + packet_type + ")");
        }
        this.recipientChannelID = tr.readUINT32();
        this.reasonCode = tr.readUINT32();
        this.description = tr.readString();
        this.languageTag = tr.readString();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_CHANNEL_OPEN_FAILURE packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(92);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeUINT32(this.reasonCode);
            tw.writeString(this.description);
            tw.writeString(this.languageTag);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
