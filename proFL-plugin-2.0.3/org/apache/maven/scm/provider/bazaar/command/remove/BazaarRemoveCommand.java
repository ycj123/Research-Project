// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.remove;

import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class BazaarRemoveCommand extends AbstractRemoveCommand implements Command
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        final String[] command = { "remove" };
        BazaarUtils.expandCommandLine(command, fileSet);
        final File workingDir = fileSet.getBasedir();
        final BazaarRemoveConsumer consumer = new BazaarRemoveConsumer(this.getLogger(), workingDir);
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), workingDir, command);
        return new RemoveScmResult(consumer.getRemovedFiles(), result);
    }
}
