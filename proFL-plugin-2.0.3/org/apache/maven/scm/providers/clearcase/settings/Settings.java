// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.clearcase.settings;

import java.io.Serializable;

public class Settings implements Serializable
{
    private String viewstore;
    private boolean useVWSParameter;
    private String clearcaseType;
    private String changelogUserFormat;
    private String modelEncoding;
    
    public Settings() {
        this.useVWSParameter = true;
        this.modelEncoding = "UTF-8";
    }
    
    public String getChangelogUserFormat() {
        return this.changelogUserFormat;
    }
    
    public String getClearcaseType() {
        return this.clearcaseType;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public String getViewstore() {
        return this.viewstore;
    }
    
    public boolean isUseVWSParameter() {
        return this.useVWSParameter;
    }
    
    public void setChangelogUserFormat(final String changelogUserFormat) {
        this.changelogUserFormat = changelogUserFormat;
    }
    
    public void setClearcaseType(final String clearcaseType) {
        this.clearcaseType = clearcaseType;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setUseVWSParameter(final boolean useVWSParameter) {
        this.useVWSParameter = useVWSParameter;
    }
    
    public void setViewstore(final String viewstore) {
        this.viewstore = viewstore;
    }
}
