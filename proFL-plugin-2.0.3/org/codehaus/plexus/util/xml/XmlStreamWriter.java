// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.util.xml;

import java.util.regex.Matcher;
import java.util.Locale;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.util.regex.Pattern;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

public class XmlStreamWriter extends Writer
{
    private static final int BUFFER_SIZE = 4096;
    private StringWriter xmlPrologWriter;
    private OutputStream out;
    private Writer writer;
    private String encoding;
    static final Pattern ENCODING_PATTERN;
    
    public XmlStreamWriter(final OutputStream out) {
        this.xmlPrologWriter = new StringWriter(4096);
        this.out = out;
    }
    
    public XmlStreamWriter(final File file) throws FileNotFoundException {
        this(new FileOutputStream(file));
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void close() throws IOException {
        if (this.writer == null) {
            this.encoding = "UTF-8";
            (this.writer = new OutputStreamWriter(this.out, this.encoding)).write(this.xmlPrologWriter.toString());
        }
        this.writer.close();
    }
    
    public void flush() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        }
    }
    
    private void detectEncoding(final char[] cbuf, final int off, final int len) throws IOException {
        int size = len;
        final StringBuffer xmlProlog = this.xmlPrologWriter.getBuffer();
        if (xmlProlog.length() + len > 4096) {
            size = 4096 - xmlProlog.length();
        }
        this.xmlPrologWriter.write(cbuf, off, size);
        if (xmlProlog.length() >= 5) {
            if (xmlProlog.substring(0, 5).equals("<?xml")) {
                final int xmlPrologEnd = xmlProlog.indexOf("?>");
                if (xmlPrologEnd > 0) {
                    final Matcher m = XmlStreamWriter.ENCODING_PATTERN.matcher(xmlProlog.substring(0, xmlPrologEnd));
                    if (m.find()) {
                        this.encoding = m.group(1).toUpperCase(Locale.ENGLISH);
                        this.encoding = this.encoding.substring(1, this.encoding.length() - 1);
                    }
                    else {
                        this.encoding = "UTF-8";
                    }
                }
                else if (xmlProlog.length() >= 4096) {
                    this.encoding = "UTF-8";
                }
            }
            else {
                this.encoding = "UTF-8";
            }
            if (this.encoding != null) {
                this.xmlPrologWriter = null;
                (this.writer = new OutputStreamWriter(this.out, this.encoding)).write(xmlProlog.toString());
                if (len > size) {
                    this.writer.write(cbuf, off + size, len - size);
                }
            }
        }
    }
    
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.xmlPrologWriter != null) {
            this.detectEncoding(cbuf, off, len);
        }
        else {
            this.writer.write(cbuf, off, len);
        }
    }
    
    static {
        ENCODING_PATTERN = XmlReader.ENCODING_PATTERN;
    }
}
