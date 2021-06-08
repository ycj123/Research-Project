// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.io.IOException;
import java.io.InputStream;

public class StreamGobbler extends InputStream
{
    private InputStream is;
    private GobblerThread t;
    private Object synchronizer;
    private boolean isEOF;
    private boolean isClosed;
    private IOException exception;
    private byte[] buffer;
    private int read_pos;
    private int write_pos;
    
    public StreamGobbler(final InputStream is) {
        this.synchronizer = new Object();
        this.isEOF = false;
        this.isClosed = false;
        this.exception = null;
        this.buffer = new byte[2048];
        this.read_pos = 0;
        this.write_pos = 0;
        this.is = is;
        (this.t = new GobblerThread()).setDaemon(true);
        this.t.start();
    }
    
    public int read() throws IOException {
        synchronized (this.synchronizer) {
            if (this.isClosed) {
                throw new IOException("This StreamGobbler is closed.");
            }
            while (this.read_pos == this.write_pos) {
                if (this.exception != null) {
                    throw this.exception;
                }
                if (this.isEOF) {
                    // monitorexit(this.synchronizer)
                    return -1;
                }
                try {
                    this.synchronizer.wait();
                }
                catch (InterruptedException ex) {}
            }
            final int n;
            final int b = n = (this.buffer[this.read_pos++] & 0xFF);
            // monitorexit(this.synchronizer)
            return n;
        }
    }
    
    public int available() throws IOException {
        synchronized (this.synchronizer) {
            if (this.isClosed) {
                throw new IOException("This StreamGobbler is closed.");
            }
            // monitorexit(this.synchronizer)
            return this.write_pos - this.read_pos;
        }
    }
    
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
    
    public void close() throws IOException {
        synchronized (this.synchronizer) {
            if (this.isClosed) {
                // monitorexit(this.synchronizer)
                return;
            }
            this.isClosed = true;
            this.isEOF = true;
            this.synchronizer.notifyAll();
            this.is.close();
        }
        // monitorexit(this.synchronizer)
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0 || off > b.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        synchronized (this.synchronizer) {
            if (this.isClosed) {
                throw new IOException("This StreamGobbler is closed.");
            }
            while (this.read_pos == this.write_pos) {
                if (this.exception != null) {
                    throw this.exception;
                }
                if (this.isEOF) {
                    // monitorexit(this.synchronizer)
                    return -1;
                }
                try {
                    this.synchronizer.wait();
                }
                catch (InterruptedException ex) {}
            }
            int avail = this.write_pos - this.read_pos;
            avail = ((avail > len) ? len : avail);
            System.arraycopy(this.buffer, this.read_pos, b, off, avail);
            this.read_pos += avail;
            // monitorexit(this.synchronizer)
            return avail;
        }
    }
    
    static /* synthetic */ void access$2(final StreamGobbler streamGobbler, final boolean isEOF) {
        streamGobbler.isEOF = isEOF;
    }
    
    static /* synthetic */ void access$6(final StreamGobbler streamGobbler, final byte[] buffer) {
        streamGobbler.buffer = buffer;
    }
    
    static /* synthetic */ void access$7(final StreamGobbler streamGobbler, final int read_pos) {
        streamGobbler.read_pos = read_pos;
    }
    
    static /* synthetic */ void access$8(final StreamGobbler streamGobbler, final int write_pos) {
        streamGobbler.write_pos = write_pos;
    }
    
    static /* synthetic */ void access$9(final StreamGobbler streamGobbler, final IOException exception) {
        streamGobbler.exception = exception;
    }
    
    class GobblerThread extends Thread
    {
        public void run() {
            final byte[] buff = new byte[8192];
            try {
                while (true) {
                    final int avail = StreamGobbler.this.is.read(buff);
                    synchronized (StreamGobbler.this.synchronizer) {
                        if (avail <= 0) {
                            StreamGobbler.access$2(StreamGobbler.this, true);
                            StreamGobbler.this.synchronizer.notifyAll();
                            // monitorexit(StreamGobbler.access$1(this.this$0))
                            break;
                        }
                        final int space_available = StreamGobbler.this.buffer.length - StreamGobbler.this.write_pos;
                        if (space_available < avail) {
                            final int unread_size = StreamGobbler.this.write_pos - StreamGobbler.this.read_pos;
                            final int need_space = unread_size + avail;
                            byte[] new_buffer = StreamGobbler.this.buffer;
                            if (need_space > StreamGobbler.this.buffer.length) {
                                int inc = need_space / 3;
                                inc = ((inc < 256) ? 256 : inc);
                                inc = ((inc > 8192) ? 8192 : inc);
                                new_buffer = new byte[need_space + inc];
                            }
                            if (unread_size > 0) {
                                System.arraycopy(StreamGobbler.this.buffer, StreamGobbler.this.read_pos, new_buffer, 0, unread_size);
                            }
                            StreamGobbler.access$6(StreamGobbler.this, new_buffer);
                            StreamGobbler.access$7(StreamGobbler.this, 0);
                            StreamGobbler.access$8(StreamGobbler.this, unread_size);
                        }
                        System.arraycopy(buff, 0, StreamGobbler.this.buffer, StreamGobbler.this.write_pos, avail);
                        final StreamGobbler this$0 = StreamGobbler.this;
                        StreamGobbler.access$8(this$0, this$0.write_pos + avail);
                        StreamGobbler.this.synchronizer.notifyAll();
                        // monitorexit(StreamGobbler.access$1(this.this$0))
                        continue;
                    }
                }
            }
            catch (IOException e) {
                synchronized (StreamGobbler.this.synchronizer) {
                    StreamGobbler.access$9(StreamGobbler.this, e);
                    StreamGobbler.this.synchronizer.notifyAll();
                }
                // monitorexit(StreamGobbler.access$1(this.this$0))
            }
        }
    }
}
