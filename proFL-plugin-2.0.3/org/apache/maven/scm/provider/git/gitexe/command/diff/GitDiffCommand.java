// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.diff;

import org.codehaus.plexus.util.StringUtils;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.command.diff.GitDiffConsumer;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class GitDiffCommand extends AbstractDiffCommand implements GitCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        final GitDiffConsumer consumer = new GitDiffConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline clDiff2Index = createCommandLine(fileSet.getBasedir(), startVersion, endVersion, false);
        int exitCode = GitCommandLineUtils.execute(clDiff2Index, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new DiffScmResult(clDiff2Index.toString(), "The git-diff command failed.", stderr.getOutput(), false);
        }
        final Commandline clDiff2Head = createCommandLine(fileSet.getBasedir(), startVersion, endVersion, true);
        exitCode = GitCommandLineUtils.execute(clDiff2Head, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new DiffScmResult(clDiff2Head.toString(), "The git-diff command failed.", stderr.getOutput(), false);
        }
        return new DiffScmResult(clDiff2Index.toString(), consumer.getChangedFiles(), consumer.getDifferences(), consumer.getPatch());
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final ScmVersion startVersion, final ScmVersion endVersion, final boolean cached) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "diff");
        if (cached) {
            cl.createArg().setValue("--cached");
        }
        if (startVersion != null && StringUtils.isNotEmpty(startVersion.getName())) {
            cl.createArg().setValue(startVersion.getName());
        }
        if (endVersion != null && StringUtils.isNotEmpty(endVersion.getName())) {
            cl.createArg().setValue(endVersion.getName());
        }
        return cl;
    }
    
    public static Commandline createDiffRawCommandLine(final File workingDirectory, final String sha1) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "diff");
        cl.createArg().setValue("--raw");
        cl.createArg().setValue(sha1);
        return cl;
    }
}
