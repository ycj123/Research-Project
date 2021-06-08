// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import java.nio.charset.Charset;
import groovy.io.EncodingAwareBufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.io.Writer;

public class StreamingMarkupWriter extends Writer
{
    protected final Writer writer;
    protected final String encoding;
    protected boolean encodingKnown;
    protected final CharsetEncoder encoder;
    protected boolean writingAttribute;
    protected boolean haveHighSurrogate;
    protected StringBuffer surrogatePair;
    private final Writer escapedWriter;
    
    public StreamingMarkupWriter(final Writer writer, final String encoding) {
        this.writingAttribute = false;
        this.haveHighSurrogate = false;
        this.surrogatePair = new StringBuffer(2);
        this.escapedWriter = new Writer() {
            @Override
            public void close() throws IOException {
                StreamingMarkupWriter.this.close();
            }
            
            @Override
            public void flush() throws IOException {
                StreamingMarkupWriter.this.flush();
            }
            
            @Override
            public void write(final int c) throws IOException {
                if (c == 60) {
                    StreamingMarkupWriter.this.writer.write("&lt;");
                }
                else if (c == 62) {
                    StreamingMarkupWriter.this.writer.write("&gt;");
                }
                else if (c == 38) {
                    StreamingMarkupWriter.this.writer.write("&amp;");
                }
                else {
                    StreamingMarkupWriter.this.write(c);
                }
            }
            
            @Override
            public void write(final char[] cbuf, int off, int len) throws IOException {
                while (len-- > 0) {
                    this.write(cbuf[off++]);
                }
            }
            
            public void setWritingAttribute(final boolean writingAttribute) {
                StreamingMarkupWriter.this.writingAttribute = writingAttribute;
            }
            
            public Writer excaped() {
                return StreamingMarkupWriter.this.escapedWriter;
            }
            
            public Writer unescaped() {
                return StreamingMarkupWriter.this;
            }
        };
        this.writer = writer;
        if (encoding != null) {
            this.encoding = encoding;
            this.encodingKnown = true;
        }
        else if (writer instanceof OutputStreamWriter) {
            this.encoding = this.getNormalizedEncoding(((OutputStreamWriter)writer).getEncoding());
            this.encodingKnown = true;
        }
        else if (writer instanceof EncodingAwareBufferedWriter) {
            this.encoding = this.getNormalizedEncoding(((EncodingAwareBufferedWriter)writer).getEncoding());
            this.encodingKnown = true;
        }
        else {
            this.encoding = "US-ASCII";
            this.encodingKnown = false;
        }
        this.encoder = Charset.forName(this.encoding).newEncoder();
    }
    
    private String getNormalizedEncoding(final String unnormalized) {
        return Charset.forName(unnormalized).name();
    }
    
    public StreamingMarkupWriter(final Writer writer) {
        this(writer, null);
    }
    
    @Override
    public void close() throws IOException {
        this.writer.close();
    }
    
    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }
    
    @Override
    public void write(final int c) throws IOException {
        if (c >= 56320 && c <= 57343) {
            this.surrogatePair.append((char)c);
            if (this.encoder.canEncode(this.surrogatePair)) {
                this.writer.write(this.surrogatePair.toString());
            }
            else {
                this.writer.write("&#x");
                this.writer.write(Integer.toHexString(65536 + ((this.surrogatePair.charAt(0) & '\u03ff') << 10) + (c & 0x3FF)));
                this.writer.write(59);
            }
            this.haveHighSurrogate = false;
            this.surrogatePair.setLength(0);
        }
        else {
            if (this.haveHighSurrogate) {
                this.haveHighSurrogate = false;
                this.surrogatePair.setLength(0);
                throw new IOException("High Surrogate not followed by Low Surrogate");
            }
            if (c >= 55296 && c <= 56319) {
                this.surrogatePair.append((char)c);
                this.haveHighSurrogate = true;
            }
            else if (!this.encoder.canEncode((char)c)) {
                this.writer.write("&#x");
                this.writer.write(Integer.toHexString(c));
                this.writer.write(59);
            }
            else if (c == 39 && this.writingAttribute) {
                this.writer.write("&apos;");
            }
            else {
                this.writer.write(c);
            }
        }
    }
    
    @Override
    public void write(final char[] cbuf, int off, int len) throws IOException {
        while (len-- > 0) {
            this.write(cbuf[off++]);
        }
    }
    
    public void setWritingAttribute(final boolean writingAttribute) {
        this.writingAttribute = writingAttribute;
    }
    
    public Writer escaped() {
        return this.escapedWriter;
    }
    
    public Writer unescaped() {
        return this;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public boolean getEncodingKnown() {
        return this.encodingKnown;
    }
}
