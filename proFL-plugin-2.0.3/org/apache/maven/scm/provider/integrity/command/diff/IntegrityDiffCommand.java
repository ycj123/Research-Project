// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.diff;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.integrity.APISession;
import org.codehaus.plexus.util.cli.CommandLineException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.ScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class IntegrityDiffCommand extends AbstractDiffCommand
{
    public DiffScmResult executeDiffCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion startRevision, final ScmVersion endRevision) throws ScmException {
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final APISession api = iRepo.getAPISession();
        this.getLogger().info("Showing differences bettween local files in " + fileSet.getBasedir().getAbsolutePath() + " and server project " + iRepo.getConfigruationPath());
        final Commandline shell = new Commandline();
        shell.setWorkingDirectory(fileSet.getBasedir());
        shell.setExecutable("si");
        shell.createArg().setValue("diff");
        shell.createArg().setValue("--hostname=" + api.getHostName());
        shell.createArg().setValue("--port=" + api.getPort());
        shell.createArg().setValue("--user=" + api.getUserName());
        shell.createArg().setValue("-R");
        shell.createArg().setValue("--filter=changed:all");
        shell.createArg().setValue("--filter=format:text");
        final IntegrityDiffConsumer shellConsumer = new IntegrityDiffConsumer(this.getLogger());
        try {
            this.getLogger().debug("Executing: " + shell.getCommandline());
            final int exitCode = CommandLineUtils.executeCommandLine(shell, shellConsumer, new CommandLineUtils.StringStreamConsumer());
            final boolean success = exitCode != 128;
            final ScmResult scmResult = new ScmResult(shell.getCommandline().toString(), "", "Exit Code: " + exitCode, success);
            return new DiffScmResult(new ArrayList<ScmFile>(), new HashMap<String, CharSequence>(), "", scmResult);
        }
        catch (CommandLineException cle) {
            this.getLogger().error("Command Line Exception: " + cle.getMessage());
            final DiffScmResult result = new DiffScmResult(shell.getCommandline().toString(), cle.getMessage(), "", false);
            return result;
        }
    }
}
