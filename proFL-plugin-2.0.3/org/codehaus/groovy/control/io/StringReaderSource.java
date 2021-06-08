// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;
import org.codehaus.groovy.control.CompilerConfiguration;

public class StringReaderSource extends AbstractReaderSource
{
    private final String string;
    
    public StringReaderSource(final String string, final CompilerConfiguration configuration) {
        super(configuration);
        this.string = string;
    }
    
    public Reader getReader() throws IOException {
        return new StringReader(this.string);
    }
}
