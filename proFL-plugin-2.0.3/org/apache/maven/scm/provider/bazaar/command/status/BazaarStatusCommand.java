// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.status;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class BazaarStatusCommand extends AbstractStatusCommand implements Command
{
    public StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final File workingDir = fileSet.getBasedir();
        final BazaarStatusConsumer consumer = new BazaarStatusConsumer(this.getLogger(), workingDir);
        final String[] statusCmd = { "status" };
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), workingDir, statusCmd);
        return new StatusScmResult(consumer.getStatus(), result);
    }
}
