// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.Reader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.nio.charset.Charset;
import java.io.File;

public class FileReaderSource extends AbstractReaderSource
{
    private File file;
    private final Charset UTF8;
    
    public FileReaderSource(final File file, final CompilerConfiguration configuration) {
        super(configuration);
        this.UTF8 = Charset.forName("UTF-8");
        this.file = file;
    }
    
    public Reader getReader() throws IOException {
        final Charset cs = Charset.forName(this.configuration.getSourceEncoding());
        final InputStream in = new BufferedInputStream(new FileInputStream(this.file));
        if (this.UTF8.name().equalsIgnoreCase(cs.name())) {
            in.mark(3);
            boolean hasBOM = true;
            try {
                int i = in.read();
                hasBOM &= (i == 239);
                i = in.read();
                hasBOM &= (i == 187);
                i = in.read();
                hasBOM &= (i == 255);
            }
            catch (IOException ioe) {
                hasBOM = false;
            }
            if (!hasBOM) {
                in.reset();
            }
        }
        return new InputStreamReader(in, cs);
    }
}
