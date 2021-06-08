// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.remove;

import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class HgRemoveCommand extends AbstractRemoveCommand implements Command
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        String[] command = { "remove" };
        command = HgUtils.expandCommandLine(command, fileSet);
        final File workingDir = fileSet.getBasedir();
        final HgRemoveConsumer consumer = new HgRemoveConsumer(this.getLogger(), workingDir);
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), workingDir, command);
        return new RemoveScmResult(consumer.getRemovedFiles(), result);
    }
}
