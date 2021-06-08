// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.inventory;

import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class HgListCommand extends AbstractListCommand implements Command
{
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion scmVersion) throws ScmException {
        final File workingDir = fileSet.getBasedir();
        final String[] listCmd = { "locate" };
        final StringBuilder cmd = new StringBuilder();
        for (int i = 0; i < listCmd.length; ++i) {
            final String s = listCmd[i];
            cmd.append(s);
            if (i < listCmd.length - 1) {
                cmd.append(" ");
            }
        }
        final HgListConsumer consumer = new HgListConsumer(this.getLogger());
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), workingDir, listCmd);
        if (result.isSuccess()) {
            return new ListScmResult(consumer.getFiles(), result);
        }
        throw new ScmException("Error while executing command " + cmd.toString());
    }
}
