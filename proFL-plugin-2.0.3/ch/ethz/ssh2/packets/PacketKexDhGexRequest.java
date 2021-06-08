// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import ch.ethz.ssh2.DHGexParameters;

public class PacketKexDhGexRequest
{
    byte[] payload;
    int min;
    int n;
    int max;
    
    public PacketKexDhGexRequest(final DHGexParameters para) {
        this.min = para.getMin_group_len();
        this.n = para.getPref_group_len();
        this.max = para.getMax_group_len();
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(34);
            tw.writeUINT32(this.min);
            tw.writeUINT32(this.n);
            tw.writeUINT32(this.max);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
