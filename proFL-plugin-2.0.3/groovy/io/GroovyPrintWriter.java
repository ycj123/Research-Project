// 
// Decompiled by Procyon v0.5.36
// 

package groovy.io;

import org.codehaus.groovy.runtime.InvokerHelper;
import java.io.OutputStream;
import java.io.Writer;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class GroovyPrintWriter extends PrintWriter
{
    public GroovyPrintWriter(final File file) throws FileNotFoundException {
        super(file);
    }
    
    public GroovyPrintWriter(final File file, final String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }
    
    public GroovyPrintWriter(final Writer out) {
        super(out);
    }
    
    public GroovyPrintWriter(final Writer out, final boolean autoflush) {
        super(out, autoflush);
    }
    
    public GroovyPrintWriter(final OutputStream out) {
        super(out);
    }
    
    public GroovyPrintWriter(final OutputStream out, final boolean autoflush) {
        super(out, autoflush);
    }
    
    public GroovyPrintWriter(final String filename) throws FileNotFoundException {
        super(filename);
    }
    
    public GroovyPrintWriter(final String filename, final String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(filename, csn);
    }
    
    @Override
    public void print(final Object x) {
        this.write(InvokerHelper.toString(x));
    }
    
    @Override
    public void println(final Object x) {
        this.println(InvokerHelper.toString(x));
    }
}
