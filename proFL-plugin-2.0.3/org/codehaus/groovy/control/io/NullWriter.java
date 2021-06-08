// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import java.io.Writer;

public class NullWriter extends Writer
{
    public static final NullWriter DEFAULT;
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public void write(final char[] cbuf, final int off, final int len) {
    }
    
    static {
        DEFAULT = new NullWriter();
    }
}
