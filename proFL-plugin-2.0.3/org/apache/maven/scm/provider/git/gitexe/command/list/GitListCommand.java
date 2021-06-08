// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.list;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class GitListCommand extends AbstractListCommand implements GitCommand
{
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final boolean recursive, final ScmVersion scmVersion) throws ScmException {
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        if ("file".equals(repository.getFetchInfo().getProtocol()) && repository.getFetchInfo().getPath().indexOf(fileSet.getBasedir().getPath()) >= 0) {
            throw new ScmException("remote repository must not be the working directory");
        }
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final GitListConsumer consumer = new GitListConsumer(this.getLogger(), fileSet.getBasedir().getParentFile(), ScmFileStatus.CHECKED_IN);
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir());
        final int exitCode = GitCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new ListScmResult(cl.toString(), "The git-ls-files command failed.", stderr.getOutput(), false);
        }
        return new ListScmResult(cl.toString(), consumer.getListedFiles());
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "ls-files");
        return cl;
    }
}
