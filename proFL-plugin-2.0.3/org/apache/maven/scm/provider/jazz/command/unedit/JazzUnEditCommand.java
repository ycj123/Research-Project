// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.unedit;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.DebugLoggerConsumer;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.unedit.AbstractUnEditCommand;

public class JazzUnEditCommand extends AbstractUnEditCommand
{
    @Override
    protected ScmResult executeUnEditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing unedit command...");
        }
        final DebugLoggerConsumer uneditConsumer = new DebugLoggerConsumer(this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand uneditCmd = this.createUneditCommand(repo, fileSet);
        final int status = uneditCmd.execute(uneditConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new UnEditScmResult(uneditCmd.getCommandString(), "Error code for Jazz SCM unedit command - " + status, errConsumer.getOutput(), false);
        }
        return new UnEditScmResult(uneditCmd.getCommandString(), "Successfully Completed.", uneditConsumer.getOutput(), true);
    }
    
    public JazzScmCommand createUneditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("lock", "release", repo, fileSet, this.getLogger());
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
