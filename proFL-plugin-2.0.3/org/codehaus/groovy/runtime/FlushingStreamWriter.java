// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FlushingStreamWriter extends OutputStreamWriter
{
    public FlushingStreamWriter(final OutputStream out) {
        super(out);
    }
    
    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        super.write(cbuf, off, len);
        this.flush();
    }
    
    @Override
    public void write(final int c) throws IOException {
        super.write(c);
        this.flush();
    }
    
    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        super.write(str, off, len);
        this.flush();
    }
}
