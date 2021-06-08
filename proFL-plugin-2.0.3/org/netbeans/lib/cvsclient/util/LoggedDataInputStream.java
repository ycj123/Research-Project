// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.io.InterruptedIOException;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class LoggedDataInputStream extends FilterInputStream
{
    private long counter;
    
    public LoggedDataInputStream(final InputStream in) {
        super(in);
    }
    
    public String readLine() throws IOException {
        return this.readLineBytes().getStringFromBytes();
    }
    
    public ByteArray readLineBytes() throws IOException {
        int n = 1;
        final ByteArray byteArray = new ByteArray();
    Label_0126:
        while (true) {
            while (!Thread.interrupted()) {
                if (this.in.available() == 0) {
                    try {
                        Thread.sleep(100L);
                        continue;
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        break Label_0126;
                    }
                }
                final int read = this.in.read();
                ++this.counter;
                switch (read) {
                    case -1: {
                        if (n != 0) {
                            throw new EOFException();
                        }
                        break;
                    }
                    case 10: {
                        break;
                    }
                    default: {
                        byteArray.add((byte)read);
                        n = 0;
                        continue;
                    }
                }
                Logger.logInput(byteArray.getBytes());
                Logger.logInput('\n');
                return byteArray;
            }
            Thread.currentThread().interrupt();
            continue Label_0126;
        }
    }
    
    public byte[] readBytes(int i) throws IOException {
        final ByteArray byteArray = new ByteArray();
    Label_0111:
        while (i != 0) {
            if (Thread.interrupted()) {
                Thread.currentThread().interrupt();
                break;
            }
            if (this.in.available() == 0) {
                try {
                    Thread.sleep(100L);
                    continue;
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            final int read = this.in.read();
            ++this.counter;
            switch (read) {
                case -1: {
                    break Label_0111;
                }
                default: {
                    byteArray.add((byte)read);
                    --i;
                    continue;
                }
            }
        }
        final byte[] bytes = byteArray.getBytes();
        Logger.logInput(bytes);
        return bytes;
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public int read(final byte[] b) throws IOException {
        final int read = this.in.read(b);
        if (read != -1) {
            Logger.logInput(b, 0, read);
            this.counter += read;
        }
        return read;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int read = this.in.read(b, off, len);
        if (read != -1) {
            Logger.logInput(b, off, read);
            this.counter += read;
        }
        return read;
    }
    
    public long skip(final long n) throws IOException {
        final long skip = super.skip(n);
        if (skip > 0L) {
            Logger.logInput(new String("<skipped " + skip + " bytes>").getBytes("utf8"));
            this.counter += skip;
        }
        return skip;
    }
    
    public int read() throws IOException {
        while (this.in.available() == 0) {
            try {
                Thread.sleep(100L);
                continue;
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
            break;
        }
        final int read = super.read();
        if (read != -1) {
            Logger.logInput((char)read);
            ++this.counter;
        }
        return read;
    }
    
    public InputStream getUnderlyingStream() {
        return this.in;
    }
    
    public void setUnderlyingStream(final InputStream in) {
        this.in = in;
    }
    
    public long getCounter() {
        return this.counter;
    }
}
