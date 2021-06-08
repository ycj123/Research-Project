// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.tag;

import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class VssTagCommand extends AbstractTagCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tagName, final ScmTagParameters scmTagParameters) throws ScmException {
        return this.executeTagCommand(repository, fileSet, tagName, (scmTagParameters == null) ? "" : scmTagParameters.getMessage());
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tagName, final String message) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing tag command...");
        }
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        final Commandline cl = this.buildCmdLine(repo, fileSet, tagName, message);
        final VssTagConsumer consumer = new VssTagConsumer(repo, this.getLogger());
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
                return new TagScmResult(cl.toString(), "The vss command failed.", error, false);
            }
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn(error);
            }
        }
        return new TagScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    public Commandline buildCmdLine(final VssScmProviderRepository repo, final ScmFileSet fileSet, final String tagName, final String message) throws ScmException {
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
        command.createArg().setValue("Label");
        command.createArg().setValue("$" + repo.getProject());
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-I-");
        command.createArg().setValue("-L" + tagName);
        return command;
    }
}
