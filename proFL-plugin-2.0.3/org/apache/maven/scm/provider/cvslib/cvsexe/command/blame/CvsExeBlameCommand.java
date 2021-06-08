// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.blame;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.blame.CvsBlameConsumer;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.blame.AbstractCvsBlameCommand;

public class CvsExeBlameCommand extends AbstractCvsBlameCommand
{
    @Override
    protected BlameScmResult executeCvsCommand(final Commandline cl, final CvsScmProviderRepository repository) throws ScmException {
        final CvsBlameConsumer consumer = new CvsBlameConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing cvs command.", ex);
        }
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
}
