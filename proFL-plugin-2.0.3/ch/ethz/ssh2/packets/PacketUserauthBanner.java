// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketUserauthBanner
{
    byte[] payload;
    String message;
    String language;
    
    public PacketUserauthBanner(final String message, final String language) {
        this.message = message;
        this.language = language;
    }
    
    public String getBanner() {
        return this.message;
    }
    
    public PacketUserauthBanner(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 53) {
            throw new IOException("This is not a SSH_MSG_USERAUTH_BANNER! (" + packet_type + ")");
        }
        this.message = tr.readString("UTF-8");
        this.language = tr.readString();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_USERAUTH_REQUEST packet!");
        }
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(53);
            tw.writeString(this.message);
            tw.writeString(this.language);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
