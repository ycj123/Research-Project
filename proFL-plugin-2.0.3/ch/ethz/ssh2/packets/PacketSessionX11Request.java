// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketSessionX11Request
{
    byte[] payload;
    public int recipientChannelID;
    public boolean wantReply;
    public boolean singleConnection;
    String x11AuthenticationProtocol;
    String x11AuthenticationCookie;
    int x11ScreenNumber;
    
    public PacketSessionX11Request(final int recipientChannelID, final boolean wantReply, final boolean singleConnection, final String x11AuthenticationProtocol, final String x11AuthenticationCookie, final int x11ScreenNumber) {
        this.recipientChannelID = recipientChannelID;
        this.wantReply = wantReply;
        this.singleConnection = singleConnection;
        this.x11AuthenticationProtocol = x11AuthenticationProtocol;
        this.x11AuthenticationCookie = x11AuthenticationCookie;
        this.x11ScreenNumber = x11ScreenNumber;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(98);
            tw.writeUINT32(this.recipientChannelID);
            tw.writeString("x11-req");
            tw.writeBoolean(this.wantReply);
            tw.writeBoolean(this.singleConnection);
            tw.writeString(this.x11AuthenticationProtocol);
            tw.writeString(this.x11AuthenticationCookie);
            tw.writeUINT32(this.x11ScreenNumber);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
