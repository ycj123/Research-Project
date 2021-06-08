// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.diff;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.diff.CvsDiffConsumer;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.diff.AbstractCvsDiffCommand;

public class CvsExeDiffCommand extends AbstractCvsDiffCommand
{
    @Override
    protected DiffScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsDiffConsumer consumer = new CvsDiffConsumer(this.getLogger(), cl.getWorkingDirectory());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        try {
            CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        return new DiffScmResult(cl.toString(), consumer.getChangedFiles(), consumer.getDifferences(), consumer.getPatch());
    }
}
