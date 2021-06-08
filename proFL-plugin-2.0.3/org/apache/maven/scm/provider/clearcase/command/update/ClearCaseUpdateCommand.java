// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.update;

import java.io.File;
import org.apache.maven.scm.provider.clearcase.command.changelog.ClearCaseChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class ClearCaseUpdateCommand extends AbstractUpdateCommand implements ClearCaseCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing update command...");
        }
        final Commandline cl = createCommandLine(fileSet);
        final ClearCaseUpdateConsumer consumer = new ClearCaseUpdateConsumer(this.getLogger());
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
            return new UpdateScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new UpdateScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final ClearCaseChangeLogCommand changeLogCmd = new ClearCaseChangeLogCommand();
        changeLogCmd.setLogger(this.getLogger());
        return changeLogCmd;
    }
    
    public static Commandline createCommandLine(final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("update");
        command.createArg().setValue("-f");
        return command;
    }
}
