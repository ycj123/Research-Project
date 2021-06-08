// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ServerFileListConsumer;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class TfsListCommand extends AbstractListCommand
{
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository r, final ScmFileSet f, final boolean recursive, final ScmVersion v) throws ScmException {
        final FileListConsumer out = new ServerFileListConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final TfsCommand command = this.createCommand(r, f, recursive);
        final int status = command.execute(out, err);
        if (status != 0 || err.hasBeenFed()) {
            return new ListScmResult(command.getCommandString(), "Error code for TFS list command - " + status, err.getOutput(), false);
        }
        return new ListScmResult(command.getCommandString(), out.getFiles());
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final boolean recursive) {
        final TfsCommand command = new TfsCommand("dir", r, f, this.getLogger());
        if (recursive) {
            command.addArgument("-recursive");
        }
        command.addArgument(f);
        return command;
    }
}
