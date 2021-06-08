// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import groovy.lang.Closure;
import java.io.FilterOutputStream;

public class SystemOutputInterceptor extends FilterOutputStream
{
    private Closure callback;
    private boolean output;
    
    public SystemOutputInterceptor(final Closure callback) {
        this(callback, true);
    }
    
    public SystemOutputInterceptor(final Closure callback, final boolean output) {
        super(output ? System.out : System.err);
        assert callback != null;
        this.callback = callback;
        this.output = output;
    }
    
    public void start() {
        if (this.output) {
            System.setOut(new PrintStream(this));
        }
        else {
            System.setErr(new PrintStream(this));
        }
    }
    
    public void stop() {
        if (this.output) {
            System.setOut((PrintStream)this.out);
        }
        else {
            System.setErr((PrintStream)this.out);
        }
    }
    
    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        final Boolean result = (Boolean)this.callback.call(new String(b, off, len));
        if (result) {
            this.out.write(b, off, len);
        }
    }
    
    @Override
    public void write(final int b) throws IOException {
        final Boolean result = (Boolean)this.callback.call(String.valueOf((char)b));
        if (result) {
            this.out.write(b);
        }
    }
}
