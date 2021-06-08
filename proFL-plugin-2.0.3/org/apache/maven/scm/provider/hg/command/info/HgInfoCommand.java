// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.info;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.AbstractCommand;

public class HgInfoCommand extends AbstractCommand implements Command
{
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String[] revCmd = { "id", "-i" };
        final HgInfoConsumer consumer = new HgInfoConsumer(this.getLogger());
        final ScmResult scmResult = HgUtils.execute(consumer, this.getLogger(), fileSet.getBasedir(), revCmd);
        return new InfoScmResult(consumer.getInfoItems(), scmResult);
    }
}
