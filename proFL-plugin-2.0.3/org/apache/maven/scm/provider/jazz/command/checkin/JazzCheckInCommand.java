// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.checkin;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.provider.jazz.command.add.JazzAddCommand;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.DebugLoggerConsumer;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class JazzCheckInCommand extends AbstractCheckInCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final ScmVersion scmVersion) throws ScmException {
        if (scmVersion != null && StringUtils.isNotEmpty(scmVersion.getName())) {
            throw new ScmException("This provider command can't handle tags.");
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing checkin command...");
        }
        final JazzScmCommand createChangesetCmd = this.createCreateChangesetCommand(repository, fileSet, message);
        final DebugLoggerConsumer outputConsumer = new DebugLoggerConsumer(this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final int status = createChangesetCmd.execute(outputConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new CheckInScmResult(createChangesetCmd.getCommandString(), "Error code for Jazz SCM create changeset command - " + status, errConsumer.getOutput(), false);
        }
        return this.executeCheckInCommand(repository, fileSet, scmVersion);
    }
    
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        final JazzAddCommand addCommand = new JazzAddCommand();
        addCommand.setLogger(this.getLogger());
        final AddScmResult addResult = addCommand.executeAddCommand(repo, fileSet);
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        if (jazzRepo.isPushChangesAndHaveFlowTargets()) {
            final JazzScmCommand deliverCmd = this.createDeliverCommand((JazzScmProviderRepository)repo, fileSet);
            final StreamConsumer deliverConsumer = new DebugLoggerConsumer(this.getLogger());
            final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
            final int status = deliverCmd.execute(deliverConsumer, errConsumer);
            if (status != 0 || errConsumer.hasBeenFed()) {
                return new CheckInScmResult(deliverCmd.getCommandString(), "Error code for Jazz SCM deliver command - " + status, errConsumer.getOutput(), false);
            }
        }
        return new CheckInScmResult(addResult.getCommandLine(), addResult.getAddedFiles());
    }
    
    public JazzScmCommand createCreateChangesetCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message) {
        final JazzScmCommand command = new JazzScmCommand("create", "changeset", repo, false, fileSet, this.getLogger());
        command.addArgument(message);
        return command;
    }
    
    public JazzScmCommand createCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("checkin", null, repo, false, fileSet, this.getLogger());
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
    
    public JazzScmCommand createDeliverCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("deliver", repo, fileSet, this.getLogger());
        if (repo.getWorkspace() != null && !repo.getWorkspace().equals("")) {
            command.addArgument("--source");
            command.addArgument(repo.getWorkspace());
        }
        if (repo.getFlowTarget() != null && !repo.getFlowTarget().equals("")) {
            command.addArgument("--target");
            command.addArgument(repo.getFlowTarget());
        }
        command.addArgument("--overwrite-uncommitted");
        return command;
    }
}
