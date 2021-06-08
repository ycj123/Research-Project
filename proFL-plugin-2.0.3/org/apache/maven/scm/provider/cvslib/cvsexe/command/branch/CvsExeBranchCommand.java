// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.branch;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.branch.CvsBranchConsumer;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.branch.AbstractCvsBranchCommand;

public class CvsExeBranchCommand extends AbstractCvsBranchCommand
{
    @Override
    protected BranchScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsBranchConsumer consumer = new CvsBranchConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new BranchScmResult(cl.toString(), "The cvs branch command failed.", stderr.getOutput(), false);
        }
        return new BranchScmResult(cl.toString(), consumer.getTaggedFiles());
    }
}
