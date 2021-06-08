// 
// Decompiled by Procyon v0.5.36
// 

package groovy.io;

import org.codehaus.groovy.runtime.InvokerHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.PrintStream;

public class GroovyPrintStream extends PrintStream
{
    public GroovyPrintStream(final OutputStream out) {
        super(out, false);
    }
    
    public GroovyPrintStream(final OutputStream out, final boolean autoFlush) {
        super(out, autoFlush);
    }
    
    public GroovyPrintStream(final OutputStream out, final boolean autoFlush, final String encoding) throws UnsupportedEncodingException {
        super(out, autoFlush, encoding);
    }
    
    public GroovyPrintStream(final String fileName) throws FileNotFoundException {
        super(fileName);
    }
    
    public GroovyPrintStream(final String fileName, final String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }
    
    public GroovyPrintStream(final File file) throws FileNotFoundException {
        super(file);
    }
    
    public GroovyPrintStream(final File file, final String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }
    
    @Override
    public void print(final Object obj) {
        this.print(InvokerHelper.toString(obj));
    }
    
    @Override
    public void println(final Object obj) {
        this.println(InvokerHelper.toString(obj));
    }
}
