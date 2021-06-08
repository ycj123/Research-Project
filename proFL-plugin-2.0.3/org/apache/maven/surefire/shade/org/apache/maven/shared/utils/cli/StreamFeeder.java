// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

class StreamFeeder extends AbstractStreamHandler
{
    private InputStream input;
    private OutputStream output;
    
    public StreamFeeder(final InputStream input, final OutputStream output) {
        this.input = input;
        this.output = output;
    }
    
    @Override
    public void run() {
        try {
            this.feed();
        }
        catch (Throwable ex) {
            this.close();
            synchronized (this) {
                this.setDone();
                this.notifyAll();
            }
        }
        finally {
            this.close();
            synchronized (this) {
                this.setDone();
                this.notifyAll();
            }
        }
    }
    
    public void close() {
        if (this.input != null) {
            synchronized (this.input) {
                try {
                    this.input.close();
                }
                catch (IOException ex) {}
                this.input = null;
            }
        }
        if (this.output != null) {
            synchronized (this.output) {
                try {
                    this.output.close();
                }
                catch (IOException ex2) {}
                this.output = null;
            }
        }
    }
    
    private void feed() throws IOException {
        for (int data = this.input.read(); !this.isDone() && data != -1; data = this.input.read()) {
            synchronized (this.output) {
                if (!this.isDisabled()) {
                    this.output.write(data);
                }
            }
        }
    }
}
