// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.blame;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class BazaarBlameCommand extends AbstractBlameCommand
{
    public static final String BLAME_CMD = "blame";
    
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        final String[] cmd = { "blame", "--all", "--long", filename };
        final BazaarBlameConsumer consumer = new BazaarBlameConsumer(this.getLogger());
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), workingDirectory.getBasedir(), cmd);
        return new BlameScmResult(consumer.getLines(), result);
    }
}
