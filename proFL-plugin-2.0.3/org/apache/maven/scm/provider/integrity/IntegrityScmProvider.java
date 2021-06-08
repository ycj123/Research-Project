// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity;

import org.apache.maven.scm.provider.integrity.command.unlock.IntegrityUnlockCommand;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.provider.integrity.command.lock.IntegrityLockCommand;
import org.apache.maven.scm.provider.integrity.command.fileinfo.IntegrityFileInfoCommand;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.integrity.command.mkdir.IntegrityMkdirCommand;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.provider.integrity.command.branch.IntegrityBranchCommand;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.provider.integrity.command.export.IntegrityExportCommand;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.provider.integrity.command.list.IntegrityListCommand;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.provider.integrity.command.blame.IntegrityBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.integrity.command.update.IntegrityUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.integrity.command.unedit.IntegrityUnEditCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.provider.integrity.command.tag.IntegrityTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.integrity.command.status.IntegrityStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.integrity.command.edit.IntegrityEditCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.provider.integrity.command.diff.IntegrityDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.integrity.command.checkout.IntegrityCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.integrity.command.checkin.IntegrityCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.integrity.command.remove.IntegrityRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.integrity.command.add.IntegrityAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.provider.integrity.command.changelog.IntegrityChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.integrity.command.login.IntegrityLoginCommand;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class IntegrityScmProvider extends AbstractScmProvider
{
    public static final String INTEGRITY_CM_URL = "[[user][/pass]@host[:port]]|configPath";
    
    public String getScmType() {
        return "integrity";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        String hostName = "";
        int port = 0;
        String userName = "";
        String password = "";
        String configPath = "";
        final String[] tokens = StringUtils.split(scmSpecificUrl, String.valueOf(delimiter));
        if (tokens.length < 1 || tokens.length > 2) {
            throw new ScmRepositoryException("Invalid SCM URL '" + scmSpecificUrl + "'.  Expecting a url using format: " + "[[user][/pass]@host[:port]]|configPath");
        }
        if (tokens[0].indexOf(64) >= 0) {
            final String userPassStr = tokens[0].substring(0, tokens[0].indexOf(64));
            this.getLogger().debug("User/Password information supplied: " + userPassStr);
            final String hostPortStr = tokens[0].substring(tokens[0].indexOf(64) + 1, tokens[0].length());
            this.getLogger().debug("Host/Port information supplied: " + hostPortStr);
            if (userPassStr.length() > 0) {
                final int userPassDelimIndx = userPassStr.indexOf(47);
                if (userPassDelimIndx > 0) {
                    userName = userPassStr.substring(0, userPassStr.indexOf(47));
                    if (userPassStr.length() > userPassDelimIndx + 1) {
                        password = userPassStr.substring(userPassStr.indexOf(47) + 1, userPassStr.length());
                    }
                }
                else {
                    userName = userPassStr;
                }
            }
            if (hostPortStr.length() > 0) {
                final int hostPortDelimIndx = hostPortStr.indexOf(58);
                if (hostPortDelimIndx > 0) {
                    hostName = hostPortStr.substring(0, hostPortStr.indexOf(58));
                    if (hostPortStr.length() > hostPortDelimIndx + 1) {
                        port = Integer.parseInt(hostPortStr.substring(hostPortStr.indexOf(58) + 1, hostPortStr.length()));
                    }
                }
                else {
                    hostName = hostPortStr;
                }
            }
        }
        configPath = tokens[tokens.length - 1];
        return new IntegrityScmProviderRepository(hostName, port, userName, password, configPath, this.getLogger());
    }
    
    @Override
    protected LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityLoginCommand command = new IntegrityLoginCommand();
        command.setLogger(this.getLogger());
        return (LoginScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final IntegrityChangeLogCommand command = new IntegrityChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityAddCommand command = new IntegrityAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityRemoveCommand command = new IntegrityRemoveCommand();
        command.setLogger(this.getLogger());
        return (RemoveScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityCheckInCommand command = new IntegrityCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityCheckOutCommand command = new IntegrityCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityDiffCommand command = new IntegrityDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected EditScmResult edit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityEditCommand command = new IntegrityEditCommand();
        command.setLogger(this.getLogger());
        return (EditScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityStatusCommand command = new IntegrityStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityTagCommand command = new IntegrityTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected UnEditScmResult unedit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityUnEditCommand command = new IntegrityUnEditCommand();
        command.setLogger(this.getLogger());
        return (UnEditScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityUpdateCommand command = new IntegrityUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityBlameCommand command = new IntegrityBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityListCommand command = new IntegrityListCommand();
        command.setLogger(this.getLogger());
        return (ListScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityExportCommand command = new IntegrityExportCommand();
        command.setLogger(this.getLogger());
        return (ExportScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityBranchCommand command = new IntegrityBranchCommand();
        command.setLogger(this.getLogger());
        return (BranchScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected MkdirScmResult mkdir(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityMkdirCommand command = new IntegrityMkdirCommand();
        command.setLogger(this.getLogger());
        return (MkdirScmResult)command.execute(repository, fileSet, params);
    }
    
    protected ScmResult fileinfo(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityFileInfoCommand command = new IntegrityFileInfoCommand();
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, params);
    }
    
    protected ScmResult lock(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityLockCommand command = new IntegrityLockCommand();
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, params);
    }
    
    protected ScmResult unlock(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final IntegrityUnlockCommand command = new IntegrityUnlockCommand(params.getString(CommandParameter.FILE));
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, params);
    }
}
