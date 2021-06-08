// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.Closeable;
import java.io.BufferedInputStream;
import groovy.lang.GroovyRuntimeException;
import java.io.BufferedOutputStream;
import groovy.lang.Closure;
import java.io.Writer;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class ProcessGroovyMethods extends DefaultGroovyMethodsSupport
{
    public static InputStream getIn(final Process self) {
        return self.getInputStream();
    }
    
    public static String getText(final Process self) throws IOException {
        return DefaultGroovyMethods.getText(new BufferedReader(new InputStreamReader(self.getInputStream())));
    }
    
    public static InputStream getErr(final Process self) {
        return self.getErrorStream();
    }
    
    public static OutputStream getOut(final Process self) {
        return self.getOutputStream();
    }
    
    public static Writer leftShift(final Process self, final Object value) throws IOException {
        return DefaultGroovyMethods.leftShift(self.getOutputStream(), value);
    }
    
    public static OutputStream leftShift(final Process self, final byte[] value) throws IOException {
        return DefaultGroovyMethods.leftShift(self.getOutputStream(), value);
    }
    
    public static void waitForOrKill(final Process self, final long numberOfMillis) {
        final ProcessRunner runnable = new ProcessRunner(self);
        final Thread thread = new Thread(runnable);
        thread.start();
        runnable.waitForOrKill(numberOfMillis);
    }
    
    public static void consumeProcessOutput(final Process self) {
        consumeProcessOutput(self, null, (OutputStream)null);
    }
    
    public static void consumeProcessOutput(final Process self, final Appendable output, final Appendable error) {
        consumeProcessOutputStream(self, output);
        consumeProcessErrorStream(self, error);
    }
    
    public static void consumeProcessOutput(final Process self, final OutputStream output, final OutputStream error) {
        consumeProcessOutputStream(self, output);
        consumeProcessErrorStream(self, error);
    }
    
    public static void waitForProcessOutput(final Process self) {
        waitForProcessOutput(self, null, (OutputStream)null);
    }
    
    public static void waitForProcessOutput(final Process self, final Appendable output, final Appendable error) {
        final Thread tout = consumeProcessOutputStream(self, output);
        final Thread terr = consumeProcessErrorStream(self, error);
        try {
            tout.join();
        }
        catch (InterruptedException ex) {}
        try {
            terr.join();
        }
        catch (InterruptedException ex2) {}
        try {
            self.waitFor();
        }
        catch (InterruptedException ex3) {}
    }
    
    public static void waitForProcessOutput(final Process self, final OutputStream output, final OutputStream error) {
        final Thread tout = consumeProcessOutputStream(self, output);
        final Thread terr = consumeProcessErrorStream(self, error);
        try {
            tout.join();
        }
        catch (InterruptedException ex) {}
        try {
            terr.join();
        }
        catch (InterruptedException ex2) {}
        try {
            self.waitFor();
        }
        catch (InterruptedException ex3) {}
    }
    
    public static Thread consumeProcessErrorStream(final Process self, final OutputStream err) {
        final Thread thread = new Thread(new ByteDumper(self.getErrorStream(), err));
        thread.start();
        return thread;
    }
    
    public static Thread consumeProcessErrorStream(final Process self, final Appendable error) {
        final Thread thread = new Thread(new TextDumper(self.getErrorStream(), error));
        thread.start();
        return thread;
    }
    
    public static Thread consumeProcessOutputStream(final Process self, final Appendable output) {
        final Thread thread = new Thread(new TextDumper(self.getInputStream(), output));
        thread.start();
        return thread;
    }
    
    public static Thread consumeProcessOutputStream(final Process self, final OutputStream output) {
        final Thread thread = new Thread(new ByteDumper(self.getInputStream(), output));
        thread.start();
        return thread;
    }
    
    public static void withWriter(final Process self, final Closure closure) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    DefaultGroovyMethods.withWriter(new BufferedOutputStream(ProcessGroovyMethods.getOut(self)), closure);
                }
                catch (IOException e) {
                    throw new GroovyRuntimeException("exception while reading process stream", e);
                }
            }
        }).start();
    }
    
    public static void withOutputStream(final Process self, final Closure closure) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    DefaultGroovyMethods.withStream(new BufferedOutputStream(ProcessGroovyMethods.getOut(self)), closure);
                }
                catch (IOException e) {
                    throw new GroovyRuntimeException("exception while reading process stream", e);
                }
            }
        }).start();
    }
    
    public static Process pipeTo(final Process left, final Process right) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                final InputStream in = new BufferedInputStream(ProcessGroovyMethods.getIn(left));
                final OutputStream out = new BufferedOutputStream(ProcessGroovyMethods.getOut(right));
                final byte[] buf = new byte[8192];
                try {
                    int next;
                    while ((next = in.read(buf)) != -1) {
                        out.write(buf, 0, next);
                    }
                }
                catch (IOException e) {
                    throw new GroovyRuntimeException("exception while reading process stream", e);
                }
                finally {
                    DefaultGroovyMethodsSupport.closeWithWarning(out);
                }
            }
        }).start();
        return right;
    }
    
    public static Process or(final Process left, final Process right) throws IOException {
        return pipeTo(left, right);
    }
    
    protected static class ProcessRunner implements Runnable
    {
        Process process;
        private boolean finished;
        
        public ProcessRunner(final Process process) {
            this.process = process;
        }
        
        private void doProcessWait() {
            try {
                this.process.waitFor();
            }
            catch (InterruptedException ex) {}
        }
        
        public void run() {
            this.doProcessWait();
            synchronized (this) {
                this.notifyAll();
                this.finished = true;
            }
        }
        
        public synchronized void waitForOrKill(final long millis) {
            if (!this.finished) {
                try {
                    this.wait(millis);
                }
                catch (InterruptedException ex) {}
                if (!this.finished) {
                    this.process.destroy();
                    this.doProcessWait();
                }
            }
        }
    }
    
    private static class TextDumper implements Runnable
    {
        InputStream in;
        Appendable app;
        
        public TextDumper(final InputStream in, final Appendable app) {
            this.in = in;
            this.app = app;
        }
        
        public void run() {
            final InputStreamReader isr = new InputStreamReader(this.in);
            final BufferedReader br = new BufferedReader(isr);
            try {
                String next;
                while ((next = br.readLine()) != null) {
                    if (this.app != null) {
                        this.app.append(next);
                        this.app.append("\n");
                    }
                }
            }
            catch (IOException e) {
                throw new GroovyRuntimeException("exception while reading process stream", e);
            }
        }
    }
    
    private static class ByteDumper implements Runnable
    {
        InputStream in;
        OutputStream out;
        
        public ByteDumper(final InputStream in, final OutputStream out) {
            this.in = new BufferedInputStream(in);
            this.out = out;
        }
        
        public void run() {
            final byte[] buf = new byte[8192];
            try {
                int next;
                while ((next = this.in.read(buf)) != -1) {
                    if (this.out != null) {
                        this.out.write(buf, 0, next);
                    }
                }
            }
            catch (IOException e) {
                throw new GroovyRuntimeException("exception while dumping process stream", e);
            }
        }
    }
}
