// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.login;

import org.apache.maven.scm.ScmResult;

public class LoginScmResult extends ScmResult
{
    private static final long serialVersionUID = -179242524702253809L;
    
    public LoginScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
}
