// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class TfsCheckOutCommand extends AbstractCheckOutCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository r, final ScmFileSet f, final ScmVersion v, final boolean recursive) throws ScmException {
        final TfsScmProviderRepository tfsRepo = (TfsScmProviderRepository)r;
        final String url = tfsRepo.getServerPath();
        final String tfsUrl = tfsRepo.getTfsUrl();
        final String workspace = tfsRepo.getWorkspace();
        final boolean workspaceProvided = workspace != null && !workspace.trim().equals("");
        if (workspaceProvided) {
            this.createWorkspace(r, f, workspace, tfsUrl);
        }
        if (workspaceProvided) {
            this.executeUnmapCommand(r, f);
        }
        final ErrorStreamConsumer out = new ErrorStreamConsumer();
        ErrorStreamConsumer err = new ErrorStreamConsumer();
        if (workspaceProvided) {
            final TfsCommand command = new TfsCommand("workfold", r, null, this.getLogger());
            command.addArgument("-workspace:" + workspace);
            command.addArgument("-map");
            command.addArgument(url);
            command.addArgument(f.getBasedir().getAbsolutePath());
            final int status = command.execute(out, err);
            if (status != 0 || err.hasBeenFed()) {
                return new CheckOutScmResult(command.getCommandString(), "Error code for TFS checkout (workfold map) command - " + status, err.getOutput(), false);
            }
        }
        final FileListConsumer fileConsumer = new FileListConsumer();
        err = new ErrorStreamConsumer();
        final TfsCommand command = this.createGetCommand(r, f, v, recursive);
        final int status = command.execute(fileConsumer, err);
        if (status != 0 || err.hasBeenFed()) {
            return new CheckOutScmResult(command.getCommandString(), "Error code for TFS checkout (get) command - " + status, err.getOutput(), false);
        }
        return new CheckOutScmResult(command.getCommandString(), fileConsumer.getFiles());
    }
    
    public TfsCommand createGetCommand(final ScmProviderRepository r, final ScmFileSet f, final ScmVersion v, final boolean recursive) {
        final TfsCommand command = new TfsCommand("get", r, f, this.getLogger());
        if (recursive) {
            command.addArgument("-recursive");
        }
        command.addArgument("-force");
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
        command.addArgument(f.getBasedir().getAbsolutePath());
        return command;
    }
    
    public int executeUnmapCommand(final ScmProviderRepository r, final ScmFileSet f) throws ScmException {
        final TfsScmProviderRepository tfsRepo = (TfsScmProviderRepository)r;
        final String url = tfsRepo.getServerPath();
        final String workspace = tfsRepo.getWorkspace();
        final ErrorStreamConsumer out = new ErrorStreamConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final TfsCommand command = new TfsCommand("workfold", r, null, this.getLogger());
        command.addArgument("-workspace:" + workspace);
        command.addArgument("-unmap");
        command.addArgument(url);
        return command.execute(out, err);
    }
    
    private void createWorkspace(final ScmProviderRepository r, final ScmFileSet f, final String workspace, final String url) throws ScmException {
        final ErrorStreamConsumer out = new ErrorStreamConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final TfsCommand command = new TfsCommand("workspace", r, null, this.getLogger());
        command.addArgument("-new");
        command.addArgument("-comment:Creating workspace for maven command");
        command.addArgument("-server:" + url);
        command.addArgument(workspace);
        command.execute(out, err);
    }
}
