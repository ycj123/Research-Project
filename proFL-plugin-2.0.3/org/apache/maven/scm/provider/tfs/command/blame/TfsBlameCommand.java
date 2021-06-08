// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.blame;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class TfsBlameCommand extends AbstractBlameCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        final Commandline cl = createCommandLine(workingDirectory.getBasedir(), filename);
        final TfsBlameConsumer consumer = new TfsBlameConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The tfs command failed.", stderr.getOutput(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final String filename) {
        final Commandline command = new Commandline();
        command.setWorkingDirectory(workingDirectory);
        command.setExecutable("tfpt");
        command.createArg().setValue("annotate");
        command.createArg().setValue("/noprompt");
        command.createArg().setValue(filename);
        return command;
    }
}
