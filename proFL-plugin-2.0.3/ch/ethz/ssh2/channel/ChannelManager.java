// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

import ch.ethz.ssh2.packets.PacketChannelOpenFailure;
import ch.ethz.ssh2.packets.TypesReader;
import ch.ethz.ssh2.packets.PacketSessionStartShell;
import ch.ethz.ssh2.packets.PacketSessionExecCommand;
import ch.ethz.ssh2.packets.PacketSessionSubsystemRequest;
import ch.ethz.ssh2.packets.PacketSessionX11Request;
import ch.ethz.ssh2.packets.PacketSessionPtyRequest;
import ch.ethz.ssh2.packets.PacketOpenSessionChannel;
import ch.ethz.ssh2.packets.PacketOpenDirectTCPIPChannel;
import ch.ethz.ssh2.packets.PacketGlobalCancelForwardRequest;
import ch.ethz.ssh2.packets.PacketGlobalForwardRequest;
import ch.ethz.ssh2.packets.PacketChannelOpenConfirmation;
import java.io.IOException;
import java.util.Vector;
import ch.ethz.ssh2.transport.TransportManager;
import java.util.HashMap;
import ch.ethz.ssh2.log.Logger;
import ch.ethz.ssh2.transport.MessageHandler;

public class ChannelManager implements MessageHandler
{
    private static final Logger log;
    private HashMap x11_magic_cookies;
    private TransportManager tm;
    private Vector channels;
    private int nextLocalChannel;
    private boolean shutdown;
    private int globalSuccessCounter;
    private int globalFailedCounter;
    private HashMap remoteForwardings;
    private Vector listenerThreads;
    private boolean listenerThreadsAllowed;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = ChannelManager.class$0) == null) {
            try {
                class$0 = (ChannelManager.class$0 = Class.forName("ch.ethz.ssh2.channel.ChannelManager"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public ChannelManager(final TransportManager tm) {
        this.x11_magic_cookies = new HashMap();
        this.channels = new Vector();
        this.nextLocalChannel = 100;
        this.shutdown = false;
        this.globalSuccessCounter = 0;
        this.globalFailedCounter = 0;
        this.remoteForwardings = new HashMap();
        this.listenerThreads = new Vector();
        this.listenerThreadsAllowed = true;
        (this.tm = tm).registerMessageHandler(this, 80, 100);
    }
    
    private Channel getChannel(final int id) {
        synchronized (this.channels) {
            for (int i = 0; i < this.channels.size(); ++i) {
                final Channel c = this.channels.elementAt(i);
                if (c.localID == id) {
                    // monitorexit(this.channels)
                    return c;
                }
            }
        }
        // monitorexit(this.channels)
        return null;
    }
    
    private void removeChannel(final int id) {
        synchronized (this.channels) {
            for (int i = 0; i < this.channels.size(); ++i) {
                final Channel c = this.channels.elementAt(i);
                if (c.localID == id) {
                    this.channels.removeElementAt(i);
                    break;
                }
            }
        }
        // monitorexit(this.channels)
    }
    
    private int addChannel(final Channel c) {
        synchronized (this.channels) {
            this.channels.addElement(c);
            // monitorexit(this.channels)
            return this.nextLocalChannel++;
        }
    }
    
    private void waitUntilChannelOpen(final Channel c) throws IOException {
        synchronized (c) {
            while (c.state == 1) {
                try {
                    c.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (c.state != 2) {
                this.removeChannel(c.localID);
                String detail = c.getReasonClosed();
                if (detail == null) {
                    detail = "state: " + c.state;
                }
                throw new IOException("Could not open channel (" + detail + ")");
            }
        }
    }
    
    private final void waitForGlobalSuccessOrFailure() throws IOException {
        synchronized (this.channels) {
            while (this.globalSuccessCounter == 0 && this.globalFailedCounter == 0) {
                if (this.shutdown) {
                    throw new IOException("The connection is being shutdown");
                }
                try {
                    this.channels.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (this.globalFailedCounter != 0) {
                throw new IOException("The server denied the request (did you enable port forwarding?)");
            }
            if (this.globalSuccessCounter == 0) {
                throw new IOException("Illegal state.");
            }
        }
        // monitorexit(this.channels)
    }
    
    private final void waitForChannelSuccessOrFailure(final Channel c) throws IOException {
        synchronized (c) {
            while (c.successCounter == 0 && c.failedCounter == 0) {
                if (c.state != 2) {
                    String detail = c.getReasonClosed();
                    if (detail == null) {
                        detail = "state: " + c.state;
                    }
                    throw new IOException("This SSH2 channel is not open (" + detail + ")");
                }
                try {
                    c.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (c.failedCounter != 0) {
                throw new IOException("The server denied the request.");
            }
        }
    }
    
    public void registerX11Cookie(final String hexFakeCookie, final X11ServerData data) {
        synchronized (this.x11_magic_cookies) {
            this.x11_magic_cookies.put(hexFakeCookie, data);
        }
        // monitorexit(this.x11_magic_cookies)
    }
    
    public void unRegisterX11Cookie(final String hexFakeCookie, final boolean killChannels) {
        if (hexFakeCookie == null) {
            throw new IllegalStateException("hexFakeCookie may not be null");
        }
        synchronized (this.x11_magic_cookies) {
            this.x11_magic_cookies.remove(hexFakeCookie);
        }
        // monitorexit(this.x11_magic_cookies)
        if (!killChannels) {
            return;
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Closing all X11 channels for the given fake cookie");
        }
        final Vector channel_copy;
        synchronized (this.channels) {
            channel_copy = (Vector)this.channels.clone();
        }
        // monitorexit(this.channels)
        for (int i = 0; i < channel_copy.size(); ++i) {
            final Channel c = channel_copy.elementAt(i);
            synchronized (c) {
                if (!hexFakeCookie.equals(c.hexX11FakeCookie)) {
                    // monitorexit(c)
                    continue;
                }
            }
            // monitorexit(c)
            try {
                this.closeChannel(c, "Closing X11 channel since the corresponding session is closing", true);
            }
            catch (IOException ex) {}
        }
    }
    
    public X11ServerData checkX11Cookie(final String hexFakeCookie) {
        synchronized (this.x11_magic_cookies) {
            if (hexFakeCookie != null) {
                // monitorexit(this.x11_magic_cookies)
                return this.x11_magic_cookies.get(hexFakeCookie);
            }
        }
        // monitorexit(this.x11_magic_cookies)
        return null;
    }
    
    public void closeAllChannels() {
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Closing all channels");
        }
        final Vector channel_copy;
        synchronized (this.channels) {
            channel_copy = (Vector)this.channels.clone();
        }
        // monitorexit(this.channels)
        for (int i = 0; i < channel_copy.size(); ++i) {
            final Channel c = channel_copy.elementAt(i);
            try {
                this.closeChannel(c, "Closing all channels", true);
            }
            catch (IOException ex) {}
        }
    }
    
    public void closeChannel(final Channel c, final String reason, final boolean force) throws IOException {
        final byte[] msg = new byte[5];
        synchronized (c) {
            if (force) {
                c.state = 4;
                c.EOF = true;
            }
            c.setReasonClosed(reason);
            msg[0] = 97;
            msg[1] = (byte)(c.remoteID >> 24);
            msg[2] = (byte)(c.remoteID >> 16);
            msg[3] = (byte)(c.remoteID >> 8);
            msg[4] = (byte)c.remoteID;
            c.notifyAll();
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                // monitorexit(c.channelSendLock)
                return;
            }
            this.tm.sendMessage(msg);
            c.closeMessageSent = true;
        }
        // monitorexit(c.channelSendLock)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Sent SSH_MSG_CHANNEL_CLOSE (channel " + c.localID + ")");
        }
    }
    
    public void sendEOF(final Channel c) throws IOException {
        final byte[] msg = new byte[5];
        synchronized (c) {
            if (c.state != 2) {
                // monitorexit(c)
                return;
            }
            msg[0] = 96;
            msg[1] = (byte)(c.remoteID >> 24);
            msg[2] = (byte)(c.remoteID >> 16);
            msg[3] = (byte)(c.remoteID >> 8);
            msg[4] = (byte)c.remoteID;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                // monitorexit(c.channelSendLock)
                return;
            }
            this.tm.sendMessage(msg);
        }
        // monitorexit(c.channelSendLock)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Sent EOF (Channel " + c.localID + "/" + c.remoteID + ")");
        }
    }
    
    public void sendOpenConfirmation(final Channel c) throws IOException {
        PacketChannelOpenConfirmation pcoc = null;
        synchronized (c) {
            if (c.state != 1) {
                // monitorexit(c)
                return;
            }
            c.state = 2;
            pcoc = new PacketChannelOpenConfirmation(c.remoteID, c.localID, c.localWindow, c.localMaxPacketSize);
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                // monitorexit(c.channelSendLock)
                return;
            }
            this.tm.sendMessage(pcoc.getPayload());
        }
        // monitorexit(c.channelSendLock)
    }
    
    public void sendData(final Channel c, final byte[] buffer, int pos, int len) throws IOException {
        while (len > 0) {
            int thislen = 0;
            byte[] msg = null;
            Label_0309: {
                synchronized (c) {
                    while (c.state != 4) {
                        if (c.state != 2) {
                            throw new IOException("SSH channel in strange state. (" + c.state + ")");
                        }
                        if (c.remoteWindow != 0L) {
                            thislen = ((c.remoteWindow >= len) ? len : ((int)c.remoteWindow));
                            int estimatedMaxDataLen = c.remoteMaxPacketSize - (this.tm.getPacketOverheadEstimate() + 9);
                            if (estimatedMaxDataLen <= 0) {
                                estimatedMaxDataLen = 1;
                            }
                            if (thislen > estimatedMaxDataLen) {
                                thislen = estimatedMaxDataLen;
                            }
                            c.remoteWindow -= thislen;
                            msg = new byte[9 + thislen];
                            msg[0] = 94;
                            msg[1] = (byte)(c.remoteID >> 24);
                            msg[2] = (byte)(c.remoteID >> 16);
                            msg[3] = (byte)(c.remoteID >> 8);
                            msg[4] = (byte)c.remoteID;
                            msg[5] = (byte)(thislen >> 24);
                            msg[6] = (byte)(thislen >> 16);
                            msg[7] = (byte)(thislen >> 8);
                            msg[8] = (byte)thislen;
                            System.arraycopy(buffer, pos, msg, 9, thislen);
                            break Label_0309;
                        }
                        try {
                            c.wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                    throw new IOException("SSH channel is closed. (" + c.getReasonClosed() + ")");
                }
            }
            synchronized (c.channelSendLock) {
                if (c.closeMessageSent) {
                    throw new IOException("SSH channel is closed. (" + c.getReasonClosed() + ")");
                }
                this.tm.sendMessage(msg);
            }
            // monitorexit(c.channelSendLock)
            pos += thislen;
            len -= thislen;
        }
    }
    
    public int requestGlobalForward(final String bindAddress, final int bindPort, final String targetAddress, final int targetPort) throws IOException {
        final RemoteForwardingData rfd = new RemoteForwardingData();
        rfd.bindAddress = bindAddress;
        rfd.bindPort = bindPort;
        rfd.targetAddress = targetAddress;
        rfd.targetPort = targetPort;
        synchronized (this.remoteForwardings) {
            final Integer key = new Integer(bindPort);
            if (this.remoteForwardings.get(key) != null) {
                throw new IOException("There is already a forwarding for remote port " + bindPort);
            }
            this.remoteForwardings.put(key, rfd);
        }
        // monitorexit(this.remoteForwardings)
        synchronized (this.channels) {
            final int n = 0;
            this.globalFailedCounter = n;
            this.globalSuccessCounter = n;
        }
        // monitorexit(this.channels)
        final PacketGlobalForwardRequest pgf = new PacketGlobalForwardRequest(true, bindAddress, bindPort);
        this.tm.sendMessage(pgf.getPayload());
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Requesting a remote forwarding ('" + bindAddress + "', " + bindPort + ")");
        }
        try {
            this.waitForGlobalSuccessOrFailure();
        }
        catch (IOException e) {
            synchronized (this.remoteForwardings) {
                this.remoteForwardings.remove(rfd);
            }
            // monitorexit(this.remoteForwardings)
            throw e;
        }
        return bindPort;
    }
    
    public void requestCancelGlobalForward(final int bindPort) throws IOException {
        RemoteForwardingData rfd = null;
        synchronized (this.remoteForwardings) {
            rfd = this.remoteForwardings.get(new Integer(bindPort));
            if (rfd == null) {
                throw new IOException("Sorry, there is no known remote forwarding for remote port " + bindPort);
            }
        }
        // monitorexit(this.remoteForwardings)
        synchronized (this.channels) {
            final int n = 0;
            this.globalFailedCounter = n;
            this.globalSuccessCounter = n;
        }
        // monitorexit(this.channels)
        final PacketGlobalCancelForwardRequest pgcf = new PacketGlobalCancelForwardRequest(true, rfd.bindAddress, rfd.bindPort);
        this.tm.sendMessage(pgcf.getPayload());
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Requesting cancelation of remote forward ('" + rfd.bindAddress + "', " + rfd.bindPort + ")");
        }
        this.waitForGlobalSuccessOrFailure();
        synchronized (this.remoteForwardings) {
            this.remoteForwardings.remove(rfd);
        }
        // monitorexit(this.remoteForwardings)
    }
    
    public void registerThread(final IChannelWorkerThread thr) throws IOException {
        synchronized (this.listenerThreads) {
            if (!this.listenerThreadsAllowed) {
                throw new IOException("Too late, this connection is closed.");
            }
            this.listenerThreads.addElement(thr);
        }
        // monitorexit(this.listenerThreads)
    }
    
    public Channel openDirectTCPIPChannel(final String host_to_connect, final int port_to_connect, final String originator_IP_address, final int originator_port) throws IOException {
        final Channel c = new Channel(this);
        synchronized (c) {
            c.localID = this.addChannel(c);
        }
        // monitorexit(c)
        final PacketOpenDirectTCPIPChannel dtc = new PacketOpenDirectTCPIPChannel(c.localID, c.localWindow, c.localMaxPacketSize, host_to_connect, port_to_connect, originator_IP_address, originator_port);
        this.tm.sendMessage(dtc.getPayload());
        this.waitUntilChannelOpen(c);
        return c;
    }
    
    public Channel openSessionChannel() throws IOException {
        final Channel c = new Channel(this);
        synchronized (c) {
            c.localID = this.addChannel(c);
        }
        // monitorexit(c)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Sending SSH_MSG_CHANNEL_OPEN (Channel " + c.localID + ")");
        }
        final PacketOpenSessionChannel smo = new PacketOpenSessionChannel(c.localID, c.localWindow, c.localMaxPacketSize);
        this.tm.sendMessage(smo.getPayload());
        this.waitUntilChannelOpen(c);
        return c;
    }
    
    public void requestPTY(final Channel c, final String term, final int term_width_characters, final int term_height_characters, final int term_width_pixels, final int term_height_pixels, final byte[] terminal_modes) throws IOException {
        final PacketSessionPtyRequest spr;
        synchronized (c) {
            if (c.state != 2) {
                throw new IOException("Cannot request PTY on this channel (" + c.getReasonClosed() + ")");
            }
            spr = new PacketSessionPtyRequest(c.remoteID, true, term, term_width_characters, term_height_characters, term_width_pixels, term_height_pixels, terminal_modes);
            final int n = 0;
            c.failedCounter = n;
            c.successCounter = n;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                throw new IOException("Cannot request PTY on this channel (" + c.getReasonClosed() + ")");
            }
            this.tm.sendMessage(spr.getPayload());
        }
        // monitorexit(c.channelSendLock)
        try {
            this.waitForChannelSuccessOrFailure(c);
        }
        catch (IOException e) {
            throw (IOException)new IOException("PTY request failed").initCause(e);
        }
    }
    
    public void requestX11(final Channel c, final boolean singleConnection, final String x11AuthenticationProtocol, final String x11AuthenticationCookie, final int x11ScreenNumber) throws IOException {
        final PacketSessionX11Request psr;
        synchronized (c) {
            if (c.state != 2) {
                throw new IOException("Cannot request X11 on this channel (" + c.getReasonClosed() + ")");
            }
            psr = new PacketSessionX11Request(c.remoteID, true, singleConnection, x11AuthenticationProtocol, x11AuthenticationCookie, x11ScreenNumber);
            final int n = 0;
            c.failedCounter = n;
            c.successCounter = n;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                throw new IOException("Cannot request X11 on this channel (" + c.getReasonClosed() + ")");
            }
            this.tm.sendMessage(psr.getPayload());
        }
        // monitorexit(c.channelSendLock)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Requesting X11 forwarding (Channel " + c.localID + "/" + c.remoteID + ")");
        }
        try {
            this.waitForChannelSuccessOrFailure(c);
        }
        catch (IOException e) {
            throw (IOException)new IOException("The X11 request failed.").initCause(e);
        }
    }
    
    public void requestSubSystem(final Channel c, final String subSystemName) throws IOException {
        final PacketSessionSubsystemRequest ssr;
        synchronized (c) {
            if (c.state != 2) {
                throw new IOException("Cannot request subsystem on this channel (" + c.getReasonClosed() + ")");
            }
            ssr = new PacketSessionSubsystemRequest(c.remoteID, true, subSystemName);
            final int n = 0;
            c.failedCounter = n;
            c.successCounter = n;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                throw new IOException("Cannot request subsystem on this channel (" + c.getReasonClosed() + ")");
            }
            this.tm.sendMessage(ssr.getPayload());
        }
        // monitorexit(c.channelSendLock)
        try {
            this.waitForChannelSuccessOrFailure(c);
        }
        catch (IOException e) {
            throw (IOException)new IOException("The subsystem request failed.").initCause(e);
        }
    }
    
    public void requestExecCommand(final Channel c, final String cmd) throws IOException {
        final PacketSessionExecCommand sm;
        synchronized (c) {
            if (c.state != 2) {
                throw new IOException("Cannot execute command on this channel (" + c.getReasonClosed() + ")");
            }
            sm = new PacketSessionExecCommand(c.remoteID, true, cmd);
            final int n = 0;
            c.failedCounter = n;
            c.successCounter = n;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                throw new IOException("Cannot execute command on this channel (" + c.getReasonClosed() + ")");
            }
            this.tm.sendMessage(sm.getPayload());
        }
        // monitorexit(c.channelSendLock)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Executing command (channel " + c.localID + ", '" + cmd + "')");
        }
        try {
            this.waitForChannelSuccessOrFailure(c);
        }
        catch (IOException e) {
            throw (IOException)new IOException("The execute request failed.").initCause(e);
        }
    }
    
    public void requestShell(final Channel c) throws IOException {
        final PacketSessionStartShell sm;
        synchronized (c) {
            if (c.state != 2) {
                throw new IOException("Cannot start shell on this channel (" + c.getReasonClosed() + ")");
            }
            sm = new PacketSessionStartShell(c.remoteID, true);
            final int n = 0;
            c.failedCounter = n;
            c.successCounter = n;
        }
        synchronized (c.channelSendLock) {
            if (c.closeMessageSent) {
                throw new IOException("Cannot start shell on this channel (" + c.getReasonClosed() + ")");
            }
            this.tm.sendMessage(sm.getPayload());
        }
        // monitorexit(c.channelSendLock)
        try {
            this.waitForChannelSuccessOrFailure(c);
        }
        catch (IOException e) {
            throw (IOException)new IOException("The shell request failed.").initCause(e);
        }
    }
    
    public void msgChannelExtendedData(final byte[] msg, final int msglen) throws IOException {
        if (msglen <= 13) {
            throw new IOException("SSH_MSG_CHANNEL_EXTENDED_DATA message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final int dataType = (msg[5] & 0xFF) << 24 | (msg[6] & 0xFF) << 16 | (msg[7] & 0xFF) << 8 | (msg[8] & 0xFF);
        final int len = (msg[9] & 0xFF) << 24 | (msg[10] & 0xFF) << 16 | (msg[11] & 0xFF) << 8 | (msg[12] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_EXTENDED_DATA message for non-existent channel " + id);
        }
        if (dataType != 1) {
            throw new IOException("SSH_MSG_CHANNEL_EXTENDED_DATA message has unknown type (" + dataType + ")");
        }
        if (len != msglen - 13) {
            throw new IOException("SSH_MSG_CHANNEL_EXTENDED_DATA message has wrong len (calculated " + (msglen - 13) + ", got " + len + ")");
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_CHANNEL_EXTENDED_DATA (channel " + id + ", " + len + ")");
        }
        synchronized (c) {
            if (c.state == 4) {
                // monitorexit(c)
                return;
            }
            if (c.state != 2) {
                throw new IOException("Got SSH_MSG_CHANNEL_EXTENDED_DATA, but channel is not in correct state (" + c.state + ")");
            }
            if (c.localWindow < len) {
                throw new IOException("Remote sent too much data, does not fit into window.");
            }
            final Channel channel = c;
            channel.localWindow -= len;
            System.arraycopy(msg, 13, c.stderrBuffer, c.stderrWritepos, len);
            final Channel channel2 = c;
            channel2.stderrWritepos += len;
            c.notifyAll();
        }
    }
    
    public int waitForCondition(final Channel c, long timeout, final int condition_mask) {
        long end_time = 0L;
        boolean end_time_set = false;
        synchronized (c) {
            while (true) {
                int current_cond = 0;
                final int stdoutAvail = c.stdoutWritepos - c.stdoutReadpos;
                final int stderrAvail = c.stderrWritepos - c.stderrReadpos;
                if (stdoutAvail > 0) {
                    current_cond |= 0x4;
                }
                if (stderrAvail > 0) {
                    current_cond |= 0x8;
                }
                if (c.EOF) {
                    current_cond |= 0x10;
                }
                if (c.getExitStatus() != null) {
                    current_cond |= 0x20;
                }
                if (c.getExitSignal() != null) {
                    current_cond |= 0x40;
                }
                if (c.state == 4) {
                    // monitorexit(c)
                    return current_cond | 0x2 | 0x10;
                }
                if ((current_cond & condition_mask) != 0x0) {
                    // monitorexit(c)
                    return current_cond;
                }
                if (timeout > 0L) {
                    if (!end_time_set) {
                        end_time = System.currentTimeMillis() + timeout;
                        end_time_set = true;
                    }
                    else {
                        timeout = end_time - System.currentTimeMillis();
                        if (timeout <= 0L) {
                            // monitorexit(c)
                            return current_cond | 0x1;
                        }
                    }
                }
                try {
                    if (timeout > 0L) {
                        c.wait(timeout);
                    }
                    else {
                        c.wait();
                    }
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public int getAvailable(final Channel c, final boolean extended) throws IOException {
        synchronized (c) {
            int avail;
            if (extended) {
                avail = c.stderrWritepos - c.stderrReadpos;
            }
            else {
                avail = c.stdoutWritepos - c.stdoutReadpos;
            }
            return (avail > 0) ? avail : (c.EOF ? -1 : 0);
        }
    }
    
    public int getChannelData(final Channel c, final boolean extended, final byte[] target, final int off, final int len) throws IOException {
        int copylen = 0;
        int increment = 0;
        int remoteID = 0;
        int localID = 0;
        synchronized (c) {
            int stdoutAvail = 0;
            int stderrAvail = 0;
            while (true) {
                stdoutAvail = c.stdoutWritepos - c.stdoutReadpos;
                stderrAvail = c.stderrWritepos - c.stderrReadpos;
                if (!extended && stdoutAvail != 0) {
                    break;
                }
                if (extended && stderrAvail != 0) {
                    break;
                }
                if (c.EOF || c.state != 2) {
                    // monitorexit(c)
                    return -1;
                }
                try {
                    c.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (!extended) {
                copylen = ((stdoutAvail > len) ? len : stdoutAvail);
                System.arraycopy(c.stdoutBuffer, c.stdoutReadpos, target, off, copylen);
                c.stdoutReadpos += copylen;
                if (c.stdoutReadpos != c.stdoutWritepos) {
                    System.arraycopy(c.stdoutBuffer, c.stdoutReadpos, c.stdoutBuffer, 0, c.stdoutWritepos - c.stdoutReadpos);
                }
                c.stdoutWritepos -= c.stdoutReadpos;
                c.stdoutReadpos = 0;
            }
            else {
                copylen = ((stderrAvail > len) ? len : stderrAvail);
                System.arraycopy(c.stderrBuffer, c.stderrReadpos, target, off, copylen);
                c.stderrReadpos += copylen;
                if (c.stderrReadpos != c.stderrWritepos) {
                    System.arraycopy(c.stderrBuffer, c.stderrReadpos, c.stderrBuffer, 0, c.stderrWritepos - c.stderrReadpos);
                }
                c.stderrWritepos -= c.stderrReadpos;
                c.stderrReadpos = 0;
            }
            if (c.state != 2) {
                // monitorexit(c)
                return copylen;
            }
            if (c.localWindow < 15000) {
                final int minFreeSpace = Math.min(30000 - c.stdoutWritepos, 30000 - c.stderrWritepos);
                increment = minFreeSpace - c.localWindow;
                c.localWindow = minFreeSpace;
            }
            remoteID = c.remoteID;
            localID = c.localID;
        }
        if (increment > 0) {
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(80, "Sending SSH_MSG_CHANNEL_WINDOW_ADJUST (channel " + localID + ", " + increment + ")");
            }
            synchronized (c.channelSendLock) {
                final byte[] msg = c.msgWindowAdjust;
                msg[0] = 93;
                msg[1] = (byte)(remoteID >> 24);
                msg[2] = (byte)(remoteID >> 16);
                msg[3] = (byte)(remoteID >> 8);
                msg[4] = (byte)remoteID;
                msg[5] = (byte)(increment >> 24);
                msg[6] = (byte)(increment >> 16);
                msg[7] = (byte)(increment >> 8);
                msg[8] = (byte)increment;
                if (!c.closeMessageSent) {
                    this.tm.sendMessage(msg);
                }
            }
            // monitorexit(c.channelSendLock)
        }
        return copylen;
    }
    
    public void msgChannelData(final byte[] msg, final int msglen) throws IOException {
        if (msglen <= 9) {
            throw new IOException("SSH_MSG_CHANNEL_DATA message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final int len = (msg[5] & 0xFF) << 24 | (msg[6] & 0xFF) << 16 | (msg[7] & 0xFF) << 8 | (msg[8] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_DATA message for non-existent channel " + id);
        }
        if (len != msglen - 9) {
            throw new IOException("SSH_MSG_CHANNEL_DATA message has wrong len (calculated " + (msglen - 9) + ", got " + len + ")");
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_CHANNEL_DATA (channel " + id + ", " + len + ")");
        }
        synchronized (c) {
            if (c.state == 4) {
                // monitorexit(c)
                return;
            }
            if (c.state != 2) {
                throw new IOException("Got SSH_MSG_CHANNEL_DATA, but channel is not in correct state (" + c.state + ")");
            }
            if (c.localWindow < len) {
                throw new IOException("Remote sent too much data, does not fit into window.");
            }
            final Channel channel = c;
            channel.localWindow -= len;
            System.arraycopy(msg, 9, c.stdoutBuffer, c.stdoutWritepos, len);
            final Channel channel2 = c;
            channel2.stdoutWritepos += len;
            c.notifyAll();
        }
    }
    
    public void msgChannelWindowAdjust(final byte[] msg, final int msglen) throws IOException {
        if (msglen != 9) {
            throw new IOException("SSH_MSG_CHANNEL_WINDOW_ADJUST message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final int windowChange = (msg[5] & 0xFF) << 24 | (msg[6] & 0xFF) << 16 | (msg[7] & 0xFF) << 8 | (msg[8] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_WINDOW_ADJUST message for non-existent channel " + id);
        }
        synchronized (c) {
            final long huge = 4294967295L;
            final Channel channel = c;
            channel.remoteWindow += ((long)windowChange & 0xFFFFFFFFL);
            if (c.remoteWindow > 4294967295L) {
                c.remoteWindow = 4294967295L;
            }
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_CHANNEL_WINDOW_ADJUST (channel " + id + ", " + windowChange + ")");
        }
    }
    
    public void msgChannelOpen(final byte[] msg, final int msglen) throws IOException {
        final TypesReader tr = new TypesReader(msg, 0, msglen);
        tr.readByte();
        final String channelType = tr.readString();
        final int remoteID = tr.readUINT32();
        final int remoteWindow = tr.readUINT32();
        final int remoteMaxPacketSize = tr.readUINT32();
        if ("x11".equals(channelType)) {
            synchronized (this.x11_magic_cookies) {
                if (this.x11_magic_cookies.size() == 0) {
                    final PacketChannelOpenFailure pcof = new PacketChannelOpenFailure(remoteID, 1, "X11 forwarding not activated", "");
                    this.tm.sendAsynchronousMessage(pcof.getPayload());
                    if (ChannelManager.log.isEnabled()) {
                        ChannelManager.log.log(20, "Unexpected X11 request, denying it!");
                    }
                    // monitorexit(this.x11_magic_cookies)
                    return;
                }
            }
            // monitorexit(this.x11_magic_cookies)
            final String remoteOriginatorAddress = tr.readString();
            final int remoteOriginatorPort = tr.readUINT32();
            final Channel c = new Channel(this);
            synchronized (c) {
                c.remoteID = remoteID;
                c.remoteWindow = ((long)remoteWindow & 0xFFFFFFFFL);
                c.remoteMaxPacketSize = remoteMaxPacketSize;
                c.localID = this.addChannel(c);
            }
            // monitorexit(c)
            final RemoteX11AcceptThread rxat = new RemoteX11AcceptThread(c, remoteOriginatorAddress, remoteOriginatorPort);
            rxat.setDaemon(true);
            rxat.start();
            return;
        }
        if (!"forwarded-tcpip".equals(channelType)) {
            final PacketChannelOpenFailure pcof2 = new PacketChannelOpenFailure(remoteID, 3, "Unknown channel type", "");
            this.tm.sendAsynchronousMessage(pcof2.getPayload());
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(20, "The peer tried to open an unsupported channel type (" + channelType + ")");
            }
            return;
        }
        final String remoteConnectedAddress = tr.readString();
        final int remoteConnectedPort = tr.readUINT32();
        final String remoteOriginatorAddress2 = tr.readString();
        final int remoteOriginatorPort2 = tr.readUINT32();
        RemoteForwardingData rfd = null;
        synchronized (this.remoteForwardings) {
            rfd = this.remoteForwardings.get(new Integer(remoteConnectedPort));
        }
        // monitorexit(this.remoteForwardings)
        if (rfd == null) {
            final PacketChannelOpenFailure pcof3 = new PacketChannelOpenFailure(remoteID, 1, "No thanks, unknown port in forwarded-tcpip request", "");
            this.tm.sendAsynchronousMessage(pcof3.getPayload());
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(20, "Unexpected forwarded-tcpip request, denying it!");
            }
            return;
        }
        final Channel c2 = new Channel(this);
        synchronized (c2) {
            c2.remoteID = remoteID;
            c2.remoteWindow = ((long)remoteWindow & 0xFFFFFFFFL);
            c2.remoteMaxPacketSize = remoteMaxPacketSize;
            c2.localID = this.addChannel(c2);
        }
        // monitorexit(c2)
        final RemoteAcceptThread rat = new RemoteAcceptThread(c2, remoteConnectedAddress, remoteConnectedPort, remoteOriginatorAddress2, remoteOriginatorPort2, rfd.targetAddress, rfd.targetPort);
        rat.setDaemon(true);
        rat.start();
    }
    
    public void msgChannelRequest(final byte[] msg, final int msglen) throws IOException {
        final TypesReader tr = new TypesReader(msg, 0, msglen);
        tr.readByte();
        final int id = tr.readUINT32();
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_REQUEST message for non-existent channel " + id);
        }
        final String type = tr.readString("US-ASCII");
        final boolean wantReply = tr.readBoolean();
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_CHANNEL_REQUEST (channel " + id + ", '" + type + "')");
        }
        if (type.equals("exit-status")) {
            if (wantReply) {
                throw new IOException("Badly formatted SSH_MSG_CHANNEL_REQUEST message, 'want reply' is true");
            }
            final int exit_status = tr.readUINT32();
            if (tr.remain() != 0) {
                throw new IOException("Badly formatted SSH_MSG_CHANNEL_REQUEST message");
            }
            synchronized (c) {
                c.exit_status = new Integer(exit_status);
                c.notifyAll();
            }
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(50, "Got EXIT STATUS (channel " + id + ", status " + exit_status + ")");
            }
        }
        else {
            if (!type.equals("exit-signal")) {
                if (wantReply) {
                    final byte[] reply = { 100, (byte)(c.remoteID >> 24), (byte)(c.remoteID >> 16), (byte)(c.remoteID >> 8), (byte)c.remoteID };
                    this.tm.sendAsynchronousMessage(reply);
                }
                if (ChannelManager.log.isEnabled()) {
                    ChannelManager.log.log(50, "Channel request '" + type + "' is not known, ignoring it");
                }
                return;
            }
            if (wantReply) {
                throw new IOException("Badly formatted SSH_MSG_CHANNEL_REQUEST message, 'want reply' is true");
            }
            final String signame = tr.readString("US-ASCII");
            tr.readBoolean();
            tr.readString();
            tr.readString();
            if (tr.remain() != 0) {
                throw new IOException("Badly formatted SSH_MSG_CHANNEL_REQUEST message");
            }
            synchronized (c) {
                c.exit_signal = signame;
                c.notifyAll();
            }
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(50, "Got EXIT SIGNAL (channel " + id + ", signal " + signame + ")");
            }
        }
    }
    
    public void msgChannelEOF(final byte[] msg, final int msglen) throws IOException {
        if (msglen != 5) {
            throw new IOException("SSH_MSG_CHANNEL_EOF message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_EOF message for non-existent channel " + id);
        }
        synchronized (c) {
            c.EOF = true;
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Got SSH_MSG_CHANNEL_EOF (channel " + id + ")");
        }
    }
    
    public void msgChannelClose(final byte[] msg, final int msglen) throws IOException {
        if (msglen != 5) {
            throw new IOException("SSH_MSG_CHANNEL_CLOSE message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_CLOSE message for non-existent channel " + id);
        }
        synchronized (c) {
            c.EOF = true;
            c.state = 4;
            c.setReasonClosed("Close requested by remote");
            c.closeMessageRecv = true;
            this.removeChannel(c.localID);
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Got SSH_MSG_CHANNEL_CLOSE (channel " + id + ")");
        }
    }
    
    public void msgChannelSuccess(final byte[] msg, final int msglen) throws IOException {
        if (msglen != 5) {
            throw new IOException("SSH_MSG_CHANNEL_SUCCESS message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_SUCCESS message for non-existent channel " + id);
        }
        synchronized (c) {
            final Channel channel = c;
            ++channel.successCounter;
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_CHANNEL_SUCCESS (channel " + id + ")");
        }
    }
    
    public void msgChannelFailure(final byte[] msg, final int msglen) throws IOException {
        if (msglen != 5) {
            throw new IOException("SSH_MSG_CHANNEL_FAILURE message has wrong size (" + msglen + ")");
        }
        final int id = (msg[1] & 0xFF) << 24 | (msg[2] & 0xFF) << 16 | (msg[3] & 0xFF) << 8 | (msg[4] & 0xFF);
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_FAILURE message for non-existent channel " + id);
        }
        synchronized (c) {
            final Channel channel = c;
            ++channel.failedCounter;
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Got SSH_MSG_CHANNEL_FAILURE (channel " + id + ")");
        }
    }
    
    public void msgChannelOpenConfirmation(final byte[] msg, final int msglen) throws IOException {
        final PacketChannelOpenConfirmation sm = new PacketChannelOpenConfirmation(msg, 0, msglen);
        final Channel c = this.getChannel(sm.recipientChannelID);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_OPEN_CONFIRMATION message for non-existent channel " + sm.recipientChannelID);
        }
        synchronized (c) {
            if (c.state != 1) {
                throw new IOException("Unexpected SSH_MSG_CHANNEL_OPEN_CONFIRMATION message for channel " + sm.recipientChannelID);
            }
            c.remoteID = sm.senderChannelID;
            c.remoteWindow = ((long)sm.initialWindowSize & 0xFFFFFFFFL);
            c.remoteMaxPacketSize = sm.maxPacketSize;
            c.state = 2;
            c.notifyAll();
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Got SSH_MSG_CHANNEL_OPEN_CONFIRMATION (channel " + sm.recipientChannelID + " / remote: " + sm.senderChannelID + ")");
        }
    }
    
    public void msgChannelOpenFailure(final byte[] msg, final int msglen) throws IOException {
        if (msglen < 5) {
            throw new IOException("SSH_MSG_CHANNEL_OPEN_FAILURE message has wrong size (" + msglen + ")");
        }
        final TypesReader tr = new TypesReader(msg, 0, msglen);
        tr.readByte();
        final int id = tr.readUINT32();
        final Channel c = this.getChannel(id);
        if (c == null) {
            throw new IOException("Unexpected SSH_MSG_CHANNEL_OPEN_FAILURE message for non-existent channel " + id);
        }
        final int reasonCode = tr.readUINT32();
        final String description = tr.readString("UTF-8");
        String reasonCodeSymbolicName = null;
        switch (reasonCode) {
            case 1: {
                reasonCodeSymbolicName = "SSH_OPEN_ADMINISTRATIVELY_PROHIBITED";
                break;
            }
            case 2: {
                reasonCodeSymbolicName = "SSH_OPEN_CONNECT_FAILED";
                break;
            }
            case 3: {
                reasonCodeSymbolicName = "SSH_OPEN_UNKNOWN_CHANNEL_TYPE";
                break;
            }
            case 4: {
                reasonCodeSymbolicName = "SSH_OPEN_RESOURCE_SHORTAGE";
                break;
            }
            default: {
                reasonCodeSymbolicName = "UNKNOWN REASON CODE (" + reasonCode + ")";
                break;
            }
        }
        final StringBuffer descriptionBuffer = new StringBuffer();
        descriptionBuffer.append(description);
        for (int i = 0; i < descriptionBuffer.length(); ++i) {
            final char cc = descriptionBuffer.charAt(i);
            if (cc < ' ' || cc > '~') {
                descriptionBuffer.setCharAt(i, '\ufffd');
            }
        }
        synchronized (c) {
            c.EOF = true;
            c.state = 4;
            c.setReasonClosed("The server refused to open the channel (" + reasonCodeSymbolicName + ", '" + descriptionBuffer.toString() + "')");
            c.notifyAll();
        }
        // monitorexit(c)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(50, "Got SSH_MSG_CHANNEL_OPEN_FAILURE (channel " + id + ")");
        }
    }
    
    public void msgGlobalRequest(final byte[] msg, final int msglen) throws IOException {
        final TypesReader tr = new TypesReader(msg, 0, msglen);
        tr.readByte();
        final String requestName = tr.readString();
        final boolean wantReply = tr.readBoolean();
        if (wantReply) {
            final byte[] reply_failure = { 82 };
            this.tm.sendAsynchronousMessage(reply_failure);
        }
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_GLOBAL_REQUEST (" + requestName + ")");
        }
    }
    
    public void msgGlobalSuccess() throws IOException {
        synchronized (this.channels) {
            ++this.globalSuccessCounter;
            this.channels.notifyAll();
        }
        // monitorexit(this.channels)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_REQUEST_SUCCESS");
        }
    }
    
    public void msgGlobalFailure() throws IOException {
        synchronized (this.channels) {
            ++this.globalFailedCounter;
            this.channels.notifyAll();
        }
        // monitorexit(this.channels)
        if (ChannelManager.log.isEnabled()) {
            ChannelManager.log.log(80, "Got SSH_MSG_REQUEST_FAILURE");
        }
    }
    
    public void handleMessage(final byte[] msg, final int msglen) throws IOException {
        if (msg == null) {
            if (ChannelManager.log.isEnabled()) {
                ChannelManager.log.log(50, "HandleMessage: got shutdown");
            }
            synchronized (this.listenerThreads) {
                for (int i = 0; i < this.listenerThreads.size(); ++i) {
                    final IChannelWorkerThread lat = this.listenerThreads.elementAt(i);
                    lat.stopWorking();
                }
                this.listenerThreadsAllowed = false;
            }
            // monitorexit(this.listenerThreads)
            synchronized (this.channels) {
                this.shutdown = true;
                for (int i = 0; i < this.channels.size(); ++i) {
                    final Channel c = this.channels.elementAt(i);
                    // monitorenter(channel = c)
                    try {
                        c.EOF = true;
                        c.state = 4;
                        c.setReasonClosed("The connection is being shutdown");
                        c.closeMessageRecv = true;
                        c.notifyAll();
                    }
                    // monitorexit(channel)
                    finally {}
                }
                this.channels.setSize(0);
                this.channels.trimToSize();
                this.channels.notifyAll();
                // monitorexit(this.channels)
                return;
            }
        }
        switch (msg[0]) {
            case 91: {
                this.msgChannelOpenConfirmation(msg, msglen);
                break;
            }
            case 93: {
                this.msgChannelWindowAdjust(msg, msglen);
                break;
            }
            case 94: {
                this.msgChannelData(msg, msglen);
                break;
            }
            case 95: {
                this.msgChannelExtendedData(msg, msglen);
                break;
            }
            case 98: {
                this.msgChannelRequest(msg, msglen);
                break;
            }
            case 96: {
                this.msgChannelEOF(msg, msglen);
                break;
            }
            case 90: {
                this.msgChannelOpen(msg, msglen);
                break;
            }
            case 97: {
                this.msgChannelClose(msg, msglen);
                break;
            }
            case 99: {
                this.msgChannelSuccess(msg, msglen);
                break;
            }
            case 100: {
                this.msgChannelFailure(msg, msglen);
                break;
            }
            case 92: {
                this.msgChannelOpenFailure(msg, msglen);
                break;
            }
            case 80: {
                this.msgGlobalRequest(msg, msglen);
                break;
            }
            case 81: {
                this.msgGlobalSuccess();
                break;
            }
            case 82: {
                this.msgGlobalFailure();
                break;
            }
            default: {
                throw new IOException("Cannot handle unknown channel message " + (msg[0] & 0xFF));
            }
        }
    }
}
