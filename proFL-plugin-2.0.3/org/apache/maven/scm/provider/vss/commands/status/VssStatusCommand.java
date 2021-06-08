// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.status;

import org.apache.maven.scm.provider.vss.commands.changelog.VssHistoryCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class VssStatusCommand extends AbstractStatusCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing status command...");
        }
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        final Commandline cl = this.buildCmdLine(repo, fileSet);
        final VssStatusConsumer consumer = new VssStatusConsumer(repo, this.getLogger(), fileSet);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
        }
        final int exitCode = VssCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            final String error = stderr.getOutput();
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("VSS returns error: [" + error + "] return code: [" + exitCode + "]");
            }
            return new StatusScmResult(cl.toString(), "The vss command failed.", error, false);
        }
        return new StatusScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    public Commandline buildCmdLine(final VssScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final Commandline command = new Commandline();
        command.setWorkingDirectory(fileSet.getBasedir().getAbsolutePath());
        try {
            command.addSystemEnvironment();
        }
        catch (Exception e) {
            throw new ScmException("Can't add system environment.", e);
        }
        command.addEnvironment("SSDIR", repo.getVssdir());
        final String ssDir = VssCommandLineUtils.getSsDir();
        command.setExecutable(ssDir + "ss");
        command.createArg().setValue("Diff");
        command.createArg().setValue("$" + repo.getProject());
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-R");
        command.createArg().setValue("-I-");
        return command;
    }
    
    protected ChangeLogCommand getChangeLogCommand() {
        final VssHistoryCommand command = new VssHistoryCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
