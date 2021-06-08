// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg;

import java.util.ArrayList;
import org.apache.maven.scm.provider.hg.command.info.HgInfoCommand;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.provider.hg.command.inventory.HgListCommand;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.provider.hg.command.branch.HgBranchCommand;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.provider.hg.command.blame.HgBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.hg.command.update.HgUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.hg.command.status.HgStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.hg.command.remove.HgRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.hg.command.diff.HgDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.hg.command.tag.HgTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.hg.command.checkout.HgCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.hg.command.checkin.HgCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.hg.command.changelog.HgChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.hg.command.add.HgAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import org.apache.maven.scm.provider.hg.repository.HgScmProviderRepository;
import java.io.File;
import java.util.List;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class HgScmProvider extends AbstractScmProvider
{
    @Override
    public String getScmSpecificFilename() {
        return ".hg";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        final HgUrlParserResult result = this.parseScmUrl(scmSpecificUrl);
        if (result.messages.size() > 0) {
            throw new ScmRepositoryException("The scm url is invalid.", result.messages);
        }
        return result.repository;
    }
    
    private HgUrlParserResult parseScmUrl(final String scmSpecificUrl) {
        final HgUrlParserResult result = new HgUrlParserResult();
        final String url = scmSpecificUrl;
        if (url.startsWith("file")) {
            if (!url.startsWith("file:///") && !url.startsWith("file://localhost/")) {
                result.messages.add("An hg 'file' url must be on the form 'file:///' or 'file://localhost/'.");
                return result;
            }
        }
        else if (url.startsWith("https")) {
            if (!url.startsWith("https://")) {
                result.messages.add("An hg 'http' url must be on the form 'https://'.");
                return result;
            }
        }
        else if (url.startsWith("http")) {
            if (!url.startsWith("http://")) {
                result.messages.add("An hg 'http' url must be on the form 'http://'.");
                return result;
            }
        }
        else {
            try {
                final File file = new File(url);
            }
            catch (Throwable e) {
                result.messages.add("The filename provided is not valid");
                return result;
            }
        }
        result.repository = new HgScmProviderRepository(url);
        return result;
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        if (path == null || !path.isDirectory()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a valid directory.");
        }
        final File hgDir = new File(path, ".hg");
        if (!hgDir.exists()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a hg directory.");
        }
        return this.makeProviderScmRepository(path.getAbsolutePath(), ':');
    }
    
    @Override
    public List<String> validateScmUrl(final String scmSpecificUrl, final char delimiter) {
        final HgUrlParserResult result = this.parseScmUrl(scmSpecificUrl);
        return result.messages;
    }
    
    public String getScmType() {
        return "hg";
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgAddCommand command = new HgAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgChangeLogCommand command = new HgChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgCheckInCommand command = new HgCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgCheckOutCommand command = new HgCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgTagCommand command = new HgTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgDiffCommand command = new HgDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgRemoveCommand command = new HgRemoveCommand();
        command.setLogger(this.getLogger());
        return (RemoveScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgStatusCommand command = new HgStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgUpdateCommand command = new HgUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgBlameCommand command = new HgBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgBranchCommand command = new HgBranchCommand();
        command.setLogger(this.getLogger());
        return (BranchScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgListCommand hgListCommand = new HgListCommand();
        hgListCommand.setLogger(this.getLogger());
        return (ListScmResult)hgListCommand.executeCommand(repository, fileSet, parameters);
    }
    
    @Override
    public InfoScmResult info(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final HgInfoCommand infoCommand = new HgInfoCommand();
        infoCommand.setLogger(this.getLogger());
        return (InfoScmResult)infoCommand.execute(repository, fileSet, parameters);
    }
    
    private static class HgUrlParserResult
    {
        private List<String> messages;
        private ScmProviderRepository repository;
        
        private HgUrlParserResult() {
            this.messages = new ArrayList<String>();
        }
    }
}
