// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketOpenDirectTCPIPChannel
{
    byte[] payload;
    int channelID;
    int initialWindowSize;
    int maxPacketSize;
    String host_to_connect;
    int port_to_connect;
    String originator_IP_address;
    int originator_port;
    
    public PacketOpenDirectTCPIPChannel(final int channelID, final int initialWindowSize, final int maxPacketSize, final String host_to_connect, final int port_to_connect, final String originator_IP_address, final int originator_port) {
        this.channelID = channelID;
        this.initialWindowSize = initialWindowSize;
        this.maxPacketSize = maxPacketSize;
        this.host_to_connect = host_to_connect;
        this.port_to_connect = port_to_connect;
        this.originator_IP_address = originator_IP_address;
        this.originator_port = originator_port;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(90);
            tw.writeString("direct-tcpip");
            tw.writeUINT32(this.channelID);
            tw.writeUINT32(this.initialWindowSize);
            tw.writeUINT32(this.maxPacketSize);
            tw.writeString(this.host_to_connect);
            tw.writeUINT32(this.port_to_connect);
            tw.writeString(this.originator_IP_address);
            tw.writeUINT32(this.originator_port);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
