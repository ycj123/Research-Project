// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

public class PacketUserauthRequestInteractive
{
    byte[] payload;
    String userName;
    String serviceName;
    String[] submethods;
    
    public PacketUserauthRequestInteractive(final String serviceName, final String user, final String[] submethods) {
        this.serviceName = serviceName;
        this.userName = user;
        this.submethods = submethods;
    }
    
    public byte[] getPayload() {
        if (this.payload == null) {
            final TypesWriter tw = new TypesWriter();
            tw.writeByte(50);
            tw.writeString(this.userName);
            tw.writeString(this.serviceName);
            tw.writeString("keyboard-interactive");
            tw.writeString("");
            tw.writeNameList(this.submethods);
            this.payload = tw.getBytes();
        }
        return this.payload;
    }
}
