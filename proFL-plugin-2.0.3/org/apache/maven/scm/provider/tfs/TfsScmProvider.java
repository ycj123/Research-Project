// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs;

import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.tfs.command.blame.TfsBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsListCommand;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsBranchCommand;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsUnEditCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsEditCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.provider.tfs.command.TfsCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.tfs.command.TfsChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.ScmRepositoryException;
import java.net.URI;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class TfsScmProvider extends AbstractScmProvider
{
    public static final String TFS_URL_FORMAT = "[[domain\\]username[;password]@]http[s]://server_name[:port][:isCheckinPoliciesEnabled]:workspace:$/TeamProject/Path/To/Project";
    
    public String getScmType() {
        return "tfs";
    }
    
    @Override
    public boolean requiresEditMode() {
        return true;
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmUrl, final char delimiter) throws ScmRepositoryException {
        final int lastAtPos = scmUrl.lastIndexOf(64);
        this.getLogger().info("scmUrl - " + scmUrl);
        String tfsUrl = (lastAtPos < 0) ? scmUrl : scmUrl.substring(lastAtPos + 1);
        final String usernamePassword = (lastAtPos < 0) ? null : scmUrl.substring(0, lastAtPos);
        final int tfsPathPos = tfsUrl.lastIndexOf(delimiter + "$/");
        String serverPath = "$/";
        if (tfsPathPos > 0) {
            serverPath = tfsUrl.substring(tfsPathPos + 1);
            tfsUrl = tfsUrl.substring(0, tfsPathPos);
        }
        final int workspacePos = tfsUrl.lastIndexOf(delimiter);
        final String workspace = tfsUrl.substring(workspacePos + 1);
        tfsUrl = tfsUrl.substring(0, workspacePos);
        this.getLogger().info("workspace: " + workspace);
        final int checkinPoliciesPos = tfsUrl.lastIndexOf(delimiter);
        final String checkinPolicies = tfsUrl.substring(checkinPoliciesPos + 1);
        tfsUrl = tfsUrl.substring(0, checkinPoliciesPos);
        this.getLogger().info("checkinPolicies: " + checkinPolicies);
        try {
            final URI tfsUri = URI.create(tfsUrl);
            final String scheme = tfsUri.getScheme();
            this.getLogger().info("Scheme - " + scheme);
            if (scheme == null || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
                throw new ScmRepositoryException("TFS Url \"" + tfsUrl + "\" is not a valid URL. " + "The TFS Url syntax is " + "[[domain\\]username[;password]@]http[s]://server_name[:port][:isCheckinPoliciesEnabled]:workspace:$/TeamProject/Path/To/Project");
            }
        }
        catch (IllegalArgumentException e) {
            throw new ScmRepositoryException("TFS Url \"" + tfsUrl + "\" is not a valid URL. The TFS Url syntax is " + "[[domain\\]username[;password]@]http[s]://server_name[:port][:isCheckinPoliciesEnabled]:workspace:$/TeamProject/Path/To/Project");
        }
        String username = null;
        String password = null;
        if (usernamePassword != null) {
            final int delimPos = usernamePassword.indexOf(59);
            username = ((delimPos < 0) ? usernamePassword : usernamePassword.substring(0, delimPos));
            password = ((delimPos < 0) ? null : usernamePassword.substring(delimPos + 1));
        }
        final boolean useCheckinPolicies = Boolean.parseBoolean(checkinPolicies);
        return new TfsScmProviderRepository(tfsUrl, username, password, serverPath, workspace, useCheckinPolicies);
    }
    
    @Override
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsChangeLogCommand command = new TfsChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsCheckOutCommand command = new TfsCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected EditScmResult edit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsEditCommand command = new TfsEditCommand();
        command.setLogger(this.getLogger());
        return (EditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected UnEditScmResult unedit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsUnEditCommand command = new TfsUnEditCommand();
        command.setLogger(this.getLogger());
        return (UnEditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsStatusCommand command = new TfsStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsUpdateCommand command = new TfsUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsCheckInCommand command = new TfsCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsAddCommand command = new TfsAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsTagCommand command = new TfsTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsBranchCommand command = new TfsBranchCommand();
        command.setLogger(this.getLogger());
        return (BranchScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsListCommand command = new TfsListCommand();
        command.setLogger(this.getLogger());
        return (ListScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final TfsBlameCommand command = new TfsBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return super.diff(repository, fileSet, parameters);
    }
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return super.export(repository, fileSet, parameters);
    }
}
