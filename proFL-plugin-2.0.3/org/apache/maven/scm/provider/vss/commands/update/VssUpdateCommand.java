// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.update;

import org.apache.maven.scm.provider.vss.commands.changelog.VssHistoryCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class VssUpdateCommand extends AbstractUpdateCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing update command...");
        }
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        final Commandline cl = this.buildCmdLine(repo, fileSet, version);
        final VssUpdateConsumer consumer = new VssUpdateConsumer(repo, this.getLogger());
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
            if (error.indexOf("A writable copy of") < 0) {
                return new UpdateScmResult(cl.toString(), "The vss command failed.", error, false);
            }
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn(error);
            }
        }
        return new UpdateScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    public Commandline buildCmdLine(final VssScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
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
        command.createArg().setValue("Get");
        command.createArg().setValue("$" + repo.getProject());
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-R");
        command.createArg().setValue("-I-");
        command.createArg().setValue("-GWS");
        if (version != null) {
            command.createArg().setValue("-VL" + version);
        }
        return command;
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final VssHistoryCommand command = new VssHistoryCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
