// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.add;

import java.util.Iterator;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.add.AddScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class PerforceAddCommand extends AbstractAddCommand implements PerforceCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet files, final String message, final boolean binary) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir(), files);
        final PerforceAddConsumer consumer = new PerforceAddConsumer();
        try {
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
        return new AddScmResult(cl.toString(), consumer.getAdditions());
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmFileSet files) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("add");
        for (final File file : files.getFileList()) {
            command.createArg().setValue(file.getName());
        }
        return command;
    }
}
