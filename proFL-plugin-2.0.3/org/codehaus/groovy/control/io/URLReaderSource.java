// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.net.URL;

public class URLReaderSource extends AbstractReaderSource
{
    private URL url;
    
    public URLReaderSource(final URL url, final CompilerConfiguration configuration) {
        super(configuration);
        this.url = url;
    }
    
    public Reader getReader() throws IOException {
        return new InputStreamReader(this.url.openStream(), this.configuration.getSourceEncoding());
    }
}
