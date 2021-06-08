// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketSessionExecCommand
{
    byte[] payload;
    public int recipientChannelID;
    public boolean wantReply;
    public String command;
    
    public PacketSessionExecCommand(final int recipientChannelID, final boolean wantReply, final String command) {
        this.recipientChannelID = recipientChannelID;
        this.wantReply = wantReply;
        this.command = command;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(98);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeString("exec");
            tw.writeBoolean(this.wantReply);
            tw.writeString(this.command);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
