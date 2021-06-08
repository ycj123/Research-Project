// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import ch.ethz.ssh2.log.Logger;

public class RemoteAcceptThread extends Thread
{
    private static final Logger log;
    Channel c;
    String remoteConnectedAddress;
    int remoteConnectedPort;
    String remoteOriginatorAddress;
    int remoteOriginatorPort;
    String targetAddress;
    int targetPort;
    Socket s;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = RemoteAcceptThread.class$0) == null) {
            try {
                class$0 = (RemoteAcceptThread.class$0 = Class.forName("ch.ethz.ssh2.channel.RemoteAcceptThread"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public RemoteAcceptThread(final Channel c, final String remoteConnectedAddress, final int remoteConnectedPort, final String remoteOriginatorAddress, final int remoteOriginatorPort, final String targetAddress, final int targetPort) {
        this.c = c;
        this.remoteConnectedAddress = remoteConnectedAddress;
        this.remoteConnectedPort = remoteConnectedPort;
        this.remoteOriginatorAddress = remoteOriginatorAddress;
        this.remoteOriginatorPort = remoteOriginatorPort;
        this.targetAddress = targetAddress;
        this.targetPort = targetPort;
        if (RemoteAcceptThread.log.isEnabled()) {
            RemoteAcceptThread.log.log(20, "RemoteAcceptThread: " + remoteConnectedAddress + "/" + remoteConnectedPort + ", R: " + remoteOriginatorAddress + "/" + remoteOriginatorPort);
        }
    }
    
    public void run() {
        try {
            this.c.cm.sendOpenConfirmation(this.c);
            this.s = new Socket(this.targetAddress, this.targetPort);
            final StreamForwarder r2l = new StreamForwarder(this.c, null, null, this.c.getStdoutStream(), this.s.getOutputStream(), "RemoteToLocal");
            final StreamForwarder l2r = new StreamForwarder(this.c, null, null, this.s.getInputStream(), this.c.getStdinStream(), "LocalToRemote");
            r2l.setDaemon(true);
            r2l.start();
            l2r.run();
            while (r2l.isAlive()) {
                try {
                    r2l.join();
                }
                catch (InterruptedException ex) {}
            }
            this.c.cm.closeChannel(this.c, "EOF on both streams reached.", true);
            this.s.close();
        }
        catch (IOException e) {
            RemoteAcceptThread.log.log(50, "IOException in proxy code: " + e.getMessage());
            try {
                this.c.cm.closeChannel(this.c, "IOException in proxy code (" + e.getMessage() + ")", true);
            }
            catch (IOException ex2) {}
            try {
                if (this.s != null) {
                    this.s.close();
                }
            }
            catch (IOException ex3) {}
        }
    }
}
