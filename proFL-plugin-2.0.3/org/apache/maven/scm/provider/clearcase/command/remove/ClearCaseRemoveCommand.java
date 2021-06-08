// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.remove;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.clearcase.command.edit.ClearCaseEditCommand;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class ClearCaseRemoveCommand extends AbstractRemoveCommand implements ClearCaseCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet scmFileSet, final String string) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing remove command...");
        }
        final Commandline cl = createCommandLine(this.getLogger(), scmFileSet);
        final ClearCaseRemoveConsumer consumer = new ClearCaseRemoveConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            final Commandline checkoutCurrentDirCommandLine = ClearCaseEditCommand.createCheckoutCurrentDirCommandLine(scmFileSet);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + checkoutCurrentDirCommandLine.getWorkingDirectory().getAbsolutePath() + ">>" + checkoutCurrentDirCommandLine.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(checkoutCurrentDirCommandLine, new CommandLineUtils.StringStreamConsumer(), stderr);
            if (exitCode == 0) {
                if (this.getLogger().isDebugEnabled()) {
                    this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
                }
                exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
                if (exitCode == 0) {
                    final Commandline checkinCurrentDirCommandLine = ClearCaseEditCommand.createCheckinCurrentDirCommandLine(scmFileSet);
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug("Executing: " + checkinCurrentDirCommandLine.getWorkingDirectory().getAbsolutePath() + ">>" + checkinCurrentDirCommandLine.toString());
                    }
                    exitCode = CommandLineUtils.executeCommandLine(checkinCurrentDirCommandLine, new CommandLineUtils.StringStreamConsumer(), stderr);
                }
            }
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getRemovedFiles());
    }
    
    public static Commandline createCommandLine(final ScmLogger logger, final ScmFileSet scmFileSet) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("rmname");
        command.createArg().setValue("-nc");
        final List<File> files = scmFileSet.getFileList();
        for (final File file : files) {
            if (logger.isInfoEnabled()) {
                logger.info("Deleting file: " + file.getAbsolutePath());
            }
            command.createArg().setValue(file.getName());
        }
        return command;
    }
}
