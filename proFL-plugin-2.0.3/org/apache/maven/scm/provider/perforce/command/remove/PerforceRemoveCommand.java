// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.remove;

import java.util.List;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class PerforceRemoveCommand extends AbstractRemoveCommand implements PerforceCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repo, final ScmFileSet files, final String message) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir(), files);
        final PerforceRemoveConsumer consumer = new PerforceRemoveConsumer();
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
            throw new ScmException("CommandLineException " + e.getMessage(), e);
        }
        return new RemoveScmResult(cl.toString(), consumer.getRemovals());
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmFileSet files) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("delete");
        final List<File> fs = files.getFileList();
        for (int i = 0; i < fs.size(); ++i) {
            final File file = fs.get(i);
            command.createArg().setValue(file.getName());
        }
        return command;
    }
}
