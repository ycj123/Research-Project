// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.remoteinfo;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.remoteinfo.AbstractRemoteInfoCommand;

public class GitRemoteInfoCommand extends AbstractRemoteInfoCommand implements GitCommand
{
    @Override
    public RemoteInfoScmResult executeRemoteInfoCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitScmProviderRepository gitRepository = (GitScmProviderRepository)repository;
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline clLsRemote = createCommandLine(gitRepository);
        final GitRemoteInfoConsumer consumer = new GitRemoteInfoConsumer(this.getLogger(), clLsRemote.toString());
        final int exitCode = GitCommandLineUtils.execute(clLsRemote, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            throw new ScmException("unbale to execute ls-remote on " + gitRepository.getFetchUrl());
        }
        return consumer.getRemoteInfoScmResult();
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(null, "ls-remote");
        cl.setWorkingDirectory(System.getProperty("java.io.tmpdir"));
        final String remoteUrl = repository.getPushUrl();
        cl.createArg().setValue(remoteUrl);
        return cl;
    }
}
