// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.blame;

import java.io.File;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.blame.BlameLine;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class PerforceBlameCommand extends AbstractBlameCommand implements PerforceCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        final PerforceScmProviderRepository p4repo = (PerforceScmProviderRepository)repo;
        final String clientspec = PerforceScmProvider.getClientspecName(this.getLogger(), p4repo, workingDirectory.getBasedir());
        Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, workingDirectory.getBasedir(), filename, clientspec);
        final PerforceBlameConsumer blameConsumer = new PerforceBlameConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, blameConsumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The perforce command failed.", stderr.getOutput(), false);
        }
        cl = createFilelogCommandLine((PerforceScmProviderRepository)repo, workingDirectory.getBasedir(), filename, clientspec);
        final PerforceFilelogConsumer filelogConsumer = new PerforceFilelogConsumer(this.getLogger());
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, filelogConsumer, stderr);
        }
        catch (CommandLineException ex2) {
            throw new ScmException("Error while executing command.", ex2);
        }
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The perforce command failed.", stderr.getOutput(), false);
        }
        final List<BlameLine> lines = blameConsumer.getLines();
        for (int i = 0; i < lines.size(); ++i) {
            final BlameLine line = lines.get(i);
            final String revision = line.getRevision();
            line.setAuthor(filelogConsumer.getAuthor(revision));
            line.setDate(filelogConsumer.getDate(revision));
        }
        return new BlameScmResult(cl.toString(), lines);
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final String filename, final String clientspec) {
        final Commandline cl = PerforceScmProvider.createP4Command(repo, workingDirectory);
        if (clientspec != null) {
            cl.createArg().setValue("-c");
            cl.createArg().setValue(clientspec);
        }
        cl.createArg().setValue("annotate");
        cl.createArg().setValue("-q");
        cl.createArg().setValue(filename);
        return cl;
    }
    
    public static Commandline createFilelogCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final String filename, final String clientspec) {
        final Commandline cl = PerforceScmProvider.createP4Command(repo, workingDirectory);
        if (clientspec != null) {
            cl.createArg().setValue("-c");
            cl.createArg().setValue(clientspec);
        }
        cl.createArg().setValue("filelog");
        cl.createArg().setValue(filename);
        return cl;
    }
}
