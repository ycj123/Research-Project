// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.command.update;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;

public class SvnUpdateScmResult extends UpdateScmResultWithRevision
{
    private static final long serialVersionUID = -3233977852698721693L;
    
    public SvnUpdateScmResult(final String commandLine, final List<ScmFile> updatedFiles, final int revision) {
        super(commandLine, updatedFiles, String.valueOf(revision));
    }
}
