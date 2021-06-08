// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import ch.ethz.ssh2.channel.Channel;
import ch.ethz.ssh2.channel.LocalAcceptThread;
import ch.ethz.ssh2.channel.ChannelManager;

public class LocalStreamForwarder
{
    ChannelManager cm;
    String host_to_connect;
    int port_to_connect;
    LocalAcceptThread lat;
    Channel cn;
    
    LocalStreamForwarder(final ChannelManager cm, final String host_to_connect, final int port_to_connect) throws IOException {
        this.cm = cm;
        this.host_to_connect = host_to_connect;
        this.port_to_connect = port_to_connect;
        this.cn = cm.openDirectTCPIPChannel(host_to_connect, port_to_connect, "127.0.0.1", 0);
    }
    
    public InputStream getInputStream() throws IOException {
        return this.cn.getStdoutStream();
    }
    
    public OutputStream getOutputStream() throws IOException {
        return this.cn.getStdinStream();
    }
    
    public void close() throws IOException {
        this.cm.closeChannel(this.cn, "Closed due to user request.", true);
    }
}
