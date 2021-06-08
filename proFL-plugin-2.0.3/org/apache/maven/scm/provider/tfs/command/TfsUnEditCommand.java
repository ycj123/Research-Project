// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.unedit.AbstractUnEditCommand;

public class TfsUnEditCommand extends AbstractUnEditCommand
{
    @Override
    protected ScmResult executeUnEditCommand(final ScmProviderRepository r, final ScmFileSet f) throws ScmException {
        final FileListConsumer out = new FileListConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final TfsCommand command = this.createCommand(r, f);
        final int status = command.execute(out, err);
        if (status != 0 || err.hasBeenFed()) {
            return new UnEditScmResult(command.getCommandString(), "Error code for TFS unedit command - " + status, err.getOutput(), false);
        }
        return new UnEditScmResult(command.getCommandString(), out.getFiles());
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f) {
        final TfsCommand command = new TfsCommand("undo", r, f, this.getLogger());
        command.addArgument(f);
        return command;
    }
}
