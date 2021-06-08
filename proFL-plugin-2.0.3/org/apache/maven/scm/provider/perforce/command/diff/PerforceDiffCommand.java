// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.diff;

import org.codehaus.plexus.util.StringUtils;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class PerforceDiffCommand extends AbstractDiffCommand implements PerforceCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet files, final ScmVersion startRev, final ScmVersion endRev) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir(), startRev, endRev);
        final PerforceDiffConsumer consumer = new PerforceDiffConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + PerforceScmProvider.clean(cl.toString()));
        }
        final boolean success = false;
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
        return new DiffScmResult(cl.toString(), success ? "Diff successful" : "Unable to diff", consumer.getOutput(), success);
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmVersion startRev, final ScmVersion endRev) {
        final String start = (startRev != null && StringUtils.isNotEmpty(startRev.getName())) ? ("@" + startRev.getName()) : "";
        final String end = (endRev != null && StringUtils.isNotEmpty(endRev.getName())) ? endRev.getName() : "now";
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("diff2");
        command.createArg().setValue("-u");
        command.createArg().setValue("..." + start);
        command.createArg().setValue("...@" + end);
        return command;
    }
}
