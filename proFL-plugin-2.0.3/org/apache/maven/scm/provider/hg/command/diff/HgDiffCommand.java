// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.diff;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class HgDiffCommand extends AbstractDiffCommand implements Command
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startRevision, final ScmVersion endRevision) throws ScmException {
        String[] diffCmd;
        if (startRevision != null && !StringUtils.isEmpty(startRevision.getName())) {
            String revArg = startRevision.getName();
            if (endRevision != null && !StringUtils.isEmpty(endRevision.getName())) {
                revArg = revArg + ".." + endRevision;
            }
            diffCmd = new String[] { "diff", "-r", revArg };
        }
        else {
            diffCmd = new String[] { "diff" };
        }
        diffCmd = HgUtils.expandCommandLine(diffCmd, fileSet);
        final HgDiffConsumer consumer = new HgDiffConsumer(this.getLogger(), fileSet.getBasedir());
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), fileSet.getBasedir(), diffCmd);
        return new DiffScmResult(consumer.getChangedFiles(), consumer.getDifferences(), consumer.getPatch(), result);
    }
}
