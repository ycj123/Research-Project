// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.update;

import java.io.File;
import org.apache.maven.scm.provider.perforce.command.changelog.PerforceChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.provider.perforce.command.checkout.PerforceCheckOutCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class PerforceUpdateCommand extends AbstractUpdateCommand implements PerforceCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet files, final ScmVersion scmVersion) throws ScmException {
        final PerforceCheckOutCommand command = new PerforceCheckOutCommand();
        command.setLogger(this.getLogger());
        final CommandParameters params = new CommandParameters();
        params.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        final CheckOutScmResult cosr = (CheckOutScmResult)command.execute(repo, files, params);
        if (!cosr.isSuccess()) {
            return new UpdateScmResult(cosr.getCommandLine(), cosr.getProviderMessage(), cosr.getCommandOutput(), false);
        }
        final PerforceScmProviderRepository p4repo = (PerforceScmProviderRepository)repo;
        final String clientspec = PerforceScmProvider.getClientspecName(this.getLogger(), p4repo, files.getBasedir());
        final Commandline cl = createCommandLine(p4repo, files.getBasedir(), clientspec);
        final String location = PerforceScmProvider.getRepoPath(this.getLogger(), p4repo, files.getBasedir());
        final PerforceHaveConsumer consumer = new PerforceHaveConsumer(this.getLogger());
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing " + cl.toString()));
            }
            final CommandLineUtils.StringStreamConsumer err = new CommandLineUtils.StringStreamConsumer();
            final int exitCode = CommandLineUtils.executeCommandLine(cl, consumer, err);
            if (exitCode != 0) {
                final String cmdLine = CommandLineUtils.toString(cl.getCommandline());
                final StringBuilder msg = new StringBuilder("Exit code: " + exitCode + " - " + err.getOutput());
                msg.append('\n');
                msg.append("Command line was:" + cmdLine);
                throw new CommandLineException(msg.toString());
            }
        }
        catch (CommandLineException e) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("CommandLineException " + e.getMessage(), e);
            }
        }
        return new UpdateScmResultWithRevision(cosr.getCommandLine(), cosr.getCheckedOutFiles(), String.valueOf(consumer.getHave()));
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final PerforceChangeLogCommand command = new PerforceChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final String clientspec) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        if (clientspec != null) {
            command.createArg().setValue("-c");
            command.createArg().setValue(clientspec);
        }
        command.createArg().setValue("changes");
        command.createArg().setValue("-m1");
        command.createArg().setValue("-ssubmitted");
        command.createArg().setValue("//" + clientspec + "/...#have");
        return command;
    }
}
