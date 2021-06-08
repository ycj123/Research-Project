// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.blame;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class SvnBlameCommand extends AbstractBlameCommand implements SvnCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, workingDirectory.getBasedir(), filename);
        final SvnBlameConsumer consumer = new SvnBlameConsumer(this.getLogger());
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
            return new BlameScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final String filename) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("blame");
        cl.createArg().setValue("--xml");
        cl.createArg().setValue(filename);
        return cl;
    }
}
