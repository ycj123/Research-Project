// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.tag;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.DebugLoggerConsumer;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class JazzTagCommand extends AbstractTagCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing tag command...");
        }
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        this.getLogger().debug("Creating Snapshot...");
        final StreamConsumer tagConsumer = new DebugLoggerConsumer(this.getLogger());
        ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand tagCreateSnapshotCmd = this.createTagCreateSnapshotCommand(jazzRepo, fileSet, tag, scmTagParameters);
        int status = tagCreateSnapshotCmd.execute(tagConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new TagScmResult(tagCreateSnapshotCmd.getCommandString(), "Error code for Jazz SCM tag (SNAPSHOT) command - " + status, errConsumer.getOutput(), false);
        }
        this.getLogger().debug("Creating Workspace from Snapshot...");
        final JazzScmCommand tagCreateWorkspaceCmd = this.createTagCreateWorkspaceCommand(jazzRepo, fileSet, tag);
        errConsumer = new ErrorConsumer(this.getLogger());
        status = tagCreateWorkspaceCmd.execute(tagConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new TagScmResult(tagCreateWorkspaceCmd.getCommandString(), "Error code for Jazz SCM tag (WORKSPACE) command - " + status, errConsumer.getOutput(), false);
        }
        if (jazzRepo.isPushChangesAndHaveFlowTargets()) {
            this.getLogger().debug("Promoting and delivering...");
            this.getLogger().debug("Delivering...");
            final JazzScmCommand tagDeliverCommand = this.createTagDeliverCommand(jazzRepo, fileSet, tag);
            errConsumer = new ErrorConsumer(this.getLogger());
            status = tagDeliverCommand.execute(tagConsumer, errConsumer);
            if (status != 0 || errConsumer.hasBeenFed()) {
                return new TagScmResult(tagDeliverCommand.getCommandString(), "Error code for Jazz SCM deliver command - " + status, errConsumer.getOutput(), false);
            }
            this.getLogger().debug("Promoting snapshot...");
            final JazzScmCommand tagSnapshotPromoteCommand = this.createTagSnapshotPromoteCommand(jazzRepo, fileSet, tag);
            errConsumer = new ErrorConsumer(this.getLogger());
            status = tagSnapshotPromoteCommand.execute(tagConsumer, errConsumer);
            if (status != 0 || errConsumer.hasBeenFed()) {
                return new TagScmResult(tagSnapshotPromoteCommand.getCommandString(), "Error code for Jazz SCM snapshot promote command - " + status, errConsumer.getOutput(), false);
            }
        }
        final List<ScmFile> taggedFiles = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File f : fileSet.getFileList()) {
            taggedFiles.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
        }
        return new TagScmResult(tagCreateSnapshotCmd.getCommandString(), taggedFiles);
    }
    
    public JazzScmCommand createTagCreateSnapshotCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) {
        final JazzScmCommand command = new JazzScmCommand("create", "snapshot", repo, fileSet, this.getLogger());
        if (tag != null && !tag.trim().equals("")) {
            command.addArgument("--name");
            command.addArgument(tag);
        }
        final String message = scmTagParameters.getMessage();
        if (message != null && !message.trim().equals("")) {
            command.addArgument("--description");
            command.addArgument(message);
        }
        command.addArgument(repo.getRepositoryWorkspace());
        return command;
    }
    
    public JazzScmCommand createTagSnapshotPromoteCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final String tag) {
        final JazzScmCommand command = new JazzScmCommand("snapshot", "promote", repo, fileSet, this.getLogger());
        if (repo.getFlowTarget() != null && !repo.getFlowTarget().equals("")) {
            command.addArgument(repo.getFlowTarget());
        }
        if (tag != null && !tag.trim().equals("")) {
            command.addArgument(tag);
        }
        return command;
    }
    
    public JazzScmCommand createTagDeliverCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final String tag) {
        final JazzScmCommand command = new JazzScmCommand("deliver", repo, fileSet, this.getLogger());
        if (repo.getWorkspace() != null && !repo.getWorkspace().equals("")) {
            command.addArgument("--source");
            command.addArgument(tag);
        }
        if (repo.getFlowTarget() != null && !repo.getFlowTarget().equals("")) {
            command.addArgument("--target");
            command.addArgument(repo.getFlowTarget());
        }
        return command;
    }
    
    public JazzScmCommand createTagCreateWorkspaceCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final String tag) {
        final JazzScmCommand command = new JazzScmCommand("create", "workspace", repo, fileSet, this.getLogger());
        if (tag != null && !tag.trim().equals("")) {
            command.addArgument(tag);
            command.addArgument("--snapshot");
            command.addArgument(tag);
        }
        return command;
    }
}
