// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

import org.apache.maven.surefire.util.internal.ByteBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputCapture
{
    public static void startCapture(final ConsoleOutputReceiver target) {
        System.setOut(new ForwardingPrintStream(true, target));
        System.setErr(new ForwardingPrintStream(false, target));
    }
    
    private static class ForwardingPrintStream extends PrintStream
    {
        private final boolean isStdout;
        private final ConsoleOutputReceiver target;
        static final byte[] newline;
        
        ForwardingPrintStream(final boolean stdout, final ConsoleOutputReceiver target) {
            super(new ByteArrayOutputStream());
            this.isStdout = stdout;
            this.target = target;
        }
        
        @Override
        public void write(final byte[] buf, final int off, final int len) {
            this.target.writeTestOutput(buf, off, len, this.isStdout);
        }
        
        @Override
        public void write(final byte[] b) throws IOException {
            this.target.writeTestOutput(b, 0, b.length, this.isStdout);
        }
        
        @Override
        public void write(final int b) {
            final byte[] buf = { (byte)b };
            try {
                this.write(buf);
            }
            catch (IOException e) {
                this.setError();
            }
        }
        
        @Override
        public void println(String s) {
            if (s == null) {
                s = "null";
            }
            final byte[] bytes = s.getBytes();
            final byte[] join = ByteBuffer.join(bytes, 0, bytes.length, ForwardingPrintStream.newline, 0, 1);
            this.target.writeTestOutput(join, 0, join.length, this.isStdout);
        }
        
        @Override
        public void close() {
        }
        
        @Override
        public void flush() {
        }
        
        static {
            newline = new byte[] { 10 };
        }
    }
}
