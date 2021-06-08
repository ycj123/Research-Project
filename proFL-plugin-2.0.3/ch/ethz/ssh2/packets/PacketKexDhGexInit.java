// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import java.math.BigInteger;

public class PacketKexDhGexInit
{
    byte[] payload;
    BigInteger e;
    
    public PacketKexDhGexInit(final BigInteger e) {
        this.e = e;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(32);
            tw.writeMPInt(this.e);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
