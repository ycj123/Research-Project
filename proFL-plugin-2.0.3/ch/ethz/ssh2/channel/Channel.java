// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.channel;

public class Channel
{
    static final int STATE_OPENING = 1;
    static final int STATE_OPEN = 2;
    static final int STATE_CLOSED = 4;
    static final int CHANNEL_BUFFER_SIZE = 30000;
    final ChannelManager cm;
    final ChannelOutputStream stdinStream;
    final ChannelInputStream stdoutStream;
    final ChannelInputStream stderrStream;
    int localID;
    int remoteID;
    final Object channelSendLock;
    boolean closeMessageSent;
    final byte[] msgWindowAdjust;
    int state;
    boolean closeMessageRecv;
    int successCounter;
    int failedCounter;
    int localWindow;
    long remoteWindow;
    int localMaxPacketSize;
    int remoteMaxPacketSize;
    final byte[] stdoutBuffer;
    final byte[] stderrBuffer;
    int stdoutReadpos;
    int stdoutWritepos;
    int stderrReadpos;
    int stderrWritepos;
    boolean EOF;
    Integer exit_status;
    String exit_signal;
    String hexX11FakeCookie;
    private final Object reasonClosedLock;
    private String reasonClosed;
    
    public Channel(final ChannelManager cm) {
        this.localID = -1;
        this.remoteID = -1;
        this.channelSendLock = new Object();
        this.closeMessageSent = false;
        this.msgWindowAdjust = new byte[9];
        this.state = 1;
        this.closeMessageRecv = false;
        this.successCounter = 0;
        this.failedCounter = 0;
        this.localWindow = 0;
        this.remoteWindow = 0L;
        this.localMaxPacketSize = -1;
        this.remoteMaxPacketSize = -1;
        this.stdoutBuffer = new byte[30000];
        this.stderrBuffer = new byte[30000];
        this.stdoutReadpos = 0;
        this.stdoutWritepos = 0;
        this.stderrReadpos = 0;
        this.stderrWritepos = 0;
        this.EOF = false;
        this.reasonClosedLock = new Object();
        this.reasonClosed = null;
        this.cm = cm;
        this.localWindow = 30000;
        this.localMaxPacketSize = 33976;
        this.stdinStream = new ChannelOutputStream(this);
        this.stdoutStream = new ChannelInputStream(this, false);
        this.stderrStream = new ChannelInputStream(this, true);
    }
    
    public ChannelInputStream getStderrStream() {
        return this.stderrStream;
    }
    
    public ChannelOutputStream getStdinStream() {
        return this.stdinStream;
    }
    
    public ChannelInputStream getStdoutStream() {
        return this.stdoutStream;
    }
    
    public String getExitSignal() {
        synchronized (this) {
            return this.exit_signal;
        }
    }
    
    public Integer getExitStatus() {
        synchronized (this) {
            return this.exit_status;
        }
    }
    
    public String getReasonClosed() {
        synchronized (this.reasonClosedLock) {
            // monitorexit(this.reasonClosedLock)
            return this.reasonClosed;
        }
    }
    
    public void setReasonClosed(final String reasonClosed) {
        synchronized (this.reasonClosedLock) {
            if (this.reasonClosed == null) {
                this.reasonClosed = reasonClosed;
            }
        }
        // monitorexit(this.reasonClosedLock)
    }
}
