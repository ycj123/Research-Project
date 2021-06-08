// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git;

import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import java.util.ArrayList;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.ScmException;
import java.util.List;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public abstract class AbstractGitScmProvider extends AbstractScmProvider
{
    @Override
    public String getScmSpecificFilename() {
        return ".git";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        try {
            final ScmUrlParserResult result = this.parseScmUrl(scmSpecificUrl, delimiter);
            if (result.messages.size() > 0) {
                throw new ScmRepositoryException("The scm url " + scmSpecificUrl + " is invalid.", result.messages);
            }
            return result.repository;
        }
        catch (ScmException e) {
            throw new ScmRepositoryException("Error creating the scm repository", e);
        }
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        if (path == null) {
            throw new NullPointerException("Path argument is null");
        }
        if (!path.isDirectory()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a valid directory.");
        }
        if (!new File(path, ".git").exists()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a git checkout directory.");
        }
        try {
            return this.makeProviderScmRepository(this.getRepositoryURL(path), ':');
        }
        catch (ScmException e) {
            throw new ScmRepositoryException("Error creating the scm repository", e);
        }
    }
    
    protected abstract String getRepositoryURL(final File p0) throws ScmException;
    
    @Override
    public List<String> validateScmUrl(final String scmSpecificUrl, final char delimiter) {
        List<String> messages = new ArrayList<String>();
        try {
            this.makeProviderScmRepository(scmSpecificUrl, delimiter);
        }
        catch (ScmRepositoryException e) {
            messages = e.getValidationMessages();
        }
        return messages;
    }
    
    public String getScmType() {
        return "git";
    }
    
    private ScmUrlParserResult parseScmUrl(final String scmSpecificUrl, final char delimiter) throws ScmException {
        final ScmUrlParserResult result = new ScmUrlParserResult();
        result.repository = new GitScmProviderRepository(scmSpecificUrl);
        return result;
    }
    
    protected abstract GitCommand getAddCommand();
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (AddScmResult)this.executeCommand(this.getAddCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getBranchCommand();
    
    @Override
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (BranchScmResult)this.executeCommand(this.getBranchCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getChangeLogCommand();
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ChangeLogScmResult)this.executeCommand(this.getChangeLogCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getCheckInCommand();
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckInScmResult)this.executeCommand(this.getCheckInCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getCheckOutCommand();
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckOutScmResult)this.executeCommand(this.getCheckOutCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getDiffCommand();
    
    public DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (DiffScmResult)this.executeCommand(this.getDiffCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getExportCommand();
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ExportScmResult)this.executeCommand(this.getExportCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getRemoveCommand();
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (RemoveScmResult)this.executeCommand(this.getRemoveCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getStatusCommand();
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (StatusScmResult)this.executeCommand(this.getStatusCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getTagCommand();
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (TagScmResult)this.executeCommand(this.getTagCommand(), repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getUpdateCommand();
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (UpdateScmResult)this.executeCommand(this.getUpdateCommand(), repository, fileSet, parameters);
    }
    
    protected ScmResult executeCommand(final GitCommand command, final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getListCommand();
    
    public ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitCommand cmd = this.getListCommand();
        return (ListScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getInfoCommand();
    
    @Override
    public InfoScmResult info(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitCommand cmd = this.getInfoCommand();
        return (InfoScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitCommand cmd = this.getBlameCommand();
        return (BlameScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getBlameCommand();
    
    @Override
    public RemoteInfoScmResult remoteInfo(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitCommand cmd = this.getRemoteInfoCommand();
        return (RemoteInfoScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract GitCommand getRemoteInfoCommand();
    
    private static class ScmUrlParserResult
    {
        private List<String> messages;
        private ScmProviderRepository repository;
        
        private ScmUrlParserResult() {
            this.messages = new ArrayList<String>();
        }
    }
}
