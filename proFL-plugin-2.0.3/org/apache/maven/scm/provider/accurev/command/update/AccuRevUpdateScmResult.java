// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.update;

import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;

public class AccuRevUpdateScmResult extends UpdateScmResultWithRevision
{
    private static final long serialVersionUID = -4896981432286000329L;
    String fromRevision;
    
    public AccuRevUpdateScmResult(final String commandLine, final String providerMessage, final String commandOutput, final String fromRevision, final String toRevision, final boolean success) {
        super(commandLine, providerMessage, commandOutput, toRevision, success);
        this.fromRevision = fromRevision;
    }
    
    public AccuRevUpdateScmResult(final String commandLines, final List<ScmFile> updatedFiles, final String fromRevision, final String toRevision) {
        super(commandLines, updatedFiles, toRevision);
        this.fromRevision = fromRevision;
    }
    
    public String getFromRevision() {
        return this.fromRevision;
    }
    
    public String getToRevision() {
        return this.getRevision();
    }
}
