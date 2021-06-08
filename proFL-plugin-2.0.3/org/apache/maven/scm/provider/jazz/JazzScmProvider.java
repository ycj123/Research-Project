// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz;

import org.apache.maven.scm.provider.jazz.command.unedit.JazzUnEditCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.provider.jazz.command.update.JazzUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.jazz.command.tag.JazzTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.jazz.command.list.JazzListCommand;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.provider.jazz.command.edit.JazzEditCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.provider.jazz.command.diff.JazzDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.jazz.command.checkout.JazzCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.jazz.command.checkin.JazzCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.jazz.command.changelog.JazzChangeLogCommand;
import org.apache.maven.scm.provider.jazz.command.status.JazzStatusCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.provider.jazz.command.blame.JazzBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.jazz.command.branch.JazzBranchCommand;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.add.JazzAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import java.net.URI;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class JazzScmProvider extends AbstractScmProvider
{
    public static final String JAZZ_URL_FORMAT = "scm:jazz:[username[;password]@]http[s]://server_name[:port]/contextRoot:repositoryWorkspace";
    
    public String getScmType() {
        return "jazz";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmUrl, final char delimiter) throws ScmRepositoryException {
        this.getLogger().debug("JazzScmProvider:makeProviderScmRepository");
        this.getLogger().debug("Provided scm url   - " + scmUrl);
        this.getLogger().debug("Provided delimiter - '" + delimiter + "'");
        String jazzUrlAndWorkspace = null;
        String usernameAndPassword = null;
        final int lastAtPosition = scmUrl.lastIndexOf(64);
        if (lastAtPosition == -1) {
            jazzUrlAndWorkspace = scmUrl;
        }
        else {
            jazzUrlAndWorkspace = ((lastAtPosition < 0) ? scmUrl : scmUrl.substring(lastAtPosition + 1));
            usernameAndPassword = ((lastAtPosition < 0) ? null : scmUrl.substring(0, lastAtPosition));
        }
        String username = null;
        String password = null;
        if (usernameAndPassword != null) {
            final int delimPosition = usernameAndPassword.indexOf(59);
            username = ((delimPosition >= 0) ? usernameAndPassword.substring(0, delimPosition) : usernameAndPassword);
            password = ((delimPosition >= 0) ? usernameAndPassword.substring(delimPosition + 1) : null);
        }
        int colonsCounted = 0;
        int colonIndex = 0;
        while (colonIndex != -1) {
            colonIndex = jazzUrlAndWorkspace.indexOf(":", colonIndex + 1);
            if (colonIndex != -1) {
                ++colonsCounted;
            }
        }
        final boolean havePort = colonsCounted == 3;
        final int repositoryWorkspacePosition = jazzUrlAndWorkspace.lastIndexOf(delimiter);
        final String repositoryWorkspace = jazzUrlAndWorkspace.substring(repositoryWorkspacePosition + 1);
        final String jazzUrl = jazzUrlAndWorkspace.substring(0, repositoryWorkspacePosition);
        try {
            final URI jazzUri = URI.create(jazzUrl);
            final String scheme = jazzUri.getScheme();
            this.getLogger().debug("Scheme - " + scheme);
            if (scheme == null || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
                throw new ScmRepositoryException("Jazz Url \"" + jazzUrl + "\" is not a valid URL. The Jazz Url syntax is " + "scm:jazz:[username[;password]@]http[s]://server_name[:port]/contextRoot:repositoryWorkspace");
            }
        }
        catch (IllegalArgumentException e) {
            throw new ScmRepositoryException("Jazz Url \"" + jazzUrl + "\" is not a valid URL. The Jazz Url syntax is " + "scm:jazz:[username[;password]@]http[s]://server_name[:port]/contextRoot:repositoryWorkspace");
        }
        String hostname = null;
        int port = 0;
        if (havePort) {
            final int protocolIndex = jazzUrl.indexOf(":") + 3;
            final int portIndex = jazzUrl.indexOf(":", protocolIndex + 1);
            hostname = jazzUrl.substring(protocolIndex, portIndex);
            final int pathIndex = jazzUrl.indexOf("/", portIndex + 1);
            final String portNo = jazzUrl.substring(portIndex + 1, pathIndex);
            try {
                port = Integer.parseInt(portNo);
            }
            catch (NumberFormatException nfe) {
                throw new ScmRepositoryException("Jazz Url \"" + jazzUrl + "\" is not a valid URL. The Jazz Url syntax is " + "scm:jazz:[username[;password]@]http[s]://server_name[:port]/contextRoot:repositoryWorkspace");
            }
        }
        else {
            final int protocolIndex = jazzUrl.indexOf(":") + 3;
            final int pathIndex2 = jazzUrl.indexOf("/", protocolIndex + 1);
            if (protocolIndex == -1 || pathIndex2 == -1) {
                throw new ScmRepositoryException("Jazz Url \"" + jazzUrl + "\" is not a valid URL. The Jazz Url syntax is " + "scm:jazz:[username[;password]@]http[s]://server_name[:port]/contextRoot:repositoryWorkspace");
            }
            hostname = jazzUrl.substring(protocolIndex, pathIndex2);
        }
        this.getLogger().debug("Creating JazzScmProviderRepository with the following values:");
        this.getLogger().debug("jazzUrl             - " + jazzUrl);
        this.getLogger().debug("username            - " + username);
        this.getLogger().debug("password            - " + password);
        this.getLogger().debug("hostname            - " + hostname);
        this.getLogger().debug("port                - " + port);
        this.getLogger().debug("repositoryWorkspace - " + repositoryWorkspace);
        return new JazzScmProviderRepository(jazzUrl, username, password, hostname, port, repositoryWorkspace);
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzAddCommand command = new JazzAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzBranchCommand command = new JazzBranchCommand();
        command.setLogger(this.getLogger());
        return (BranchScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzBlameCommand command = new JazzBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzStatusCommand statusCommand = new JazzStatusCommand();
        statusCommand.setLogger(this.getLogger());
        statusCommand.execute(repository, fileSet, parameters);
        final JazzChangeLogCommand command = new JazzChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzCheckInCommand command = new JazzCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzCheckOutCommand command = new JazzCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzDiffCommand command = new JazzDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected EditScmResult edit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzEditCommand command = new JazzEditCommand();
        command.setLogger(this.getLogger());
        return (EditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return super.export(repository, fileSet, parameters);
    }
    
    @Override
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzStatusCommand statusCommand = new JazzStatusCommand();
        statusCommand.setLogger(this.getLogger());
        statusCommand.execute(repository, fileSet, parameters);
        final JazzListCommand command = new JazzListCommand();
        command.setLogger(this.getLogger());
        return (ListScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzStatusCommand command = new JazzStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzStatusCommand statusCommand = new JazzStatusCommand();
        statusCommand.setLogger(this.getLogger());
        statusCommand.execute(repository, fileSet, parameters);
        final JazzTagCommand command = new JazzTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzUpdateCommand command = new JazzUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected UnEditScmResult unedit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final JazzUnEditCommand command = new JazzUnEditCommand();
        command.setLogger(this.getLogger());
        return (UnEditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    public String getScmSpecificFilename() {
        return ".jazz5";
    }
}
