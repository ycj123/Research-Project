// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.add;

import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class VssAddCommand extends AbstractAddCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("You must provide at least one file/directory to add");
        }
        final Commandline cl = this.buildCmdLine(repo, fileSet);
        final VssAddConsumer consumer = new VssAddConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        final int exitCode = VssCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The vss command failed.", stderr.getOutput(), false);
        }
        return new AddScmResult(cl.toString(), consumer.getAddedFiles());
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
        command.createArg().setValue("Add");
        VssCommandLineUtils.addFiles(command, fileSet);
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-I-");
        return command;
    }
    
    public Commandline buildSetCurrentProjectCmdLine(final VssScmProviderRepository repo) throws ScmException {
        final Commandline command = new Commandline();
        try {
            command.addSystemEnvironment();
        }
        catch (Exception e) {
            throw new ScmException("Can't add system environment.", e);
        }
        command.addEnvironment("SSDIR", repo.getVssdir());
        final String ssDir = VssCommandLineUtils.getSsDir();
        command.setExecutable(ssDir + "ss");
        command.createArg().setValue("CP");
        command.createArg().setValue("$" + repo.getProject());
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-I-");
        return command;
    }
}
