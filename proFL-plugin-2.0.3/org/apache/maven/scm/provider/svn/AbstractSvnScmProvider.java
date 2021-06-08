// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn;

import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.blame.BlameScmResult;
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
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.svn.util.SvnUtil;
import java.util.ArrayList;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.util.Iterator;
import org.apache.maven.scm.command.info.InfoItem;
import org.apache.maven.scm.command.info.InfoScmResult;
import java.util.List;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public abstract class AbstractSvnScmProvider extends AbstractScmProvider
{
    private static final String CHECK_WORKING_DIRECTORY_URL = "scmCheckWorkingDirectoryUrl";
    
    @Override
    public String getScmSpecificFilename() {
        return ".svn";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        final ScmUrlParserResult result = this.parseScmUrl(scmSpecificUrl);
        if (this.checkWorkingDirectoryUrl()) {
            this.getLogger().debug("Checking svn info 'URL:' field matches current sources directory");
            try {
                final InfoScmResult info = this.info(result.repository, new ScmFileSet(new File(".")), new CommandParameters());
                final String url = this.findUrlInfoItem(info);
                if (url != null && !url.equals(scmSpecificUrl)) {
                    result.messages.add("The scm url does not match the value returned by svn info");
                }
            }
            catch (ScmException e) {
                throw new ScmRepositoryException("An error occurred while trying to svn info", e);
            }
        }
        if (result.messages.size() > 0) {
            throw new ScmRepositoryException("The scm url is invalid.", result.messages);
        }
        return result.repository;
    }
    
    private boolean checkWorkingDirectoryUrl() {
        return Boolean.getBoolean("scmCheckWorkingDirectoryUrl");
    }
    
    private String findUrlInfoItem(final InfoScmResult infoScmResult) {
        for (final InfoItem infoItem : infoScmResult.getInfoItems()) {
            if (infoItem.getURL() != null) {
                this.getLogger().debug("URL found: " + infoItem.getURL());
                return infoItem.getURL();
            }
        }
        return null;
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        if (path == null) {
            throw new NullPointerException("Path argument is null");
        }
        if (!path.isDirectory()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a valid directory.");
        }
        if (!new File(path, ".svn").exists()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a svn checkout directory.");
        }
        try {
            return this.makeProviderScmRepository(this.getRepositoryURL(path), ':');
        }
        catch (ScmException e) {
            throw new ScmRepositoryException("Error executing info command", e);
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
        return "svn";
    }
    
    private ScmUrlParserResult parseScmUrl(final String scmSpecificUrl) {
        final ScmUrlParserResult result = new ScmUrlParserResult();
        final String url = scmSpecificUrl;
        if (url.startsWith("file")) {
            if (!url.startsWith("file://")) {
                result.messages.add("A svn 'file' url must be on the form 'file://[hostname]/'.");
                return result;
            }
        }
        else if (url.startsWith("https")) {
            if (!url.startsWith("https://")) {
                result.messages.add("A svn 'http' url must be on the form 'https://'.");
                return result;
            }
        }
        else if (url.startsWith("http")) {
            if (!url.startsWith("http://")) {
                result.messages.add("A svn 'http' url must be on the form 'http://'.");
                return result;
            }
        }
        else if (url.startsWith("svn+")) {
            if (url.indexOf("://") < 0) {
                result.messages.add("A svn 'svn+xxx' url must be on the form 'svn+xxx://'.");
                return result;
            }
            final String tunnel = url.substring("svn+".length(), url.indexOf("://"));
            if (!"ssh".equals(tunnel)) {
                final SvnConfigFileReader reader = new SvnConfigFileReader();
                if (SvnUtil.getSettings().getConfigDirectory() != null) {
                    reader.setConfigDirectory(new File(SvnUtil.getSettings().getConfigDirectory()));
                }
                if (StringUtils.isEmpty(reader.getProperty("tunnels", tunnel))) {
                    result.messages.add("The tunnel '" + tunnel + "' isn't defined in your subversion configuration file.");
                    return result;
                }
            }
        }
        else {
            if (!url.startsWith("svn")) {
                result.messages.add(url + " url isn't a valid svn URL.");
                return result;
            }
            if (!url.startsWith("svn://")) {
                result.messages.add("A svn 'svn' url must be on the form 'svn://'.");
                return result;
            }
        }
        result.repository = new SvnScmProviderRepository(url);
        return result;
    }
    
    protected abstract SvnCommand getAddCommand();
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (AddScmResult)this.executeCommand(this.getAddCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getBranchCommand();
    
    @Override
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (BranchScmResult)this.executeCommand(this.getBranchCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getChangeLogCommand();
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ChangeLogScmResult)this.executeCommand(this.getChangeLogCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getCheckInCommand();
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckInScmResult)this.executeCommand(this.getCheckInCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getCheckOutCommand();
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckOutScmResult)this.executeCommand(this.getCheckOutCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getDiffCommand();
    
    public DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (DiffScmResult)this.executeCommand(this.getDiffCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getExportCommand();
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ExportScmResult)this.executeCommand(this.getExportCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getRemoveCommand();
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (RemoveScmResult)this.executeCommand(this.getRemoveCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getStatusCommand();
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (StatusScmResult)this.executeCommand(this.getStatusCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getTagCommand();
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (TagScmResult)this.executeCommand(this.getTagCommand(), repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getUpdateCommand();
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (UpdateScmResult)this.executeCommand(this.getUpdateCommand(), repository, fileSet, parameters);
    }
    
    protected ScmResult executeCommand(final SvnCommand command, final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getListCommand();
    
    public ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final SvnCommand cmd = this.getListCommand();
        return (ListScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getInfoCommand();
    
    @Override
    public InfoScmResult info(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final SvnCommand cmd = this.getInfoCommand();
        return (InfoScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final SvnCommand cmd = this.getBlameCommand();
        return (BlameScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getBlameCommand();
    
    public MkdirScmResult mkdir(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final SvnCommand cmd = this.getMkdirCommand();
        return (MkdirScmResult)this.executeCommand(cmd, repository, fileSet, parameters);
    }
    
    protected abstract SvnCommand getMkdirCommand();
    
    public abstract boolean remoteUrlExist(final ScmProviderRepository p0, final CommandParameters p1) throws ScmException;
    
    private static class ScmUrlParserResult
    {
        private List<String> messages;
        private ScmProviderRepository repository;
        
        private ScmUrlParserResult() {
            this.messages = new ArrayList<String>();
        }
    }
}
