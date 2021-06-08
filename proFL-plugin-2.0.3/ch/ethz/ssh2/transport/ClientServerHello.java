// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.transport;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientServerHello
{
    String server_line;
    String client_line;
    String server_versioncomment;
    
    public static final int readLineRN(final InputStream is, final byte[] buffer) throws IOException {
        int pos = 0;
        boolean need10 = false;
        int len = 0;
        while (true) {
            final int c = is.read();
            if (c == -1) {
                throw new IOException("Premature connection close");
            }
            buffer[pos++] = (byte)c;
            if (c == 13) {
                need10 = true;
            }
            else {
                if (c == 10) {
                    return len;
                }
                if (need10) {
                    throw new IOException("Malformed line sent by the server, the line does not end correctly.");
                }
                ++len;
                if (pos >= buffer.length) {
                    throw new IOException("The server sent a too long line.");
                }
                continue;
            }
        }
    }
    
    public ClientServerHello(final InputStream bi, final OutputStream bo) throws IOException {
        this.client_line = "SSH-2.0-Ganymed Build_210";
        bo.write((String.valueOf(this.client_line) + "\r\n").getBytes());
        bo.flush();
        final byte[] serverVersion = new byte[512];
        for (int i = 0; i < 50; ++i) {
            final int len = readLineRN(bi, serverVersion);
            this.server_line = new String(serverVersion, 0, len);
            if (this.server_line.startsWith("SSH-")) {
                break;
            }
        }
        if (!this.server_line.startsWith("SSH-")) {
            throw new IOException("Malformed server identification string. There was no line starting with 'SSH-' amongst the first 50 lines.");
        }
        if (this.server_line.startsWith("SSH-1.99-")) {
            this.server_versioncomment = this.server_line.substring(9);
        }
        else {
            if (!this.server_line.startsWith("SSH-2.0-")) {
                throw new IOException("Server uses incompatible protocol, it is not SSH-2 compatible.");
            }
            this.server_versioncomment = this.server_line.substring(8);
        }
    }
    
    public byte[] getClientString() {
        return this.client_line.getBytes();
    }
    
    public byte[] getServerString() {
        return this.server_line.getBytes();
    }
}
