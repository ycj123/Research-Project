// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.add;

import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class BazaarAddCommand extends AbstractAddCommand implements Command
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        String[] addCmd = { "add", "--no-recurse" };
        addCmd = BazaarUtils.expandCommandLine(addCmd, fileSet);
        final File workingDir = fileSet.getBasedir();
        final BazaarAddConsumer consumer = new BazaarAddConsumer(this.getLogger(), workingDir);
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), workingDir, addCmd);
        return new AddScmResult(consumer.getAddedFiles(), result);
    }
}
