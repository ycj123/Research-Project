// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.unedit;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.unedit.AbstractUnEditCommand;

public class ClearCaseUnEditCommand extends AbstractUnEditCommand implements ClearCaseCommand
{
    @Override
    protected ScmResult executeUnEditCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing unedit command...");
        }
        final Commandline cl = createCommandLine(this.getLogger(), fileSet);
        final ClearCaseUnEditConsumer consumer = new ClearCaseUnEditConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getUnEditFiles());
    }
    
    public static Commandline createCommandLine(final ScmLogger logger, final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("unco");
        command.createArg().setValue("-keep");
        final List<File> files = scmFileSet.getFileList();
        for (final File file : files) {
            command.createArg().setValue(file.getName());
        }
        return command;
    }
}
