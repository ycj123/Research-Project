// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods.multipart;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.IOException;
import java.io.OutputStream;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class StringPart extends PartBase
{
    private static final Log LOG;
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_CHARSET = "US-ASCII";
    public static final String DEFAULT_TRANSFER_ENCODING = "8bit";
    private byte[] content;
    private String value;
    
    public StringPart(final String name, final String value, final String charset) {
        super(name, "text/plain", (charset == null) ? "US-ASCII" : charset, "8bit");
        if (value == null) {
            throw new IllegalArgumentException("Value may not be null");
        }
        if (value.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        }
        this.value = value;
    }
    
    public StringPart(final String name, final String value) {
        this(name, value, null);
    }
    
    private byte[] getContent() {
        if (this.content == null) {
            this.content = HttpConstants.getContentBytes(this.value, this.getCharSet());
        }
        return this.content;
    }
    
    protected void sendData(final OutputStream out) throws IOException {
        StringPart.LOG.trace("enter sendData(OutputStream)");
        out.write(this.getContent());
    }
    
    protected long lengthOfData() throws IOException {
        StringPart.LOG.trace("enter lengthOfData()");
        return this.getContent().length;
    }
    
    public void setCharSet(final String charSet) {
        super.setCharSet(charSet);
        this.content = null;
    }
    
    static {
        LOG = LogFactory.getLog(StringPart.class);
    }
}
