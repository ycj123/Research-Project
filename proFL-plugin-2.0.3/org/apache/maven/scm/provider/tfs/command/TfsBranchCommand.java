// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import org.apache.maven.scm.ScmException;
import java.util.List;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class TfsBranchCommand extends AbstractBranchCommand
{
    @Override
    protected ScmResult executeBranchCommand(final ScmProviderRepository r, final ScmFileSet f, final String branch, final String message) throws ScmException {
        final TfsCommand command = this.createCommand(r, f, branch);
        final CommandLineUtils.StringStreamConsumer out = new CommandLineUtils.StringStreamConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final int status = command.execute(out, err);
        this.getLogger().info("status of branch command is= " + status + "; err= " + err.getOutput());
        if (status != 0 || err.hasBeenFed()) {
            return new BranchScmResult(command.getCommandString(), "Error code for TFS branch command - " + status, err.getOutput(), false);
        }
        return new BranchScmResult(command.getCommandString(), new ArrayList<ScmFile>(0));
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final String branch) {
        final TfsCommand command = new TfsCommand("branch", r, f, this.getLogger());
        final String serverPath = ((TfsScmProviderRepository)r).getServerPath();
        command.addArgument(serverPath);
        command.addArgument("-checkin");
        command.addArgument(branch);
        return command;
    }
}
