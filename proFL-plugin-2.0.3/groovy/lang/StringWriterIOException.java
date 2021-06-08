// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.io.IOException;

public class StringWriterIOException extends RuntimeException
{
    public StringWriterIOException(final IOException e) {
        super(e);
    }
    
    public IOException getIOException() {
        return (IOException)this.getCause();
    }
}
