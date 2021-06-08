// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

import java.io.Writer;
import java.io.IOException;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.IOUtil;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class StreamPumper extends AbstractStreamHandler
{
    private final BufferedReader in;
    private final StreamConsumer consumer;
    private final PrintWriter out;
    private volatile Exception exception;
    private static final int SIZE = 1024;
    
    public StreamPumper(final InputStream in, final StreamConsumer consumer) {
        this(in, null, consumer);
    }
    
    private StreamPumper(final InputStream in, final PrintWriter writer, final StreamConsumer consumer) {
        this.exception = null;
        this.in = new BufferedReader(new InputStreamReader(in), 1024);
        this.out = writer;
        this.consumer = consumer;
    }
    
    @Override
    public void run() {
        try {
            for (String line = this.in.readLine(); line != null; line = this.in.readLine()) {
                try {
                    if (this.exception == null) {
                        this.consumeLine(line);
                    }
                }
                catch (Exception t) {
                    this.exception = t;
                }
                if (this.out != null) {
                    this.out.println(line);
                    this.out.flush();
                }
            }
        }
        catch (IOException e) {
            this.exception = e;
            IOUtil.close(this.in);
            synchronized (this) {
                this.setDone();
                this.notifyAll();
            }
        }
        finally {
            IOUtil.close(this.in);
            synchronized (this) {
                this.setDone();
                this.notifyAll();
            }
        }
    }
    
    public void flush() {
        if (this.out != null) {
            this.out.flush();
        }
    }
    
    public void close() {
        IOUtil.close(this.out);
    }
    
    public Exception getException() {
        return this.exception;
    }
    
    private void consumeLine(final String line) {
        if (this.consumer != null && !this.isDisabled()) {
            this.consumer.consumeLine(line);
        }
    }
}
