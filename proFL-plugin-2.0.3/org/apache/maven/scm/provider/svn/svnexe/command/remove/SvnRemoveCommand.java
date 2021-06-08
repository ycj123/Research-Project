// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.remove;

import java.io.IOException;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class SvnRemoveCommand extends AbstractRemoveCommand implements SvnCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("You must provide at least one file/directory to remove");
        }
        final Commandline cl = createCommandLine(fileSet.getBasedir(), fileSet.getFileList());
        final SvnRemoveConsumer consumer = new SvnRemoveConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new RemoveScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new RemoveScmResult(cl.toString(), consumer.getRemovedFiles());
    }
    
    private static Commandline createCommandLine(final File workingDirectory, final List<File> files) throws ScmException {
        final Commandline cl = new Commandline();
        cl.setExecutable("svn");
        cl.setWorkingDirectory(workingDirectory.getAbsolutePath());
        cl.createArg().setValue("remove");
        try {
            SvnCommandLineUtils.addTarget(cl, files);
        }
        catch (IOException e) {
            throw new ScmException("Can't create the targets file", e);
        }
        return cl;
    }
}
