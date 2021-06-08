// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.info;

import java.util.Iterator;
import java.io.File;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.AbstractCommand;

public class SvnInfoCommand extends AbstractCommand implements SvnCommand
{
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeInfoCommand((SvnScmProviderRepository)repository, fileSet, parameters, false, null);
    }
    
    public InfoScmResult executeInfoCommand(final SvnScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters, final boolean recursive, final String revision) throws ScmException {
        final Commandline cl = createCommandLine(repository, fileSet, recursive, revision);
        final SvnInfoConsumer consumer = new SvnInfoConsumer();
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
            return new InfoScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new InfoScmResult(cl.toString(), consumer.getInfoItems());
    }
    
    protected static Commandline createCommandLine(final SvnScmProviderRepository repository, final ScmFileSet fileSet, final boolean recursive, final String revision) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(fileSet.getBasedir(), repository);
        cl.createArg().setValue("info");
        if (recursive) {
            cl.createArg().setValue("--recursive");
        }
        if (StringUtils.isNotEmpty(revision)) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(revision);
        }
        for (final File file : fileSet.getFileList()) {
            if (repository == null) {
                cl.createArg().setValue(file.getPath());
            }
            else {
                cl.createArg().setValue(repository.getUrl() + "/" + file.getPath().replace('\\', '/'));
            }
        }
        return cl;
    }
}
