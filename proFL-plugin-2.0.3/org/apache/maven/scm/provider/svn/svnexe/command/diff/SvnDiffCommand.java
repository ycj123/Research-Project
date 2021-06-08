// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.diff;

import org.codehaus.plexus.util.StringUtils;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.command.diff.SvnDiffConsumer;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class SvnDiffCommand extends AbstractDiffCommand implements SvnCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, fileSet.getBasedir(), startVersion, endVersion);
        final SvnDiffConsumer consumer = new SvnDiffConsumer(this.getLogger(), fileSet.getBasedir());
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
            return new DiffScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new DiffScmResult(cl.toString(), consumer.getChangedFiles(), consumer.getDifferences(), consumer.getPatch());
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final ScmVersion startVersion, final ScmVersion endVersion) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("diff");
        if (startVersion != null && StringUtils.isNotEmpty(startVersion.getName())) {
            cl.createArg().setValue("-r");
            if (endVersion != null && StringUtils.isNotEmpty(endVersion.getName())) {
                cl.createArg().setValue(startVersion.getName() + ":" + endVersion.getName());
            }
            else {
                cl.createArg().setValue(startVersion.getName());
            }
        }
        return cl;
    }
}
