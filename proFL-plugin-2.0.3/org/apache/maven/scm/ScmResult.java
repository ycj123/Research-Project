// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public class ScmResult implements Serializable
{
    private static final long serialVersionUID = 7037918334820621525L;
    private final boolean success;
    private final String providerMessage;
    private final String commandOutput;
    private final String commandLine;
    
    public ScmResult(final ScmResult scmResult) {
        this.commandLine = scmResult.commandLine;
        this.providerMessage = scmResult.providerMessage;
        this.commandOutput = scmResult.commandOutput;
        this.success = scmResult.success;
    }
    
    public ScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        this.commandLine = commandLine;
        this.providerMessage = providerMessage;
        this.commandOutput = commandOutput;
        this.success = success;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    public String getProviderMessage() {
        return this.providerMessage;
    }
    
    public String getCommandOutput() {
        return this.commandOutput;
    }
    
    public String getCommandLine() {
        return this.commandLine;
    }
}
