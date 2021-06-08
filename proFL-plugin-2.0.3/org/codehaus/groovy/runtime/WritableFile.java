// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import groovy.lang.Writable;
import java.io.File;

public class WritableFile extends File implements Writable
{
    private final String encoding;
    
    public WritableFile(final File delegate) {
        this(delegate, null);
    }
    
    public WritableFile(final File delegate, final String encoding) {
        super(delegate.toURI());
        this.encoding = encoding;
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        final Reader reader = (this.encoding == null) ? DefaultGroovyMethods.newReader(this) : DefaultGroovyMethods.newReader(this, this.encoding);
        try {
            for (int c = reader.read(); c != -1; c = reader.read()) {
                out.write(c);
            }
        }
        finally {
            reader.close();
        }
        return out;
    }
}
