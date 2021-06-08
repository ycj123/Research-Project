// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class TfsCheckInCommand extends AbstractCheckInCommand
{
    private static final String TFS_CHECKIN_POLICIES_ERROR = "TF10139";
    
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository r, final ScmFileSet f, final String m, final ScmVersion v) throws ScmException {
        final TfsCommand command = this.createCommand(r, f, m);
        final FileListConsumer fileConsumer = new FileListConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final int status = command.execute(fileConsumer, err);
        this.getLogger().debug("status of checkin command is= " + status + "; err= " + err.getOutput());
        if (err.hasBeenFed() && err.getOutput().startsWith("TF10139")) {
            this.getLogger().debug("exclusion: got error TF10139 due to checkin policies. Ignoring it...");
        }
        if (status != 0 || (err.hasBeenFed() && !err.getOutput().startsWith("TF10139"))) {
            this.getLogger().error("ERROR in command: " + command.getCommandString() + "; Error code for TFS checkin command - " + status);
            return new CheckInScmResult(command.getCommandString(), "Error code for TFS checkin command - " + status, err.getOutput(), false);
        }
        return new CheckInScmResult(command.getCommandString(), fileConsumer.getFiles());
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final String m) {
        final TfsCommand command = new TfsCommand("checkin", r, f, this.getLogger());
        command.addArgument("-noprompt");
        if (StringUtils.isNotBlank(m)) {
            command.addArgument("-comment:" + m);
        }
        command.addArgument(f);
        final TfsScmProviderRepository tfsScmProviderRepo = (TfsScmProviderRepository)r;
        if (tfsScmProviderRepo.isUseCheckinPolicies()) {
            command.addArgument("/override:checkin_policy");
        }
        return command;
    }
}
