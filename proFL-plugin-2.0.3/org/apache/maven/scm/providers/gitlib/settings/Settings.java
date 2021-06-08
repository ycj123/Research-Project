// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.gitlib.settings;

import java.io.Serializable;

public class Settings implements Serializable
{
    private String revParseDateFormat;
    private String traceGitCommand;
    private boolean commitNoVerify;
    private String modelEncoding;
    
    public Settings() {
        this.revParseDateFormat = "yyyy-MM-dd HH:mm:ss";
        this.traceGitCommand = "";
        this.commitNoVerify = false;
        this.modelEncoding = "UTF-8";
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public String getRevParseDateFormat() {
        return this.revParseDateFormat;
    }
    
    public String getTraceGitCommand() {
        return this.traceGitCommand;
    }
    
    public boolean isCommitNoVerify() {
        return this.commitNoVerify;
    }
    
    public void setCommitNoVerify(final boolean commitNoVerify) {
        this.commitNoVerify = commitNoVerify;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setRevParseDateFormat(final String revParseDateFormat) {
        this.revParseDateFormat = revParseDateFormat;
    }
    
    public void setTraceGitCommand(final String traceGitCommand) {
        this.traceGitCommand = traceGitCommand;
    }
}
