// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.svn.settings;

import java.io.Serializable;

public class Settings implements Serializable
{
    private String configDirectory;
    private boolean useCygwinPath;
    private String cygwinMountPath;
    private boolean useNonInteractive;
    private boolean useAuthCache;
    private boolean trustServerCert;
    private String modelEncoding;
    
    public Settings() {
        this.useCygwinPath = false;
        this.cygwinMountPath = "/cygwin";
        this.useNonInteractive = true;
        this.useAuthCache = false;
        this.trustServerCert = false;
        this.modelEncoding = "UTF-8";
    }
    
    public String getConfigDirectory() {
        return this.configDirectory;
    }
    
    public String getCygwinMountPath() {
        return this.cygwinMountPath;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public boolean isTrustServerCert() {
        return this.trustServerCert;
    }
    
    public boolean isUseAuthCache() {
        return this.useAuthCache;
    }
    
    public boolean isUseCygwinPath() {
        return this.useCygwinPath;
    }
    
    public boolean isUseNonInteractive() {
        return this.useNonInteractive;
    }
    
    public void setConfigDirectory(final String configDirectory) {
        this.configDirectory = configDirectory;
    }
    
    public void setCygwinMountPath(final String cygwinMountPath) {
        this.cygwinMountPath = cygwinMountPath;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setTrustServerCert(final boolean trustServerCert) {
        this.trustServerCert = trustServerCert;
    }
    
    public void setUseAuthCache(final boolean useAuthCache) {
        this.useAuthCache = useAuthCache;
    }
    
    public void setUseCygwinPath(final boolean useCygwinPath) {
        this.useCygwinPath = useCygwinPath;
    }
    
    public void setUseNonInteractive(final boolean useNonInteractive) {
        this.useNonInteractive = useNonInteractive;
    }
}
