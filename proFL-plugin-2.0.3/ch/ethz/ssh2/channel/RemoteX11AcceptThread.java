// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import ch.ethz.ssh2.log.Logger;

public class RemoteX11AcceptThread extends Thread
{
    private static final Logger log;
    Channel c;
    String remoteOriginatorAddress;
    int remoteOriginatorPort;
    Socket s;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = RemoteX11AcceptThread.class$0) == null) {
            try {
                class$0 = (RemoteX11AcceptThread.class$0 = Class.forName("ch.ethz.ssh2.channel.RemoteX11AcceptThread"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public RemoteX11AcceptThread(final Channel c, final String remoteOriginatorAddress, final int remoteOriginatorPort) {
        this.c = c;
        this.remoteOriginatorAddress = remoteOriginatorAddress;
        this.remoteOriginatorPort = remoteOriginatorPort;
    }
    
    public void run() {
        try {
            this.c.cm.sendOpenConfirmation(this.c);
            final OutputStream remote_os = this.c.getStdinStream();
            final InputStream remote_is = this.c.getStdoutStream();
            final byte[] header = new byte[6];
            if (remote_is.read(header) != 6) {
                throw new IOException("Unexpected EOF on X11 startup!");
            }
            if (header[0] != 66 && header[0] != 108) {
                throw new IOException("Unknown endian format in X11 message!");
            }
            final int idxMSB = (header[0] != 66) ? 1 : 0;
            final byte[] auth_buff = new byte[6];
            if (remote_is.read(auth_buff) != 6) {
                throw new IOException("Unexpected EOF on X11 startup!");
            }
            final int authProtocolNameLength = (auth_buff[idxMSB] & 0xFF) << 8 | (auth_buff[1 - idxMSB] & 0xFF);
            final int authProtocolDataLength = (auth_buff[2 + idxMSB] & 0xFF) << 8 | (auth_buff[3 - idxMSB] & 0xFF);
            if (authProtocolNameLength > 256 || authProtocolDataLength > 256) {
                throw new IOException("Buggy X11 authorization data");
            }
            final int authProtocolNamePadding = (4 - authProtocolNameLength % 4) % 4;
            final int authProtocolDataPadding = (4 - authProtocolDataLength % 4) % 4;
            final byte[] authProtocolName = new byte[authProtocolNameLength];
            final byte[] authProtocolData = new byte[authProtocolDataLength];
            final byte[] paddingBuffer = new byte[4];
            if (remote_is.read(authProtocolName) != authProtocolNameLength) {
                throw new IOException("Unexpected EOF on X11 startup! (authProtocolName)");
            }
            if (remote_is.read(paddingBuffer, 0, authProtocolNamePadding) != authProtocolNamePadding) {
                throw new IOException("Unexpected EOF on X11 startup! (authProtocolNamePadding)");
            }
            if (remote_is.read(authProtocolData) != authProtocolDataLength) {
                throw new IOException("Unexpected EOF on X11 startup! (authProtocolData)");
            }
            if (remote_is.read(paddingBuffer, 0, authProtocolDataPadding) != authProtocolDataPadding) {
                throw new IOException("Unexpected EOF on X11 startup! (authProtocolDataPadding)");
            }
            if (!"MIT-MAGIC-COOKIE-1".equals(new String(authProtocolName))) {
                throw new IOException("Unknown X11 authorization protocol!");
            }
            if (authProtocolDataLength != 16) {
                throw new IOException("Wrong data length for X11 authorization data!");
            }
            final StringBuffer tmp = new StringBuffer(32);
            for (int i = 0; i < authProtocolData.length; ++i) {
                final String digit2 = Integer.toHexString(authProtocolData[i] & 0xFF);
                tmp.append((digit2.length() == 2) ? digit2 : ("0" + digit2));
            }
            final String hexEncodedFakeCookie = tmp.toString();
            synchronized (this.c) {
                this.c.hexX11FakeCookie = hexEncodedFakeCookie;
            }
            // monitorexit(this.c)
            final X11ServerData sd = this.c.cm.checkX11Cookie(hexEncodedFakeCookie);
            if (sd == null) {
                throw new IOException("Invalid X11 cookie received.");
            }
            this.s = new Socket(sd.hostname, sd.port);
            final OutputStream x11_os = this.s.getOutputStream();
            final InputStream x11_is = this.s.getInputStream();
            x11_os.write(header);
            if (sd.x11_magic_cookie == null) {
                final byte[] emptyAuthData = new byte[6];
                x11_os.write(emptyAuthData);
            }
            else {
                if (sd.x11_magic_cookie.length != 16) {
                    throw new IOException("The real X11 cookie has an invalid length!");
                }
                x11_os.write(auth_buff);
                x11_os.write(authProtocolName);
                x11_os.write(paddingBuffer, 0, authProtocolNamePadding);
                x11_os.write(sd.x11_magic_cookie);
                x11_os.write(paddingBuffer, 0, authProtocolDataPadding);
            }
            x11_os.flush();
            final StreamForwarder r2l = new StreamForwarder(this.c, null, null, remote_is, x11_os, "RemoteToX11");
            final StreamForwarder l2r = new StreamForwarder(this.c, null, null, x11_is, remote_os, "X11ToRemote");
            r2l.setDaemon(true);
            r2l.start();
            l2r.run();
            while (r2l.isAlive()) {
                try {
                    r2l.join();
                }
                catch (InterruptedException ex) {}
            }
            this.c.cm.closeChannel(this.c, "EOF on both X11 streams reached.", true);
            this.s.close();
        }
        catch (IOException e) {
            RemoteX11AcceptThread.log.log(50, "IOException in X11 proxy code: " + e.getMessage());
            try {
                this.c.cm.closeChannel(this.c, "IOException in X11 proxy code (" + e.getMessage() + ")", true);
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
