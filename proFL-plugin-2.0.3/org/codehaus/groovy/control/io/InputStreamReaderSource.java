// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.InputStream;

public class InputStreamReaderSource extends AbstractReaderSource
{
    private InputStream stream;
    
    public InputStreamReaderSource(final InputStream stream, final CompilerConfiguration configuration) {
        super(configuration);
        this.stream = stream;
    }
    
    public Reader getReader() throws IOException {
        if (this.stream != null) {
            final Reader reader = new InputStreamReader(this.stream, this.configuration.getSourceEncoding());
            this.stream = null;
            return reader;
        }
        return null;
    }
    
    @Override
    public boolean canReopenSource() {
        return false;
    }
}
