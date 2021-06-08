// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.cvslib.settings;

import java.util.Properties;
import java.io.Serializable;

public class Settings implements Serializable
{
    private String changeLogCommandDateFormat;
    private boolean useCvsrc;
    private int compressionLevel;
    private boolean traceCvsCommand;
    private String temporaryFilesDirectory;
    private Properties cvsVariables;
    private boolean useForceTag;
    private String modelEncoding;
    
    public Settings() {
        this.changeLogCommandDateFormat = "yyyy-MM-dd HH:mm:ssZ";
        this.useCvsrc = false;
        this.compressionLevel = 3;
        this.traceCvsCommand = false;
        this.useForceTag = true;
        this.modelEncoding = "UTF-8";
    }
    
    public void addCvsVariable(final String key, final String value) {
        this.getCvsVariables().put(key, value);
    }
    
    public String getChangeLogCommandDateFormat() {
        return this.changeLogCommandDateFormat;
    }
    
    public int getCompressionLevel() {
        return this.compressionLevel;
    }
    
    public Properties getCvsVariables() {
        if (this.cvsVariables == null) {
            this.cvsVariables = new Properties();
        }
        return this.cvsVariables;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public String getTemporaryFilesDirectory() {
        return this.temporaryFilesDirectory;
    }
    
    public boolean isTraceCvsCommand() {
        return this.traceCvsCommand;
    }
    
    public boolean isUseCvsrc() {
        return this.useCvsrc;
    }
    
    public boolean isUseForceTag() {
        return this.useForceTag;
    }
    
    public void setChangeLogCommandDateFormat(final String changeLogCommandDateFormat) {
        this.changeLogCommandDateFormat = changeLogCommandDateFormat;
    }
    
    public void setCompressionLevel(final int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }
    
    public void setCvsVariables(final Properties cvsVariables) {
        this.cvsVariables = cvsVariables;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setTemporaryFilesDirectory(final String temporaryFilesDirectory) {
        this.temporaryFilesDirectory = temporaryFilesDirectory;
    }
    
    public void setTraceCvsCommand(final boolean traceCvsCommand) {
        this.traceCvsCommand = traceCvsCommand;
    }
    
    public void setUseCvsrc(final boolean useCvsrc) {
        this.useCvsrc = useCvsrc;
    }
    
    public void setUseForceTag(final boolean useForceTag) {
        this.useForceTag = useForceTag;
    }
}
