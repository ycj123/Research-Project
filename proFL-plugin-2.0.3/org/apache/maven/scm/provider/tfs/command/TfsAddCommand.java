// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class TfsAddCommand extends AbstractAddCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository r, final ScmFileSet f, final String m, final boolean b) throws ScmException {
        final TfsCommand command = this.createCommand(r, f);
        final FileListConsumer fileConsumer = new FileListConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final int status = command.execute(fileConsumer, err);
        if (status != 0 || err.hasBeenFed()) {
            return new AddScmResult(command.getCommandString(), "Error code for TFS add command - " + status, err.getOutput(), false);
        }
        return new AddScmResult(command.getCommandString(), fileConsumer.getFiles());
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f) {
        final TfsCommand command = new TfsCommand("add", r, f, this.getLogger());
        command.addArgument(f);
        return command;
    }
}
