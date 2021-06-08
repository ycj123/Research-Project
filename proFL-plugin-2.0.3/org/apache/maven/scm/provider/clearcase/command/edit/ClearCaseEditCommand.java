// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.edit;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class ClearCaseEditCommand extends AbstractEditCommand implements ClearCaseCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing edit command...");
        }
        final Commandline cl = createCommandLine(this.getLogger(), fileSet);
        final ClearCaseEditConsumer consumer = new ClearCaseEditConsumer(this.getLogger());
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
            return new EditScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new EditScmResult(cl.toString(), consumer.getEditFiles());
    }
    
    public static Commandline createCommandLine(final ScmLogger logger, final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("co");
        command.createArg().setValue("-nc");
        final List<File> files = scmFileSet.getFileList();
        for (final File file : files) {
            if (logger.isInfoEnabled()) {
                logger.info("edit file: " + file.getAbsolutePath());
            }
            command.createArg().setValue(file.getAbsolutePath());
        }
        return command;
    }
    
    public static Commandline createCheckoutCurrentDirCommandLine(final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("co");
        command.createArg().setValue("-nc");
        command.createArg().setValue(".");
        return command;
    }
    
    public static Commandline createCheckinCurrentDirCommandLine(final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("ci");
        command.createArg().setValue("-nc");
        command.createArg().setValue(".");
        return command;
    }
}
