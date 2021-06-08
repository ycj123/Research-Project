// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.blame;

import org.apache.maven.scm.provider.integrity.APISession;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class IntegrityBlameCommand extends AbstractBlameCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repository, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        this.getLogger().info("Attempting to display blame results for file: " + filename);
        if (null == filename || filename.length() == 0) {
            throw new ScmException("A single filename is required to execute the blame command!");
        }
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final APISession api = iRepo.getAPISession();
        final Commandline shell = new Commandline();
        shell.setWorkingDirectory(workingDirectory.getBasedir());
        shell.setExecutable("si");
        shell.createArg().setValue("annotate");
        shell.createArg().setValue("--hostname=" + api.getHostName());
        shell.createArg().setValue("--port=" + api.getPort());
        shell.createArg().setValue("--user=" + api.getUserName());
        shell.createArg().setValue("--fields=date,revision,author");
        shell.createArg().setValue('\"' + filename + '\"');
        final IntegrityBlameConsumer shellConsumer = new IntegrityBlameConsumer(this.getLogger());
        try {
            this.getLogger().debug("Executing: " + shell.getCommandline());
            final int exitCode = CommandLineUtils.executeCommandLine(shell, shellConsumer, new CommandLineUtils.StringStreamConsumer());
            final boolean success = exitCode != 128;
            final ScmResult scmResult = new ScmResult(shell.getCommandline().toString(), "", "Exit Code: " + exitCode, success);
            return new BlameScmResult(shellConsumer.getBlameList(), scmResult);
        }
        catch (CommandLineException cle) {
            this.getLogger().error("Command Line Exception: " + cle.getMessage());
            final BlameScmResult result = new BlameScmResult(shell.getCommandline().toString(), cle.getMessage(), "", false);
            return result;
        }
    }
}
