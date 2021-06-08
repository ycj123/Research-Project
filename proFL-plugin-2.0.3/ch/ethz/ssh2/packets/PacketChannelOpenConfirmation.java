// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketChannelOpenConfirmation
{
    byte[] payload;
    public int recipientChannelID;
    public int senderChannelID;
    public int initialWindowSize;
    public int maxPacketSize;
    
    public PacketChannelOpenConfirmation(final int recipientChannelID, final int senderChannelID, final int initialWindowSize, final int maxPacketSize) {
        this.recipientChannelID = recipientChannelID;
        this.senderChannelID = senderChannelID;
        this.initialWindowSize = initialWindowSize;
        this.maxPacketSize = maxPacketSize;
    }
    
    public PacketChannelOpenConfirmation(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 91) {
            throw new IOException("This is not a SSH_MSG_CHANNEL_OPEN_CONFIRMATION! (" + packet_type + ")");
        }
        this.recipientChannelID = tr.readUINT32();
        this.senderChannelID = tr.readUINT32();
        this.initialWindowSize = tr.readUINT32();
        this.maxPacketSize = tr.readUINT32();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_CHANNEL_OPEN_CONFIRMATION packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(91);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeUINT32(this.senderChannelID);
            tw.writeUINT32(this.initialWindowSize);
            tw.writeUINT32(this.maxPacketSize);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
