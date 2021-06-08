// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class TfsUpdateCommand extends AbstractUpdateCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository r, final ScmFileSet f, final ScmVersion v) throws ScmException {
        final FileListConsumer fileConsumer = new FileListConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final TfsCommand command = this.createCommand(r, f, v);
        final int status = command.execute(fileConsumer, err);
        if (status != 0 || err.hasBeenFed()) {
            return new UpdateScmResult(command.getCommandString(), "Error code for TFS update command - " + status, err.getOutput(), false);
        }
        return new UpdateScmResult(command.getCommandString(), fileConsumer.getFiles());
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final ScmVersion v) {
        final String serverPath = ((TfsScmProviderRepository)r).getServerPath();
        final TfsCommand command = new TfsCommand("get", r, f, this.getLogger());
        command.addArgument(serverPath);
        if (v != null && !v.equals("")) {
            String vType = "";
            if (v.getType().equals("Tag")) {
                vType = "L";
            }
            if (v.getType().equals("Revision")) {
                vType = "C";
            }
            command.addArgument("-version:" + vType + v.getName());
        }
        return command;
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final TfsChangeLogCommand changeLogCommand = new TfsChangeLogCommand();
        changeLogCommand.setLogger(this.getLogger());
        return changeLogCommand;
    }
}
