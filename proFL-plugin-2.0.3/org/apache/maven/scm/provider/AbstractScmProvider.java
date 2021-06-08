// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider;

import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.command.blame.BlameScmRequest;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmRevision;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import java.util.Date;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.NoSuchCommandScmException;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.log.ScmLogDispatcher;

public abstract class AbstractScmProvider implements ScmProvider
{
    private ScmLogDispatcher logDispatcher;
    
    public AbstractScmProvider() {
        this.logDispatcher = new ScmLogDispatcher();
    }
    
    public String getScmSpecificFilename() {
        return null;
    }
    
    public String sanitizeTagName(final String tag) {
        return tag;
    }
    
    public boolean validateTagName(final String tag) {
        return true;
    }
    
    public List<String> validateScmUrl(final String scmSpecificUrl, final char delimiter) {
        final List<String> messages = new ArrayList<String>();
        try {
            this.makeProviderScmRepository(scmSpecificUrl, delimiter);
        }
        catch (ScmRepositoryException e) {
            messages.add(e.getMessage());
        }
        return messages;
    }
    
    public boolean requiresEditMode() {
        return false;
    }
    
    public AddScmResult add(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.add(repository, fileSet, (String)null);
    }
    
    public AddScmResult add(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.MESSAGE, (message == null) ? "" : message);
        parameters.setString(CommandParameter.BINARY, "false");
        return this.add(repository.getProviderRepository(), fileSet, parameters);
    }
    
    public AddScmResult add(final ScmRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        this.login(repository, fileSet);
        parameters.setString(CommandParameter.BINARY, "false");
        return this.add(repository.getProviderRepository(), fileSet, parameters);
    }
    
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("add");
    }
    
    public BranchScmResult branch(final ScmRepository repository, final ScmFileSet fileSet, final String branchName) throws ScmException {
        return this.branch(repository, fileSet, branchName, new ScmBranchParameters());
    }
    
    public BranchScmResult branch(final ScmRepository repository, final ScmFileSet fileSet, final String branchName, final String message) throws ScmException {
        final ScmBranchParameters scmBranchParameters = new ScmBranchParameters();
        if (StringUtils.isNotEmpty(message)) {
            scmBranchParameters.setMessage(message);
        }
        return this.branch(repository, fileSet, branchName, scmBranchParameters);
    }
    
    public BranchScmResult branch(final ScmRepository repository, final ScmFileSet fileSet, final String branchName, final ScmBranchParameters scmBranchParameters) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.BRANCH_NAME, branchName);
        parameters.setScmBranchParameters(CommandParameter.SCM_BRANCH_PARAMETERS, scmBranchParameters);
        return this.branch(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("branch");
    }
    
    @Deprecated
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final String branch) throws ScmException {
        return this.changeLog(repository, fileSet, startDate, endDate, numDays, branch, null);
    }
    
    @Deprecated
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final String branch, final String datePattern) throws ScmException {
        ScmBranch scmBranch = null;
        if (StringUtils.isNotEmpty(branch)) {
            scmBranch = new ScmBranch(branch);
        }
        return this.changeLog(repository, fileSet, startDate, endDate, numDays, scmBranch, null);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final ScmBranch branch) throws ScmException {
        return this.changeLog(repository, fileSet, startDate, endDate, numDays, branch, null);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final ScmBranch branch, final String datePattern) throws ScmException {
        final ChangeLogScmRequest request = new ChangeLogScmRequest(repository, fileSet);
        request.setDateRange(startDate, endDate);
        request.setNumDays(numDays);
        request.setScmBranch(branch);
        request.setDatePattern(datePattern);
        return this.changeLog(request);
    }
    
    public ChangeLogScmResult changeLog(final ChangeLogScmRequest request) throws ScmException {
        final ScmRepository scmRepository = request.getScmRepository();
        final ScmFileSet scmFileSet = request.getScmFileSet();
        this.login(scmRepository, scmFileSet);
        return this.changelog(scmRepository.getProviderRepository(), scmFileSet, request.getCommandParameters());
    }
    
    @Deprecated
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final String startTag, final String endTag) throws ScmException {
        return this.changeLog(repository, fileSet, startTag, endTag, null);
    }
    
    @Deprecated
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final String startTag, final String endTag, final String datePattern) throws ScmException {
        ScmVersion startRevision = null;
        ScmVersion endRevision = null;
        if (StringUtils.isNotEmpty(startTag)) {
            startRevision = new ScmRevision(startTag);
        }
        if (StringUtils.isNotEmpty(endTag)) {
            endRevision = new ScmRevision(endTag);
        }
        return this.changeLog(repository, fileSet, startRevision, endRevision, null);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        return this.changeLog(repository, fileSet, startVersion, endVersion, null);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.START_SCM_VERSION, startVersion);
        parameters.setScmVersion(CommandParameter.END_SCM_VERSION, endVersion);
        parameters.setString(CommandParameter.CHANGELOG_DATE_PATTERN, datePattern);
        return this.changelog(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("changelog");
    }
    
    @Deprecated
    public CheckInScmResult checkIn(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        ScmVersion scmVersion = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmVersion = new ScmBranch(tag);
        }
        return this.checkIn(repository, fileSet, scmVersion, message);
    }
    
    public CheckInScmResult checkIn(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        return this.checkIn(repository, fileSet, (ScmVersion)null, message);
    }
    
    public CheckInScmResult checkIn(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final String message) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        parameters.setString(CommandParameter.MESSAGE, message);
        return this.checkin(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("checkin");
    }
    
    @Deprecated
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final String tag) throws ScmException {
        return this.checkOut(repository, fileSet, tag, true);
    }
    
    @Deprecated
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final boolean recursive) throws ScmException {
        ScmVersion scmVersion = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmVersion = new ScmBranch(tag);
        }
        return this.checkOut(repository, fileSet, scmVersion, recursive);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.checkOut(repository, fileSet, (ScmVersion)null, true);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        return this.checkOut(repository, fileSet, scmVersion, true);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final boolean recursive) throws ScmException {
        return this.checkOut(repository, fileSet, (ScmVersion)null, recursive);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final boolean recursive) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        parameters.setString(CommandParameter.RECURSIVE, Boolean.toString(recursive));
        return this.checkout(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("checkout");
    }
    
    @Deprecated
    public DiffScmResult diff(final ScmRepository repository, final ScmFileSet fileSet, final String startRevision, final String endRevision) throws ScmException {
        ScmVersion startVersion = null;
        ScmVersion endVersion = null;
        if (StringUtils.isNotEmpty(startRevision)) {
            startVersion = new ScmRevision(startRevision);
        }
        if (StringUtils.isNotEmpty(endRevision)) {
            endVersion = new ScmRevision(endRevision);
        }
        return this.diff(repository, fileSet, startVersion, endVersion);
    }
    
    public DiffScmResult diff(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.START_SCM_VERSION, startVersion);
        parameters.setScmVersion(CommandParameter.END_SCM_VERSION, endVersion);
        return this.diff(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("diff");
    }
    
    public EditScmResult edit(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        return this.edit(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected EditScmResult edit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (this.getLogger().isWarnEnabled()) {
            this.getLogger().warn("Provider " + this.getScmType() + " does not support edit operation.");
        }
        return new EditScmResult("", null, null, true);
    }
    
    @Deprecated
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final String tag) throws ScmException {
        return this.export(repository, fileSet, tag, null);
    }
    
    @Deprecated
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final String outputDirectory) throws ScmException {
        ScmVersion scmVersion = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmVersion = new ScmRevision(tag);
        }
        return this.export(repository, fileSet, scmVersion, outputDirectory);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.export(repository, fileSet, (ScmVersion)null, null);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        return this.export(repository, fileSet, scmVersion, null);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final String outputDirectory) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        parameters.setString(CommandParameter.OUTPUT_DIRECTORY, outputDirectory);
        return this.export(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("export");
    }
    
    public ListScmResult list(final ScmRepository repository, final ScmFileSet fileSet, final boolean recursive, final String tag) throws ScmException {
        ScmVersion scmVersion = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmVersion = new ScmRevision(tag);
        }
        return this.list(repository, fileSet, recursive, scmVersion);
    }
    
    public ListScmResult list(final ScmRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion scmVersion) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.RECURSIVE, Boolean.toString(recursive));
        if (scmVersion != null) {
            parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        }
        return this.list(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("list");
    }
    
    public MkdirScmResult mkdir(final ScmRepository repository, final ScmFileSet fileSet, String message, final boolean createInLocal) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        if (message == null) {
            message = "";
            if (!createInLocal) {
                this.getLogger().warn("Commit message is empty!");
            }
        }
        parameters.setString(CommandParameter.MESSAGE, message);
        parameters.setString(CommandParameter.SCM_MKDIR_CREATE_IN_LOCAL, Boolean.toString(createInLocal));
        return this.mkdir(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected MkdirScmResult mkdir(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("mkdir");
    }
    
    private void login(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        final LoginScmResult result = this.login(repository.getProviderRepository(), fileSet, new CommandParameters());
        if (!result.isSuccess()) {
            throw new ScmException("Can't login.\n" + result.getCommandOutput());
        }
    }
    
    protected LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return new LoginScmResult(null, null, null, true);
    }
    
    public RemoveScmResult remove(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.MESSAGE, (message == null) ? "" : message);
        return this.remove(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("remove");
    }
    
    public StatusScmResult status(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        return this.status(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("status");
    }
    
    public TagScmResult tag(final ScmRepository repository, final ScmFileSet fileSet, final String tagName) throws ScmException {
        return this.tag(repository, fileSet, tagName, new ScmTagParameters());
    }
    
    public TagScmResult tag(final ScmRepository repository, final ScmFileSet fileSet, final String tagName, final String message) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.TAG_NAME, tagName);
        if (StringUtils.isNotEmpty(message)) {
            parameters.setString(CommandParameter.MESSAGE, message);
        }
        final ScmTagParameters scmTagParameters = new ScmTagParameters(message);
        parameters.setScmTagParameters(CommandParameter.SCM_TAG_PARAMETERS, scmTagParameters);
        return this.tag(repository.getProviderRepository(), fileSet, parameters);
    }
    
    public TagScmResult tag(final ScmRepository repository, final ScmFileSet fileSet, final String tagName, final ScmTagParameters scmTagParameters) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.TAG_NAME, tagName);
        parameters.setScmTagParameters(CommandParameter.SCM_TAG_PARAMETERS, scmTagParameters);
        return this.tag(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("tag");
    }
    
    public UnEditScmResult unedit(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        return this.unedit(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected UnEditScmResult unedit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (this.getLogger().isWarnEnabled()) {
            this.getLogger().warn("Provider " + this.getScmType() + " does not support unedit operation.");
        }
        return new UnEditScmResult("", null, null, true);
    }
    
    @Deprecated
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag) throws ScmException {
        return this.update(repository, fileSet, tag, true);
    }
    
    @Deprecated
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final boolean runChangelog) throws ScmException {
        return this.update(repository, fileSet, tag, "", runChangelog);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.update(repository, fileSet, (ScmVersion)null, true);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        return this.update(repository, fileSet, scmVersion, true);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final boolean runChangelog) throws ScmException {
        return this.update(repository, fileSet, (ScmVersion)null, "", runChangelog);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final boolean runChangelog) throws ScmException {
        return this.update(repository, fileSet, scmVersion, "", runChangelog);
    }
    
    @Deprecated
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final String datePattern) throws ScmException {
        return this.update(repository, fileSet, tag, datePattern, true);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final String datePattern) throws ScmException {
        return this.update(repository, fileSet, scmVersion, datePattern, true);
    }
    
    @Deprecated
    private UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final String datePattern, final boolean runChangelog) throws ScmException {
        ScmBranch scmBranch = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmBranch = new ScmBranch(tag);
        }
        return this.update(repository, fileSet, scmBranch, datePattern, runChangelog);
    }
    
    private UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final String datePattern, final boolean runChangelog) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        parameters.setString(CommandParameter.CHANGELOG_DATE_PATTERN, datePattern);
        parameters.setString(CommandParameter.RUN_CHANGELOG_WITH_UPDATE, String.valueOf(runChangelog));
        return this.update(repository.getProviderRepository(), fileSet, parameters);
    }
    
    @Deprecated
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final Date lastUpdate) throws ScmException {
        return this.update(repository, fileSet, tag, lastUpdate, null);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final Date lastUpdate) throws ScmException {
        return this.update(repository, fileSet, scmVersion, lastUpdate, null);
    }
    
    @Deprecated
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String tag, final Date lastUpdate, final String datePattern) throws ScmException {
        ScmBranch scmBranch = null;
        if (StringUtils.isNotEmpty(tag)) {
            scmBranch = new ScmBranch(tag);
        }
        return this.update(repository, fileSet, scmBranch, lastUpdate, datePattern);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final Date lastUpdate, final String datePattern) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setScmVersion(CommandParameter.SCM_VERSION, scmVersion);
        if (lastUpdate != null) {
            parameters.setDate(CommandParameter.START_DATE, lastUpdate);
        }
        parameters.setString(CommandParameter.CHANGELOG_DATE_PATTERN, datePattern);
        parameters.setString(CommandParameter.RUN_CHANGELOG_WITH_UPDATE, "true");
        return this.update(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("update");
    }
    
    public BlameScmResult blame(final ScmRepository repository, final ScmFileSet fileSet, final String filename) throws ScmException {
        this.login(repository, fileSet);
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.FILE, filename);
        return this.blame(repository.getProviderRepository(), fileSet, parameters);
    }
    
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        throw new NoSuchCommandScmException("blame");
    }
    
    public BlameScmResult blame(final BlameScmRequest blameScmRequest) throws ScmException {
        return this.blame(blameScmRequest.getScmRepository().getProviderRepository(), blameScmRequest.getScmFileSet(), blameScmRequest.getCommandParameters());
    }
    
    public InfoScmResult info(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return null;
    }
    
    public RemoteInfoScmResult remoteInfo(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return null;
    }
    
    public void addListener(final ScmLogger logger) {
        this.logDispatcher.addListener(logger);
    }
    
    public ScmLogger getLogger() {
        return this.logDispatcher;
    }
    
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        throw new UnknownRepositoryStructure();
    }
}
