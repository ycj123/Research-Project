// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.changelog;

import org.apache.maven.scm.ScmResult;

public class ChangeLogScmResult extends ScmResult
{
    private static final long serialVersionUID = 559431861541372265L;
    private ChangeLogSet changeLog;
    
    public ChangeLogScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public ChangeLogScmResult(final String commandLine, final ChangeLogSet changeLog) {
        super(commandLine, null, null, true);
        this.changeLog = changeLog;
    }
    
    public ChangeLogScmResult(final ChangeLogSet changeLog, final ScmResult result) {
        super(result);
        this.changeLog = changeLog;
    }
    
    public ChangeLogSet getChangeLog() {
        return this.changeLog;
    }
}
