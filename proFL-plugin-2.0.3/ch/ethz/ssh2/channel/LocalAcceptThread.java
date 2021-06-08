// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class LocalAcceptThread extends Thread implements IChannelWorkerThread
{
    ChannelManager cm;
    int local_port;
    String host_to_connect;
    int port_to_connect;
    final ServerSocket ss;
    
    public LocalAcceptThread(final ChannelManager cm, final int local_port, final String host_to_connect, final int port_to_connect) throws IOException {
        this.cm = cm;
        this.local_port = local_port;
        this.host_to_connect = host_to_connect;
        this.port_to_connect = port_to_connect;
        this.ss = new ServerSocket(local_port);
    }
    
    public void run() {
        try {
            this.cm.registerThread(this);
        }
        catch (IOException e2) {
            this.stopWorking();
            return;
        }
        while (true) {
            Socket s = null;
            try {
                s = this.ss.accept();
            }
            catch (IOException e3) {
                this.stopWorking();
                return;
            }
            Channel cn = null;
            StreamForwarder r2l = null;
            StreamForwarder l2r = null;
            try {
                cn = this.cm.openDirectTCPIPChannel(this.host_to_connect, this.port_to_connect, s.getInetAddress().getHostAddress(), s.getPort());
            }
            catch (IOException e) {
                try {
                    s.close();
                }
                catch (IOException ex) {}
            }
            try {
                r2l = new StreamForwarder(cn, null, null, cn.stdoutStream, s.getOutputStream(), "RemoteToLocal");
                l2r = new StreamForwarder(cn, r2l, s, s.getInputStream(), cn.stdinStream, "LocalToRemote");
            }
            catch (IOException e) {
                try {
                    cn.cm.closeChannel(cn, "Weird error during creation of StreamForwarder (" + e.getMessage() + ")", true);
                }
                catch (IOException ex2) {}
            }
            r2l.setDaemon(true);
            l2r.setDaemon(true);
            r2l.start();
            l2r.start();
        }
    }
    
    public void stopWorking() {
        try {
            this.ss.close();
        }
        catch (IOException ex) {}
    }
}
