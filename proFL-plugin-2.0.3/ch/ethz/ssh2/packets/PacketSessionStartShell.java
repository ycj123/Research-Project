// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketSessionStartShell
{
    byte[] payload;
    public int recipientChannelID;
    public boolean wantReply;
    
    public PacketSessionStartShell(final int recipientChannelID, final boolean wantReply) {
        this.recipientChannelID = recipientChannelID;
        this.wantReply = wantReply;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(98);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeString("shell");
            tw.writeBoolean(this.wantReply);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
