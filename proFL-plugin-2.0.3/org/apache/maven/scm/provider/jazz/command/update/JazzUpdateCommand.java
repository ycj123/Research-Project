// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.update;

import org.apache.maven.scm.provider.jazz.command.changelog.JazzChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.apache.maven.scm.ScmFile;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class JazzUpdateCommand extends AbstractUpdateCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing update command...");
        }
        final JazzUpdateConsumer updateConsumer = new JazzUpdateConsumer(repo, this.getLogger());
        final ErrorConsumer err = new ErrorConsumer(this.getLogger());
        final JazzScmCommand updateCmd = this.createAcceptCommand(repo, fileSet);
        final int status = updateCmd.execute(updateConsumer, err);
        if (status != 0 || err.hasBeenFed()) {
            return new UpdateScmResult(updateCmd.getCommandString(), "Error code for Jazz SCM update command - " + status, err.getOutput(), false);
        }
        if (this.getLogger().isDebugEnabled()) {
            if (!updateConsumer.getUpdatedFiles().isEmpty()) {
                this.getLogger().debug("Iterating over \"Update\" results");
                for (final ScmFile file : updateConsumer.getUpdatedFiles()) {
                    this.getLogger().debug(file.getPath() + " : " + file.getStatus());
                }
            }
            else {
                this.getLogger().debug("There are no updated files");
            }
        }
        return new UpdateScmResult(updateCmd.getCommandString(), updateConsumer.getUpdatedFiles());
    }
    
    public JazzScmCommand createAcceptCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("accept", repo, fileSet, this.getLogger());
        command.addArgument("--flow-components");
        return command;
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final JazzChangeLogCommand command = new JazzChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
