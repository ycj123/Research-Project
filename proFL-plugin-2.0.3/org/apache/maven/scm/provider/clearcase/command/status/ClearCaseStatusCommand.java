// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.status;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class ClearCaseStatusCommand extends AbstractStatusCommand implements ClearCaseCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet scmFileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing status command...");
        }
        final Commandline cl = createCommandLine(scmFileSet);
        final ClearCaseStatusConsumer consumer = new ClearCaseStatusConsumer(this.getLogger(), scmFileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getCheckedOutFiles());
    }
    
    public static Commandline createCommandLine(final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("lscheckout");
        command.createArg().setValue("-cview");
        command.createArg().setValue("-r");
        command.createArg().setValue("-fmt");
        command.createArg().setValue("%n\\n");
        return command;
    }
}
