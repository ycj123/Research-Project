// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.command.info;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.command.info.InfoItem;
import java.util.Collection;
import java.util.List;
import org.apache.maven.scm.command.info.InfoScmResult;

public class GitInfoScmResult extends InfoScmResult
{
    private static final long serialVersionUID = -1314905338508176675L;
    
    public GitInfoScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public GitInfoScmResult(final String commandLine, final List<GitInfoItem> files) {
        super(commandLine, null, null, true);
        if (files != null) {
            this.getInfoItems().addAll(files);
        }
    }
    
    public GitInfoScmResult(final List<GitInfoItem> files, final ScmResult result) {
        super(result);
        if (files != null) {
            this.getInfoItems().addAll(files);
        }
    }
}
