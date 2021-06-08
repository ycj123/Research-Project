// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar;

import org.apache.maven.scm.provider.bazaar.command.blame.BazaarBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.bazaar.command.update.BazaarUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.bazaar.command.tag.BazaarTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.bazaar.command.status.BazaarStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.bazaar.command.remove.BazaarRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.bazaar.command.diff.BazaarDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.bazaar.command.checkout.BazaarCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.bazaar.command.checkin.BazaarCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.bazaar.command.changelog.BazaarChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.provider.bazaar.command.add.BazaarAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.bazaar.repository.BazaarScmProviderRepository;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class BazaarScmProvider extends AbstractScmProvider
{
    @Override
    public String getScmSpecificFilename() {
        return ".bzr";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        return new BazaarScmProviderRepository(scmSpecificUrl);
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        if (path == null || !path.isDirectory()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a valid directory.");
        }
        final File bzrDir = new File(path, ".bzr");
        if (!bzrDir.exists()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a bazaar directory.");
        }
        return this.makeProviderScmRepository("file:///" + path.getAbsolutePath(), ':');
    }
    
    @Override
    public List<String> validateScmUrl(final String scmSpecificUrl, final char delimiter) {
        final List<String> errorMessages = new ArrayList<String>();
        final String[] checkCmd = { "check", scmSpecificUrl };
        try {
            final File tmpDir = new File(System.getProperty("java.io.tmpdir"));
            final ScmResult result = BazaarUtils.execute(tmpDir, checkCmd);
            if (!result.isSuccess()) {
                errorMessages.add(result.getCommandOutput());
                errorMessages.add(result.getProviderMessage());
            }
        }
        catch (ScmException e) {
            errorMessages.add(e.getMessage());
        }
        return errorMessages;
    }
    
    public String getScmType() {
        return "bazaar";
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarAddCommand command = new BazaarAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarChangeLogCommand command = new BazaarChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarCheckInCommand command = new BazaarCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarCheckOutCommand command = new BazaarCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarDiffCommand command = new BazaarDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarRemoveCommand command = new BazaarRemoveCommand();
        command.setLogger(this.getLogger());
        return (RemoveScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarStatusCommand command = new BazaarStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarTagCommand command = new BazaarTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarUpdateCommand command = new BazaarUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final BazaarBlameCommand command = new BazaarBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, parameters);
    }
}
