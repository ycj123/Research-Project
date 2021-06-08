// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.command.update;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;

public class GitUpdateScmResult extends UpdateScmResultWithRevision
{
    private static final long serialVersionUID = 7360578324181996847L;
    
    public GitUpdateScmResult(final String commandLine, final List<ScmFile> updatedFiles, final int revision) {
        super(commandLine, updatedFiles, String.valueOf(revision));
    }
}
