// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.update;

import org.apache.maven.scm.provider.cvslib.cvsexe.command.changelog.CvsExeChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.update.CvsUpdateConsumer;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.update.AbstractCvsUpdateCommand;

public class CvsExeUpdateCommand extends AbstractCvsUpdateCommand
{
    @Override
    protected UpdateScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsUpdateConsumer consumer = new CvsUpdateConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new UpdateScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new UpdateScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final CvsExeChangeLogCommand command = new CvsExeChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
