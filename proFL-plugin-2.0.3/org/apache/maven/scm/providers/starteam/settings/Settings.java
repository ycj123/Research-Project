// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.starteam.settings;

import java.io.Serializable;

public class Settings implements Serializable
{
    private boolean compressionEnable;
    private String eol;
    private String modelEncoding;
    
    public Settings() {
        this.compressionEnable = false;
        this.eol = "on";
        this.modelEncoding = "UTF-8";
    }
    
    public String getEol() {
        return this.eol;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public boolean isCompressionEnable() {
        return this.compressionEnable;
    }
    
    public void setCompressionEnable(final boolean compressionEnable) {
        this.compressionEnable = compressionEnable;
    }
    
    public void setEol(final String eol) {
        this.eol = eol;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
}
