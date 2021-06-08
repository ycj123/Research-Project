// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.status;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.apache.maven.scm.ScmFile;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class JazzStatusCommand extends AbstractStatusCommand
{
    public StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing status command...");
        }
        final JazzStatusConsumer statusConsumer = new JazzStatusConsumer(repo, this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand statusCmd = this.createStatusCommand(repo, fileSet);
        final int status = statusCmd.execute(statusConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new StatusScmResult(statusCmd.getCommandString(), "Error code for Jazz SCM status command - " + status, errConsumer.getOutput(), false);
        }
        if (this.getLogger().isDebugEnabled()) {
            if (!statusConsumer.getChangedFiles().isEmpty()) {
                this.getLogger().debug("Iterating over \"Status\" results");
                for (final ScmFile file : statusConsumer.getChangedFiles()) {
                    this.getLogger().debug(file.getPath() + " : " + file.getStatus());
                }
            }
            else {
                this.getLogger().debug("There are no differences");
            }
        }
        return new StatusScmResult(statusCmd.getCommandString(), statusConsumer.getChangedFiles());
    }
    
    public JazzScmCommand createStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("status", null, repo, false, fileSet, this.getLogger());
        command.addArgument("--wide");
        return command;
    }
}
