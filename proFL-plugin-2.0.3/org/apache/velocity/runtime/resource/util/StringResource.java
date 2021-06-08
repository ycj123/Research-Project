// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.util;

public final class StringResource
{
    private String body;
    private String encoding;
    private long lastModified;
    
    public StringResource(final String body, final String encoding) {
        this.setBody(body);
        this.setEncoding(encoding);
    }
    
    public String getBody() {
        return this.body;
    }
    
    public long getLastModified() {
        return this.lastModified;
    }
    
    public void setBody(final String body) {
        this.body = body;
        this.lastModified = System.currentTimeMillis();
    }
    
    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
}
