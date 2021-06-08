// 
// Decompiled by Procyon v0.5.36
// 

package groovy.io;

import java.nio.charset.Charset;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class EncodingAwareBufferedWriter extends BufferedWriter
{
    private OutputStreamWriter out;
    
    public EncodingAwareBufferedWriter(final OutputStreamWriter out) {
        super(out);
        this.out = out;
    }
    
    public String getEncoding() {
        return this.out.getEncoding();
    }
    
    public String getNormalizedEncoding() {
        return Charset.forName(this.getEncoding()).name();
    }
}
