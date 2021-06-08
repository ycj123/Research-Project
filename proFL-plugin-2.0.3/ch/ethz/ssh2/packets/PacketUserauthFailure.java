// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.io.IOException;

public class PacketUserauthFailure
{
    byte[] payload;
    String[] authThatCanContinue;
    boolean partialSuccess;
    
    public PacketUserauthFailure(final String[] authThatCanContinue, final boolean partialSuccess) {
        this.authThatCanContinue = authThatCanContinue;
        this.partialSuccess = partialSuccess;
    }
    
    public PacketUserauthFailure(final byte[] payload, final int off, final int len) throws IOException {
        System.arraycopy(payload, off, this.payload = new byte[len], 0, len);
        final TypesReader tr = new TypesReader(payload, off, len);
        final int packet_type = tr.readByte();
        if (packet_type != 51) {
            throw new IOException("This is not a SSH_MSG_USERAUTH_FAILURE! (" + packet_type + ")");
        }
        this.authThatCanContinue = tr.readNameList();
        this.partialSuccess = tr.readBoolean();
        if (tr.remain() != 0) {
            throw new IOException("Padding in SSH_MSG_USERAUTH_FAILURE packet!");
        }
    }
    
    public String[] getAuthThatCanContinue() {
        return this.authThatCanContinue;
    }
    
    public boolean isPartialSuccess() {
        return this.partialSuccess;
    }
}
