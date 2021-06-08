// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketGlobalCancelForwardRequest
{
    byte[] payload;
    public boolean wantReply;
    public String bindAddress;
    public int bindPort;
    
    public PacketGlobalCancelForwardRequest(final boolean wantReply, final String bindAddress, final int bindPort) {
        this.wantReply = wantReply;
        this.bindAddress = bindAddress;
        this.bindPort = bindPort;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(80);
            tw.writeString("cancel-tcpip-forward");
            tw.writeBoolean(this.wantReply);
            tw.writeString(this.bindAddress);
            tw.writeUINT32(this.bindPort);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
