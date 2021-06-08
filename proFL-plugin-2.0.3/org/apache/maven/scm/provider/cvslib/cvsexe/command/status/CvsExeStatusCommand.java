// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.status;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.status.CvsStatusConsumer;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.status.AbstractCvsStatusCommand;

public class CvsExeStatusCommand extends AbstractCvsStatusCommand
{
    @Override
    protected StatusScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsStatusConsumer consumer = new CvsStatusConsumer(this.getLogger(), cl.getWorkingDirectory());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getChangedFiles());
    }
}
