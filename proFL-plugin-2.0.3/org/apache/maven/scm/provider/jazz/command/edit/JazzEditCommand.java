// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.edit;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.DebugLoggerConsumer;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class JazzEditCommand extends AbstractEditCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing edit command...");
        }
        final DebugLoggerConsumer editConsumer = new DebugLoggerConsumer(this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand editCmd = this.createEditCommand(repo, fileSet);
        final int status = editCmd.execute(editConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new EditScmResult(editCmd.getCommandString(), "Error code for Jazz SCM edit command - " + status, errConsumer.getOutput(), false);
        }
        return new EditScmResult(editCmd.getCommandString(), "Successfully Completed.", editConsumer.getOutput(), true);
    }
    
    protected JazzScmCommand createEditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("lock", "acquire", repo, fileSet, this.getLogger());
        final List<File> files = fileSet.getFileList();
        if (files != null && !files.isEmpty()) {
            for (final File file : files) {
                command.addArgument(file.getPath());
            }
        }
        else {
            command.addArgument(".");
        }
        return command;
    }
}
