// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.io.OutputStream;
import java.io.InputStream;
import ch.ethz.ssh2.channel.X11ServerData;
import java.io.IOException;
import java.security.SecureRandom;
import ch.ethz.ssh2.channel.Channel;
import ch.ethz.ssh2.channel.ChannelManager;

public class Session
{
    ChannelManager cm;
    Channel cn;
    boolean flag_pty_requested;
    boolean flag_x11_requested;
    boolean flag_execution_started;
    boolean flag_closed;
    String x11FakeCookie;
    final SecureRandom rnd;
    
    Session(final ChannelManager cm, final SecureRandom rnd) throws IOException {
        this.flag_pty_requested = false;
        this.flag_x11_requested = false;
        this.flag_execution_started = false;
        this.flag_closed = false;
        this.x11FakeCookie = null;
        this.cm = cm;
        this.cn = cm.openSessionChannel();
        this.rnd = rnd;
    }
    
    public void requestDumbPTY() throws IOException {
        this.requestPTY("dumb", 0, 0, 0, 0, null);
    }
    
    public void requestPTY(final String term) throws IOException {
        this.requestPTY(term, 0, 0, 0, 0, null);
    }
    
    public void requestPTY(final String term, final int term_width_characters, final int term_height_characters, final int term_width_pixels, final int term_height_pixels, byte[] terminal_modes) throws IOException {
        if (term == null) {
            throw new IllegalArgumentException("TERM cannot be null.");
        }
        if (terminal_modes != null && terminal_modes.length > 0) {
            if (terminal_modes[terminal_modes.length - 1] != 0) {
                throw new IOException("Illegal terminal modes description, does not end in zero byte");
            }
        }
        else {
            terminal_modes = new byte[] { 0 };
        }
        synchronized (this) {
            if (this.flag_closed) {
                throw new IOException("This session is closed.");
            }
            if (this.flag_pty_requested) {
                throw new IOException("A PTY was already requested.");
            }
            if (this.flag_execution_started) {
                throw new IOException("Cannot request PTY at this stage anymore, a remote execution has already started.");
            }
            this.flag_pty_requested = true;
        }
        this.cm.requestPTY(this.cn, term, term_width_characters, term_height_characters, term_width_pixels, term_height_pixels, terminal_modes);
    }
    
    public void requestX11Forwarding(final String hostname, final int port, final byte[] cookie, final boolean singleConnection) throws IOException {
        if (hostname == null) {
            throw new IllegalArgumentException("hostname argument may not be null");
        }
        synchronized (this) {
            if (this.flag_closed) {
                throw new IOException("This session is closed.");
            }
            if (this.flag_x11_requested) {
                throw new IOException("X11 forwarding was already requested.");
            }
            if (this.flag_execution_started) {
                throw new IOException("Cannot request X11 forwarding at this stage anymore, a remote execution has already started.");
            }
            this.flag_x11_requested = true;
        }
        final X11ServerData x11data = new X11ServerData();
        x11data.hostname = hostname;
        x11data.port = port;
        x11data.x11_magic_cookie = cookie;
        final byte[] fakeCookie = new byte[16];
        String hexEncodedFakeCookie;
        do {
            this.rnd.nextBytes(fakeCookie);
            final StringBuffer tmp = new StringBuffer(32);
            for (int i = 0; i < fakeCookie.length; ++i) {
                final String digit2 = Integer.toHexString(fakeCookie[i] & 0xFF);
                tmp.append((digit2.length() == 2) ? digit2 : ("0" + digit2));
            }
            hexEncodedFakeCookie = tmp.toString();
        } while (this.cm.checkX11Cookie(hexEncodedFakeCookie) != null);
        this.cm.requestX11(this.cn, singleConnection, "MIT-MAGIC-COOKIE-1", hexEncodedFakeCookie, 0);
        synchronized (this) {
            if (!this.flag_closed) {
                this.x11FakeCookie = hexEncodedFakeCookie;
                this.cm.registerX11Cookie(hexEncodedFakeCookie, x11data);
            }
        }
    }
    
    public void execCommand(final String cmd) throws IOException {
        if (cmd == null) {
            throw new IllegalArgumentException("cmd argument may not be null");
        }
        synchronized (this) {
            if (this.flag_closed) {
                throw new IOException("This session is closed.");
            }
            if (this.flag_execution_started) {
                throw new IOException("A remote execution has already started.");
            }
            this.flag_execution_started = true;
        }
        this.cm.requestExecCommand(this.cn, cmd);
    }
    
    public void startShell() throws IOException {
        synchronized (this) {
            if (this.flag_closed) {
                throw new IOException("This session is closed.");
            }
            if (this.flag_execution_started) {
                throw new IOException("A remote execution has already started.");
            }
            this.flag_execution_started = true;
        }
        this.cm.requestShell(this.cn);
    }
    
    public void startSubSystem(final String name) throws IOException {
        if (name == null) {
            throw new IllegalArgumentException("name argument may not be null");
        }
        synchronized (this) {
            if (this.flag_closed) {
                throw new IOException("This session is closed.");
            }
            if (this.flag_execution_started) {
                throw new IOException("A remote execution has already started.");
            }
            this.flag_execution_started = true;
        }
        this.cm.requestSubSystem(this.cn, name);
    }
    
    public InputStream getStdout() {
        return this.cn.getStdoutStream();
    }
    
    public InputStream getStderr() {
        return this.cn.getStderrStream();
    }
    
    public OutputStream getStdin() {
        return this.cn.getStdinStream();
    }
    
    public int waitUntilDataAvailable(final long timeout) throws IOException {
        if (timeout < 0L) {
            throw new IllegalArgumentException("timeout must not be negative!");
        }
        final int conditions = this.cm.waitForCondition(this.cn, timeout, 28);
        if ((conditions & 0x1) != 0x0) {
            return -1;
        }
        if ((conditions & 0xC) != 0x0) {
            return 1;
        }
        if ((conditions & 0x10) != 0x0) {
            return 0;
        }
        throw new IllegalStateException("Unexpected condition result (" + conditions + ")");
    }
    
    public int waitForCondition(final int condition_set, final long timeout) {
        if (timeout < 0L) {
            throw new IllegalArgumentException("timeout must be non-negative!");
        }
        return this.cm.waitForCondition(this.cn, timeout, condition_set);
    }
    
    public Integer getExitStatus() {
        return this.cn.getExitStatus();
    }
    
    public String getExitSignal() {
        return this.cn.getExitSignal();
    }
    
    public void close() {
        synchronized (this) {
            if (this.flag_closed) {
                // monitorexit(this)
                return;
            }
            this.flag_closed = true;
            if (this.x11FakeCookie != null) {
                this.cm.unRegisterX11Cookie(this.x11FakeCookie, true);
            }
            try {
                this.cm.closeChannel(this.cn, "Closed due to user request", true);
            }
            catch (IOException ex) {}
        }
    }
}
