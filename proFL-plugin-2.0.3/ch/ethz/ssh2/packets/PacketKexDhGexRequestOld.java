// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import ch.ethz.ssh2.DHGexParameters;

public class PacketKexDhGexRequestOld
{
    byte[] payload;
    int n;
    
    public PacketKexDhGexRequestOld(final DHGexParameters para) {
        this.n = para.getPref_group_len();
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(30);
            tw.writeUINT32(this.n);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
