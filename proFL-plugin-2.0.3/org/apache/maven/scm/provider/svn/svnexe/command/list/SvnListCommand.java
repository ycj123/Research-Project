// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.list;

import java.util.Iterator;
import org.apache.maven.scm.ScmRevision;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import java.io.File;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class SvnListCommand extends AbstractListCommand implements SvnCommand
{
    private static final File TMP_DIR;
    
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) throws ScmException {
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repository, fileSet, recursive, version);
        final SvnListConsumer consumer = new SvnListConsumer();
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
            return new ListScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new ListScmResult(cl.toString(), consumer.getFiles());
    }
    
    static Commandline createCommandLine(final SvnScmProviderRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(SvnListCommand.TMP_DIR, repository);
        cl.createArg().setValue("list");
        if (recursive) {
            cl.createArg().setValue("--recursive");
        }
        if (version != null && StringUtils.isNotEmpty(version.getName()) && version instanceof ScmRevision) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(version.getName());
        }
        for (final File file : fileSet.getFileList()) {
            cl.createArg().setValue(repository.getUrl() + "/" + file.getPath().replace('\\', '/'));
        }
        return cl;
    }
    
    static {
        TMP_DIR = new File(System.getProperty("java.io.tmpdir"));
    }
}
