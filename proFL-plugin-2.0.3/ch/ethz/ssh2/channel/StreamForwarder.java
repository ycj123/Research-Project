// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamForwarder extends Thread
{
    OutputStream os;
    InputStream is;
    byte[] buffer;
    Channel c;
    StreamForwarder sibling;
    Socket s;
    String mode;
    
    StreamForwarder(final Channel c, final StreamForwarder sibling, final Socket s, final InputStream is, final OutputStream os, final String mode) throws IOException {
        this.buffer = new byte[30000];
        this.is = is;
        this.os = os;
        this.mode = mode;
        this.c = c;
        this.sibling = sibling;
        this.s = s;
    }
    
    public void run() {
        try {
            while (true) {
                final int len = this.is.read(this.buffer);
                if (len <= 0) {
                    break;
                }
                this.os.write(this.buffer, 0, len);
                this.os.flush();
            }
        }
        catch (IOException ignore) {
            try {
                this.c.cm.closeChannel(this.c, "Closed due to exception in StreamForwarder (" + this.mode + "): " + ignore.getMessage(), true);
            }
            catch (IOException ex) {}
        }
        finally {
            try {
                this.os.close();
            }
            catch (IOException ex2) {}
            try {
                this.is.close();
            }
            catch (IOException ex3) {}
            if (this.sibling != null) {
                while (this.sibling.isAlive()) {
                    try {
                        this.sibling.join();
                    }
                    catch (InterruptedException ex4) {}
                }
                try {
                    this.c.cm.closeChannel(this.c, "StreamForwarder (" + this.mode + ") is cleaning up the connection", true);
                }
                catch (IOException ex5) {}
                try {
                    if (this.s != null) {
                        this.s.close();
                    }
                }
                catch (IOException ex6) {}
            }
        }
        try {
            this.os.close();
        }
        catch (IOException ex7) {}
        try {
            this.is.close();
        }
        catch (IOException ex8) {}
        if (this.sibling != null) {
            while (this.sibling.isAlive()) {
                try {
                    this.sibling.join();
                }
                catch (InterruptedException ex9) {}
            }
            try {
                this.c.cm.closeChannel(this.c, "StreamForwarder (" + this.mode + ") is cleaning up the connection", true);
            }
            catch (IOException ex10) {}
            try {
                if (this.s != null) {
                    this.s.close();
                }
            }
            catch (IOException ex11) {}
        }
    }
}
