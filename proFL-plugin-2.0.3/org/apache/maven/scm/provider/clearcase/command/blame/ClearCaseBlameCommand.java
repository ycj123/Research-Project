// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.blame;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class ClearCaseBlameCommand extends AbstractBlameCommand implements ClearCaseCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing blame command...");
        }
        final Commandline cl = createCommandLine(workingDirectory.getBasedir(), filename);
        final ClearCaseBlameConsumer consumer = new ClearCaseBlameConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing cvs command.", ex);
        }
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final String filename) {
        final Commandline command = new Commandline();
        command.setExecutable("cleartool");
        command.createArg().setValue("annotate");
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        final StringBuilder format = new StringBuilder();
        format.append("VERSION:%Ln@@@");
        format.append("USER:%u@@@");
        format.append("DATE:%Nd@@@");
        command.createArg().setValue("-out");
        command.createArg().setValue("-");
        command.createArg().setValue("-fmt");
        command.createArg().setValue(format.toString());
        command.createArg().setValue("-nheader");
        command.createArg().setValue("-f");
        command.createArg().setValue(filename);
        return command;
    }
}
