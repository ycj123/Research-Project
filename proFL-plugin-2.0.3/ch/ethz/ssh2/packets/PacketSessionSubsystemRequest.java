// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketSessionSubsystemRequest
{
    byte[] payload;
    public int recipientChannelID;
    public boolean wantReply;
    public String subsystem;
    
    public PacketSessionSubsystemRequest(final int recipientChannelID, final boolean wantReply, final String subsystem) {
        this.recipientChannelID = recipientChannelID;
        this.wantReply = wantReply;
        this.subsystem = subsystem;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(98);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeString("subsystem");
            tw.writeBoolean(this.wantReply);
            tw.writeString(this.subsystem);
            tw.getBytes(this.payload = tw.getBytes());
        }
        return this.payload;
    }
}
