// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketSessionPtyRequest
{
    byte[] payload;
    public int recipientChannelID;
    public boolean wantReply;
    public String term;
    public int character_width;
    public int character_height;
    public int pixel_width;
    public int pixel_height;
    public byte[] terminal_modes;
    
    public PacketSessionPtyRequest(final int recipientChannelID, final boolean wantReply, final String term, final int character_width, final int character_height, final int pixel_width, final int pixel_height, final byte[] terminal_modes) {
        this.recipientChannelID = recipientChannelID;
        this.wantReply = wantReply;
        this.term = term;
        this.character_width = character_width;
        this.character_height = character_height;
        this.pixel_width = pixel_width;
        this.pixel_height = pixel_height;
        this.terminal_modes = terminal_modes;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(98);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeString("pty-req");
            tw.writeBoolean(this.wantReply);
            tw.writeString(this.term);
            tw.writeUINT32(this.character_width);
            tw.writeUINT32(this.character_height);
            tw.writeUINT32(this.pixel_width);
            tw.writeUINT32(this.pixel_height);
            tw.writeString(this.terminal_modes, 0, this.terminal_modes.length);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
