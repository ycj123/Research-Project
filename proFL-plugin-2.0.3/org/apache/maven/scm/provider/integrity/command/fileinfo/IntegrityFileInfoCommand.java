// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.fileinfo;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.integrity.APISession;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.fileinfo.AbstractFileInfoCommand;

public class IntegrityFileInfoCommand extends AbstractFileInfoCommand
{
    public ScmResult executeFileInfoCommand(final ScmProviderRepository repository, final File workingDirectory, final String filename) throws ScmException {
        this.getLogger().info("Attempting to display scm file information for file: " + filename);
        if (null == filename || filename.length() == 0) {
            throw new ScmException("A single filename is required to execute the fileinfo command!");
        }
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final APISession api = iRepo.getAPISession();
        final Commandline shell = new Commandline();
        shell.setWorkingDirectory(workingDirectory);
        shell.setExecutable("si");
        shell.createArg().setValue("memberinfo");
        shell.createArg().setValue("--hostname=" + api.getHostName());
        shell.createArg().setValue("--port=" + api.getPort());
        shell.createArg().setValue("--user=" + api.getUserName());
        shell.createArg().setValue('\"' + filename + '\"');
        final IntegrityFileInfoConsumer shellConsumer = new IntegrityFileInfoConsumer(this.getLogger());
        ScmResult result;
        try {
            this.getLogger().debug("Executing: " + shell.getCommandline());
            final int exitCode = CommandLineUtils.executeCommandLine(shell, shellConsumer, new CommandLineUtils.StringStreamConsumer());
            final boolean success = exitCode != 128;
            result = new ScmResult(shell.getCommandline().toString(), "", "Exit Code: " + exitCode, success);
        }
        catch (CommandLineException cle) {
            this.getLogger().error("Command Line Exception: " + cle.getMessage());
            result = new ScmResult(shell.getCommandline().toString(), cle.getMessage(), "", false);
        }
        return result;
    }
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeFileInfoCommand(repository, fileSet.getBasedir(), parameters.getString(CommandParameter.FILE));
    }
}
