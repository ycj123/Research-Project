// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import org.apache.maven.scm.ScmFile;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ChangedFileConsumer;
import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class TfsStatusCommand extends AbstractStatusCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository r, final ScmFileSet f) throws ScmException {
        final TfsScmProviderRepository tfsRepo = (TfsScmProviderRepository)r;
        final TfsCommand command = this.createCommand(tfsRepo, f);
        final ChangedFileConsumer out = new ChangedFileConsumer(this.getLogger());
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final int status = command.execute(out, err);
        if (status != 0 || err.hasBeenFed()) {
            return new StatusScmResult(command.getCommandString(), "Error code for TFS status command - " + status, err.getOutput(), false);
        }
        final Iterator<ScmFile> iter = out.getChangedFiles().iterator();
        this.getLogger().debug("Iterating");
        while (iter.hasNext()) {
            final ScmFile file = iter.next();
            this.getLogger().debug(file.getPath() + ":" + file.getStatus());
        }
        return new StatusScmResult(command.getCommandString(), out.getChangedFiles());
    }
    
    public TfsCommand createCommand(final TfsScmProviderRepository r, final ScmFileSet f) {
        final String url = r.getServerPath();
        final String workspace = r.getWorkspace();
        final TfsCommand command = new TfsCommand("status", r, f, this.getLogger());
        if (workspace != null && !workspace.trim().equals("")) {
            command.addArgument("-workspace:" + workspace);
        }
        command.addArgument("-recursive");
        command.addArgument("-format:detailed");
        command.addArgument(url);
        return command;
    }
}
