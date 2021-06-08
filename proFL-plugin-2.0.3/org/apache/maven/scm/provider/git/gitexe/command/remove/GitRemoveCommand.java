// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.remove;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class GitRemoveCommand extends AbstractRemoveCommand implements GitCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message) throws ScmException {
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("You must provide at least one file/directory to remove");
        }
        final Commandline cl = createCommandLine(fileSet.getBasedir(), fileSet.getFileList());
        final GitRemoveConsumer consumer = new GitRemoveConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = GitCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new RemoveScmResult(cl.toString(), "The git command failed.", stderr.getOutput(), false);
        }
        return new RemoveScmResult(cl.toString(), consumer.getRemovedFiles());
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final List<File> files) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "rm");
        for (final File file : files) {
            if (file.isAbsolute()) {
                if (file.isDirectory()) {
                    cl.createArg().setValue("-r");
                    break;
                }
                continue;
            }
            else {
                final File absFile = new File(workingDirectory, file.getPath());
                if (absFile.isDirectory()) {
                    cl.createArg().setValue("-r");
                    break;
                }
                continue;
            }
        }
        GitCommandLineUtils.addTarget(cl, files);
        return cl;
    }
}
