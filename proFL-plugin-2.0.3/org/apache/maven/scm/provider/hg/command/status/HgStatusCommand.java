// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.status;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class HgStatusCommand extends AbstractStatusCommand implements Command
{
    public StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final File workingDir = fileSet.getBasedir();
        final HgStatusConsumer consumer = new HgStatusConsumer(this.getLogger(), workingDir);
        final String[] statusCmd = { "status" };
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), workingDir, statusCmd);
        return new StatusScmResult(consumer.getStatus(), result);
    }
}
