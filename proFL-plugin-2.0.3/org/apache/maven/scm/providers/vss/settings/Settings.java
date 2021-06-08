// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.vss.settings;

import java.io.Serializable;

public class Settings implements Serializable
{
    private String vssDirectory;
    private String modelEncoding;
    
    public Settings() {
        this.modelEncoding = "UTF-8";
    }
    
    public String getVssDirectory() {
        return this.vssDirectory;
    }
    
    public void setVssDirectory(final String vssDirectory) {
        this.vssDirectory = vssDirectory;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
